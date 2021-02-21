public class EU1_del2
{
	public static void main(String[] args)
	{
		int[] x = {13, 2, 3, 4, 5, 6, 7, 8 ,9, 10, 11, 12, 1, 14, 15, 16};
		System.out.println(min(x));
	}

	public static int min (int[] arrayen)
	{
		int minsta = arrayen[0];
		for(int i : arrayen)
		{
			if(i <= minsta)
			{
				minsta = i;
			}
		}
		return minsta;
	}
}