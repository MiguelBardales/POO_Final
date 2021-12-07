package transporte;

public class Factoria {
	public static Pasajero obtenerVecino(String tipo, int dni, int telefono, String estadoCivil, int edad, String correoElectronico) throws Exception {
		switch (tipo) {
			case "Vecino":
				return new Vecino(dni, telefono, estadoCivil, edad, correoElectronico);
			case "Personal":
				return new Personal(dni, telefono, estadoCivil, edad, correoElectronico);
			default:
				return null;
		}
	}
}
