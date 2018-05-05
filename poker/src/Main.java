import types.Carta;
import types.Mao;
import types.Naipe;
import types.Valor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.COPAS, Valor.CINCO), new Carta(Naipe.PAUS, Valor.CINCO),
                new Carta(Naipe.ESPADAS, Valor.SEIS), new Carta(Naipe.ESPADAS, Valor.SETE),
                new Carta(Naipe.OUROS, Valor.REI));
        Mao maoUm = new Mao(cartasJogadorUm);

        List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.PAUS, Valor.DOIS), new Carta(Naipe.PAUS, Valor.TRES),
                new Carta(Naipe.ESPADAS, Valor.SEIS), new Carta(Naipe.ESPADAS, Valor.SETE),
                new Carta(Naipe.OUROS, Valor.REI));
        Mao maoDois = new Mao(cartasJogadorDois);
    }
}
