import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
public class CollegeFeeCalculator{
	public static void main(String[] args){
		int InStateFull = 4996;
		int OutStateFull = (4996 + 9401);
		int InternationalFull = (9401 + 1000 + 4996);
		int InStatePart = 350;
		int OutStatePart = (600 + 350);
		int InternationalPart = (350 + 600 + 70);
		int Earhart = 4745;
		int Hillenbrand = 5307;
		int Owen = 4130;
		int Windsor = 4150;

		String result;
		int time;
		String hours;
		String resident;
		String Hall;
		String House;
			//TODO 0
		JOptionPane.showMessageDialog(null, "Welcome to CollegeFeeCalculator!","CollegeFeeCalator",JOptionPane.INFORMATION_MESSAGE);
			//TODO 1
		result = JOptionPane.showInputDialog(null,"Please enter your name, then press OK","Name",JOptionPane.QUESTION_MESSAGE);
			//TODO 2
		String[] selections = {"Part-Time", "Full-Time"};
		time = JOptionPane.showOptionDialog(null,"Please select your enrollment","Enrollment",0,JOptionPane.QUESTION_MESSAGE,null,selections,"PHP");
			//TODO 3
		hours = JOptionPane.showInputDialog(null,"Please enter the no. of credit hours,then press OK","Credit Hours",JOptionPane.QUESTION_MESSAGE);
		System.out.println(time);
	System.out.println(hours);
		if((time==0&&hours>="8")||(time==1&&hours<="8")){
			//TODO 4
		JOptionPane.showMessageDialog(null,"Please enter valid credit hours for the current enrollment","Invalid no. of credits",JOptionPane.ERROR_MESSAGE);
		}
			//TODO 5
		String[] Drop = {"In-state", "Out-of-state","International"};
		resident = (String) JOptionPane.showInputDialog(null,"Please selcet the appropriate residency","Residency",JOptionPane.QUESTION_MESSAGE,null,Drop,Drop[0]);
			//TODO 6
		String[] Housing = {"ON-Campus","OFF-Campus"};
		House = (String) JOptionPane.showInputDialog(null,"Please select your housing","Housing",JOptionPane.QUESTION_MESSAGE,null,Housing,Housing[0]);
			//TODO 7
		String[] Halls = {"Earhart","Hillenbrand","Owen","Windsor"};
		Hall = (String) JOptionPane.showInputDialog(null,"Please select the residence hall","Residence-Hall",JOptionPane.QUESTION_MESSAGE,null,Halls,Halls[0]);


	}
}
