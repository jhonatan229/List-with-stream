package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);

		File list = new File("C:\\projeto-java\\play-with-stream\\listStrem\\ListProduct.txt");
		List<Product> product = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(list))){
			String line = br.readLine();
			
			while(line != null) {
				String[] vect = line.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				product.add(new Product(name, price));
				line = br.readLine();	
			}
		}
		catch(IOException e ) {
			System.out.println("Error: " + e.getMessage());
		}
		
		double med = product.stream().mapToDouble(x -> x.getPrice()).average().getAsDouble();
		List<String> filtes = product.stream().filter(x -> x.getPrice() < med).sorted(Comparator.comparing(Product::getNameProduct).reversed()).map(x -> x.getNameProduct())
				.collect(Collectors.toList());
		System.out.printf("Average Price: %.2f%n%n", med);
		System.out.println("Products down of average:");
	    filtes.forEach(System.out::println);
		
		
	}
}
