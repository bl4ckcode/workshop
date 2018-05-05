package impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import types.Carta;
import types.Mao;
import types.TipoMao;
import types.Valor;

public class Comparador {

	public TipoMao verificarTipoDeMao(Mao mao) {
		boolean flush = mao.getCartas().stream()
				.map(Carta::getNaipe).distinct().count() == 1;

		List<Valor> valoresOrdenados = mao.getCartas().stream()
				.map(Carta::getValor).sorted().collect(Collectors.toList());

		int total = 0;
		for (int i = 0; i < valoresOrdenados.size() - 1; i++) {
			total += valoresOrdenados.get(i + 1).ordinal() - valoresOrdenados.get(i).ordinal();
		}
		boolean straight = total == 4;

		if (straight && flush) {
			if (valoresOrdenados.get(0) == Valor.DEZ) {
				return TipoMao.ROYAL_FLUSH;
			} else {
				return TipoMao.STRAIGHT_FLUSH;
			}
		} else if (straight) {
			return TipoMao.STRAIGHT;
		} else if (flush) {
			return TipoMao.FLUSH;
		}

		boolean quadraOuFullHouse = valoresOrdenados.stream().distinct().count() == 2;
		if (quadraOuFullHouse) {
			long valoresIguais = valoresOrdenados.stream().filter(v -> v == valoresOrdenados.get(0)).count();
			if (valoresIguais == 3 || valoresIguais == 2) {
				return TipoMao.FULL_HOUSE;
			} else {
				return TipoMao.QUADRA;
			}
		}

		Map<Valor, List<Valor>> mapaValores = valoresOrdenados.stream().collect(Collectors.groupingBy(v -> v));
		int quantidadeDupla = 0;
		int quantidadeTripla = 0;

		for(Entry<Valor, List<Valor>> valor : mapaValores.entrySet()) {
			int quantidade = valor.getValue().size();
			if(quantidade == 2) {
				quantidadeDupla++;
			} else if(quantidade == 3) {
				quantidadeTripla++;
			}
		}

		if(quantidadeDupla == 1) {
			return TipoMao.PAR;
		} else if(quantidadeDupla == 2) {
			return TipoMao.DOIS_PARES;
		} else if(quantidadeTripla == 1) {
			return TipoMao.TRINCA;
		} 

		return TipoMao.CARTA_ALTA;
	}
}
