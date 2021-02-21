public class DenKortasteVagen
{
	public static int[] mellanstationer(double[] a, double[][] b, double[] c)
	{
		int forsta = 0;
		int andra = 0;
		double kortasteLangden = a[0]+b[0][0]+c[0];
 		for(int i = 1; i < a.length; i++)
		{
			for(int j = 1; j < c.length; j++)
			{
				if(a[i] + b[i][j] + c[j] <= kortasteLangden)
				{
					kortasteLangden = a[i] + b[i][j] + c[j];
					forsta = i;
					andra = j;
				}
			}
		}
		int[] returnVektor = new int[2];
		returnVektor[0] = forsta;
		returnVektor[1] = andra;
		return returnVektor;
	}

	public static double langd(double[] a, double[][] b, double[] c)
	{
		int[] mellanstationerna = mellanstationer(a,b,c);
		return a[mellanstationerna[0]] + b[mellanstationerna[0]][mellanstationerna[1]] + c[mellanstationerna[1]];
	}
}