package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import clases.Paciente;
import hilos.HiloCreador;
import hilos.HiloLectura;
import shared.ListaPacientes;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ListaPacientes listaPacientes = new ListaPacientes();
		File archivo = new File("pacientes.txt"); // Introduce aquí la ruta del archivo a leer

		HiloLectura lectura = new HiloLectura(listaPacientes, archivo);
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

		System.out.println("Introduzca ID del paciente: ");
		String paciente = sc.nextLine();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder db;
		Document document;
		File file;
		XPath xPath;
		NodeList nodeList;
		int id = 1;
		ArrayList<Paciente> personas = new ArrayList<Paciente>();
		try {
			db = dbf.newDocumentBuilder();
			file = new File("Hospital/Pacientes/"+path+"/Citas.xml");
			document = db.parse(file);
			document.normalizeDocument();
			xPath = XPathFactory.newInstance().newXPath();
			path = "//cita";
			nodeList = (NodeList) xPath.compile(path).evaluate(document, XPathConstants.NODESET);
			System.out.println("Citas: ");
			for(int i = 0; i < nodeList.getLength();i++) {
				Element elemento = (Element) nodeList.item(i);
				System.out.print("> "+elemento.getElementsByTagName("centro").item(0).getTextContent()); 
				System.out.print(". "+elemento.getElementsByTagName("especialidad").item(0).getTextContent());
				System.out.print(". "+elemento.getElementsByTagName("doctor").item(0).getTextContent());
				System.out.print(". "+elemento.getElementsByTagName("fecha").item(0).getTextContent());
				System.out.println(". "+elemento.getElementsByTagName("hora").item(0).getTextContent());
			}
			
		} catch (Exception o) {
			System.err.println("exception");
		String path;

		try {
			db = dbf.newDocumentBuilder();
			file = new File("Hospital/Pacientes/" + paciente + "/Datos personales.xml");
			document = db.parse(file);
			document.normalizeDocument();
			xPath = XPathFactory.newInstance().newXPath();
			path = "//pacientes";
			nodeList = (NodeList) xPath.compile(path).evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element elemento = (Element) nodeList.item(i);
				System.out.println("Nombre: " + elemento.getElementsByTagName("nombre").item(0).getTextContent());
				System.out.println("Apellidos: " + elemento.getElementsByTagName("apellido").item(0).getTextContent()
						+ " " + elemento.getElementsByTagName("apellido").item(1).getTextContent());
				System.out
						.println("Nacimiento: " + elemento.getElementsByTagName("nacimiento").item(0).getTextContent());
				System.out.println("Localidad: " + elemento.getElementsByTagName("localidad").item(0).getTextContent());
			}
		} catch (Exception e) {
			System.out.println("Excepcion");
		}
	}
}
}