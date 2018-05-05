package types;

public class Carta {
	private Naipe naipe;
	private Valor valor;

	public Carta(Naipe naipe, Valor valor) {
		this.naipe = naipe;
		this.valor = valor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Carta carta = (Carta) o;

		return naipe == carta.naipe && valor == carta.valor;
	}

	@Override
	public int hashCode() {
		int result = naipe != null ? naipe.hashCode() : 0;
		result = 31 * result + (valor != null ? valor.hashCode() : 0);
		return result;
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public void setNaipe(Naipe naipe) {
		this.naipe = naipe;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	public int compareTo(Carta outra) {
		int comparacaoValor = this.valor.compareTo(outra.valor);
		if (comparacaoValor != 0) {
			return comparacaoValor;
		}

		return this.naipe.compareTo(outra.naipe);
	}
}
