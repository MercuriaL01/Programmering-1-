public class Triangel
{
	public static double Arean(double h, double b)
	{
		return (h*b)/2;
	}

	public static double Omkrets(double a, double b, double c)
	{
		return a+b+c;
	}

	public static double Median(double a, double b, double c)
	{
		return 1/2*(2*((b*b))+(2*(c*c))-(a*a));
	}

	//Denna g�r att anv�nda f�r att f� l�ngden p� samtliga bisektriser om man har alla l�ngder och vinklar i triangeln eftersom a,b,c �r p�hittade namn som an kan ge till alla vinklar och sidor av triangeln
	public static double Bisektris(double b, double c, double a)
	{
		return ((2 * b*c)/(b+c))*Math.cos(a/2);
	}

	public static double Arean(double a, double b, double c)
	{
		double    p = (a+b+c)/2;
		return Math.sqrt((p*(p-a)*(p-b)*(p-c)));
	}

	public static double OmskrivnaCirkelRadie(double a, double b, double c)
	{
		return (a*b*c)/(4*(Arean(a,b,c)));
	}

	public static double InskrivnaCirkelRadie(double a, double b, double c)
	{
		double s=(a+b+c)/2;
		return Math.sqrt(((s-a)*(s-b)*(s-c))/s);
	}
}