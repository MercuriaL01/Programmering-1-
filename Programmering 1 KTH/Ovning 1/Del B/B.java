import java.util.Scanner;

//�vning 1 del B

class B
{
	public static void main (String[] args)
	{
		System.out.println("REKTANGEL AREA");
		System.out.println();

		//inmaningsverktyg
		Scanner in = new java.util.Scanner(System.in);
		in.useLocale(java.util.Locale.US);

		//mata in uppgifter om l�ngd och bredd
		System.out.print("Langd: ");
		double langd = in.nextDouble();
		System.out.print("Bredd: ");
		double bredd = in.nextDouble();


		//Ber�kna arean
		double area = langd * bredd;

		//visa resultatet
		System.out.println("Rektangelns area �r: " + area);
	}
}