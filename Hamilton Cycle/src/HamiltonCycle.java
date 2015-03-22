import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class HamiltonCycle {
	private static Scanner sc;
	private static HashMap<String, HashSet<String>> edgeList;
	private static ArrayList<String> path;
	private static HashSet<String> edges;
	private static HashSet<String> vertices;
	private static int N, M, p;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			edges = new HashSet<String>();
			for (int j = 0; j < M; j++) {
				String u = sc.next();
				String v = sc.next();
				// insert into list
				edges.add(u + " " + v);
			}
			
			p = sc.nextInt();
			path = new ArrayList<String>(p + 10);
			for (int k = 0; k < p; k++) {
				path.add(sc.next());
			}
			
			System.out.println(isHamiltonCycle() ? "YES" : "NO");
		}
		sc.close();
	}
	
	public static boolean isHamiltonCycle() {
		HashSet<String> visits = new HashSet<String>(N + 10, 1);
		for (int i = 0; i < path.size() - 1; i++) {
			if (visits.contains(path.get(i))) {
				return false;
			}
			
			if (edges.contains(path.get(i) + " " + path.get(i + 1)) || edges.contains(path.get(i + 1) + " " + path.get(i))) {
				visits.add(path.get(i));
			} else {
				return false;
			}
		}
		return visits.size() == N  && path.get(0).equals(path.get(path.size() - 1));
	}
	
	/*
	// Returns true if the path is a Hamilton cycle
	public static boolean isHamiltonCycle() {
		HashSet<String> appear = new HashSet<String>(N+1, 1); 
		for (int i = 0; i < path.size() - 1; i++) {
			if(appear.contains(path.get(i))) {
				return false;
			}
			path.add(path.get(i));
			
			boolean temp = true;
			//check if path[i], path[i+1] exists
			if (edgeList.containsKey(path.get(i))) {
				if (!edgeList.get(path.get(i)).contains(path.get(i + 1))) {
					temp = false;
				}
			} else if (edgeList.containsKey(path.get(i + 1))) {
				if (!edgeList.get(path.get(i + 1)).contains(path.get(i))) {
					temp = false;
				}
			} else {
				return false;
			}
			
			if (temp == false) {
				return false;
			}
		}
		
		return appear.size() == N && path.get(0).equals(path.get(path.size() - 1)); 
	}
	*/
}
