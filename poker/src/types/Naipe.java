package types;

public enum Naipe {
	OUROS("D"),
	COPAS("H"),
	ESPADAS("S"),
	PAUS("C");

	String codigo;

	Naipe(String codigo){
		this.codigo = codigo;
	}
}
