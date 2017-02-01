package linkStateAlgo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Interface {
	protected static int srcRouter;
	static linkState linkobj = new linkState();
	static ConnectionTable connObj = new ConnectionTable();

	public static void main(String[] args) throws IOException, Exception {
		//first step to call the menu!
		menuOptions();

	}

	public static void menuOptions() throws IOException, Exception {
		// giving the options to the user!!
		String[] options = { "Create a Network Topology", "Build a Connection Table",
				"Shortest path to Destination Router", "Modify a topology", "Exit" };
		// initializing the matrix to null
		int matrix[][] = null;

		int exit = 0;

		System.out.println("**************** Link State Routing Simulator ***************");
		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + " - " + options[i]);
		}
		System.out.println("*****************************************************");
		System.out.println("Enter your choice: ");
		Scanner keyboard = new Scanner(System.in);
		int enterNo = keyboard.nextInt();
		// the options will get printed until the user enters exit or something
		// that he does not understand!!

		do {

			switch (enterNo) {
			case 1:
				matrix = getMatrix();
				displayMatrix(matrix);
				menuOptions();
				break;

			case 2:
				matrix = getMatrix();
				connectionTable(matrix);
				menuOptions();
				break;

			case 3:
				matrix = getMatrix();
				shortestPath(matrix);
				menuOptions();
				break;

			case 4:
				matrix = getMatrix();
				removeRouter(matrix);
				break;

			case 5:
				exit = 1;
				System.out.println("Good Bye!!");
				System.exit(0);

			default:
				System.out.println("I'm sorry, but I didn't understand that.");
				System.out.println();
				menuOptions();
				break;
			}
		} while (exit != 1);
	}

	// to read the matrix from a given file
	// the file should be in the same directory!!!
	public static int[][] getMatrix() throws IOException, Exception {

		int row = 0;
		int column = 0;
		String curLine[] = null;
		BufferedReader br = null;

		// Enter the file name that is in the directory!!
		System.out.println("Enter file name: ");
		Scanner sc = new Scanner(System.in);
		String file = sc.next();

		try {

			br = new BufferedReader(new FileReader(file));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				row++;
				curLine = currentLine.split("\\s");
				column = curLine.length;

			}
			int matrix[][] = new int[row][column];
			br.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("File was not found!Please Enter the correct file name!!");
			main(null);
			System.out.println();
		} catch (IOException ioe) {
			System.out.println("Some thing wrong!!!!.");
			main(null);
			System.out.println();
		}

		return storeMatrix(file, row, column);
	}

	
	
	//to store the matrix that has been read by the file 
	public static int[][] storeMatrix(String txtFile, int row, int column) throws IOException {

		int tempRow = 0;
		String newCurrentLine;
		BufferedReader br1 = null;
		int matrix[][] = new int[row][column];

		br1 = new BufferedReader(new FileReader(txtFile));

		while ((newCurrentLine = br1.readLine()) != null) {
			String newLine[] = newCurrentLine.split("\\s");

			for (int i = 0; i < column; i++) {
				// converting string in file to integer!!!!
				matrix[tempRow][i] = Integer.parseInt(newLine[i]);
			}
			tempRow++;
		}

		br1.close();

		return matrix;

	}

	
		//to display the matrix : will be called in case1 : choice 1
	public static void displayMatrix(int matrix[][]) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
//to display the connection table of which ever router the user specifies!!
	public static void connectionTable(int matrix[][]) {

		System.out.println("Enter router for its connection table: ");
		Scanner sc = new Scanner(System.in);
		srcRouter = sc.nextInt();

		int mLen = matrix.length;
		//displaying the connection table for the source router
		Object arry[][] = new Object[1][2];
		System.out.println("Router " + srcRouter + " Connection Table");
		System.out.println("Destination |  Interface");

		System.out.println("-------------------------");
		System.out.println("Router " + srcRouter + "    |   - ");
		try {
			for (int i = 0; i < mLen; i++) {
				if (i == (srcRouter)) {
					i++;
				}

				arry[0][0] = "Router " + (i);

				arry[0][1] = connObj.connection_Table(matrix, srcRouter, i);
				conTableDisplay(arry);

			}

			System.out.println("");

		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}

	public static void conTableDisplay(Object arry[][]) {
		for (int i = 0; i < arry.length; i++) {
			for (int j = 0; j < arry[0].length; j++) {
				System.out.print(arry[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void shortestPath(int matrix[][]) throws IOException, Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the source to find the shortest path");
		int src = sc.nextInt();
		System.out.println("enter the destination");
		int des = sc.nextInt();
		linkobj.dAlogorithm(matrix, src, des, 3);
	}

	public static void removeRouter(int[][] matrix) throws IOException {

		System.out.println("Enter the router to be removed");
		Scanner s = new Scanner(System.in);
		int Router = s.nextInt();

		try {
			int deletenode = Router;
			int row = matrix.length;
			int column = matrix.length;
			int Nmatrix[][] = new int[row][column];

			int RemoveRow = Router;
			int RemoveColumn = Router;
			int NRow = 0;
			for (int i = 0; i < row; ++i) {
				int newCol = 0;
				for (int j = 0; j < column; ++j) {
					if (i == RemoveRow || j == RemoveColumn)
						Nmatrix[NRow][newCol] = -1;
					else
						Nmatrix[NRow][newCol] = matrix[i][j];
					++newCol;
				}

				++NRow;
			}
			System.out.println("Updated Matrix :");
			displayMatrix(Nmatrix);
			for (int i = 0; i < column; ++i) {
				for (int j = 0; j < column; ++j) {
					matrix[i][j] = Nmatrix[i][j];
				}
			}
			//to check we run the shortest path function
			//can run connection table as well
			shortestPath(matrix);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
