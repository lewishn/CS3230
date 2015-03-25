import java.io.*;
import java.util.*;

public class HamiltonCycle {
	private static Scanner sc;
	private static ArrayList<String> path;
	private static HashSet<String> edges;
	private static TreeSet<String> reducedEdges;
	private static int N, M, p;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			reducedEdges = new TreeSet<String>();
			//doHamilton();
			doMapping();
		}
		sc.close();
	}
	
	public static class Pair implements Comparable<Pair> {
		public String a, b;
		
		public Pair(String x, String y) {
			a = x;
			b = y;
		}
		
		public int compareTo(Pair other) {
			if (a.equals(other.a)) {
				return b.compareTo(other.b);
			}
			else {
				return a.compareTo(other.a);
			}
		}
	}
	
	public static void doMapping() {
		ArrayList<Pair> edgy = new ArrayList<Pair>();
		for (int i = 0; i < M; i++) {
			//edgy.add(new Pair(sc.next(), sc.next()));
			//System.out.println(edgy.get(i));
			reduceMap(sc.next(), sc.next());
		}

		//PrintWriter p = new PrintWriter(new OutputStreamWriter(System.out));
		String x = N * 3 + " " + reducedEdges.size();
		System.out.println(x);
		//Iterator<String> i = reducedEdges.iterator();
		for (String i : reducedEdges) {
			System.out.println(i);
		}
		//p.close();
	}

	
	public static void doHamilton() {
		edges = new HashSet<String>();
		for (int j = 0; j < M; j++) {
			String u = sc.next();
			String v = sc.next();
			edges.add(u + " " + v);
		}
		
		p = sc.nextInt();
		path = new ArrayList<String>(p + 10);
		for (int k = 0; k < p; k++) {
			path.add(sc.next());
		}
		
		System.out.println(isHamiltonCycle() ? "YES" : "NO");
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
	
	// directed a->b to (ha-a, ha-tb, tb-b)
	public static void reduceMap(String a, String b) {
		reducedEdges.add("T".concat(a.substring(1)).concat(" ").concat(a));
		reducedEdges.add("H".concat(a.substring(1)).concat(" ").concat(a));
		reducedEdges.add("H".concat(a.substring(1)).concat(" T").concat(b.substring(1)));
		reducedEdges.add("T".concat(b.substring(1)).concat(" ").concat(b));
		reducedEdges.add("H".concat(b.substring(1)).concat(" ").concat(b));
		
	}
}
