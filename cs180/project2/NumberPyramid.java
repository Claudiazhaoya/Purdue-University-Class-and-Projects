import java.util.Scanner;
public class NumberPyramid {
	public static void main(String[] args) {
		NumberPyramid start = new NumberPyramid();
		Scanner in = new Scanner(System.in);
		int size = 0;
		while ( size <= 0) {
			System.out.println("Enter size of triangle [integer >0]:");
			size = in.nextInt();
		}
//printing
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i);
			}
			System.out.println();
		}
		for (int i = (size - 1); i >= 1; i--) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
