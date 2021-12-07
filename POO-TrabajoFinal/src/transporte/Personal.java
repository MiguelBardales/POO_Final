package transporte;

public class Personal extends Pasajero {
	public Personal(int dni, int telefono, String estadoCivil, int edad, String correoElectronico) throws Exception {
		super(dni, telefono, estadoCivil, edad, correoElectronico);
		setEdad(edad);
	}
	
	@Override
	protected void setEdad(int edad) throws Exception {
		if (edad < 18) {
			throw new Exception("El personal del Club de Ecología no es un adulto mayor.");
		}
		super.setEdad(edad);
	}
	
	@Override
	public String obtenerPromocion() {
		if (getEstadoCivil().equals("Casado")) {
			return "Chocolate Sublime";
		}
		else {
			return ""; //Ninguna promoción
		}
	}
	
	@Override
	public String datos() {
		return super.datos() + "\nMiembro del Club de Ecología de San Borja";
	}
}
