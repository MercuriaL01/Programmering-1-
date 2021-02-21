import java.util. *;	//Scanner
import static java.lang.System.out;

class OperationerMedNaturligaHeltalGivnaSomTeckenstrangar
{
	public static void main (String[] args)
	{
		out.println ("OPERATIONER MED NATURLIGA HELTAL GIVNA SOM TECKENSTRANGAR\n");

		// mata in tv� naturliga heltal
		Scanner    in = new Scanner (System.in);
		out.println ("tv� naturliga heltal:");
		String    tal1 = in.next ();
		String    tal2 = in.next ();
		out.println ();

		// addera heltalen och visa resultatet
		String    summa = addera (tal1, tal2);
		visa (tal1, tal2, summa, '+');

		// subtrahera heltalen och visa resultatet
		String differans = subtrahera (tal1, tal2);
		visa (tal1, tal2, differans, '-');
	}

	// addera tar emot tv� naturliga heltal givna somteckenstr�ngar, och returnerar deras
	// summa som en teckenstr�ng.
	public static String addera (String tal1, String tal2)
	{
		StringBuilder returnString = new StringBuilder();
		int x = 0;
		String storsta = "";
		String minsta = "";
		//kolla vilket tal som �r minsta (detta anv�nder senare i denna metod)
		if(tal1.length() >= tal2.length())
		{
			storsta = tal1;
			minsta = tal2;
		}
		else
		{
			storsta = tal2;
			minsta = tal1;
		}
		int j = minsta.length() -1;
		//i �r en int s� att man ska kunna ha hur stora stringar som helst (en strings max chars �r inte h�gre �n h�gsta v�rdet p� int)
		for(int i = storsta.length() - 1; i >= 0; i--)
		{
			//denna kollar om alla chars i kortaste siffran har anv�ndts, i s� fall kollat man bara v�rdet p� den l�ngre str�ngen.
			//annars adderas den korta och l�nga siffrans char i loop indexet
			int summa = (j < 0) ? Integer.parseInt(String.valueOf(storsta.charAt(i))) : Integer.parseInt(String.valueOf(storsta.charAt(i))) + Integer.parseInt(String.valueOf(minsta.charAt(j)))	;
			if(x == 1)
			{
				summa++;
				x = 0;
			}
			if(summa >= 10)
			{
		    	x = 1;
		    	summa = summa % 10;
			}
			//appendar i slutet av return str�ngen(summan)
			returnString.append(Integer.toString(summa));
			j--;
		}
		//om x = 1 i slutet av loopen betyder det att 1 ska l�ggas till.
		if (x == 1)
		{
			returnString.append(x);
		}
		//�ndra h�ll p� summan eftersom jag har appendat ist�llet f�r att s�tta siffran i b�rjan som jag skulle ha gjort om det fanns n�gon sorts "reverse append"
		return returnString.reverse().toString();
	}

	// subtrahera tar emot tv� naturliga heltal givna somteckenstr�ngar, och returnerar
	// deras differens somen teckenstr�ng.
	// Det f�rsta heltalet �r inte mindre �n det andra heltalet.
	public static String subtrahera (String tal1, String tal2)
	{
		StringBuilder returnString = new StringBuilder();
		int x = 0;
		String storsta = "";
		String minsta = "";
		//kolla vilket tal som �r minsta (detta anv�nder senare i denna metod)
		if(tal1.length() >= tal2.length())
		{
			storsta = tal1;
			minsta = tal2;
		}
		else
		{
			storsta = tal2;
			minsta = tal1;
		}
		int j = minsta.length() -1;
		//i �r en int s� att man ska kunna ha hur stora stringar som helst (en strings max chars �r inte h�gre �n h�gsta v�rdet p� int)
		for(int i = storsta.length() - 1; i >= 0; i--)
		{
			//denna kollar om alla chars i kortaste siffran har anv�ndts, i s� fall kollat man bara v�rdet p� den l�ngre str�ngen.
			//annars adderas den korta och l�nga siffrans char i loop indexet
			int differans = (j < 0) ? Integer.parseInt(String.valueOf(storsta.charAt(i))) : Integer.parseInt(String.valueOf(storsta.charAt(i))) - Integer.parseInt(String.valueOf(minsta.charAt(j)));
			if(x == 1)
			{
				differans--;
				x = 0;
			}
			if(differans < 0)
			{
	   			differans += 10;
				x = 1;
			}
			//appendar i slutet av return str�ngen(summan)
			returnString.append(Integer.toString(differans));
			j--;
		}
		//�ndra h�ll p� summan eftersom jag har appendat ist�llet f�r att s�tta siffran i b�rjan som jag skulle ha gjort om det fanns n�gon sorts "reverse append"
		return taBortLedandeNollor(returnString.reverse().toString());
	}

	// visa visar tv� givna naturliga heltal, och resultatet av en aritmetisk operation
	// utf�rd i samband med hetalen
	public static void visa (String tal1, String tal2,String resultat, char operator)
	{
		// s�tt en l�mplig l�ngd p� heltalen och resultatet
		int    len1 = tal1.length ();
		int    len2 = tal2.length ();
		int    len  = resultat.length ();
		int    maxLen = Math.max (Math.max (len1, len2), len);
		tal1 = sattLen (tal1, maxLen -len1);
		tal2 = sattLen (tal2, maxLen -len2);
		resultat = sattLen (resultat, maxLen -len);
		// visa heltalen och resultatet
		out.println ("  " + tal1);
		out.println ("" + operator + " " + tal2);
		for (int i = 0; i < maxLen + 2; i++)
			out.print ("-");
		out.println ();
		out.println ("  " + resultat + "\n");
	}

	// sattLen l�gger till ett angivet antal mellanslagi b�rjan av en given str�ng
	public static String sattLen (String s, int antal)
	{
		StringBuilder    sb = new StringBuilder (s);
		for (int i = 0; i < antal; i++)
			sb.insert (0, " ");

		return sb.toString ();
	}

	//Metod f�r att ta bort nollor i b�rjan av en str�ng
	public static String taBortLedandeNollor(String value)
	{
	     while (value.indexOf("0") == 0)
	     {
			 //Metoden substrings parameter �r 1 s� att den g�r en ny str�ng som b�rjar fr�n den gammlas index "1". Stringar har index eftersom som de �r char vektorer
			 //Allts� tas eventuellt nollv�rde i b�rjan av str�ngen bort fr�n str�ngen
	         value = value.substring(1);
		 }
         return value;
	}
}












