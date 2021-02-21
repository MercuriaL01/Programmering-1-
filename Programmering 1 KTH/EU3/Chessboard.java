import java.lang.StringBuilder;

public class Chessboard
{
	public static class Field
	{
		private char    row;
		private byte    column;
		private Chesspiece    piece= null;
		private boolean    marked= false;

		public Field (char row, byte column)
		{
			this.row = row;
			this.column = column;
		}

		public void put (Chesspiece piece)
		{
			this.piece = piece;
		}

		public Chesspiece take ()
		{
			piece = null;
			return piece;
		}

		public void mark ()
		{
			marked = true;
		}

		public void unmark ()
		{
			marked = false;
		}

		public String toString ()
		{
			String    s = (marked)? "xx" : "--";
			return (piece == null)? s : piece.toString ();
		}
	}


	public static final int    NUMBER_OF_ROWS = 8;
	public static final int    NUMBER_OF_COLUMNS = 8;

	public static final int    FIRST_ROW = 'a';
	public static final int    FIRST_COLUMN = 1;

	private Field[][]    fields;

	public Chessboard ()
	{
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		char    row = 0;
		byte    column = 0;
		for (int r = 0; r < NUMBER_OF_ROWS; r++)
		{
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
			{
				fields[r][c] = new Field (row, column);
				column++;
			}
		}
	}

	public String toString ()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("  ");
		for(int i = 0; i < NUMBER_OF_COLUMNS; i++)
		{
			stringBuilder.append((i + 1) + " ");
		}
		stringBuilder.append("\n");
		for(int i = 0; i < NUMBER_OF_COLUMNS; i++)
		{

			for(int j = 0; j < NUMBER_OF_ROWS; j++)
			{
				if(j == 0)
				{
					stringBuilder.append((char)(FIRST_ROW + i) + " ");
				}
				stringBuilder.append(fields[i][j].toString());
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public boolean isValidField (char row, byte column)
	{
		if(row >= FIRST_ROW + NUMBER_OF_ROWS || row <= FIRST_ROW - 1)
		{
			return false;
		}
		else if(column >= FIRST_COLUMN + NUMBER_OF_COLUMNS || column <= FIRST_COLUMN - 1)
		{
			return false;
		}

		return true;
	}


	public abstract class Chesspiece
	{
		private char    color;
		// w -white, b -black

		private char    name;
		// K -King, Q -Queen, R -Rook, B -Bishop, N -Knight, P –Pawn

		protected char    row = 0;
		protected byte    column= -1;

		protected Chesspiece (char color, char name)
		{
			this.color = color;
			this.name = name;
		}

		public String toString ()
		{
			return "" + color + name;
		}

		public boolean isOnBoard ()
		{
			return Chessboard.this.isValidField (row, column);
		}

		public void moveTo (char row, byte column)throws NotValidFieldException
		{
			if (!Chessboard.this.isValidField (row, column))
				throw new NotValidFieldException ("bad field: " + row + column );

			this.row = row;
			this.column = column;

			int    r = row -FIRST_ROW;
			int    c = column -FIRST_COLUMN;
			Chessboard.this.fields[r][c].put (this);
		}

		public void moveOut ()
		{
			if(this.isOnBoard())
			{
				int r = row - FIRST_ROW;
				int c = column - FIRST_COLUMN;
				Chessboard.this.fields[r][c].take();
			}
			//Sätter till något värde utanför bärdet för att de ska tas bort från brädet.
			this.row = 0;
			this.column = -1;
		}

		public abstract void markReachableFields ();

		public abstract void unmarkReachableFields ();
	}


	public class Pawn extends Chesspiece
	{
		public Pawn (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields ()
		{
			//Check if pawn can move forward one square
			byte    col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
		}

		public void unmarkReachableFields ()
		{
			//Check if pawn can move forward one square
			byte    col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
	}


	public class Rook extends Chesspiece
	{
		public Rook (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields ()
		{
				for(int i = 0; i < 7; i++)
				{
					//Check where Rook can move forward
					byte    col = (byte) (column + (i + 1));
					if (Chessboard.this.isValidField (row, col))
					{
						int    r = row -FIRST_ROW;
						int    c = col -FIRST_COLUMN;
						Chessboard.this.fields[r][c].mark ();
					}
					//Check where Rook can move backwards
					col = (byte) (column - (i + 1));
					if (Chessboard.this.isValidField (row, col))
					{
						int    r = row -FIRST_ROW;
						int    c = col -FIRST_COLUMN;
						Chessboard.this.fields[r][c].mark ();
					}
					//Checker where Rook can move to the right
					byte	ro = (byte) (((byte)row) + (i + 1));
					if (Chessboard.this.isValidField ((char)ro,column))
					{
						int    r = ro -FIRST_ROW;
						int    c = column -FIRST_COLUMN;
						Chessboard.this.fields[r][c].mark ();
					}
					//Checker where Rook can move to the left
					ro = (byte) (((byte)row) - (i + 1));
					if (Chessboard.this.isValidField ((char)ro,column))
					{
						int    r = ro -FIRST_ROW;
						int    c = column -FIRST_COLUMN;
						Chessboard.this.fields[r][c].mark ();
					}
				}
			}

			public void unmarkReachableFields ()
			{
				for(int i = 0; i < 7; i++)
				{
					//Check where Rook can move forward
					byte    col = (byte) (column + (i + 1));
					if (Chessboard.this.isValidField (row, col))
					{
						int    r = row -FIRST_ROW;
						int    c = col -FIRST_COLUMN;
						Chessboard.this.fields[r][c].unmark ();
					}
					//Check where Rook can move backwards
					col = (byte) (column - (i + 1));
					if (Chessboard.this.isValidField (row, col))
					{
						int    r = row -FIRST_ROW;
						int    c = col -FIRST_COLUMN;
						Chessboard.this.fields[r][c].unmark ();
					}
					//Checker where Rook can move to the right
					byte	ro = (byte) (((byte)row) + (i + 1));
					if (Chessboard.this.isValidField ((char)ro,column))
					{
						int    r = ro -FIRST_ROW;
						int    c = column -FIRST_COLUMN;
						Chessboard.this.fields[r][c].unmark ();
					}
					//Checker where Rook can move to the left
					ro = (byte) (((byte)row) - (i + 1));
					if (Chessboard.this.isValidField ((char)ro,column))
					{
						int    r = ro -FIRST_ROW;
						int    c = column -FIRST_COLUMN;
						Chessboard.this.fields[r][c].unmark ();
					}
				}
		}
	}

	public class Knight extends Chesspiece
	{
		public Knight (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields()
		{
			//Checker if Knight can move up to the left
			byte col = (byte) (column + 2);
			byte ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move 1 up 2 to the left
			col = (byte) (column + 1);
			ro = (byte)(row - 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move up to the rigth
			col = (byte) (column + 2);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move 1 up 2 to the right
			col = (byte) (column + 1);
			ro = (byte)(row + 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move down to the left
			col = (byte) (column - 2);
		 	ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move down 1 to the left 2
			col = (byte) (column - 1);
			ro = (byte)(row - 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move down to the rigth
			col = (byte) (column - 2);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Checker if Knight can move down 1 to the right 2
			col = (byte) (column - 1);
			ro = (byte)(row + 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
    	}

    	public void unmarkReachableFields()
    	{
			//Checker if Knight can move up to the left
			byte col = (byte) (column + 2);
			byte ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move 1 up 2 to the left
			col = (byte) (column + 1);
			ro = (byte)(row - 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move up to the rigth
			col = (byte) (column + 2);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move 1 up 2 to the right
			col = (byte) (column + 1);
			ro = (byte)(row + 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move down to the left
			col = (byte) (column - 2);
			ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move down 1 to the left 2
			col = (byte) (column - 1);
			ro = (byte)(row - 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move down to the rigth
			col = (byte) (column - 2);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Checker if Knight can move down 1 to the right 2
			col = (byte) (column - 1);
			ro = (byte)(row + 2);
			if (Chessboard.this.isValidField ((char)ro,col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
	}

	public class Bishop extends Chesspiece
	{
		public Bishop (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields()
		{
			for(int i = 0; i < 7; i++)
			{
				//Check where Bishop can move forward to the left
				byte col = (byte) (column + (i + 1));
				byte ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Bishop can move forward to the right
				col = (byte) (column + (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Bishop can move backwards to the left
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Bishop can move backwards to the right
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}
		}
		public void unmarkReachableFields()
		{
			for(int i = 0; i < 7; i++)
			{
				//Check where Bishop can move forward to the left
				byte col = (byte) (column + (i + 1));
				byte ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Bishop can move forward to the right
				col = (byte) (column + (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Bishop can move backwards to the left
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Bishop can move backwards to the right
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}
		}
	}

	public class Queen extends Chesspiece
	{
		public Queen (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields()
		{
			for(int i = 0; i < 7; i++)
			{
				//Check where Rook can move forward
				byte    col = (byte) (column + (i + 1));
				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Rook can move backwards
				col = (byte) (column - (i + 1));
				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Checker where Rook can move to the right
				byte	ro = (byte) (((byte)row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro,column))
				{
					int    r = ro -FIRST_ROW;
					int    c = column -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Checker where Rook can move to the left
				ro = (byte) (((byte)row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro,column))
				{
					int    r = ro -FIRST_ROW;
					int    c = column -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}

			for(int i = 0; i < 7; i++)
			{
				//Check where Bishop can move forward to the left
				byte col = (byte) (column + (i + 1));
				byte ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Bishop can move forward to the right
				col = (byte) (column + (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Bishop can move backwards to the left
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				//Check where Bishop can move backwards to the right
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}

		}

		public void unmarkReachableFields()
		{
			for(int i = 0; i < 7; i++)
			{
				//Check where Rook can move forward
				byte    col = (byte) (column + (i + 1));
				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Rook can move backwards
				col = (byte) (column - (i + 1));
				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Checker where Rook can move to the right
				byte	ro = (byte) (((byte)row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro,column))
				{
					int    r = ro -FIRST_ROW;
					int    c = column -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Checker where Rook can move to the left
				ro = (byte) (((byte)row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro,column))
				{
					int    r = ro -FIRST_ROW;
					int    c = column -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}

			for(int i = 0; i < 7; i++)
			{
				//Check where Bishop can move forward to the left
				byte col = (byte) (column + (i + 1));
				byte ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Bishop can move forward to the right
				col = (byte) (column + (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Bishop can move backwards to the left
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) - (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				//Check where Bishop can move backwards to the right
				col = (byte) (column - (i + 1));
				ro = (byte) (((byte) row) + (i + 1));
				if (Chessboard.this.isValidField ((char)ro, col))
				{
					int    r = ro -FIRST_ROW;
					int    c = col -FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}
		}
	}

	public class King extends Chesspiece
	{
		public King (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields()
		{
			//Check if King can move forward one square
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move backwards one square
			col = (byte) (column - 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move forward to the left one square
			col = (byte) (column + 1);
			byte ro = (byte) (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move forward to the right one square
			col = (byte) (column + 1);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move backwards to the left one square
			col = (byte) (column - 1);
			ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move backwards to the rigth one square
			col = (byte) (column - 1);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move to the right one square
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro, column))
			{
				int    r = ro -FIRST_ROW;
				int    c = column -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
			//Check if King can move to the left one square
			ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro, column))
			{
				int    r = ro -FIRST_ROW;
				int    c = column -FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
		}
		public void unmarkReachableFields()
		{
			//Check if King can move forward one square
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move backwards one square
			col = (byte) (column - 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move forward to the left one square
			col = (byte) (column + 1);
			byte ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move forward to the right one square
			col = (byte) (column + 1);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move backwards to the left one square
			col = (byte) (column - 1);
			ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move backwards to the rigth one square
			col = (byte) (column - 1);
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro, col))
			{
				int    r = ro -FIRST_ROW;
				int    c = col -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move to the right one square
			ro = (byte)(row + 1);
			if (Chessboard.this.isValidField ((char)ro, column))
			{
				int    r = ro -FIRST_ROW;
				int    c = column -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
			//Check if King can move to the left one square
			ro = (byte)(row - 1);
			if (Chessboard.this.isValidField ((char)ro, column))
			{
				int    r = ro -FIRST_ROW;
				int    c = column -FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
	}
	public class NotValidFieldException extends RuntimeException
	{
		public NotValidFieldException(String message)
		{
			super(message);
		}
	}
}





































