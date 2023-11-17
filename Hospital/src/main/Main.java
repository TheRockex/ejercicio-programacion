package main;

	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Paciente;

	public class Main {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
			File archivo = new File("pacientes.txt"); // Introduce aquí la ruta del archivo a leer
			        File carpeta = new File("Hospital/Pacientes");
			        if (!carpeta.exists()) {
			        	carpeta.mkdir();
			                System.out.println("Carpeta creada ");
			                FileWriter fw;
			    			PrintWriter pw;
			    	
			    			for(int i = 0; i < listaPacientes.size(); i++) {
			    				File carpetaPacientesID = new File("Hospital/Pacientes/" + listaPacientes.get(i).getNombre());
			    				File file1 = new File("Hospital/Pacientes/" + listaPacientes.get(i).getNombre() +"/Datos personales.xml");
			    				File file2 = new File("Hospital/Pacientes/" + listaPacientes.get(i).getNombre() +"/Citas.xml");
			    				try {
			    					file1.createNewFile();
			    					fw = new FileWriter(file1);
			    					pw = new PrintWriter(fw);
			    					
			    					pw.println(listaPacientes.get(i).getId() + ";");
			    					pw.println(listaPacientes.get(i).getNombre() + ";");
			    					pw.println(listaPacientes.get(i).getApellidos()[0] + ";");
			    					pw.println(listaPacientes.get(i).getApellidos()[1] + ";");
			    					pw.println(listaPacientes.get(i).getNaicimento() + ";");
			    					pw.println(listaPacientes.get(i).getLocalidad() + ";");
			    					
			    					pw.flush();
			    					pw.close();
			    					
			    					file2.createNewFile();
			    					fw = new FileWriter(file2);
			    					pw = new PrintWriter(fw);
			    					
			    					for(int x = 0; x < listaPacientes.get(i).getListaCitas().size(); x++) {
			    						pw.println(listaPacientes.get(i).getListaCitas().get(i).getCentro() + ";");
			    						pw.println(listaPacientes.get(i).getListaCitas().get(i).getEspecialidad() + ";");
			    						pw.println(listaPacientes.get(i).getListaCitas().get(i).getDoctor() + ";");
			    						pw.println(listaPacientes.get(i).getListaCitas().get(i).getFecha() + ";");
			    						pw.println(listaPacientes.get(i).getListaCitas().get(i).getHora() + ";");
			    					}
			    					pw.flush();
			    					pw.close();
			    				} catch (IOException e) {
			    					e.printStackTrace();
			    				}
			    						
			    			}
			        } else {
			            // System.out.println("La carpeta ya existe");
			        }
		}
		
	}


