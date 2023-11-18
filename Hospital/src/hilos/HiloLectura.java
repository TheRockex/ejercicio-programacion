package hilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import clases.CitaMedica;
import clases.Paciente;
import shared.ListaPacientes;

public class HiloLectura extends Thread {
	private ListaPacientes listaPacientes;
	private File archivo;

	public HiloLectura(ListaPacientes listaPacientes, File archivo) {
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
			Paciente p = null;

			while ((linea = br.readLine()) != null) {
				String[] s = linea.split(";");

				if (s[0].charAt(0) == '#') {

					if (p != null) {
						String[] d = s[3].split("/"); //Fecha de la cita
						String[] h = s[4].split(":"); //Hora de la cita

						p.getListaCitas().add(new CitaMedica(s[0].substring(1), s[1], s[2],
								LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0])),
								LocalTime.of(Integer.parseInt(h[0]), Integer.parseInt(h[1]))));
					}

				} else {

					if (p != null) {
						listaPacientes.add(p);
					}

					String[] d = s[4].split("/"); // Fecha de nacimiento
					p = new Paciente(Integer.parseInt(s[0]), s[1], new String[] { s[2], s[3] },
							LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0])), s[5]);

				}

				/*
				 * try { String[] d = s[4].split("/"); // Date nacimiento
				 * 
				 * listaPacientes.add(new Paciente(Integer.parseInt(s[0]), s[1], new String[] {
				 * s[2], s[3] }, LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]),
				 * Integer.parseInt(d[0])), s[5]));
				 * 
				 * } catch (NumberFormatException e) { String[] d = s[3].split("/"); // Date
				 * fecha
				 * 
				 * CitaMedica cita = new CitaMedica(s[0], s[1], s[2], s[3], s[4]);
				 * listaCitas.add(cita); }
				 */
			}
			
			listaPacientes.add(p);

			System.out.println("Hola");

		} catch (FileNotFoundException e) {
			System.err.println("ERROR. EL ARCHIVO DE LA RUTA INDICADA NO EXISTE.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
