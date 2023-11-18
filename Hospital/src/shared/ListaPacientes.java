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
}
