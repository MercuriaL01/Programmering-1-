public class Polylinje1
{
	private Punkt[]    horn;
	private String     farg = "svart";
	private int        bredd = 1;

	//Polylinje1 konstruktor
	public Polylinje1 ()
	{
		this.horn = new Punkt[0];
	}

	//Polylinje1 konstruktor som tar emot en array med punkter och skapar en polylinje av dem
	public Polylinje1 (Punkt[] horn)
	{
			this.horn = horn;
	}

	//F�r att kunna skriva ut informationen om en specific polylinje
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

	//En vanlig get, i detta fall f�r horn
	public Punkt[] getHorn()
	{
		return this.horn;
	}

	//En vanlig get, i detta fall f�r farg
	public String getFarg()
	{
		return this.farg;
	}

	//En vanlig get, i detta fall f�r bredd
	public int getBredd()
	{
		return this.bredd;
	}

	//En vanlig get, i detta fall f�r farg
	public void setFarg(String farg)
	{
		this.farg = farg;
	}

	//En vanlig set, i detta fall f�r bredd
	public void setBredd(int bredd)
	{
		this.bredd = bredd;
	}

	//Denna ber�knar l�ngden av en polylinje, genom att m�ta l�ngden mellan alla h�rn i polylinjen
	public double langd()
	{
		double returnLangd = 0;
		for(int i = 0; i < this.horn.length - 1; i++)
		{
			returnLangd += horn[i].avstand(horn[i + 1]);
		}
		return returnLangd;
	}

	//L�gger till en punkt till en polylinjes horn
	public void laggTill (Punkt horn)
	{
		Punkt[]    h = new Punkt[this.horn.length + 1];
		int    i = 0;
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		h[i] = horn;

		this.horn = h;
	}

	//Med denna metod kan man v�lja var man vill l�gga in en punkt i en polylinjes horn
	public void laggTillFramfor(Punkt punkten, String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];
		int i = 0;
		for(Punkt punkt : this.horn)
		{
			if(punkt.getNamn() == hornNamn)
			{
				h[i] = punkten;
				h[i+1] = punkt;
				i += 2;
				continue;
			}
			h[i] = punkt;
			i++;
		}
		this.horn = h;
	}

	//Denna metod har en String som imparameter, denna string ska vara namnet p� ett horn man vill radera fr�n en polylinje.
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
}