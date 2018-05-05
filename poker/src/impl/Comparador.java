package impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import types.Carta;
import types.Mao;
import types.Naipe;
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

		TipoMao x = getTipoMaoConjunto(valoresOrdenados);
		if (x != null)
			return x;

		return TipoMao.CARTA_ALTA;
	}

	private TipoMao getTipoMaoConjunto(List<Valor> valoresOrdenados) {
		Map<Valor, List<Valor>> mapaValores = valoresOrdenados.stream().collect(Collectors.groupingBy(v -> v));
		int quantidadeDupla = 0;
		int quantidadeTripla = 0;

		for (Entry<Valor, List<Valor>> valor : mapaValores.entrySet()) {
			int quantidade = valor.getValue().size();
			if (quantidade == 2) {
				quantidadeDupla++;
			} else if (quantidade == 3) {
				quantidadeTripla++;
			}
		}

		if (quantidadeDupla == 1) {
			return TipoMao.PAR;
		} else if (quantidadeDupla == 2) {
			return TipoMao.DOIS_PARES;
		} else if (quantidadeTripla == 1) {
			return TipoMao.TRINCA;
		}

		return null;
	}

	public int compararMaos(Mao maoUm, Mao maoDois) {
		TipoMao tipoMaoUm = verificarTipoDeMao(maoUm);
		TipoMao tipoMaoDois = verificarTipoDeMao(maoDois);

		int comparacaoTipos = tipoMaoUm.compareTo(tipoMaoDois);
		if (comparacaoTipos != 0) {
			return comparacaoTipos;
		}

		switch (tipoMaoUm) {
			case ROYAL_FLUSH:
				return compararMaosRoyalFlush(maoUm, maoDois);
			case FULL_HOUSE:
				return compararFullHouse(maoUm, maoDois);
			case PAR:
				return compararPar(maoUm, maoDois);
			default:
				return compararCartaAlta(maoUm, maoDois);
		}
	}

	private int compararMaosRoyalFlush(Mao maoUm, Mao maoDois) {
		Naipe naipeFlushUm = maoUm.getCartas().stream().map(Carta::getNaipe).findFirst().orElse(null);
		Naipe naipeFlushDois = maoDois.getCartas().stream().map(Carta::getNaipe).findFirst().orElse(null);

		if (naipeFlushUm != null && naipeFlushDois != null) {
			return naipeFlushUm.compareTo(naipeFlushDois);
		}

		return 0;
	}

	private int compararFullHouse(Mao maoUm, Mao maoDois) {
		Map<Valor, List<Valor>> mapaValoresMaoUm = maoUm.getCartas().stream().map(Carta::getValor).collect(Collectors.groupingBy(v -> v));
		Map<Valor, List<Valor>> mapaValoresMaoDois = maoDois.getCartas().stream().map(Carta::getValor).collect(Collectors.groupingBy(v -> v));

		Entry<Valor, List<Valor>> triplaUm = mapaValoresMaoUm.entrySet().stream().filter(e -> e.getValue().size() == 3).findFirst().orElse(null);
		Entry<Valor, List<Valor>> triplaDois = mapaValoresMaoDois.entrySet().stream().filter(e -> e.getValue().size() == 3).findFirst().orElse(null);

		if (triplaUm == null || triplaDois == null) {
			return 0;
		}

		Valor valorUm = triplaUm.getKey();
		Valor valorDois = triplaDois.getKey();

		int comparacaoValor = valorUm.compareTo(valorDois);
		if (comparacaoValor != 0) {
			return comparacaoValor;
		}

		return compararPar(maoUm, maoDois);
	}

	private int compararPar(Mao maoUm, Mao maoDois) {
		Map<Valor, List<Valor>> mapaValoresMaoUm = maoUm.getCartas().stream().map(Carta::getValor).collect(Collectors.groupingBy(v -> v));
		Map<Valor, List<Valor>> mapaValoresMaoDois = maoDois.getCartas().stream().map(Carta::getValor).collect(Collectors.groupingBy(v -> v));

		Entry<Valor, List<Valor>> parUm = mapaValoresMaoUm.entrySet().stream().filter(e -> e.getValue().size() == 2).findFirst().orElse(null);
		Entry<Valor, List<Valor>> parDois = mapaValoresMaoDois.entrySet().stream().filter(e -> e.getValue().size() == 2).findFirst().orElse(null);

		if (parUm == null || parDois == null) {
			return 0;
		}

		Valor valorUm = parUm.getKey();
		Valor valorDois = parDois.getKey();

		int comparacaoValor = valorUm.compareTo(valorDois);
		if (comparacaoValor != 0) {
			return comparacaoValor;
		}

		return compararCartaAlta(maoUm, maoDois);
	}

	private int compararCartaAlta(Mao maoUm, Mao maoDois) {
		maoUm.getCartas().sort(Carta::compareTo);
		Collections.reverse(maoUm.getCartas());

		maoDois.getCartas().sort(Carta::compareTo);
		Collections.reverse(maoDois.getCartas());

		Carta cartaUm = maoUm.getCartas().get(0);
		Carta cartaDois = maoDois.getCartas().get(0);

		return cartaUm.compareTo(cartaDois);
	}
}
