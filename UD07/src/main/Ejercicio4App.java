package main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JOptionPane;

public class Ejercicio4App {

	public static void main(String[] args) {
		Hashtable<String, ArrayList<Double>> stock = new Hashtable<String, ArrayList<Double>>();
		ArrayList<ArrayList<String>> carrito = new ArrayList<ArrayList<String>>();
		final double IVA=0.21;
		
		String peticion = JOptionPane.showInputDialog("¿Qué deseas hacer? (AÑADIR, CONSULTAR, STOCK, COMPRAR, CARRITO (EXIT para terminar))");
		while (!peticion.equals("EXIT")) {
			if(peticion.equals("AÑADIR")) {
				añadirArticulo(stock);
			}
			else if(peticion.equals("CONSULTAR")) {
				consultarArticulo(stock);
			}
			else if(peticion.equals("STOCK")) {
				listarStock(stock);
			}
			else if(peticion.equals("COMPRAR")) {
				comprarArticulos(stock, carrito);
			}
			else if(peticion.equals("CARRITO")) {
				mostrarInfoCarrito(carrito, IVA);
			}
			else {
				JOptionPane.showMessageDialog(null, "Peticiones disponibles: AÑADIR, CONSULTAR, STOCK, COMPRAR, CARRITO, EXIT");
			}
			peticion = JOptionPane.showInputDialog("¿Qué deseas hacer? (AÑADIR, CONSULTAR, STOCK, COMPRAR, CARRITO (EXIT para terminar))");
			
		}

	}

	public static void añadirArticulo(Hashtable<String, ArrayList<Double>> stock) {
		if(stock.size()>=10) {
			JOptionPane.showMessageDialog(null,"Límite de 10 artículos alcanzado, no se pueden añadir más");
		}
		else {
			String articulo = JOptionPane.showInputDialog("Introduce el nombre del artículo deseado");
			if(stock.containsKey(articulo)) {
				String cantidad_string = JOptionPane.showInputDialog("Introduce cuántas existencias quieres reponer de este producto existente (mayor que 0)");
				while(Double.parseDouble(cantidad_string) <= 0) {
					cantidad_string = JOptionPane.showInputDialog("Introduce cuántas existencias quieres reponer de este producto existente (mayor que 0)");
				}
				stock.get(articulo).set(1, stock.get(articulo).get(1)+Double.parseDouble(cantidad_string));
			}
			else {
				String precio_string = JOptionPane.showInputDialog("Introduce el precio del artículo (mayor que 0)");
				while(Double.parseDouble(precio_string) <= 0) {
					precio_string = JOptionPane.showInputDialog("Introduce el precio del artículo (mayor que 0)");
				}
				String cantidad_string = JOptionPane.showInputDialog("Introduce la cantidad de existencias del artículo (mayor que 0)");
				while(Double.parseDouble(cantidad_string) <= 0) {
					cantidad_string = JOptionPane.showInputDialog("Introduce la cantidad de existencias del artículo (mayor que 0)");
				}
				ArrayList<Double> datos_articulo = new ArrayList<Double>();
				datos_articulo.add(Double.parseDouble(precio_string));
				datos_articulo.add(Double.parseDouble(cantidad_string));
				stock.put(articulo, datos_articulo);
			}
		}
	}
	
	public static void consultarArticulo(Hashtable<String, ArrayList<Double>> stock) {
		String articulo = JOptionPane.showInputDialog("Introduce el nombre del artículo a consultar");
		if(stock.containsKey(articulo)) {
			JOptionPane.showMessageDialog(null,"El artículo \""+articulo+"\" vale "+stock.get(articulo).get(0)+" y hay "+stock.get(articulo).get(1)+" existencias");

		}
		else {
			JOptionPane.showMessageDialog(null,"El artículo \""+articulo+"\" no se encuentra en la base de datos");
		}

		
	}
	
	public static void listarStock(Hashtable<String, ArrayList<Double>> stock) {
		System.out.println("--------------------------------------");
		for(Map.Entry<String, ArrayList<Double>> articulo: stock.entrySet()) {
			System.out.println("El artículo \""+articulo.getKey()+"\" vale "+articulo.getValue().get(0)+" y hay "+articulo.getValue().get(1)+" existencias");
		}
	}

	public static void comprarArticulos(Hashtable<String, ArrayList<Double>> stock, ArrayList<ArrayList<String>> carrito) {

		String articulo = JOptionPane.showInputDialog("Introduce el nombre del articulo que quieres comprar (STOP para cancelar)");
		while(!articulo.equals("STOP")) {
			ArrayList<String> info_articulo_carrito = new ArrayList<String>();
			if(stock.containsKey(articulo)){
				double cantidad_maxima = stock.get(articulo).get(1);
				String cantidad_string = JOptionPane.showInputDialog("Introduce la cantidad comprada de este artículo (mayor que 0 y como máximo "+cantidad_maxima+" (0 o negativo para cancelar))");
				if(Integer.parseInt(cantidad_string) > 0) {
					while(Integer.parseInt(cantidad_string) <= 0 || Integer.parseInt(cantidad_string) > cantidad_maxima) {
						cantidad_string = JOptionPane.showInputDialog("Introduce la cantidad comprada de este artículo (mayor que 0 y como máximo "+cantidad_maxima+"(0 o negativo para cancelar))");
					}
					info_articulo_carrito.add(articulo);
					info_articulo_carrito.add(String.valueOf(stock.get(articulo).get(0)));
					info_articulo_carrito.add(cantidad_string);
					
					carrito.add(info_articulo_carrito);
					stock.get(articulo).set(1, cantidad_maxima-Integer.parseInt(cantidad_string));
				}
				else {
					JOptionPane.showMessageDialog(null,"Compra cancelada");
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null,"El artículo \""+articulo+"\" no existe");
			}
			articulo = JOptionPane.showInputDialog("Introduce el nombre del articulo que quieres comprar (STOP para cancelar)");
		}

	}

	public static void mostrarInfoCarrito(ArrayList<ArrayList<String>> carrito, double IVA) {
		double precio_total_bruto = 0;
		double precio_total_IVA = 0;
		int articulos_comprados = 0;
		System.out.println("--------------------------------------");
		for(int i = 0; i< carrito.size(); i++) {
			System.out.println("Artículo "+(i+1)+": ");
			System.out.println("	·Nombre: "+ carrito.get(i).get(0));
			System.out.println("	·Precio: "+ carrito.get(i).get(1));
			System.out.println("	·Cantidad: "+ carrito.get(i).get(2));
			
			double precio_articulo = Double.parseDouble(carrito.get(i).get(1));
			int cantidad_articulo = Integer.parseInt(carrito.get(i).get(2));
			articulos_comprados = articulos_comprados + cantidad_articulo;
			precio_total_bruto = precio_total_bruto+ precio_articulo*cantidad_articulo;
		}
		System.out.println("IVA aplicado a los productos: "+IVA*100+"%");
		precio_total_IVA = precio_total_bruto+(precio_total_bruto*IVA);
		System.out.println("Precio total bruto: "+precio_total_bruto);
		System.out.println("Precio total con IVA: "+precio_total_IVA);
		System.out.println("Número de artículos comprados: "+articulos_comprados);
		String pagar = JOptionPane.showInputDialog("¿Quieres pagar? (SI/NO)");
		while(!pagar.equals("NO")) {
			if(pagar.equals("SI")) {
				calcularPago(carrito, precio_total_IVA);
				pagar = "NO";
			}
			else {
				pagar = JOptionPane.showInputDialog("¿Quieres pagar? (SI/NO)");
			}
		}
	}
	
	public static void calcularPago(ArrayList<ArrayList<String>> carrito, double precio_total_IVA) {
		double pago = 0;
		do {
			String pago_string = JOptionPane.showInputDialog("¿Cuánto vas a pagar? (tanto o más que el precio con IVA): "+precio_total_IVA);
			pago = Double.parseDouble(pago_string);
		}while(pago < precio_total_IVA);
		System.out.println("Cantidad pagada: "+pago);
		System.out.println("Cantidad devuelta: "+(pago-precio_total_IVA));
		carrito.clear();
	}

}
