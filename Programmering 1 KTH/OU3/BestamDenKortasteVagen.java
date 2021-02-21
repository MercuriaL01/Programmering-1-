import java.util.Scanner;

public class BestamDenKortasteVagen
{
	public static void main(String[] args)
	{
		//Antalet staioner i Z2 och Z3
		Scanner scan = new Scanner(System.in);
		System.out.println("Antalet stationer i zonen Z2");
		int antalZ2 = scan.nextInt();
		System.out.println("Antalet stationer i zonen Z3");
		int antalZ3 = scan.nextInt();

		//Avstånd mellan Z1 och Z2
		double[] avstand1 = new double[antalZ2];
		for(int i = 0; i < antalZ2; i++)
		{
			System.out.print("Avståndet från X till U" + (i + 1) + " är: ");
			avstand1[i] = scan.nextDouble();
			System.out.println();
		}

		//Avstånd mellan Z2 och Z3
		double[][] avstand2 = new double[antalZ2][antalZ3];
		for(int i = 0; i < antalZ2; i++)
		{
			for(int j = 0; j < antalZ3; j++)
			{
				System.out.print("Avståndet från U" + (i + 1) + " till V" + (j + 1) + " är: ");
				avstand2[i][j] = scan.nextDouble();
				System.out.println();
			}
		}

		//Avstånd mellan Z3 och Z4
		double[] avstand3 = new double[antalZ3];
		for(int i = 0; i < antalZ3; i++)
		{
			System.out.print("Avståndet från V" + (i + 1) + " till Y" + " är: ");
			avstand3[i] = scan.nextDouble();
			System.out.println();
		}

		DenKortasteVagen kortastevagen = new DenKortasteVagen();
		System.out.println("Längden av den kortaste vägen är: " + kortastevagen.langd(avstand1, avstand2, avstand3));
	}
}