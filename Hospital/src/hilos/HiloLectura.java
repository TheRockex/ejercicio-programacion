package hilos;


import java.io.File;
import shared.ListaPacientes;

public class HiloLectura extends Thread {
	private ListaPacientes listaPacientes;
	private File archivo;

	public HiloLectura(ListaPacientes listaPacientes, File archivo) {
		this.listaPacientes = listaPacientes;
		this.archivo = archivo;
	}
	
	
	
	public void run() {
			listaPacientes.leer(archivo);
	}
	
}
