package linkStateAlgo;

public class ConnectionTable {
	public Object connection_Table(int[][] matrix, int src, int des) {

		try {
			int srcindex = src;
			int k = 1;
			System.out.println();
			// Array to hold all the shortest path distances from source to
			// destination
			// using clone function to give a copy of the matrix to the distance
			// matrix
			int[] nodeDistance = matrix[src].clone();

			// to keep count of the visited node
			boolean[] nodeVisited = new boolean[matrix[0].length];

			// to track the path of the next nodes!!
			int[] path = new int[matrix[0].length];
			// hold the length of the matrix
			int matlen = matrix[0].length;

			// for loop that goes through all the rows and columns of the input
			// matrix
			for (int i = 0; i < path.length; i++) {
				// Initializing path array with the source
				path[i] = src;
			}

			nodeVisited[srcindex] = true;
			
			for(int z =0;z<matlen;z++){

				// Initializing min variable to a maximum value
				int min = Integer.MAX_VALUE;

				// Find the minimum element in the nodeDistance
				for (int v = 0; v < nodeDistance.length; v++) {

					if (!nodeVisited[v]  && v != srcindex  && nodeDistance[v] != -1) {

						if (nodeDistance[v] < min) {
							min = nodeDistance[v];
							//getting the next node
							srcindex = v;
						}
					}
				}

				// Initialize the nextnode of the minimum element in the visited
				nodeVisited[srcindex] = true;
				
				
				
				//to find the cost and record the path!!
				for (int i = 0; i < nodeDistance.length; i++) {

					if ((nodeDistance[i] == -1 && !nodeVisited[i]  && matrix[srcindex][i] != -1 )) {
						
						nodeDistance[i] = nodeDistance[srcindex] + matrix[srcindex][i];
						path[i] = srcindex;
					}

					else if ((matrix[srcindex][i] != -1 && nodeDistance[srcindex] + matrix[srcindex][i] < nodeDistance[i])) {
						nodeDistance[i] = nodeDistance[srcindex] + matrix[srcindex][i];
						path[i] = srcindex;

					}
				}

			}
		/*	for (int i = 0; i < matlen; i++) {
				System.out.println("||" + path[i]);

			}
			System.out.println("||");
			
		*/
		
			

			//will have the final path from which connection table can be deduced!!
			int[] Rtable = new int[matrix.length];
			Rtable[0] = des;
			int D = des;

			// to get the next hop
			while (path[D] != src) {
				D = path[D];
				Rtable[k] = D;
				k++;
			}

			Rtable[k] = src;
			int d=0;

			for (int j = k; j > 0; j--) {

				d++;
				if (d == 2)
					return "  Router " + (Rtable[j] + 1);
			}

			if (d == 1) {
				return "  direct Path";
			}

		
		//printing the path array!!
		for (int i = 0; i < matlen; i++) {
			System.out.println("|" + path[i]);

		}
		System.out.println("|");
		
		
		
		//for testing
		
		for (int i = 0; i < matlen; i++) {
			System.out.println("|" + nodeDistance[i]);

		}
		System.out.println("|");
		
		
		//for testing
		for (int i = 0; i < matlen; i++) {
			System.out.println("|" + nodeVisited[i]);

		}
		System.out.println("|");
		
		
		
		
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
		

	}

}
