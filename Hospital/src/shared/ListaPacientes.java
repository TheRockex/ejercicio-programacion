package shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import clases.CitaMedica;
import clases.Paciente;

public class ListaPacientes {
	ArrayList<Paciente> listaPacientes;
	public static final int CAPACIDAD = 12;

	public ListaPacientes() {
		listaPacientes = new ArrayList<Paciente>();
	}

	public ListaPacientes(ArrayList<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public ArrayList<Paciente> getListaPacientes() {
		return listaPacientes;
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

		notifyAll();
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
				pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.println("<pacientes>");
				pw.println("   <paciente>");
				pw.println("      <paciente id=" + listaPacientes.get(i).getId() + ">");
				pw.println("         <nombre>" + listaPacientes.get(i).getNombre() + "</nombre>");
				pw.println("         <apellidos>");
				pw.println("            <apellido>" + listaPacientes.get(i).getApellidos()[0] + "</apellido>");
				pw.println("            <apellido>" + listaPacientes.get(i).getApellidos()[1] + "</apellido>");
				pw.println("         </apellidos>");
				pw.println("         <nacimiento>" + listaPacientes.get(i).getNacimento() + "</nacimiento>");
				pw.println("         <localidad>" + listaPacientes.get(i).getLocalidad() + "</localidad>");
				pw.println("   </paciente>");
				pw.println("</pacientes>");
				pw.flush();
				pw.close();

				file2.createNewFile();
				fw = new FileWriter(file2);
				pw = new PrintWriter(fw);

				for (int x = 0; x < listaPacientes.get(i).getListaCitas().size(); x++) {
					if(x == 0) {
						pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
						pw.println("<citas>");
					}
					pw.println("   <cita>");
					pw.println("      <centro>" + listaPacientes.get(i).getListaCitas().get(0).getCentro() + "</centro>");
					pw.println("      <especialidad>" + listaPacientes.get(i).getListaCitas().get(0).getEspecialidad()
							+ "</especialidad>");
					pw.println("      <doctor>" + listaPacientes.get(i).getListaCitas().get(0).getDoctor() + "</doctor>");
					pw.println("      <fecha>" + listaPacientes.get(i).getListaCitas().get(0).getFecha() + "</fecha>");
					pw.println("      <hora>" + listaPacientes.get(i).getListaCitas().get(0).getHora() + "</hora>");
					pw.println("   </cita>");
					if(x == listaPacientes.get(i).getListaCitas().size()) {
						pw.println("</citas>");
					}
					
				}
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
