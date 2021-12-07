package transporte;

public class Pasajero {
	private int dni;
	private int telefono;
	private String estadoCivil;
	private int edad;
	private String correoElectronico;
	
	public Pasajero(int dni, int telefono, String estadoCivil, int edad, String correoElectronico) throws Exception {
		if (dni < 10000000 || dni > 99999999) {
			throw new Exception("DNI inválido.");
		}
		if (edad < 0) {
			throw new Exception("Edad inválida");
		}
		this.dni = dni;
		this.telefono = telefono;
		setEstadoCivil(estadoCivil);
		this.edad = edad;
		this.correoElectronico = correoElectronico;
	}
	
	protected void setEdad(int edad) throws Exception {
		this.edad = edad;
	}
	
	public int getDNI() {
		return dni;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) throws Exception {
		if (!estadoCivil.equals("Casado") && !estadoCivil.equals("Soltero")) {
			throw new Exception("Estado civil incorrecto.");
		}
		this.estadoCivil = estadoCivil;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String obtenerPromocion() {
		return ""; //Ninguna promoción
	}
	
	public String datos() {
		return "DNI: " + getDNI() + "\n" +
		"Teléfono: " + getTelefono() + "\n" +
		"Estado civil: " + getEstadoCivil() + "\n" +
		"Edad: " + getEdad() + "\n" +
		"Correo electrónico: " + getCorreoElectronico();
	}
	
	public String boleto(Bus bus, int asiento) {
		return "Boleto de reserva\n" +
		"Bus N° " + bus.getNumero() + "\n" +
		"=================\n" +
		"DNI: " + getDNI() + "\n" +
		"Edad: " + getEdad() + "\n" +
		"Fecha de salida: " + bus.getFecha() + "\n" +
		"Asiento: " + asiento +
		(obtenerPromocion().equals("") ? "" : "\n\n¡Ud. recibe una unidad de " + obtenerPromocion() + " gratis!");
		
	}
}
