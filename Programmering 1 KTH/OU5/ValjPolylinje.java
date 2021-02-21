import java.util.Random;

class ValjPolylinje
{
	public static final Random    rand = new Random ();
	public static final int    ANTAL_POLYLINJER = 10;

	public static void main (String[] args)
	{
		// skapa ett antal slumpm�ssiga polylinjer
		Polylinje[]    polylinjer = new Polylinje[ANTAL_POLYLINJER];
		for (int i = 0; i < ANTAL_POLYLINJER; i++)
			polylinjer[i] = slumpPolylinje ();

		// visa polylinjerna
		for(int i = 0; i < ANTAL_POLYLINJER; i++)
		{
			System.out.println("Polylinje nummer " + (i + 1) + " �r: " + polylinjer[i].toString());
			System.out.println();
		}

		// best�m den kortaste av de polylinjer som �r gula
		Polylinje kortaste = null;
		for(int i = 0; i < ANTAL_POLYLINJER; i++)
		{
			if(polylinjer[i].getFarg() == "gul")
			{
				//viktigt att f�rst kolla om null h�r, eftersom annars kollar den l�ngden p� null, som ger en exception!
				if(kortaste == null || polylinjer[i].langd() < kortaste.langd())
				{
					kortaste = polylinjer[i];
				}
			}
		}

		//visa den valda polylinjen
		System.out.println();
		if(kortaste != null)
		{
			System.out.println("Den kortaste gula polylinjen ser ut som f�ljande: " + kortaste.toString());
		}
		else
		{
			System.out.println("Ingen av polylinjerna var gul! s� det finns ingen kortaste gula polylinje");
		}
	}

	// slumpPunkt returnerar en punkt med ett slumpm�ssigt namn,som �r en stor bokstav i
	// det engelska alfabetet, ochslumpm�ssiga koordinater.
	public static Punkt slumpPunkt ()
	{
		String    n = "" + (char) (65 + rand.nextInt (26));
		int    x = rand.nextInt (11);
		int    y = rand.nextInt (11);

		return new Punkt (n, x, y);
	}

	// slumpPolylinje returnerar en slumpm�ssig polylinje, vars f�rg �r antingen bl�, eller r�d
	//eller gul. Namn p� polylinjens h�rn �r stora bokst�ver i det engelskaalfabetet. Tv� h�rn
	//kan inte ha samma namn.
	public static Polylinje slumpPolylinje ()
	{
		// skapa en tom polylinje, och l�gg till h�rn till den
		Polylinje polylinje = new Polylinje ();
		int    antalHorn = 2 + rand.nextInt (7);
		int    antalValdaHorn = 0;
		boolean[]    valdaNamn = new boolean[26];
		// ett och samma namn kan inte f�rekomma flera g�nger
		Punkt    valdPunkt= null;
		char    valtChar = 0;
		Punkt[] punkterna = new Punkt[antalHorn];
		int i = 0;
		while (antalValdaHorn < antalHorn)
		{
			Punkt testPunkt = slumpPunkt();
			String namn = testPunkt.getNamn();
			valtChar = namn.charAt(0);
			if(valdaNamn[(int)valtChar - 65] == true)
			{
				continue;
			}
			else
			{
				valdaNamn[(int)valtChar - 65] = true;
				polylinje.laggTill(testPunkt);
				antalValdaHorn++;
			}
		}

		// s�tt f�rg
		int fargValet = rand.nextInt(3);
		switch (fargValet)
		{
			case 0 :
			polylinje.setFarg("bl�");
			break;
			case 1 :
			polylinje.setFarg("r�d");
			break;
			case 2:
			polylinje.setFarg("gul");
			break;
			default:
			break;
		}
		return polylinje;
	}
}












