import java.util.Scanner;

public class EnTriangelOchDessCirklar
{
	public static void main(String[] args)
	{
		Triangel triangel = new Triangel();
		Scanner scan = new Scanner(System.in);
		double a = 0;
		double b = 0;
		double c = 0;
		while(true)
		{
			System.out.println("Skriv in triangel sida a");
			a = scan.nextDouble();
			System.out.println("Skriv in triangel sida b");
			b = scan.nextDouble();
			System.out.println("Skriv in triangel sida c");
			c = scan.nextDouble();
			double storsta = a;
			double minsta1 = b;
			double minsta2 = c;
			if(b > storsta)
			{
				storsta = b;
				minsta1 = a;
			}
			if(c > storsta)
			{
				if(storsta == a)
				{
					storsta = c;
					minsta2 = a;
				}
				if(storsta == b)
				{
					storsta = c;
					minsta2 = b;
				}
			}
			if(storsta >= minsta1 + minsta2)
			{
				System.out.println("Detta är ej en riktig triangel (kom ihåg triangelolikheten)");
				continue;
			}
			break;
		}
		double oRadie = triangel.OmskrivnaCirkelRadie(a,b,c);
		double iRadie = triangel.InskrivnaCirkelRadie(a,b,c);
		System.out.println("Omskrivna cirkelns radie är: " + oRadie);
		System.out.println("Inskrivna cirkelns radie är: " + iRadie);
	}
}