import java.util.Scanner;

public class HamiltonCycle {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			for (int j = 0; j < M; j++) {
				String u = sc.next();
				String v = sc.next();
				// insert into list
				
			}
			int p = sc.nextInt();
			for (int k = 0; k < p; k++) {
				String vertex = sc.next();
				// insert to path
			}
			
			System.out.println(isHamiltonCycle() ? "YES" : "NO");
		}
		
	}
	
	public static boolean isHamiltonCycle() {
		return false;
	}
}
