package transporte;

public class Vecino extends Pasajero {
	public Vecino(int dni, int telefono, String estadoCivil, int edad, String correoElectronico) throws Exception {
		super(dni, telefono, estadoCivil, edad, correoElectronico);
		setEdad(edad);
	}
	
	@Override
	protected void setEdad(int edad) throws Exception {
		if (edad < 60) {
			throw new Exception("El vecino no es un adulto mayor.");
		}
		super.setEdad(edad);
	}
	
	@Override
	public String obtenerPromocion() {
		if (getEdad() >= 75) {
			return "Bebida";
		}
		else {
			return ""; //Ninguna promoción
		}
	}
	
	@Override
	public String datos() {
		return super.datos() + "\nVecino adulto-mayor de San Borja";
	}
}
