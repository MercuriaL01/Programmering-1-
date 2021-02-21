public class Polylinjer
{
	public static Polylinje kortasteGula(Polylinje[] polylinjerna)
	{
		Polylinje returnPolylinje = null;
		for(Polylinje polylinjen : polylinjerna)
		{
			if(polylinjen.getFarg().equals("gul"))
			{
				if(returnPolylinje == null || polylinjen.langd() < returnPolylinje.langd())
				{
					returnPolylinje = polylinjen;
				}
			}
		}
		return returnPolylinje;
	}
}