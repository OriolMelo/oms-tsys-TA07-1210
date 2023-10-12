package main;

import javax.swing.JOptionPane;
import java.util.Hashtable;

public class Ejercicio1App {

	public static void main(String[] args) {
		
		Hashtable<String, Double> alumnos = new Hashtable<String, Double>();
		
		alumnos = pedirAlumnos(alumnos);
		
		if(alumnos.isEmpty()) {
			System.out.println("No se han introducido alumnos");
		}
		else {
			mostrarMediaAlumnos(alumnos);
		}
	}
	
	public static Hashtable<String, Double> pedirAlumnos(Hashtable<String, Double> alumnos) {
		String alumno = JOptionPane.showInputDialog("Introduce el nombre del alumno (STOP para finalizar)");
		while(!alumno.contentEquals("STOP")){
			double media = añadirNotas();
			alumnos.put(alumno, media);
			alumno= JOptionPane.showInputDialog("Introduce el nombre del alumno (STOP para finalizar)");
		}
		return alumnos;
	}
	
	public static double añadirNotas() {
		String nota_string = JOptionPane.showInputDialog("Introduce la nota deseada (-1 para finalizar)");
		double nota = Double.parseDouble(nota_string);
		int num_notas = 0;
		double suma_notas = 0;
		while(nota != -1) {
			num_notas++;
			suma_notas = suma_notas + nota;
			nota_string = JOptionPane.showInputDialog("Introduce la nota deseada (-1 para finalizar)");
			nota = Double.parseDouble(nota_string);
		}
		double media = calcularMedia(num_notas, suma_notas);
		return media;
	}
	
	public static double calcularMedia(int num_notas, double suma_notas) {
		if(num_notas == 0) {
			num_notas = 1;
		}
		return suma_notas/num_notas;
	}
	
	public static void mostrarMediaAlumnos(Hashtable<String, Double> alumnos) {

		System.out.println(alumnos);
	}

}
