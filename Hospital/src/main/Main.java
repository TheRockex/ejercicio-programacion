package main;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import hilos.HiloCreador;
import hilos.HiloLectura;
import shared.ListaPacientes;

public class Main {

	public static void main(String[] args) {
		ListaPacientes listaPacientes = new ListaPacientes();
		File pacientes = new File("Pacientes");
		File txt = new File("pacientes.txt"); // Introduce aquí la ruta del archivo txt

		pacientes.mkdir();
		HiloLectura lectura = new HiloLectura(listaPacientes, txt);
		HiloCreador creador = new HiloCreador(listaPacientes);
		lectura.start();

		creador.start();

		try {
			lectura.join();
			creador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la ruta de la carpeta del paciente: ");
		String pathPaciente = sc.nextLine();

		File file = new File(pathPaciente);

		if (file.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder db;
			Document document;
			XPath xPath;
			String path;
			NodeList nodeList;
			try {
				
				db = dbf.newDocumentBuilder();
				document = db.parse(file+"/Datos Personales.xml");
				document.normalizeDocument();
				xPath = XPathFactory.newInstance().newXPath();
				path = "//paciente";
				nodeList = (NodeList) xPath.compile(path).evaluate(document, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element elemento = (Element) nodeList.item(i);
					System.out.println("ID: " + elemento.getAttribute("id"));
					System.out.print("Nombre: " + elemento.getElementsByTagName("nombre").item(0).getTextContent()+" ");
					System.out
							.println(elemento.getElementsByTagName("apellido").item(0).getTextContent()
									+ " " + elemento.getElementsByTagName("apellido").item(1).getTextContent());
					System.out.println(
							"Nacimiento: " + elemento.getElementsByTagName("nacimiento").item(0).getTextContent());
					System.out.println(
							"Localidad: " + elemento.getElementsByTagName("localidad").item(0).getTextContent());
				}
				file = new File(pathPaciente + "/Citas.xml");
				document = db.parse(file);
				document.normalizeDocument();
				path = "//cita";
				nodeList = (NodeList) xPath.compile(path).evaluate(document, XPathConstants.NODESET);
				System.out.println("Citas: ");
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element elemento = (Element) nodeList.item(i);
					System.out.print("> " + elemento.getElementsByTagName("centro").item(0).getTextContent());
					System.out.print(". " + elemento.getElementsByTagName("especialidad").item(0).getTextContent());
					System.out.print(". " + elemento.getElementsByTagName("doctor").item(0).getTextContent());
					System.out.print(". " + elemento.getElementsByTagName("fecha").item(0).getTextContent());
					System.out.println(". " + elemento.getElementsByTagName("hora").item(0).getTextContent());
				}
			} catch (Exception e) {
				System.err.println("Exception");

			} 
			
		} else {
			System.err.println("ERROR. La ruta indicada no existe.");
		}
	}
}