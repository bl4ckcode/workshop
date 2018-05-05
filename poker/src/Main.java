import impl.Comparador;
import types.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Carta> cartasJogadorUm = Arrays.asList(new Carta(Naipe.COPAS, Valor.CINCO), new Carta(Naipe.PAUS, Valor.CINCO),
                new Carta(Naipe.ESPADAS, Valor.SEIS), new Carta(Naipe.ESPADAS, Valor.SETE), new Carta(Naipe.OUROS, Valor.REI));
        Mao maoUm = new Mao(cartasJogadorUm);

        List<Carta> cartasJogadorDois = Arrays.asList(new Carta(Naipe.PAUS, Valor.DOIS), new Carta(Naipe.PAUS, Valor.TRES),
                new Carta(Naipe.ESPADAS, Valor.OITO), new Carta(Naipe.OUROS, Valor.OITO), new Carta(Naipe.OUROS, Valor.VALETE));
        Mao maoDois = new Mao(cartasJogadorDois);

        Comparador comparador = new Comparador();

        TipoMao tipoMaoJogadorUm = comparador.verificarTipoDeMao(maoUm);
        TipoMao tipoMaoJogadorDois = comparador.verificarTipoDeMao(maoDois);
    }
}
