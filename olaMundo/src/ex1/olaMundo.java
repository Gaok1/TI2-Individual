package ex1;

import java.util.Scanner;

class olaMundo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int num1, num2;
		
		//leituras
		System.out.println("digite um numero:");
		num1= scanner.nextInt();
		System.out.println("digite um numero:");
		num2= scanner.nextInt();
		
		//somar
		int soma = num1 + num2;
		//printar na tela
		System.out.println("a soma é: " + soma);
		scanner.close();
	}

}
