package shared;

import java.util.ArrayList;

import clases.Paciente;

public class ListaPacientes {
	ArrayList<Paciente> listaPacientes;
	
	public ListaPacientes() {
		listaPacientes = new ArrayList<Paciente>();
	}
	
	public void add (Paciente p) {
		listaPacientes.add(p);
	}
<<<<<<< Updated upstream
=======

	public ArrayList<Paciente> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(ArrayList<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public synchronized void leer(File archivo) {

		while (listaPacientes.size() == CAPACIDAD) {

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		try {
			FileReader fr;
			BufferedReader br;
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			Paciente p = null;
			int i = 0;

			while ((linea = br.readLine()) != null) {
				i++;
				String[] s = linea.split(";");

				if (s[0].charAt(0) == '#') {

					if (p != null) {
						try {
							String[] d = s[3].split("/"); // Fecha de la cita
							String[] h = s[4].split(":"); // Hora de la cita

							p.getListaCitas()
									.add(new CitaMedica(s[0].substring(1), s[1], s[2],
											LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]),
													Integer.parseInt(d[0])),
											LocalTime.of(Integer.parseInt(h[0]), Integer.parseInt(h[1]))));
						} catch (Exception e) {
							System.err.println("ERROR. No se pudo cargar la cita de la linea " + i + ".");
						}
					}

				} else {

					if (p != null) {
						listaPacientes.add(p);
						notifyAll();
					}

					if (s[0].length() <= 9) {
						try {
							String[] d = s[4].split("/"); // Fecha de nacimiento

							p = new Paciente(
									Integer.parseInt(s[0]), s[1], new String[] { s[2], s[3] }, LocalDate
											.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0])),
									s[5]);
						} catch (Exception e) {
							System.err.println("ERROR. No se pudo crear el paciente de la linea " + i + ".");
							p = null;
						}
					} else {
						System.err.println(
								"Error al crear el paciente. id supera los digitos permitidos (9) en linea " + i + ".");
						p = null;
					}

				}
			}

			br.close();
			fr.close();

			if (p != null) {
				listaPacientes.add(p);
			}

		} catch (FileNotFoundException e) {
			System.err.println("ERROR. EL ARCHIVO DE LA RUTA INDICADA NO EXISTE.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	public synchronized void Crear() {

		while (listaPacientes.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		FileWriter fw;
		PrintWriter pw;

		for (int i = 0; i < listaPacientes.size(); i++) {
			File carpetaPacientesID = new File("Hospital/Pacientes/" + listaPacientes.get(i).getId());
			File file1 = new File("Hospital/Pacientes/" + listaPacientes.get(i).getId() + "/Datos personales.xml");
			File file2 = new File("Hospital/Pacientes/" + listaPacientes.get(i).getId() + "/Citas.xml");

			try {
				carpetaPacientesID.mkdir();
				file1.createNewFile();
				fw = new FileWriter(file1);
				pw = new PrintWriter(fw);
				pw.println("<paciente>");
				pw.println("   <paciente id=" + listaPacientes.get(i).getId() + ">");
				pw.println("      <nombre>" + listaPacientes.get(i).getNombre() + "</nombre>");
				pw.println("      <apellidos>");
				pw.println("         <apellido>" +listaPacientes.get(i).getApellidos()[0] + "</apellido>");
				pw.println("         <apellido>" +listaPacientes.get(i).getApellidos()[1] + "</apellido>");
				pw.println("      </apellidos>");
				pw.println("      <nacimiento>" + listaPacientes.get(i).getNacimento() + "</nacimiento>");
				pw.println("      <localidad>" + listaPacientes.get(i).getLocalidad() + "</localidad>");
				pw.println("</paciente>");
				pw.flush();
				pw.close();

				file2.createNewFile();
				fw = new FileWriter(file2);
				pw = new PrintWriter(fw);
				System.out.println("-----------------------");

				for (int x = 0; x < listaPacientes.get(i).getListaCitas().size(); x++) {
					
					System.out.println("   <centro>" + listaPacientes.get(i).getListaCitas().get(i).getCentro() + "</centro>");
					System.out.println("   <especialidad>" + listaPacientes.get(i).getListaCitas().get(i).getEspecialidad() + "</especialidad>\"");
					System.out.println("   <doctor>" + listaPacientes.get(i).getListaCitas().get(i).getDoctor() + "</doctor>");
					System.out.println("   <fecha>" + listaPacientes.get(i).getListaCitas().get(i).getFecha() + "</fecha>");
					System.out.println("   <hora>" + listaPacientes.get(i).getListaCitas().get(i).getHora() + "</hora>");
					
					pw.println("<cita>");
					pw.println("   <centro>" + listaPacientes.get(i).getListaCitas().get(i).getCentro() + "</centro>");
					pw.println("   <especialidad>" + listaPacientes.get(i).getListaCitas().get(i).getEspecialidad() + "</especialidad>");
					pw.println("   <doctor>" + listaPacientes.get(i).getListaCitas().get(i).getDoctor() + "</doctor>");
					pw.println("   <fecha>" + listaPacientes.get(i).getListaCitas().get(i).getFecha() + "</fecha>");
					pw.println("   <hora>" + listaPacientes.get(i).getListaCitas().get(i).getHora() + "</hora>");
					pw.println("</cita>");
				}
				System.out.println("-----------------------");
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

>>>>>>> Stashed changes
}
