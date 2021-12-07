package transporte;

import java.util.Scanner;

public class Main {
	static Registro registro = new Registro();
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int dni, numero;
		String fecha;
		int opcion = 0;
		while(opcion != 4) {
			boolean esperar = false;
			opcion = Menu.mostrarMenu();
			switch (opcion) {
				case 1:
					System.out.println();
					int subopcion = Menu.mostrarMenuRegistro();
					switch (subopcion) {
						case 1:
							System.out.print("Ingrese el número del nuevo bus: ");
							numero = Menu.leerEntero("Ingrese un número de bus válido: ");
							if (registro.buscarBus(numero) == null) {
								//Si el bus no está registrado
								System.out.print("Ingrese la fecha de salida del bus: ");
								fecha = scanner.nextLine();
								System.out.print("El bus sale a las horas: ");
								int horas = Menu.leerEntero("Ingrese un número entero: ");
								System.out.print("¿Con cuántos minutos? ");
								int minutos = Menu.leerEntero("Ingrese un número entero: ");
								try {
									registro.registrarBus(numero, fecha, horas, minutos);
									System.out.println("El bus numero " + numero + " ha sido registrado correctamente.");
									esperar = true;
								} catch (Exception e) {
									System.out.println("El bus no se pudo registrar. Asegúrese de colocar los datos correctamente.");
								} finally {
									esperar = true;
								}
							}
							else {
								System.out.println("El bus de numero " + numero + " ya está registrado.");
								esperar = true;
							}
							break;
						case 2:
							System.out.print("Ingrese el DNI del vecino: ");
							dni = Menu.leerDNI();
							if (registro.buscarPasajero(dni) == null) {
								//Si el vecino no está registrado
								System.out.print("Ingrese el número de teléfono del vecino: ");
								int telefono = Menu.leerEntero("Ingrese un número entero: ");
								System.out.print("Ingrese el estado civil del vecino: ");
								String estadoCivil = scanner.nextLine();
								System.out.print("Ingrese la edad del vecino: ");
								int edad = Menu.leerEntero("Ingrese un número entero: ");
								System.out.print("Ingrese el correo electrónico del vecino: ");
								String correoElectronico = scanner.nextLine();
								System.out.println("¿El vecino es adulto mayor o miembro del Club de Ecología?");
								System.out.println("1. Adulto mayor");
								System.out.println("2. Personal del Club\n");
								int opcionVecino = Menu.leerDosAlternativas();
								String tipo = (opcionVecino == 1) ? "Vecino" : "Personal";
								try {
									registro.registrarPasajero(tipo, dni, telefono, estadoCivil, edad, correoElectronico);
									System.out.println("El vecino con DNI N° " + dni + " ha sido registrado correctamente.");
									esperar = true;
								} catch (Exception e) {
									System.out.println("El vecino no se pudo registrar. Asegúrese de colocar los datos correctamente.");
								} finally {
									esperar = true;
								}
							}
							else {
								System.out.println("El vecino con DNI N° " + dni + " ya está registrado.");
								esperar = true;
							}
							break;
					}
					break;
				case 2:
					//Pendiente
					System.out.print("Ingrese el número de DNI del pasajero: ");
					dni = Menu.leerDNI();
					break;
				case 3:
					System.out.print("Ingrese el número de DNI del pasajero: ");
					dni = Menu.leerDNI();
					Menu.imprimirDatosPasajero(registro, dni);
					esperar = true;
					break;
				case 4:
					System.out.println("\nSaliendo. Presione Enter para terminar de salir.");
					try {
						System.in.read();
					} catch (Exception e) { }
					break;
			}
			if (esperar) {
				System.out.println("Presione Enter para volver al menú principal.\n");
				try {
					System.in.read();
				} catch (Exception e) { }
			}
		}
	}
	
	static class Menu {
		static int mostrarMenu() {
			System.out.println("============");
			System.out.println("    Menu    ");
			System.out.println("============");
			System.out.println("1. Registrar");
			System.out.println("2. Reservar");
			System.out.println("3. Buscar");
			System.out.println("4. Salir");
			System.out.print("\nIngrese una opción: ");
			return leerOpcionMenu();
		}
		
		static int mostrarMenuRegistro() {
			System.out.println("=========");
			System.out.println("Registrar");
			System.out.println("=========");
			System.out.println("1. Bus");
			System.out.println("2. Vecino");
			System.out.print("\nIngrese una opción: ");
			return leerDosAlternativas();
		}
		
		static int leerEntero(String mensaje) {
			try {
				return Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.print(mensaje);
				return leerEntero(mensaje);
			}
		}
		
		static int leerOpcionMenu() {
			int opcion = leerEntero("Ingrese una opción válida: ");
			if (opcion >= 1 && opcion <= 4) {
				return opcion;
			}
			else {
				System.out.print("Ingrese una opción válida: ");
				return leerOpcionMenu();
			}
		}
		
		static int leerDosAlternativas() {
			int opcion = leerEntero("Ingrese una opción válida: ");
			if (opcion == 1 || opcion == 2) {
				return opcion;
			}
			else {
				System.out.print("Ingrese una opción válida: ");
				return leerDosAlternativas();
			}
		}
		
		static int leerDNI() {
			int dni = leerEntero("Ingrese un número de DNI válido: ");
			if (dni >= 10000000 && dni <= 99999999) {
				return dni;
			}
			else {
				System.out.print("Ingrese un número de DNI válido: ");
				return leerDNI();
			}
		}
		
		static void imprimirDatosPasajero(Registro registro, int dni) {
			Pasajero pasajero = registro.buscarPasajero(dni);
			if (pasajero == null) {
				System.out.println("No hay vecino registrado con DNI N° " + dni + ".");
			}
			else {
				System.out.println("\n====Datos====\n" + pasajero.datos() + "\n");
			}
		}
	}
}
