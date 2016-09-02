import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
public class GradeCalculator {
	private int[][] gradesMatrix = { 
		{90, 100, 95, 80, 80, 90, 90, 100, 90, 87, 85, 2}, 
		{100, 90, 98, 100, 89, 92, 90, 100, 80, 90, 92, 5}, 
		{95, 86, 80, 95, 85, 80, 60, 65, 70, 76, 62, 2}, 
		{70, 50, 85, 70, 60, 72, 70, 80, 100, 68, 71, 0}, 
		{60, 47, 50, 60, 75, 60, 80, 70, 70, 45, 58, 3}, 
		{100, 100, 100, 100, 100, 95, 90, 100, 90, 96, 89, 5} 
	}; //contains all grades of all students
    
	private int[] letterCounts = {0, 0, 0, 0, 0};  // contains the total count of grades for each letter
	private char[] letterGrades = new char[gradesMatrix.length]; // contains the final letter grade of each student
	private double[] numberGrades = new double[gradesMatrix.length]; 
  
  
	void letterGrades() { //computes the letter grade from the number grade using cutoffs
		for (int i = 0; i < numberGrades.length; i ++ ) {
			if ( numberGrades[i] > 89.5) {
				letterGrades[i] = 'A';
				letterCounts[0] ++;
			} else if ((numberGrades[i] > 80.5) && (numberGrades[i] <= 89.5)) {
				letterGrades[i] = 'B';
				letterCounts[1] ++;
			} else if ((numberGrades[i] > 69.5) && (numberGrades[i] < 80.5)) {
				letterGrades[i] = 'C';
				letterCounts[2] ++;
			} else if ((numberGrades[i] > 60.5) && (numberGrades[i] < 69.5)) {
				letterGrades[i] = 'D';
				letterCounts[3] ++;
			} else if (numberGrades[i] < 60.5) {
				letterGrades[i] = 'F';
				letterCounts[4] ++;
			}
		}
  
	}
	double maxGrade() { //computes the highest grade of all students
		double max = 0;
		for (int i = 0; i < numberGrades.length; i++) {
			if (numberGrades[i] > max) {
				max = numberGrades[i];
			}
		}
		return max;
	}
	double meanGrade() { // computes the average of all final grades
		double avg = 0;
		for ( int i = 0; i < numberGrades.length; i++ ) {
			avg = avg + numberGrades[i];
		}
		avg = (avg / numberGrades.length);
		return avg;
	}
	double minGrade() { //computes the lowest grade of all students
		double min = 100;
		for (int i = 0; i < numberGrades.length; i++) {
			if (numberGrades[i] < min)
				min = numberGrades[i];
		}
		return min;
	}
	void totalGrades() { // compute the number grade for each student using everything
		for (int i = 0; i < gradesMatrix.length; i++) {
			double labs = 0;
			double projects = 0;
			double quize = 0;
			double exam = 0;
			double extra = 0;
			double overall = 0;
			for (int j = 0; j < 3; j++) {
				labs = labs + gradesMatrix[i][j];
			}
			labs = labs / 3;
		//labs = Math.round(labs *100)/100;
			for (int j = 3; j < 6; j++) {
				projects = projects + gradesMatrix[i][j];
			}
			projects = projects / 3;
			//projects = Math.round(projects *100)/100;
			for (int j = 6; j < 9; j++) {
				quize = quize + gradesMatrix[i][j];
			}
			quize = quize / 3;
			//quize = Math.round(quize *100)/100;
			for (int j = 9; j < 11; j++) {
				exam = exam + gradesMatrix[i][j];
			}
			exam = exam / 2;
			//exam = Math.round(exam *100)/100;
			for (int j = 11; j < 12; j++) {
				extra = extra + gradesMatrix[i][j];
			}
			overall = (labs * .2) + (projects * .3) + (quize * .05) + (exam * .45) + extra;
			//overall = Math.round(overall *100)/100;
			numberGrades[i] = overall;
   
		}
	}
	void print(String max, String min, String mean) {
		String gradeOut = "";
		for (int i = 0; i < numberGrades.length; i++) {
			gradeOut = String.format("%.2f" , numberGrades[i]);
			System.out.println("Student" + (i + 1) + "\t\t" + gradeOut + "\t" + letterGrades[i]);
		}
		System.out.println("\n\n");
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
		System.out.println("Mean: " + mean);
	}
	String star(int location) {
		String stars = "";
		for (int i = 0; i < letterCounts[location]; i++) {
			stars += '*';
		}
		return stars;
	}
	public static void main(String[] args)	{
		GradeCalculator calculator = new GradeCalculator();
		double max = 0;
		String gradeMax = "";
		double min = 0;
		String gradeMin = "";
		double average = 0;
		String gradeAverage = "";
		calculator.totalGrades();
		max = calculator.maxGrade();
		gradeMax = String.format("%.2f", max);
		min = calculator.minGrade();
		gradeMin = String.format("%.2f", min);
		average = calculator.meanGrade();
		gradeAverage = String.format("%.2f", average);
		calculator.letterGrades();
		calculator.print(gradeMax, gradeMin, gradeAverage);
		String output = "";
		output = "A  " + calculator.star(0) + "\nB  " + calculator.star(1) + "\nC  " + calculator.star(2) + "\nD  ";
		output += calculator.star(2) + "\nF  " + calculator.star(4);
		output += "\nMin: " + gradeMin + "\nMax: " + gradeMax + "\nMean: " + gradeAverage;
		JOptionPane.showMessageDialog(null, output, "GradeCalculator", JOptionPane.INFORMATION_MESSAGE);
	}
}