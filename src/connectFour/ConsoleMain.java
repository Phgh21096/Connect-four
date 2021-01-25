package connectFour;

import java.util.InputMismatchException;

import java.util.Scanner;

import connect4.Board;
import connect4.Constants;
import connect4.MiniMaxAi;
import connect4.Move;

public class ConsoleMain {

	static final int NUMOFCOLUMNS = Constants.NUM_OF_COLUMNS;
	static final int INAROW = Constants.IN_A_ROW;

	public static void main(String[] args) {

		String validNumbers = "";
		for (int i = 0; i < NUMOFCOLUMNS; i++) {
			if (i < NUMOFCOLUMNS - 2) {
				validNumbers += i + 1 + ", ";
			} else if (i == NUMOFCOLUMNS - 2) {
				validNumbers += i + 1 + " or ";
			} else if (i == NUMOFCOLUMNS - 1) {
				validNumbers += i + 1;
			}
		}

		int XColumnPosition;
		int maxDepth = 3;
		MiniMaxAi OPlayer = new MiniMaxAi(maxDepth, Constants.P2);
		Board connect4Board = new Board();

		System.out.println("Minimax Connect-" + INAROW + "!\n");
		System.out.println("\n*****************************");
		Board.printBoard(connect4Board.getGameBoard());
		System.out.println();

		Scanner in = new Scanner(System.in);

		while (!connect4Board.checkForGameOver()) {
			switch (connect4Board.getLastPlayer()) {

				case Constants.P2:
					System.out.print("Human 'X' moves.");
					try {
						do {
							System.out.print("\nGive column (1-" + NUMOFCOLUMNS + "): ");
							XColumnPosition = in.nextInt();
						} while (connect4Board.checkFullColumn(XColumnPosition - 1));
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("\nValid numbers are: " + validNumbers + ".\n");
						break;
					} catch (InputMismatchException e) {
						System.err.println("\nInput an integer number.");
						System.err.println("\nValid numbers are: " + validNumbers + ".\n");
						break;
					}
					connect4Board.makeMove(XColumnPosition - 1, Constants.P1);
					System.out.println();
					break;

				case Constants.P1:
					System.out.println("AI 'O' moves.");

					Move OMove = OPlayer.miniMax(connect4Board);

					connect4Board.makeMove(OMove.getColumn(), Constants.P2);
					System.out.println();
					break;

				default:
					break;
			}
			System.out.println("Turn: " + connect4Board.getTurn());
			Board.printBoard(connect4Board.getGameBoard());
		}
		in.close();

		System.out.println();

		if (connect4Board.getWinner() == Constants.P1) {
			System.out.println("Human player 'X' wins!");
		} else if (connect4Board.getWinner() == Constants.P2) {
			System.out.println("AI computer 'O' wins!");
		} else {
			System.out.println("It's a draw!");
		}

		System.out.println("Game over.");

	}

}
