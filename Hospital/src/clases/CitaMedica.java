package clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaMedica {
	private String centro;
	private String especialidad;
	private String doctor;
	private LocalDate fecha;
	private LocalTime hora;
	
	public CitaMedica(String centro, String especialidad, String doctor, LocalDate fecha, LocalTime hora) {
		this.centro = centro;
		this.especialidad = especialidad;
		this.doctor = doctor;
		this.fecha = fecha;
		this.hora = hora;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
