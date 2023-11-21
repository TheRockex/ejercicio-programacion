package shared;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Paciente;

public class ListaPacientes {
	ArrayList<Paciente> listaPacientes;
	private boolean finished;

	public ListaPacientes() {
		listaPacientes = new ArrayList<Paciente>();
		finished = false;
	}

	public ListaPacientes(ArrayList<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public ArrayList<Paciente> getListaPacientes() {
		return listaPacientes;
	}

	public synchronized void add(Paciente p) {
		listaPacientes.add(p);
		System.out.println("LEIDO");
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

		Paciente p = listaPacientes.remove(0);

		File carpetaPacientesID = new File("Hospital/Pacientes/" + String.format("%09d", p.getId()));
		File file1 = new File("Hospital/Pacientes/" + String.format("%09d", p.getId()) + "/Datos personales.xml");
		File file2 = new File("Hospital/Pacientes/" + String.format("%09d", p.getId()) + "/Citas.xml");
		try {
			carpetaPacientesID.mkdir();
			file1.createNewFile();
			fw = new FileWriter(file1);
			pw = new PrintWriter(fw);
			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			pw.println("<pacientes>");
			pw.println("      <paciente id=\"" + String.format("%09d", p.getId()) + "\">");
			pw.println("         <nombre>" + p.getNombre() + "</nombre>");
			pw.println("         <apellidos>");
			pw.println("            <apellido>" + p.getApellidos()[0] + "</apellido>");
			pw.println("            <apellido>" + p.getApellidos()[1] + "</apellido>");
			pw.println("         </apellidos>");
			pw.println("         <nacimiento>" + p.getNacimento() + "</nacimiento>");
			pw.println("         <localidad>" + p.getLocalidad() + "</localidad>");
			pw.println("      </paciente>");
			pw.println("</pacientes>");
			pw.flush();
			pw.close();

			file2.createNewFile();
			fw = new FileWriter(file2);
			pw = new PrintWriter(fw);

			for (int x = 0; x < p.getListaCitas().size(); x++) {
				if (x == 0) {
					pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					pw.println("<citas>");
				}
				pw.println("   <cita>");
				pw.println("      <centro>" + p.getListaCitas().get(x).getCentro() + "</centro>");
				pw.println("      <especialidad>" + p.getListaCitas().get(x).getEspecialidad() + "</especialidad>");
				pw.println("      <doctor>" + p.getListaCitas().get(x).getDoctor() + "</doctor>");
				pw.println("      <fecha>" + p.getListaCitas().get(x).getFecha() + "</fecha>");
				pw.println("      <hora>" + p.getListaCitas().get(x).getHora() + "</hora>");
				pw.println("   </cita>");
				if (x == p.getListaCitas().size() - 1) {
					pw.println("</citas>");
				}
			}
			pw.flush();
			pw.close();
			
			System.out.println("CREADO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Paciente get(int i) {
		return listaPacientes.get(i);
	}

	public boolean isFinished() {
		return finished;
	}

	public synchronized void setFinished(boolean finished) {
		this.finished = finished;
		notifyAll();
	}

	public boolean isEmpty() {
		return listaPacientes.size() == 0;
	}
}
