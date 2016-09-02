public class Duplicate{
	String temp="";
	public String dup(String input){
		if(!input.matches("[a-zA-Z0-9]+")){
			return "";
		}else{
			temp+=input.charAt(0);
			temp+=input.charAt(0);
			dup(input.substring(1,input.length()));
		}
		return temp;

	}
	public static void main(String[] args){
		Duplicate test = new Duplicate();
		String output = test.dup("java");
		System.out.println(output);
	}
}
