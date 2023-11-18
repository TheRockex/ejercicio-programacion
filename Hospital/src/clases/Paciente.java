package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente {
	private int id;
	private String nombre;
	private String[] apellidos;
	private LocalDate nacimento;
	private String localidad;
	private ArrayList<CitaMedica> listaCitas;

	public Paciente(int id, String nombre, String[] apellidos, LocalDate nacimento, String localidad) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimento = nacimento;
		this.localidad = localidad;
		listaCitas = new ArrayList<CitaMedica>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String[] getApellidos() {
		return apellidos;
	}

	public void setApellidos(String[] apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getNacimento() {
		return nacimento;
	}

	public void setNaicimento(LocalDate nacimento) {
		this.nacimento = nacimento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<CitaMedica> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(ArrayList<CitaMedica> listaCitas) {
		this.listaCitas = listaCitas;
	}
}
