import static java.lang.System.*;

public class PolylinjeTest
{
	public static void main (String[] args)
	{
		Punkt[] punkter = new Punkt[3];
		for(int i = 0; i <=2; i++)
		{
			String namnet = Integer.toString(i);
			punkter[i] = new Punkt(namnet, 5 - i, 3 + i);
		}
		Polylinje polylinje = null;
		polylinje = new VPolylinje (punkter);
		//polylinje = new NPolylinje (punkter);
		System.out.println("polylinjen har f�rgen: " + polylinje.getFarg());
		System.out.println("polylinjen har bredden: " + polylinje.getBredd());
		polylinje.setFarg("gr�n");
		polylinje.setBredd(17);
		System.out.println("polylinjen har f�rgen: " + polylinje.getFarg() + " efter den �ndrades till gr�n");
		System.out.println("polylinjen har bredden: " + polylinje.getBredd() + " efter den �ndrades till 17");
		System.out.println("poylinjen har l�ngden: " + polylinje.langd());
		Punkt laggTillPunkt = new Punkt("laggTillPunkt", 1, 2);
		polylinje.laggTill(laggTillPunkt);
		System.out.println("polylinjen med en extra punkt jag la in med laggTill metoden" + polylinje.toString());
		Punkt laggTillFramforPunkt = new Punkt("laggTillFramf�rPunkt", 3, 2);
		polylinje.laggTillFramfor(laggTillFramforPunkt, polylinje.getHorn()[1].getNamn());
		System.out.println("polylinjen med en extra punkt jag la in med laggTillFramf�r metoden, p� plats 2" + polylinje.toString());
		polylinje.taBort(polylinje.getHorn()[1].getNamn());
		System.out.println("polylinjen med punkten jag la till i f�rra raden borta" + polylinje.toString());
		System.out.println();
		System.out.println();

		//Anv�nda iteratorn
		System.out.println("NU B�RJAR JAG TESTA ITERATORN");
		java.util.Iterator<Punkt> iterator = polylinje.iterator();
		while(iterator.hasNext())
		{
			Punkt hornet = iterator.next();
			System.out.print(hornet + " ");
		}
		System.out.println();
		iterator = polylinje.iterator();
		iterator.next();
		//iterator.remove();
		System.out.println();
		System.out.println(":)");
	}
}
