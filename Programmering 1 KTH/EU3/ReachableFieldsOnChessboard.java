import java.util.concurrent.TimeUnit;
import java.util.Random;

public class ReachableFieldsOnChessboard
{
	public static void main(String[] args) throws Exception
	{
		Chessboard    chessBoard = new Chessboard ();
		System.out.println (chessBoard + "\n");

		Chessboard.Chesspiece[]  pieces = new Chessboard.Chesspiece[6];
		pieces[0] = chessBoard.new Pawn ('w', 'P');
		pieces[1] = chessBoard.new Rook ('b', 'R');
		pieces[2] = chessBoard.new Queen ('w', 'Q');
		pieces[3] = chessBoard.new Bishop ('w', 'B');
		pieces[4] = chessBoard.new King ('b', 'K');
		pieces[5] = chessBoard.new Knight ('w', 'N');

		Random rand = new Random ();
		int number = rand.nextInt(Chessboard.NUMBER_OF_ROWS);
		char row = (char)(Chessboard.FIRST_ROW + number);
		number = rand.nextInt(Chessboard.NUMBER_OF_COLUMNS);
		byte column = (byte)(Chessboard.FIRST_COLUMN + number);

		for(int i = 0; i <= 5; i++)
		{
			pieces[i].moveTo(row, column);
			pieces[i].markReachableFields();
			System.out.println (chessBoard + "\n");
			TimeUnit.SECONDS.sleep(4);
			pieces[i].unmarkReachableFields();
			pieces[i].moveOut();
		}
	}
}