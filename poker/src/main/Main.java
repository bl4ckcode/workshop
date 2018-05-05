package main;

import java.util.Arrays;
import java.util.List;

import impl.Comparador;
import types.Carta;
import types.Mao;
import types.Naipe;
import types.Valor;

public class Main {
	public static void main(String[] args) {
		teste1DoisPares();
		teste2CartaAlta();
		teste3TincaFlush();
		teste4ParCartaAlta();
		teste5FullHouse();
	}

	public static void teste1DoisPares() {
		List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.COPAS, Valor.CINCO), new Carta(Naipe.PAUS, Valor.CINCO),
				new Carta(Naipe.ESPADAS, Valor.SEIS), new Carta(Naipe.ESPADAS, Valor.SETE), new Carta(Naipe.OUROS, Valor.REI));
		Mao maoUm = new Mao(cartasJogadorUm);

		List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.PAUS, Valor.DOIS), new Carta(Naipe.PAUS, Valor.TRES),
				new Carta(Naipe.ESPADAS, Valor.OITO), new Carta(Naipe.OUROS, Valor.OITO), new Carta(Naipe.OUROS, Valor.VALETE));
		Mao maoDois = new Mao(cartasJogadorDois);

		Comparador comparador = new Comparador();

		int resultadoComparacao = comparador.compararMaos(maoUm, maoDois);

		assert resultadoComparacao > 0;

		System.out.println("teste1 OK");
	}

	public static void teste2CartaAlta() {
		List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.OUROS, Valor.CINCO), new Carta(Naipe.PAUS, Valor.OITO),
				new Carta(Naipe.ESPADAS, Valor.NOVE), new Carta(Naipe.ESPADAS, Valor.VALETE), new Carta(Naipe.PAUS, Valor.AS));
		Mao maoUm = new Mao(cartasJogadorUm);

		List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.PAUS, Valor.DOIS), new Carta(Naipe.PAUS, Valor.CINCO),
				new Carta(Naipe.OUROS, Valor.SETE), new Carta(Naipe.ESPADAS, Valor.OITO), new Carta(Naipe.COPAS, Valor.DAMA));
		Mao maoDois = new Mao(cartasJogadorDois);

		Comparador comparador = new Comparador();

		int resultadoComparacao = comparador.compararMaos(maoUm, maoDois);

		assert resultadoComparacao < 0;

		System.out.println("teste2 OK");
	}

	public static void teste3TincaFlush() {
		List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.OUROS, Valor.DOIS), new Carta(Naipe.PAUS, Valor.NOVE),
				new Carta(Naipe.ESPADAS, Valor.AS), new Carta(Naipe.COPAS, Valor.AS), new Carta(Naipe.PAUS, Valor.AS));
		Mao maoUm = new Mao(cartasJogadorUm);

		List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.OUROS, Valor.TRES), new Carta(Naipe.OUROS, Valor.SEIS),
				new Carta(Naipe.OUROS, Valor.SETE), new Carta(Naipe.OUROS, Valor.VALETE), new Carta(Naipe.OUROS, Valor.DAMA));
		Mao maoDois = new Mao(cartasJogadorDois);

		Comparador comparador = new Comparador();

		int resultadoComparacao = comparador.compararMaos(maoUm, maoDois);

		assert resultadoComparacao > 0;

		System.out.println("teste3 OK");
	}

	public static void teste4ParCartaAlta() {
		List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.OUROS, Valor.QUATRO), new Carta(Naipe.ESPADAS, Valor.SEIS),
				new Carta(Naipe.COPAS, Valor.NOVE), new Carta(Naipe.COPAS, Valor.DAMA), new Carta(Naipe.PAUS, Valor.DAMA));
		Mao maoUm = new Mao(cartasJogadorUm);

		List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.OUROS, Valor.TRES), new Carta(Naipe.OUROS, Valor.SEIS),
				new Carta(Naipe.COPAS, Valor.SETE), new Carta(Naipe.ESPADAS, Valor.DAMA), new Carta(Naipe.OUROS, Valor.DAMA));
		Mao maoDois = new Mao(cartasJogadorDois);

		Comparador comparador = new Comparador();

		int resultadoComparacao = comparador.compararMaos(maoUm, maoDois);

		assert resultadoComparacao < 0;

		System.out.println("teste4 OK");
	}

	public static void teste5FullHouse() {
		List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.COPAS, Valor.DOIS), new Carta(Naipe.OUROS, Valor.DOIS),
				new Carta(Naipe.OUROS, Valor.QUATRO), new Carta(Naipe.ESPADAS, Valor.QUATRO), new Carta(Naipe.PAUS, Valor.QUATRO));
		Mao maoUm = new Mao(cartasJogadorUm);

		List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.OUROS, Valor.TRES), new Carta(Naipe.PAUS, Valor.TRES),
				new Carta(Naipe.ESPADAS, Valor.TRES), new Carta(Naipe.ESPADAS, Valor.NOVE), new Carta(Naipe.OUROS, Valor.NOVE));
		Mao maoDois = new Mao(cartasJogadorDois);

		Comparador comparador = new Comparador();

		int resultadoComparacao = comparador.compararMaos(maoUm, maoDois);

		assert resultadoComparacao < 0;

		System.out.println("teste5 OK");
	}
}
