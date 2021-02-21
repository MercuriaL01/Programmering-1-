public class Punkt
{
	private String namn;
	private int x;
	private int y;
	public Punkt(String namn, int x, int y)
	{
		this.namn = namn;
		this.x = x;
		this.y = y;
	}
	public Punkt(Punkt punkt)
	{
		this.namn = punkt.namn;
		this.x = punkt.x;
		this.y = punkt.y;
	}
	public String getNamn()
	{
		return this.namn;
	}

	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public void setX(int varde)
	{
		this.x = varde;
	}
	public void setY(int varde)
	{
		this.y = varde;
	}
	//Gjorde detta en double eftersom att avst�ndet kan vara st�rre �n ett int v�rde (om punkterna �r p� vardera sida y axeln till exempel)
	public double avstand(Punkt punkt)
	{
		return Math.sqrt(((this.x - punkt.x)*(this.x - punkt.x))+((this.y - punkt.y)*(this.y - punkt.y)));
	}
	public String toString()
	{
		return "(\"" + this.namn + "\", " + this.x + ", " + this.y + ")";
	}
}