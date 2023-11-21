package main;

import java.io.File;
import java.util.ArrayList;

import clases.Paciente;
import hilos.HiloCreador;
import hilos.HiloLectura;
import shared.ListaPacientes;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaPacientes listaPacientes = new ListaPacientes();
		File archivo = new File("pacientes.txt"); // Introduce aquí la ruta del archivo a leer

		
		HiloLectura lectura = new HiloLectura(listaPacientes,archivo);
		HiloCreador creador = new HiloCreador(listaPacientes);
		lectura.start();
		creador.start();
		
		
		try {
			lectura.join();
			creador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Hola");
	}
}