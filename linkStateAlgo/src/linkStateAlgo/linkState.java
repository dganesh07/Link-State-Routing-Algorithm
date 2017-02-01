package linkStateAlgo;

import java.util.*;
import java.util.Scanner;

public class linkState {

	public Object dAlogorithm(int[][] matrix, int src, int des, int choice) {

		// distance matrix : for storing the shortest distance to all the
		// routers!
		int[] nodeDistance = new int[matrix[0].length];
		int[] nodeVisited = new int[matrix[0].length];
		int NextNode = 0;
		int min = 0;
		// path array: to get the shortest path!
		int path[] = new int[matrix[0].length];
		int len = matrix[0].length;

		// Initializing the visited array and the path array to 0;
		for (int i = 0; i < len; i++) {
			nodeVisited[i] = 0;
			path[i] = 0;
		}
		// make the distance of the source 0 and make it as visited!!
		nodeDistance = matrix[0].clone();
		// to update the nodeDistance matrix
		for (int i = 0; i < len; i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j < len; j++) {
				if (nodeVisited[j] != 1 && nodeDistance[j] < min) {
					min = nodeDistance[j];
					NextNode = j;
				}
			}
			nodeVisited[NextNode] = 1;

			for (int d = 0; d < len; d++) {
				if (nodeVisited[d] != 1) {
					if (min + matrix[NextNode][d] < nodeDistance[d]) {
						nodeDistance[d] = min + matrix[NextNode][d];
						path[d] = NextNode;
					}
				}
			}
		} // main for loop close!!

		// shortest path use your code
		if (choice == 3) {
			System.out.print("The shortest path from router " + (src) + " to router " + (des) + " is: ");
			displayShortestPath(path, src, des, nodeDistance.length);
			System.out.println();
			return nodeDistance[des];

		}
		// if any other choice then return null
		else
			return null;

	}

	// to display the shortest path that is got from the path array used to
	// record the path in the algorithm!!
	public static void displayShortestPath(int[] path, int src, int des, int len) {

		for (int i = 0; i < len; i++) {
			int j;

			if (i == des) {
				System.out.print("path =" + i);
				j = i;
				do {
					j = path[j];
					System.out.print("<-" + " " + j);

				} while (j != 0);
			}

		}
		System.out.println(" ");

	}

}
