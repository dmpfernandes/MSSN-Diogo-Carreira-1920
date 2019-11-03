package tpc2;

public class Rule 
{
	private char symbol;
	private String symbols;
	private String string;

	public Rule(char symbol, String string) 
	{
		this.symbol = symbol;
		this.string = string; 
	}


	public char getSymbol() {
		return symbol;
	}

	public String getString() {
		return string;
	}

	public String getSymbols() {
		return symbols;
	}
}



