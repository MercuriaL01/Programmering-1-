import java.util.Iterator;

public class VPolylinje implements Polylinje
{
	private Punkt[]    horn;
	private String     farg = "svart";
	private int        bredd = 1;

	public VPolylinje ()
	{
		this.horn = new Punkt[0];
	}

	//VPolylinje konstruktor som tar emot en array med punkter och skapar en polylinje av dem
	public VPolylinje (Punkt[] horn)
	{
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}

	public String toString()
	{
		String returnString = "{[";
		for(Punkt punkt : horn)
		{
			returnString += punkt.toString();
		}
		returnString += "], " + this.farg + ", " + this.bredd + "}";
		return returnString;
	}

	public Punkt[] getHorn ()
	{
		Punkt[] hornKopia = new Punkt[horn.length];
		int i = 0;
		for(Punkt punkt : horn)
		{
			hornKopia[i] = punkt;
			i++;
		}
		return hornKopia;
	}

	public String getFarg ()
	{
		return this.farg;
	}

	public int getBredd ()
	{
		return this.bredd;
	}

	public double langd ()
	{
		double returnLangd = 0;
		for(int i = 0; i < this.horn.length - 1; i++)
		{
			returnLangd += horn[i].avstand(horn[i + 1]);
		}
		return returnLangd;
	}

	public	void setFarg (String farg)
	{
		this.farg = farg;
	}

	public void setBredd (int bredd)
	{
		this.bredd = bredd;
	}

	public void laggTill (Punkt horn)
	{
		Punkt[]    h = new Punkt[this.horn.length + 1];
		int    i = 0;
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
			h[i] = new Punkt (horn);

		this.horn = h;
	}

	public void laggTillFramfor (Punkt horn, String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];
		int i = 0;
		for(Punkt punkt : this.horn)
		{
			if(punkt.getNamn() == hornNamn)
			{
				h[i] = new Punkt(horn);
				h[i+1] = punkt;
				i += 2;
				continue;
			}
			h[i] = punkt;
			i++;
		}
		this.horn = h;
	}

	public void taBort (String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length - 1];
		int i = 0;
		for(Punkt punkt : this.horn)
		{
			if(punkt.getNamn() == hornNamn)
			{
				continue;
			}
			h[i] = punkt;
			i++;
		}
		this.horn = h;
	}

	public java.util.Iterator<Punkt> iterator()
	{
		int currentSize = this.horn.length;
		Iterator<Punkt> it = new Iterator<Punkt>()
		{
			private int currentIndex = 0;

				 public boolean hasNext()
				 {
		    	 	return currentIndex < currentSize && horn[currentIndex] != null;
				 }
				 public Punkt next()
				 {
				 	return horn[currentIndex++];
				 }
		   	 	 //public void remove()
		    	 //{
				 //	throw new UnsupportedOperationException();
				 //}
			 };
        return it;
	}
}