package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Ejercicio2App {

	public static void main(String[] args) {
		final double IVA = 0.21;
		
		ArrayList<ArrayList<String>> carrito = new ArrayList<ArrayList<String>>();
		pedirArticulos(carrito);
		mostrarInfoCarrito(carrito, IVA);
	}
	
	public static void pedirArticulos(ArrayList<ArrayList<String>> carrito) {

		String articulo = JOptionPane.showInputDialog("Introduce el nombre del articulo (STOP para finalizar)");
		while(!articulo.equals("STOP")) {
			ArrayList<String> info_articulos = new ArrayList<String>();
			info_articulos.add(articulo);
			String precio_string = JOptionPane.showInputDialog("Introduce el precio del artículo (mayor que 0)");
			while(Double.parseDouble(precio_string) <= 0) {
				precio_string = JOptionPane.showInputDialog("Introduce el precio del artículo (mayor que 0)");
			}
			info_articulos.add(precio_string);
			String cantidad_string = JOptionPane.showInputDialog("Introduce la cantidad comprada de este artículo (mayor que 0)");
			while(Integer.parseInt(cantidad_string) <= 0) {
				cantidad_string = JOptionPane.showInputDialog("Introduce la cantidad comprada de este artículo (mayor que 0)");
			}
			info_articulos.add(cantidad_string);
			carrito.add(info_articulos);
			articulo = JOptionPane.showInputDialog("Introduce el nombre del articulo (STOP para finalizar)");
		}

	}
	
	public static void mostrarInfoCarrito(ArrayList<ArrayList<String>> carrito, double IVA) {
		double precio_total_bruto = 0;
		double precio_total_IVA = 0;
		int articulos_comprados = 0;
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
		calcularPago(precio_total_IVA);
	}
	
	public static void calcularPago(double precio_total_IVA) {
		double pago = 0;
		do {
			String pago_string = JOptionPane.showInputDialog("¿Cuánto vas a pagar? (tanto o más que el precio con IVA): "+precio_total_IVA);
			pago = Double.parseDouble(pago_string);
		}while(pago < precio_total_IVA);
		System.out.println("Cantidad pagada: "+pago);
		System.out.println("Cantidad devuelta: "+(pago-precio_total_IVA));
	}

}
