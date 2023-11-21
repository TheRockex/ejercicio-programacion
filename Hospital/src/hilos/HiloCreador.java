package hilos;

import shared.ListaPacientes;

public class HiloCreador extends Thread{
	private ListaPacientes listaPacientes;

	public HiloCreador(ListaPacientes listaPacientes) {
		this.listaPacientes = listaPacientes;
	}
		
		public void run() {
			for (int i = 0; i < 12; i++) {
				listaPacientes.Crear();
			}
		}	
}
		
		
		
		
		
		
		
		
		