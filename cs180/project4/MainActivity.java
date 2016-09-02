package edu.purdue.cs180.interestcalculator;


import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		final Spinner bankSpinner = (Spinner) findViewById(R.id.bankSpinner);
		List<String> list = new ArrayList<String>();
		list.add("Iron Bank of Braavos"); // Name of Bank
		list.add("Bank of CS180"); // Name of Bank
		list.add("Khallesi Fedral Credit Union"); //Name of Bank
		list.add("Bank of the Forsaken"); // Name of Bank
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bankSpinner.setAdapter(dataAdapter);
		final Context context = this;
		Button submitB = (Button) findViewById(R.id.submitB);
		Button resetB = (Button) findViewById(R.id.resetB);
		Button rateFinder = (Button) findViewById(R.id.findRate);
		final EditText nameBox = (EditText) findViewById(R.id.nameField);
		final EditText principalBox = (EditText) findViewById(R.id.principalField);
		final TextView rateBox = (TextView) findViewById(R.id.rateDisplay);
		final EditText yearsBox = (EditText) findViewById(R.id.timeField);
		final RadioGroup radioGender = (RadioGroup) findViewById(R.id.radioSelection);
		final RadioGroup interestSelection = (RadioGroup) findViewById(R.id.chosenInterest);
		//  to display comments
		final TextView commentsBox = (TextView) findViewById(R.id.comments);


		resetB.setOnClickListener(new View.OnClickListener() {
			//Executes everything that goes inside this body
			public void onClick(View v) {


				// TODO 1: Reset the principal, rate, years and name fields
				principalBox.setText("");
				rateBox.setText("");
				yearsBox.setText("");
				nameBox.setText("");

			}
		});

		rateFinder.setOnClickListener(new View.OnClickListener() {
			// Executes everything that goes inside this body
			public void onClick(View v) {
				// chosenBank will contain the name of the bank
				String chosenBank = bankSpinner.getSelectedItem().toString();


				// TODO 2: Use nested if else statements to check which bank was selected and then
					// display the rated offered by it in the rate TextView.
					// Refer to the "Rate Offered" table given in the wiki
					
					if(chosenBank.equals("Iron Bank of Braavos")){
						rateBox.setText("5%");
					}else if(chosenBank.equals("Bank of CS180")){
						rateBox.setText("8%");
					}else if(chosenBank.equals("Khallesi Fedral Credit Union")){
						rateBox.setText("3%");
					}else if(chosenBank.equals("Bank of the Forsaken")){
						rateBox.setText("12%");
					}
					
						
			}
		});

		submitB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				// TODO 3: Retrieve the name, principal and years entered by the user
					// Store it as String variables. Refer to Lab08.
					String principal = principalBox.getText().toString();
					String rate = rateBox.getText().toString();
					String years = yearsBox.getText().toString();
					String name = nameBox.getText().toString();


				int selectedId = radioGender.getCheckedRadioButtonId();
				RadioButton radioGenderButton = (RadioButton) findViewById(selectedId);
				String genderSelected = radioGenderButton.getText().toString();
				int selectedId2 = interestSelection.getCheckedRadioButtonId();
				RadioButton interestButton = (RadioButton) findViewById(selectedId2);
				// interestSelected can take two values: "Compound Interest", "Simple Interest"
				String interestSelected = interestButton.getText().toString();
				rateBox.setText(interestSelected);
				
				String finalAnswer = "";
				double rateValue = 0.00;
				String chosenBank = bankSpinner.getSelectedItem().toString();


				// TODO 4: Use nested if else statements to identify the selected bank
					// and override the value of rateValue accordingly. 
					// Refer to "Rate Offered" table on wiki
					
					if(chosenBank.equals("Iron Bank of Braavos")){
						rateValue = 5.00;
					}else if(chosenBank.equals("Bank of CS180")){
						rateValue = 8.00;
					}else if(chosenBank.equals("Khallesi Fedral Credit Union")){
						rateValue = 3.00;
					}else if(chosenBank.equals("Bank of the Forsaken")){
						rateValue = 12.00;
					}
				
				
				
				
				
				// TODO 5: Check if the principal and years entered by the user are valid
					// If the data entered is valid check the type of interest selected 
						// Based on the type of interest selected compute the balance
						// This is similar to Lab07
					// Else If any of the EditText field is empty ask the user to enter more data
						// by setting the text within the commentsBox, 
						// i.e. commentsBox.setText("Please enter more data");

					double princ = 0;
                                        double rates = rateValue;
					double year = 0;
					double balance = 0;
					String output = "";

					if((!principal.matches("[0-9]+"))||(!years.matches("[0-9]"))){
						commentsBox.setText("Please enter valid data");
						return;
					}
					if((principal.matches(""))||(years.matches(""))||(name.matches(""))){
						commentsBox.setText("Please enter more information");
						return;
					}
					//compound interest.
					if(interestSelected.matches("Compound Interest")){
						princ = Double.parseDouble(principal);
						year = Double.parseDouble(years);
						balance = (princ *Math.pow((1+rates/100),year));
						output = String.format("%.2f",balance);
					//simple interset
					}else if(interestSelected.matches("Simple Interest")){
						princ = Double.parseDouble(principal);
						year = Double.parseDouble(years);
						double rateTemp = (rateValue/100);
						double temp = (1+rateTemp * year);
						balance = (princ * temp);
						output = String.format("%.2f",balance);
   					}
						
						
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.custom_main);
				dialog.setTitle("Here's the summary");

				TextView text1 = (TextView) dialog.findViewById(R.id.text1);
				String namePrefix = "";


				// TODO 6: Based on the gender assign namePrefix to Mr or Ms
				if(genderSelected.equals("Male")){
					namePrefix = "Mr. ";
				}else if(genderSelected.equals("Female")){
					namePrefix = "Mrs. ";
				}

				
				
				text1.setText("Customer Name : " + namePrefix + name);
				TextView text2 = (TextView) dialog.findViewById(R.id.text2);
				text2.setText("\n" + "Bank : " + chosenBank);
				TextView text3 = (TextView) dialog.findViewById(R.id.text3);
				text3.setText("\n\n" +"Principal : $ " + principal);
				TextView text4 = (TextView) dialog.findViewById(R.id.text4);
				text4.setText("\n\n\n" +"Rate Applied : " + rateValue + "%");
				TextView text5 = (TextView) dialog.findViewById(R.id.text5);
				text5.setText("\n\n\n\n" +"Years : " + year);
				TextView text6 = (TextView) dialog.findViewById(R.id.text6);
				text6.setText("\n\n\n\n\n" +"Interest Type : " + interestSelected);
				TextView text7 = (TextView) dialog.findViewById(R.id.text7);
				text7.setText("\n\n\n\n\n\n" +"Balance : $" + output);


				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();	
			}
		});




	}
}