import java.util.concurrent.TimeUnit;
import java.util.Random;

public class ChessboardTest  //I created "the super chess piece that has the property of all the chess pieces :)
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

		pieces[0].moveTo(row, column);
		pieces[0].markReachableFields();
		pieces[1].moveTo(row, column);
		pieces[1].markReachableFields();
		pieces[2].moveTo(row, column);
		pieces[2].markReachableFields();
		pieces[3].moveTo(row, column);
		pieces[3].markReachableFields();
		pieces[4].moveTo(row, column);
		pieces[4].markReachableFields();
		pieces[5].moveTo(row, column);
		pieces[5].markReachableFields();
		System.out.println (chessBoard + "\n");
		TimeUnit.SECONDS.sleep(5);
		pieces[0].unmarkReachableFields();
		pieces[0].moveOut();
		pieces[1].unmarkReachableFields();
		pieces[1].moveOut();
		pieces[2].unmarkReachableFields();
		pieces[2].moveOut();
		pieces[3].unmarkReachableFields();
		pieces[3].moveOut();
		pieces[4].unmarkReachableFields();
		pieces[4].moveOut();
		pieces[5].unmarkReachableFields();
		pieces[5].moveOut();

	}
}