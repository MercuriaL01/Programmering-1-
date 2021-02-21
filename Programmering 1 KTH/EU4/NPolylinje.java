import java.util.Iterator;

public class NPolylinje implements Polylinje
{
	private static class Nod
	{
		public Punkt    horn;
		public Nod      nastaNod;

		public Nod (Punkt horn)
		{
			this.horn = horn;
			nastaNod = null;
		}
	}

	private Nod    horn;
	private String     farg = "svart";
	private int        bredd= 1;  // pixlar

	public NPolylinje ()
	{
		this.horn = null;
	}

	public NPolylinje (Punkt[] horn)
	{
		if (horn.length > 0)
		{
			Nod   nod = new Nod (new Punkt (horn[0]));
			this.horn = nod;
			int    pos = 1;
			while (pos < horn.length)
			{
				nod.nastaNod = new Nod (new Punkt (horn[pos++]));
				nod = nod.nastaNod;
			}
		}
	}

		public String toString()
		{
			Nod   nod = this.horn;
			String returnString = "{[";
			while(nod != null)
			{
				returnString += nod.horn.toString();
				nod = nod.nastaNod;
			}
			returnString += "], " + this.farg + ", " + this.bredd + "}";
			return returnString;
		}

		public Punkt[] getHorn ()
		{
			Nod nod = this.horn;
			int i = 0;
			while(nod != null)
			{
				i++;
				nod = nod.nastaNod;
			}
			Punkt[] hornKopia = new Punkt[i];
			nod = this.horn;
			i = 0;
			while(nod != null)
			{
				hornKopia[i] = nod.horn;
				i++;
				nod = nod.nastaNod;
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
			Nod nod = this.horn;
			double returnLangd = 0;
			while(nod.nastaNod != null)
			{
					returnLangd += nod.horn.avstand(nod.nastaNod.horn);
					nod = nod.nastaNod;
			}
			return returnLangd;
		}

		public void setFarg (String farg)
		{
			this.farg = farg;
		}

		public void setBredd (int bredd)
		{
			this.bredd = bredd;
		}

		public void laggTill (Punkt horn)
		{
			Nod nod = this.horn;
			while(nod.nastaNod != null)
			{
				nod = nod.nastaNod;
			}
			nod.nastaNod = new Nod(horn);
		}

		public void laggTillFramfor (Punkt horn, String hornNamn)
		{
			Nod nod = this.horn;
			if(nod.horn.getNamn().equals(hornNamn))
			{
				nod.nastaNod = nod;
				nod = new Nod(horn);
				return;
			}
			while(nod.nastaNod != null)
			{
				if(nod.nastaNod.horn.getNamn().equals(hornNamn))
				{
					Nod sparadNod = nod.nastaNod;
					nod.nastaNod = new Nod(horn);
					nod.nastaNod.nastaNod = sparadNod;
					return;
				}
				nod = nod.nastaNod;
			}
		}

		public void taBort (String hornNamn)
		{
			Nod nod = this.horn;
			if(nod.horn.getNamn().equals(hornNamn))
			{
				nod = nod.nastaNod;
				return;
			}
			while(nod.nastaNod != null)
			{
				if(nod.nastaNod.horn.getNamn().equals(hornNamn))
				{
					nod.nastaNod = nod.nastaNod.nastaNod;
					return;
				}
				nod = nod.nastaNod;
			}
		}

	public java.util.Iterator<Punkt> iterator ()
	{
		Nod   nod = this.horn;
		int x = 0;
		while(nod.nastaNod != null)
		{
			nod = nod.nastaNod;
			x += 1;
		}
		int currentSize = x;
		Nod   nod2 = this.horn;
		Iterator<Punkt> it = new Iterator<Punkt>()
		{
			Nod tmpNod = nod2;
			private int currentIndex = 0;

	 			public boolean hasNext()
				{
			    	return tmpNod.nastaNod != null;
				}
				public Punkt next()
				{
					if(currentIndex == 0)
					{
						currentIndex++;
						return tmpNod.horn;
					}
					Punkt sparPunkt = tmpNod.nastaNod.horn;
					tmpNod = tmpNod.nastaNod;
					return sparPunkt;
				}
			   	//public void remove()
			    //{
				// 	throw new UnsupportedOperationException("no remove");
				//}
			};
        return it;
	}
}