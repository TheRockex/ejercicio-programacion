package hilos;

import shared.ListaPacientes;

public class HiloCreador extends Thread{
	private ListaPacientes listaPacientes;

	public HiloCreador(ListaPacientes listaPacientes) {
		this.listaPacientes = listaPacientes;
	}
		
		public void run() {
			while(!listaPacientes.isFinished()||!listaPacientes.isEmpty()) {
				listaPacientes.Crear();
			}
		}	
}
		
		
		
		
		
		
		
		
		