package clases;

public class CitaMedica {
	private String centro;
	private String especialidad;
	private String doctor;
	private String fecha;
	private String hora;
	
	public CitaMedica(String centro, String especialidad, String doctor, String fecha, String hora) {
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}
