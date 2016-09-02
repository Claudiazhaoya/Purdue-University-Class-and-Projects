import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
public class InterestCalculatorGUI{
	public static void main(String[] args){
		boolean calculate = true;
	while(calculate == true){
		int rateUp = 10;
		int rateDown = 5;
		String result;
		int type;
		String ammount;
		String years;
		double Balance=0;
		int choice;
		JOptionPane.showMessageDialog(null, "Welcome to Interest Calculator!", "Interest Calculator", JOptionPane.INFORMATION_MESSAGE);
		result = JOptionPane.showInputDialog(null,"Please enter your name, then press OK","Name",JOptionPane.QUESTION_MESSAGE);
			//TODO 2
		String[] selections = {"Simple Interest", "Compound Interest"};
		type = JOptionPane.showOptionDialog(null,"What would you like to calculate","Choose a type",0,JOptionPane.QUESTION_MESSAGE,null,selections,"Interest");
			//TODO 3
		ammount = JOptionPane.showInputDialog(null,"Please ente principal ammount","Enter Principal ammount",JOptionPane.QUESTION_MESSAGE);
		while(!ammount.matches(".*\\%d.*")){
			JOptionPane.showMessageDialog(null,"Please enter valid ammount","Invalid ammount",JOptionPane.ERROR_MESSAGE);
ammount = JOptionPane.showInputDialog(null,"Please ente principal ammount","Enter Principal ammount",JOptionPane.QUESTION_MESSAGE);

		}

	int money = Integer.parseInt(ammount);
	if(money<0){
		JOptionPane.showMessageDialog(null,"Please enter valid ammount","Invalid ammount",JOptionPane.ERROR_MESSAGE);
		ammount = JOptionPane.showInputDialog(null,"Please ente principal ammount","Enter Principal ammount",JOptionPane.QUESTION_MESSAGE);
	}

			String[] Halls = {"1","2","3","4","5"};
		years = (String) JOptionPane.showInputDialog(null,"Select numbe of years from the drop down menu","Select years",JOptionPane.QUESTION_MESSAGE,null,Halls,Halls[0]);
		
				int rate = 0;
		int time = Integer.parseInt(years);

		if(money >=5000){
			rate = 10;
		}else if(money< 5000){
			rate = 5;
		}
		String type2 = "";
		if(type ==0){
			System.out.println(money);
			System.out.println(rate);
			System.out.println(time);
			double time2 = (double) time;
			double rate2 = (double) rate/100;
			System.out.println(rate2);
			double temp = (1+rate2 *time2);

			System.out.println(temp);
			Balance = (money * temp);
			System.out.println(Balance);
			type2 = "Simple Interest";
			}
		else if(type==1){
			double time2 = (double) time;
			double rate2 = (double) rate/100;

			System.out.println("TEST");
			Balance = (money*(Math.pow(1+rate2,time2)));
			System.out.println(Balance);
			type2 = "Compound Interest";
		}
		String output = "Name: " + result+"\n"+ "principal: "+ money+"\n"+"Rate applied: "+rate+"%\n"+"Time: "+time+"\n"+"Interest Type: "+ type2+"\n"+"Result: "+Balance; 
		JOptionPane.showMessageDialog(null, output , "Interset Calulator", JOptionPane.INFORMATION_MESSAGE);

		String[] options = {"Yes", "No"};
		choice = JOptionPane.showOptionDialog(null,"Would you like to preform another caluclation?","Are you done?",0,JOptionPane.QUESTION_MESSAGE,null,options,"option");
		if(choice == 1){
			calculate =false;
		}

	}
		

	}
}
