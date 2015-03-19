import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Karatsuba {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))); // use this (a much faster output routine) instead of Java System.out.println (slow)
        
        /*
        String X = "9484857578438355666555";
        String Y = "23445879438579345305843092";
        System.out.println(toString(karatsuba(toArray(Y), toArray(X))));
        */
        
        int T, B;
		String V, M; 
        T = sc.nextInt();
		
        for (int i = 1; i <= T; ++i) {
            B = sc.nextInt();
			sc.nextLine();
			V = sc.nextLine(); 
			M = sc.nextLine();
			
			// Insert solution here.
			String P = toString(karatsuba(toArray(V), toArray(M)));
						
			pw.write(trimZeros(P));
			pw.write("\n");
        }
        sc.close();
        pw.close(); // do not forget to use this
    }
	
	private static String trimZeros(String input) {
		int left = 0;
		int right = input.length()-1;
		int fp = input.indexOf('.');
		if (fp == -1) {
			fp = input.length();
		}
		
		while(left < fp-1) {
			if (input.charAt(left) != '0')
				break;
			left++;
		}
		
		if (left >= fp)
			return "0" + input.substring(left,right+1);
		return input.substring(left,right+1);
	}
		
	public static String toString(int[] arr) {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s = arr[i] + s;
		}
		return s;
	}
		
	public static int[] toArray(String s) {
		int[] ans = new int[s.length()];
		int j = 0;
		//least significant digits read first
		for (int i = s.length() - 1; i >= 0; i--) {
			ans[j] = s.charAt(i) - '0';
			j++;
		}
		return ans;
	}
	
	public static int[] karatsuba(int[] a, int[] b) {
		int N = (a.length < b.length) ? a.length : b.length;
		if (a.length < 100 || b.length < 100) {
			return multiply(a, b);
		}
		N = N / 2;
		int[] l1 = Arrays.copyOf(a, N);
		int[] l2 = Arrays.copyOf(b, N);
		int[] h1 = Arrays.copyOfRange(a, N, a.length);
		int[] h2 = Arrays.copyOfRange(b, N, b.length);
		
		
		int[] i = karatsuba(l1, l2);
		int[] j = karatsuba(add(l1, h1), add(l2, h2));
		int[] k = karatsuba(h1, h2);
		int[] x = shift(k, 2 * N);
		int[] y = shift(subtract(subtract(j, k), i), N);
		return add(x, add(y, i));
	}
	
	public static int[] multiply (int[] a, int[] b) {
		int asize = a.length;
		int bsize = b.length;
		int answer[] = new int[asize + bsize];
		int carry;
		int interim;
		
		for (int i = 0; i < bsize; i++) {
			carry = 0;
			int j = 0;
			for (; j < asize; j++) {
				int current = i + j;
				interim = carry + answer[current] + (a[j] * b[i]);
				carry = interim / 10;
				answer[current] = interim % 10;
			}
			if (carry > 0) {
				interim = carry + answer[i + j] ;
				carry = interim / 10;
				answer[i + j] = interim % 10;
			}
		}
		
		return answer;
	}
	
	public static int[] add(int[] a, int[] b) {
		int[] answer, min, max;
		if (a.length > b.length) {
			answer = new int[a.length + 1];
			min = b;
			max = a;
		} else {
			answer = new int[b.length + 1];
			min = a;
			max = b;
		}
		int carry = 0;
		int i = 0;
		for (; i < max.length; i++) {
			int temp = max[i] + carry;
			if (i < min.length) {
				temp += min[i];
			}
			if (temp > 9) {
				carry = 1;
				temp -= 10;
			} else {
				carry = 0;
			}
			answer[i] = temp;
		}
		if (carry != 0) {
			answer[i] = carry;
		}
		
		return answer;
	}

	public static int[] shift(int[] a, int N) {
		int[] answer = new int[a.length + N];
		System.arraycopy(a,  0,  answer,  N, a.length);
		return answer;
	}
	
	// a > b
	public static int[] subtract(int[] a, int[] b) {
		int[] answer = new int[a.length];
		int carry = 0;
		int i = 0;
		for (; i < a.length; i++) {
			int temp = a[i] - carry;
			if (i < b.length) {
				temp -= b[i];
			}
			if (temp < 0) {
				carry = 1;
				temp = 10 + temp;
			} else {
				carry = 0;
			}
			answer[i] = temp; 
		}		
		return answer;
	}
}
