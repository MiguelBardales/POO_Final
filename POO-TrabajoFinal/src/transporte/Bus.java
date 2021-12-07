package transporte;

public class Bus {
	public static final int CAPACIDAD = 40;
	
	private int numero;
	private String fecha;
	private double hora;
	private Pasajero[] relacionPasajeros;
	
	//Hora en formato decimal, entre las 9 y 15 horas
	public Bus(int numero, String fecha, double hora) throws Exception {
		if (hora < 9 || hora > 15) {
			throw new Exception("Hora de salida fuera del rango de 9 a.m. a 3 p.m.");
		}
		this.numero = numero;
		this.fecha = fecha;
		this.hora = hora;
		this.relacionPasajeros = new Pasajero[CAPACIDAD];
	}
	
	//Hora en formato de horas y minutos, entre las 9 y 15 horas
	public Bus(int numero, String fecha, int horas, int minutos) throws Exception {
		if ((horas < 9 || horas > 15) || (horas == 15 && minutos > 0)) {
			throw new Exception("Hora de salida fuera del rango de 9 a.m. a 3 p.m.");
		}
		if (minutos < 0 || minutos > 59) {
			throw new Exception("Los minutos deben estar entre 0 y 59.");
		}
		double hora = horas + ((double)minutos)/60.0;
		this.numero = numero;
		this.fecha = fecha;
		this.hora = hora;
		this.relacionPasajeros = new Pasajero[CAPACIDAD];
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public double getHora() {
		return hora;
	}
	
	public Pasajero getPasajero(int asiento) throws Exception {
		//'asiento' es un entero entre 1 y 40.
		if (asiento < 1 || asiento > CAPACIDAD) {
			throw new Exception("Número de asiento fuera del rango permitido.");
		}
		return relacionPasajeros[asiento-1];
	}
	
	public Pasajero[] getRelacionPasajeros() {
		return relacionPasajeros;
	}
	
	public void agregarPasajero(Pasajero pasajero, int asiento) throws Exception {
		if (asiento < 1 || asiento > CAPACIDAD) {
			throw new Exception("Número de asiento fuera del rango permitido.");
		}
		//'asiento' es un entero entre 1 y 40. 'asientoReal' lo transforma a una de las entradas del
		//arreglo (entre 0 y 39).
		int asientoReal = asiento - 1;
		if (relacionPasajeros[asientoReal] != null) {
			throw new Exception("Asiento de bus ocupado.");
		}
		relacionPasajeros[asientoReal] = pasajero;
	}
	
	public void quitarPasajero(int asiento) throws Exception {
		if (asiento < 1 || asiento > CAPACIDAD) {
			throw new Exception("Número de asiento fuera del rango permitido.");
		}
		//'asiento' es un entero entre 1 y 40. 'asientoReal' lo transforma a una de las entradas del
		//arreglo (entre 0 y 39).
		int asientoReal = asiento - 1;
		relacionPasajeros[asientoReal] = null; //Si el asiento ya estaba vacío, no sucede nada.
	}
	
	public void quitarTodo() throws Exception {
		for (int i = 0; i < CAPACIDAD; i++) {
			quitarPasajero(i);
		}
	}
	
	public int numeroAsientosOcupados() {
		int resultado = 0;
		for (int i = 0; i < CAPACIDAD; i++) {
			if (relacionPasajeros[i] != null) {
				resultado++;
			}
		}
		return resultado;
	}
	
	public int numeroAsientosDisponibles() {
		return CAPACIDAD - numeroAsientosOcupados();
	}
}
