package types;

import java.util.List;

public class Mao {
	private List<Carta> cartas;

	public Mao(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
}
