import static java.lang.System.*;
import java.util.Random;

public class PolylinjerTest
{
	public static void main (String[] args)
	{
		Punkt[] punkter = new Punkt[3];
		Punkt[] punkter2 = new Punkt[3];
		Punkt[] punkter3 = new Punkt[3];
		for(int i = 0; i <= 2; i++)
		{
			String namnet = Integer.toString(i);
			punkter[i] = new Punkt(namnet, 5 - i, 3 + i);
			punkter2[i] = new Punkt(namnet, 3 - i, 7 + i);
			punkter3[i] = new Punkt(namnet, 3 - i, 1 + i);
		}
		VPolylinje vPl = new VPolylinje(punkter);
		valjFarg(vPl);
		NPolylinje nPl = new NPolylinje(punkter2);
		valjFarg(nPl);

		VPolylinje vPl2 = new VPolylinje(punkter3);
		valjFarg(vPl2);
		NPolylinje nPl2 = new NPolylinje(punkter);
		valjFarg(nPl2);

		VPolylinje vPl3 = new VPolylinje(punkter2);
		valjFarg(vPl3);
		NPolylinje nPl3 = new NPolylinje(punkter3);
		valjFarg(nPl3);

		VPolylinje[] vPolylinjeArray = {vPl, vPl2, vPl3};
		NPolylinje[] nPolylinjeArray = {nPl, nPl2, nPl3};
		Polylinje[] polylinjeArray = {vPl, vPl2, vPl3, nPl, nPl2, nPl3};

		if(Polylinjer.kortasteGula(vPolylinjeArray) != null)
		{
			System.out.println("Den kortaste gula VPolylinjen är: " + Polylinjer.kortasteGula(vPolylinjeArray));

		}
		else
		{
			System.out.println("Ingen av polylinjerna var gul! så det finns ingen kortaste gula VPolylinje");
		}

		if(Polylinjer.kortasteGula(nPolylinjeArray) != null)
		{
			System.out.println("Den kortaste gula NPolylinjen är: " + Polylinjer.kortasteGula(nPolylinjeArray));
		}
		else
		{
			System.out.println("Ingen av polylinjerna var gul! så det finns ingen kortaste gula NPolylinje");
		}

		if(Polylinjer.kortasteGula(polylinjeArray) != null)
		{
		System.out.println("Den kortaste gula Polylinjen är: " + Polylinjer.kortasteGula(polylinjeArray));
		}
		else
		{
			System.out.println("Ingen av polylinjerna var gul! så det finns ingen kortaste gula Polylinje");
		}
	}

	public static Polylinje valjFarg(Polylinje polylinje)
	{
		Random rand = new Random();
		int fargValet = rand.nextInt(3);
		switch (fargValet)
		{
			case 0 :
				polylinje.setFarg("blå");
				break;
			case 1 :
				polylinje.setFarg("röd");
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