package edu.purdue.YL.lab; //Don't make any changes to this

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
/**
 * @author pujari (Sahil Pujari)
 */
public class MainActivity extends Activity {
 
        /**
         * Called when the activity is first created.
         */
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //Set the activity content from a layout resource
                setContentView(R.layout.fragment_main);
                /**
                 * submitB
                 *              refers to the "Submit" button on the app screen
                 * resetB
                 *              refers to the "Reset" button on the app screen
                 * principleBox
                 *              refers to the text field where the user can enter the principle amount
                 * rateBox
                 *              refers to the text field where the user can enter the rate amount
                 * yearsBox
                 *              refers to the text field where the user can enter the number of years
                 * resultBox
                 *              refers to the final result text displayed after submitting data
                 */
                Button submitB = (Button) findViewById(R.id.submitButton);
                Button resetB = (Button) findViewById(R.id.resetButton);
                final EditText principleBox = (EditText) findViewById(R.id.ent_p);
                final EditText rateBox = (EditText) findViewById(R.id.ent_r);
                final EditText yearsBox = (EditText) findViewById(R.id.ent_y);
                final TextView resultBox = (TextView) findViewById(R.id.result);
                //Defines a particular action to be invoked when the reset button is pressed
                resetB.setOnClickListener(new View.OnClickListener() {
                        //Executes everything that goes inside this body
                        public void onClick(View v) {
                                //TODO 1: Reset the text inside the EditText fields of principle, rate and years
                                        //using the setText() method
                        //Example : principleBox.setText("");
			principleBox.setText("");
			rateBox.setText("");
			yearsBox.setText("");
			resultBox.setText("");
                        }
                });
                //Defines a particular action to be invoked when the submit button is pressed
                submitB.setOnClickListener(new View.OnClickListener() {
                        //Executes everything that goes inside this body
                        public void onClick(View v) {
                                //TODO 2: Retrieve the data entered by the user in the EditText fields
                                //Use getText() method to do so. Also cast it to string using toString() method of the
                                //String class
                        //Example : String principal = principleBox.getText().toString();
			String principal = principleBox.getText().toString();
			String rate = rateBox.getText().toString();
			String years = yearsBox.getText().toString();
			double princ=0;
			double rates=0;
			double year=0;
			double balance = 0;
			String output;

 
                                //TODO 3: Checks if the data entered is in only number format in all three fields
			if((!principal.matches("[0-9]+"))||(!rate.matches("[0-9]+"))||(!years.matches("[0-9]+"))){
				resultBox.setText("Please enter valid input");
			}else{
				
			princ =  Double.parseDouble(principal);
			rates =  Double.parseDouble(rate);
			year =  Double.parseDouble(years);
			
			balance = (princ * Math.pow((1+rates/100),year));
			//output = String.valueOf(balance);
			output = String.format("%.2f",balance);
			resultBox.setText(output);
			}
                          
                               
                                //TODO 4: Checks if either of the 3 fields is left empty
                                //If true, set the text of resultBox to "Please enter more data"
			if((principal.matches(""))||(rate.matches(""))||(years.matches(""))){
				resultBox.setText("Please enter more data");
				return;
			}else{
			princ =  Double.parseDouble(principal);
			rates =  Double.parseDouble(rate);
			year =  Double.parseDouble(years);
			
			balance = (princ * Math.pow((1+rates/100),year));
			//output = String.valueOf(balance);
			output = String.format("%.2f",balance);
			resultBox.setText(output);	
			}
                               
 
                                //TODO 5: Check if invalid data is entered like entering "Yellow" instead of 1500
                                //If true, set the text of resultBox to "Please enter valid data
			if((!principal.matches("[0-9]+"))||(!rate.matches("[0-9]+"))||(!years.matches("[0-9]+"))){
				resultBox.setText("Please enter valid input");
			}else{
				
			princ =  Double.parseDouble(principal);
			rates =  Double.parseDouble(rate);
			year =  Double.parseDouble(years);
			
			balance = (princ * Math.pow((1+rates/100),year));
			//output = String.valueOf(balance);
			output = String.format("%.2f",balance);
			resultBox.setText(output);
			}
			}
                       
                });
        }
}
