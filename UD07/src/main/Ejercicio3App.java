package main;

import java.util.Hashtable;
import java.util.Map;

import javax.swing.JOptionPane;
public class Ejercicio3App {

	public static void main(String[] args) {
		Hashtable<String, Double> stock = new Hashtable<String, Double>();
		
		String peticion = JOptionPane.showInputDialog("¿Qué deseas hacer? (AÑADIR, CONSULTAR, LISTAR (EXIT para terminar)");
		while (!peticion.equals("EXIT")) {
			if(peticion.equals("AÑADIR")) {
				añadirArticulo(stock);
			}

			else if(peticion.equals("CONSULTAR")) {
				consultarArticulo(stock);
			}
				
			else if(peticion.equals("LISTAR")) {
				listarStock(stock);
			}
			else {
				JOptionPane.showMessageDialog(null, "Peticiones disponibles: AÑADIR, CONSULTAR, LISTAR, EXIT");
			}
			peticion = JOptionPane.showInputDialog("¿Qué deseas hacer? (AÑADIR, CONSULTAR, LISTAR (EXIT para terminar)");
			
		}
	}

	public static void añadirArticulo(Hashtable<String, Double> stock) {
		if(stock.size()>=10) {
			JOptionPane.showMessageDialog(null,"Límite de 10 artículos alcanzado, no se pueden añadir más");
		}
		else {
			String articulo = JOptionPane.showInputDialog("Introduce el nombre del artículo deseado");
			while(stock.containsKey(articulo)) {
				articulo = JOptionPane.showInputDialog("Introduce el nombre del artículo deseado, no puede ser repetido");

			}
			String precio_string = JOptionPane.showInputDialog("Introduce el precio del artículo (mayor que 0)");
			while(Double.parseDouble(precio_string) <= 0) {
				precio_string = JOptionPane.showInputDialog("Introduce el precio del artículo (mayor que 0)");
			}
			double precio = Double.parseDouble(precio_string);
			
			stock.put(articulo, precio);
		}
	}
	
	public static void consultarArticulo(Hashtable<String, Double> stock) {
		String articulo = JOptionPane.showInputDialog("Introduce el nombre del artículo a consultar");
		if(stock.containsKey(articulo)) {
			JOptionPane.showMessageDialog(null,"El artículo "+articulo+" vale "+stock.get(articulo));

		}
		else {
			JOptionPane.showMessageDialog(null,"El artículo "+articulo+" no se encuentra en la base de datos");
		}

		
	}
	
	public static void listarStock(Hashtable<String, Double> stock) {
		for(Map.Entry<String, Double> articulo: stock.entrySet()) {
			System.out.println("El artículo "+articulo.getKey()+" vale "+articulo.getValue());
		}
		System.out.println("--------------------------------------");
	}
}
