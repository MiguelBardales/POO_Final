package transporte;

import java.util.ArrayList;
import java.util.List;

public class Registro {
	private List<Pasajero> registroPasajeros;
	private List<Bus> registroBuses;
	
	public Registro() {
		registroPasajeros = new ArrayList<Pasajero>();
		registroBuses = new ArrayList<Bus>();
	}
	
	//Registrar pasajero a partir de una instancia existente:
	public void registrarPasajero(Pasajero pasajero) throws Exception {
		if (pasajero.getEdad() < 18) {
			throw new Exception("No se puede registrar a un pasajero menor de edad.");
		}
		if (buscarPasajero(pasajero.getDNI()) != null) {
			throw new Exception("El pasajero ya se encuentra registrado.");
		}
		registroPasajeros.add(pasajero);
	}
	
	//Registrar pasajero a partir de una nueva instancia:
	public void registrarPasajero(String tipo, int dni, int telefono, String estadoCivil, int edad, String correoElectronico) throws Exception {
		Pasajero pasajero = Factoria.obtenerVecino(tipo, dni, telefono, estadoCivil, edad, correoElectronico);
		registrarPasajero(pasajero);
	}
	
	public Pasajero buscarPasajero(int dni) {
		for (Pasajero pasajero : registroPasajeros) {
			if (pasajero.getDNI() == dni) {
				return pasajero;
			}
		}
		return null; //Retorna null si el pasajero no está registrado
	}
	
	//Registrar bus a partir de una instancia existente:
	public void registrarBus(Bus bus) throws Exception {
		if (buscarBus(bus.getNumero()) != null) {
			throw new Exception("El bus ya se encuentra registrado.");
		}
		registroBuses.add(bus);
	}
	
	//Registrar bus a partir de una nueva instancia:
	public void registrarBus(int numero, String fecha, int horas, int minutos) throws Exception {
		Bus bus = new Bus(numero, fecha, horas, minutos);
		if (buscarBus(bus.getNumero()) != null) {
			throw new Exception("El bus ya se encuentra registrado.");
		}
		registroBuses.add(bus);
	}
	
	public Bus buscarBus(int numero) {
		for (Bus bus : registroBuses) {
			if (bus.getNumero() == numero) {
				return bus;
			}
		}
		return null; //Retorna null si el bus no está registrado
	}
	
	private Bus encontrarBus(String fecha) throws Exception {
		for (Bus bus : registroBuses) {
			if (bus.getFecha().equals(fecha) && bus.numeroAsientosDisponibles() > 0) {
				return bus;
			}
		}
		throw new Exception("No hay bus disponible para la fecha indicada.");
	}
	
	private int encontrarAsiento(Bus bus) throws Exception {
		//Devuelve un entero del 1 al 40.
		for (int i = 1; i <= Bus.CAPACIDAD; i++) {
			if (bus.getPasajero(i) == null) {
				return i;
			}
		}
		throw new Exception("Bus lleno.");
	}
	
	private void asignarAsiento(Pasajero pasajero, Bus bus, int asiento) throws Exception {
		if (buscarPasajero(pasajero.getDNI()) == null) {
			throw new Exception("El pasajero no está registrado.");
		}
		if (buscarBus(bus.getNumero()) == null) {
			throw new Exception("El bus no está registrado.");
		}
		bus.agregarPasajero(pasajero, asiento);
	}
	
	public String reservarAsiento(int dni, String fecha) throws Exception {
		//Reserva y devuelve un boleto si hay bus disponible
		Pasajero pasajero = buscarPasajero(dni);
		Bus busDisponible = encontrarBus(fecha);
		int asientoDisponible = encontrarAsiento(busDisponible);
		asignarAsiento(pasajero, busDisponible, asientoDisponible);
		return pasajero.boleto(busDisponible, asientoDisponible);
	}
}
