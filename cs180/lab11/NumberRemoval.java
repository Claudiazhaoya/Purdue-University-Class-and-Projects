public class NumberRemoval{
	static String out="";
	static String remove(String input){
		if(input==null||input.isEmpty()){
			return out;
		}else{
			if(input.substring(0,1).matches("[0-9]")){
			}
			else{
				out+=input.charAt(0);
			}
			remove(input.substring(1,input.length()));
		}
		return out;
	}
	public static void main(String[] args){
		NumberRemoval test = new NumberRemoval();
		String output = test.remove("this0is1a2test3");
		System.out.println(output);
	}
}
