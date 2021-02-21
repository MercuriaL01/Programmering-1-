public class Polylinje
{
	private Punkt[]    horn;
	private String     farg = "svart";
	private int        bredd = 1;

	//Polylinje konstruktor
	public Polylinje ()
	{
		this.horn = new Punkt[0];
	}

	//Polylinje konstruktor som tar emot en array med punkter och skapar en polylinje av dem
	public Polylinje (Punkt[] horn)
	{
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}

	//För att kunna skriva ut informationen om en specific polylinje
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

	//En vanlig get, i detta fall för horn
	public Punkt[] getHorn()
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

	//En vanlig get, i detta fall för farg
	public String getFarg()
	{
		return this.farg;
	}

	//En vanlig get, i detta fall för bredd
	public int getBredd()
	{
		return this.bredd;
	}

	//En vanlig get, i detta fall för farg
	public void setFarg(String farg)
	{
		this.farg = farg;
	}

	//En vanlig set, i detta fall för bredd
	public void setBredd(int bredd)
	{
		this.bredd = bredd;
	}

	//Denna beräknar längden av en polylinje, genom att mäta längden mellan alla hörn i polylinjen
	public double langd()
	{
		double returnLangd = 0;
		for(int i = 0; i < this.horn.length - 1; i++)
		{
			returnLangd += horn[i].avstand(horn[i + 1]);
		}
		return returnLangd;
	}

	//Lägger till en punkt till en polylinjes horn
	public void laggTill (Punkt horn)
	{
		Punkt[]    h = new Punkt[this.horn.length + 1];
		int    i = 0;
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		h[i] = new Punkt (horn);

		this.horn = h;
	}

	//Med denna metod kan man välja var man vill lägga in en punkt i en polylinjes horn
	public void laggTillFramfor(Punkt punkten, String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];
		int i = 0;
		for(Punkt punkt : this.horn)
		{
			if(punkt.getNamn() == hornNamn)
			{
				h[i] = new Punkt(punkten);
				h[i+1] = punkt;
				i += 2;
				continue;
			}
			h[i] = punkt;
			i++;
		}
		this.horn = h;
	}

	//Denna metod har en String som imparameter, denna string ska vara namnet på ett horn man vill radera från en polylinje.
	public void taBort(String hornNamn)
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

	public class PolylinjeIterator
	{
		private int    aktuell = -1;

		public PolylinjeIterator ()
		{
			if (Polylinje.this.horn.length >0)
			aktuell = 0;

		}

		public boolean finnsHorn ()
		{
			return aktuell != -1;
		}

		public Punkt horn ()throws java.util.NoSuchElementException
		{
			if (!this.finnsHorn ())
			throw new java.util.NoSuchElementException ("slut av iterationen");
			Punkt    horn = Polylinje.this.horn[aktuell];

			return horn;
		}

		public void gaFram ()
		{
			if (aktuell >= 0  && aktuell < Polylinje.this.horn.length -1)
			{
				aktuell++;
			}
			else
			{
					aktuell = -1;
			}
		}
	}
}