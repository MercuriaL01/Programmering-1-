import static java.lang.System.*;

public class PolylinjeTest
{
	public static void main (String[] args)
	{
		Polylinje pl1 = new Polylinje();
		pl1.toString();
		Punkt[] punkter = new Punkt[3];
		for(int i = 0; i <=2; i++)
		{
			String namnet = Integer.toString(i);
			punkter[i] = new Punkt(namnet, 5 - i, 3 + i);
		}
		Polylinje pl2 = new Polylinje(punkter);
		Punkt[] svarPunkter = pl2.getHorn();
		int i = 0;
		for(Punkt punkt : svarPunkter)
		{
			i++;
			System.out.println("Punkt nummer " + i + "�r: " + punkt);
		}
		System.out.println("pl2 har f�rgen: " + pl2.getFarg());
		System.out.println("pl2 har bredden: " + pl2.getBredd());
		pl2.setFarg("gr�n");
		pl2.setBredd(17);
		System.out.println("pl2 har f�rgen: " + pl2.getFarg() + " efter den �ndrades till gr�n");
		System.out.println("pl2 har bredden: " + pl2.getBredd() + " efter den �ndrades till 17");
		System.out.println("pl2 har l�ngden: " + pl2.langd());
		Punkt laggTillPunkt = new Punkt("laggTillPunkt", 1, 2);
		pl2.laggTill(laggTillPunkt);
		System.out.println("pl2 med en extra punkt jag la in med laggTill metoden" + pl2.toString());
		Punkt laggTillFramforPunkt = new Punkt("laggTillFramf�rPunkt", 3, 2);
		pl2.laggTillFramfor(laggTillFramforPunkt, pl2.getHorn()[1].getNamn());
		System.out.println("pl2 med en extra punkt jag la in med laggTillFramf�r metoden, p� plats 2" + pl2.toString());
		pl2.taBort(pl2.getHorn()[1].getNamn());
		System.out.println("pl2 med punkten jag la till i f�rra raden borta" + pl2.toString());
		System.out.println();
		System.out.println();
		System.out.println("NU B�RJAR POLYLINJEITERATOR TESTET");
		Polylinje.PolylinjeIterator iterator = pl2.new PolylinjeIterator();
		while(iterator.finnsHorn() == true)
		{
			System.out.println(iterator.horn());
			iterator.gaFram();
		}
	}
}