public class Pattern{
	public static void main(String[] args){
		int num = 1;
		for(int i = 5;i>0;i--){
			for(int j=i-1;j>0;j--){
				System.out.print("#");
			}
			System.out.print(num);
			for(int k = i; k<5;k++){
				System.out.print("#");
			}
			System.out.println();
			num = num+2;
		}
	}
}
