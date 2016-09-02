import java.util.Scanner;
public class concep_2{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int count = 0;
		for (int i=1; i<=n;i++){
			for(int j=1;j<=i;j++){
				count++;
			}
		}
		System.out.println(count);
	}
}
