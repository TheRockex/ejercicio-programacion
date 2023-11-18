package main;

import java.io.File;
import hilos.HiloLectura;
import shared.ListaPacientes;

public class Main {

	public static void main(String[] args) {
		ListaPacientes listaPacientes = new ListaPacientes();
		File archivo = new File("pacientes.txt"); // Introduce aquí la ruta del archivo a leer
		
		HiloLectura lectura = new HiloLectura(listaPacientes, archivo);
		lectura.start();
	}
}
