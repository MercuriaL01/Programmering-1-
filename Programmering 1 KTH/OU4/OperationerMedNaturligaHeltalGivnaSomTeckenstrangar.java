import java.util. *;	//Scanner
import static java.lang.System.out;

class OperationerMedNaturligaHeltalGivnaSomTeckenstrangar
{
	public static void main (String[] args)
	{
		out.println ("OPERATIONER MED NATURLIGA HELTAL GIVNA SOM TECKENSTRANGAR\n");

		// mata in två naturliga heltal
		Scanner    in = new Scanner (System.in);
		out.println ("två naturliga heltal:");
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

	// addera tar emot två naturliga heltal givna somteckensträngar, och returnerar deras
	// summa som en teckensträng.
	public static String addera (String tal1, String tal2)
	{
		StringBuilder returnString = new StringBuilder();
		int x = 0;
		String storsta = "";
		String minsta = "";
		//kolla vilket tal som är minsta (detta använder senare i denna metod)
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
		//i är en int så att man ska kunna ha hur stora stringar som helst (en strings max chars är inte högre än högsta värdet på int)
		for(int i = storsta.length() - 1; i >= 0; i--)
		{
			//denna kollar om alla chars i kortaste siffran har användts, i så fall kollat man bara värdet på den längre strängen.
			//annars adderas den korta och långa siffrans char i loop indexet
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
			//appendar i slutet av return strängen(summan)
			returnString.append(Integer.toString(summa));
			j--;
		}
		//om x = 1 i slutet av loopen betyder det att 1 ska läggas till.
		if (x == 1)
		{
			returnString.append(x);
		}
		//ändra håll på summan eftersom jag har appendat istället för att sätta siffran i början som jag skulle ha gjort om det fanns någon sorts "reverse append"
		return returnString.reverse().toString();
	}

	// subtrahera tar emot två naturliga heltal givna somteckensträngar, och returnerar
	// deras differens somen teckensträng.
	// Det första heltalet är inte mindre än det andra heltalet.
	public static String subtrahera (String tal1, String tal2)
	{
		StringBuilder returnString = new StringBuilder();
		int x = 0;
		String storsta = "";
		String minsta = "";
		//kolla vilket tal som är minsta (detta använder senare i denna metod)
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
		//i är en int så att man ska kunna ha hur stora stringar som helst (en strings max chars är inte högre än högsta värdet på int)
		for(int i = storsta.length() - 1; i >= 0; i--)
		{
			//denna kollar om alla chars i kortaste siffran har användts, i så fall kollat man bara värdet på den längre strängen.
			//annars adderas den korta och långa siffrans char i loop indexet
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
			//appendar i slutet av return strängen(summan)
			returnString.append(Integer.toString(differans));
			j--;
		}
		//ändra håll på summan eftersom jag har appendat istället för att sätta siffran i början som jag skulle ha gjort om det fanns någon sorts "reverse append"
		return taBortLedandeNollor(returnString.reverse().toString());
	}

	// visa visar två givna naturliga heltal, och resultatet av en aritmetisk operation
	// utförd i samband med hetalen
	public static void visa (String tal1, String tal2,String resultat, char operator)
	{
		// sätt en lämplig längd på heltalen och resultatet
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

	// sattLen lägger till ett angivet antal mellanslagi början av en given sträng
	public static String sattLen (String s, int antal)
	{
		StringBuilder    sb = new StringBuilder (s);
		for (int i = 0; i < antal; i++)
			sb.insert (0, " ");

		return sb.toString ();
	}

	//Metod för att ta bort nollor i början av en sträng
	public static String taBortLedandeNollor(String value)
	{
	     while (value.indexOf("0") == 0)
	     {
			 //Metoden substrings parameter är 1 så att den gör en ny sträng som börjar från den gammlas index "1". Stringar har index eftersom som de är char vektorer
			 //Alltså tas eventuellt nollvärde i början av strängen bort från strängen
	         value = value.substring(1);
		 }
         return value;
	}
}












