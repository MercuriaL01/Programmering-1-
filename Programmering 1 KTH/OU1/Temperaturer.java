import java.util.*;     // Scanner, Locale
class Temperaturer
{
	public static void main (String[] args)
	{
		System.out.println ("TEMPERATURER\n");

		// inmatningsverktyg
		Scanner in = new Scanner (System.in);
		in.useLocale (Locale.US);

		// mata in uppgifter om antalet veckor och antaletmätningar
		System.out.print ("antalet veckor: ");
		int    antalVeckor = in.nextInt ();
		System.out.print ("antalet mätningar per vecka: ");
		int    antalMatningarPerVecka = in.nextInt ();

		// plats att lagra temperaturer
		double[][]    t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];

		// mata in temperaturerna
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			System.out.println ("temperaturer - vecka " + vecka+ ":");
			for (int matning = 1;matning <= antalMatningarPerVecka; matning++)
				t[vecka][matning] = in.nextDouble ();
		}
		System.out.println ();

		// visa temperaturerna
		System.out.println ("temperaturerna:");
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			for (int matning = 1;matning <= antalMatningarPerVecka; matning++)
				System.out.print (t[vecka][matning] + " ");
			System.out.println ();
		}
		System.out.println ();

		// den minsta,den största och medeltemperaturen–veckovis
		double[]    minT = new double[antalVeckor + 1];
		double[]    maxT = new double[antalVeckor + 1];
		double[]    sumT = new double[antalVeckor + 1];
		double[]    medelT = new double[antalVeckor + 1];

		//koden jag skrev för minsta, största och medeltemperaturen veckovis
		for(int vecka = 1; vecka<=antalVeckor; vecka++)
		{
			for(int matning = 1; matning<=antalMatningarPerVecka; matning++)
			{
				if(matning == 1 || t[vecka][matning] < minT[vecka])
				{
					minT[vecka] = t[vecka][matning];
				}
				if(matning == 1 || t[vecka][matning] > maxT[vecka])
				{
					maxT[vecka] = t[vecka][matning];
				}
				sumT[vecka] += t[vecka][matning];
			}
			medelT[vecka] = sumT[vecka]/antalMatningarPerVecka;
		}

		// visa den minsta, den största och medeltemperaturen för varje vecka (JAG SKREV KODEN, LA TILL SUMMA, KANSKE INTE SKA DET, VET EJ)
		for(int vecka = 1; vecka<=antalVeckor; vecka++)
		{
				System.out.println("Vecka nummer " + vecka + " var den lägsta temperaturen: " + minT[vecka]);
				System.out.println("Vecka nummer " + vecka + " var den högsta temperaturen: " + maxT[vecka]);
				System.out.println("Vecka nummer " + vecka + " var summan av temperaturerna: " + sumT[vecka]);
				System.out.println("Vecka nummer " + vecka + " var medeltemperaturen: " + medelT[vecka]);
				System.out.println();
		}

		// den minsta, den största och medeltemperaturen -helamätperioden
		double    minTemp = minT[1];
		double    maxTemp = maxT[1];
		double    sumTemp = sumT[1];
		double    medelTemp = 0;

		// koden ska skrivas här
		for(int i = 2; i <=antalVeckor; i++)
		{
			if(minTemp > minT[i])
			{
				minTemp = minT[i];
			}
			if(maxTemp < maxT[i])
			{
				maxTemp = maxT[i];
			}
			sumTemp += sumT[i];
		}
		medelTemp = sumTemp/(antalMatningarPerVecka*antalVeckor);

		// visa den minsta, den största och medeltemperaturen i hela mätperioden (JAG SKREV KODEN)
			System.out.println("Den lägsta temperaturen för alla veckorna var: " + minTemp);
			System.out.println("Den högsta temperaturen för alla veckorna var: " + maxTemp);
			System.out.println("Summan av temperaturerna för alla veckorna var: " + sumTemp);
			System.out.println("Medeltemperaturen för veckorna var: " + medelTemp);
			System.out.println();
	}
}