package tpc2.ex2;

public class StocasticLSystem {
	private String sequence;
	private Rule[] ruleset;  
	private int generation;
	private float uncertainty;
//TODO
	public StocasticLSystem(String axiom, Rule[] ruleset, float uncertainty) 
	{
		sequence = axiom;
		this.ruleset = ruleset;
		generation = 0;
		this.uncertainty = uncertainty;
	}

	public String getSequence()
	{
		return sequence;
	}
	
	public int getGeneration()
	{
		return generation;
	}
	
	public void nextGeneration()
	{
		generation++;

		String nextgen = "";
		for(int i=0;i<sequence.length();i++) 
		{
			char c = sequence.charAt(i);
			String replace = "" + c;
			for(int j=0;j<ruleset.length;j++)
			{
				float aux = (float) Math.random();
				if (c == ruleset[j].getSymbol() && aux >= uncertainty) 
				{
					replace = ruleset[j].getString();
					break;
				}
			}
			nextgen += replace;
		}
		this.sequence = nextgen;
	}
}