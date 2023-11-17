package Hilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import clases.CitaMedica;
import clases.Paciente;

public class HiloLectura extends Thread {
	private ArrayList<Paciente> listaPacientes;
	private File archivo;

	public HiloLectura(ArrayList<Paciente> listaPacientes, File archivo) {
		this.listaPacientes = listaPacientes;
		this.archivo = archivo;
	}

	public void run() {
		try {
			FileReader fr;
			BufferedReader br;
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			ArrayList<CitaMedica> listaCitas=new ArrayList<CitaMedica>();

			while ((linea = br.readLine()) != null) {
				String[] s = linea.split(";");
				try {
				 listaPacientes.add(new Paciente(Integer.parseInt(s[0]), s[1], new String[] { s[2], s[3] }, s[4], s[5]))  ;
					
					
				} catch (NumberFormatException e) {
					
					CitaMedica cita = new CitaMedica(s[0], s[1], s[2], s[3], s[4]);
					listaCitas.add(cita);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("ERROR. EL ARCHIVO DE LA RUTA INDICADA NO EXISTE.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
