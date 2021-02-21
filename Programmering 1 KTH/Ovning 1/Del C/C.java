// C.java

// ett program som hanterar personliga uppgifter

/**********************************************************************

Anv�ndaren matar in sina personliga uppgifter. Dessa uppgifter sparas
sedan i en fil.

**********************************************************************/

import java.util.Scanner;
import java.io.PrintWriter;

class C
{
    public static void main (String[] args)

    throws Exception
    {
	System.out.println("DINOSAURIE INFORMATION");
	System.out.println();

	//inmatningsverktyg
	Scanner scan = new Scanner(System.in);

	//mata in dinosarie informationen
	System.out.print("Namn: ");
	String namn = scan.nextLine();
	System.out.print("Vikt: ");
	Double vikt = scan.nextDouble();
	scan.nextLine(); //Denna beh�vs eftersom att double och inte tar hand om "enter"
	System.out.print("Foda: ");
	String foda = scan.nextLine();
	System.out.println();

	//spara informationen i en fil
	PrintWriter filen = new PrintWriter("dinoInfo.txt");
	filen.println("Namn: " + namn);
	filen.println("Vikt: " + vikt);
	filen.println("F�da: " + foda);
	filen.println();   // denna �r h�r s� att om jag l�gger till fler dinosaurier s� blir det ett mellanrum mellan dem.
	filen.flush();

	//ett meddelande
	System.out.println("Dinosaurie informationen finns sparad p� dinoInfo.txt :)");
    }
}