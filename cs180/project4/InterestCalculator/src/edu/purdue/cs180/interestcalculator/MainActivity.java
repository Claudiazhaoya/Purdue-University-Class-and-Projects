package edu.purdue.cs180.interestcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity {

	@Override 
	protected void onCreate(Bundle savedInstanceState) {   
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit(); 
		}
	}

	@Override  
	public boolean onCreateOptionsMenu(Menu menu) {    

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override 
	public boolean onOptionsItemSelected(MenuItem item) { 
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * A placeholder fragment containing a simple view.  
	 */
	public static class PlaceholderFragment extends Fragment { 

		public PlaceholderFragment() {
		}

		@Override 
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void onRadioButtonClicked(View view){
		boolean checked = ((RadioButton) view).isChecked();
		
		switch(view.getId()){
		case R.id.siRadio:
			if(checked){
				Intent simple = new Intent(this,Simple_interest.class);
				startActivity(simple);
				break;
			}
		case R.id.ciRadio:
			if(checked){
				Intent compound = new Intent(this,Compound_interest.class);
				startActivity(compound);
				break;
			}
		}
	}
	//public void GO(View view){ 
	//	int player=0;
	//	RadioButton interestButton = (RadioButton)findViewById();

	//	Intent mode = new Intent(this, Simple_interest.class);
	//	startActivity(mode);
	//} 
}