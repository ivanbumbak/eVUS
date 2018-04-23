package com.example.android.evus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button calculateBtn;
    private EditText userEcts;
    private EditText enrolledEcts;
    private TextView ectsPriceText;
    private TextView summaryEctsText;
    private TextView summaryPriceText;
    private Spinner courses;
    private RadioButton laterEnrolmentRB;
    private RadioButton earlierEnrolmentRB;
    private RadioGroup radioGroup;
    String cors;
    int price;
    Double ects;
    Double enrolled;
    Double enrolledCost = 260.0;
    Double fullScholar = 6000.0;
    Double yearEcts;
    Double calculatedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        //Initialization
        userEcts = (EditText) findViewById(R.id.ects_input);
        enrolledEcts = (EditText) findViewById(R.id.ects_enrolled);
        ectsPriceText = (TextView) findViewById(R.id.ects_price_text);
        courses = (Spinner) findViewById(R.id.course_spinner);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        laterEnrolmentRB = (RadioButton) findViewById(R.id.laterEnrolmentRadioButton);
        earlierEnrolmentRB = (RadioButton) findViewById(R.id.earlierEnrolmentRadioButton);
        summaryEctsText = (TextView) findViewById(R.id.text_summary_ects_number);
        summaryPriceText = (TextView) findViewById(R.id.text_summary_price_number);
        calculateBtn = (Button) findViewById(R.id.calculate);

        //Setting clickListener on button
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //When the button is clicked, call the calculate method.
                calculate();
            }
        });

        //Setting changeListener on RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.earlierEnrolmentRadioButton:
                        if (cors.equals(getString(R.string.undergraduate_traf))) {
                            ectsPriceText.setText("120.00 kn");
                            price = 120;
                        } else {
                            ectsPriceText.setText("100.00 kn");
                            price = 100;
                        }
                        break;
                    case R.id.laterEnrolmentRadioButton:
                        if (cors.equals(getString(R.string.undergraduate_traf))) {
                            ectsPriceText.setText("250.00 kn");
                            price = 250;
                        } else {
                            ectsPriceText.setText("200.00 kn");
                            price = 200;
                        }
                        break;
                }
            }
        });

        //Initialization of list of courses for Spinner
        final String[] courseArray = new String[4];
        {
            courseArray[0] = getString(R.string.undergraduate_man);
            courseArray[1] = getString(R.string.undergraduate_traf);
            courseArray[2] = getString(R.string.undergraduate_admin);
            courseArray[3] = getString(R.string.graduate_man);
        }

        //Creating ArrayAdapter of list of courses
        final ArrayAdapter courseAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, courseArray);
        //Setting list type
        courseAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        //Setting adapter to courses Spinner
        courses.setAdapter(courseAdapter);

        //Creating listener for selecting specific item from @courseArray
        courses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {

                //Getting item from Spinner
                cors = parent.getItemAtPosition(pos).toString();
                if (cors.equals(courseArray[0])) {
                    if(laterEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("200.00 kn");
                        price = 200;
                    } else if(earlierEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("100.00 kn");
                        price = 100;
                    } else {
                        //Do nothing
                    }
                    fullScholar = 6000.0;
                } else if (cors.equals(courseArray[1])) {
                    if(laterEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("250.00 kn");
                        price = 250;
                    } else if(earlierEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("120.00 kn");
                        price = 120;
                    } else {
                        //Do nothing
                    }
                    fullScholar = 7500.0;
                } else if (cors.equals(courseArray[2])) {
                    if(laterEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("200.00 kn");
                        price = 200;
                    } else if(earlierEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("100.00 kn");
                        price = 100;
                    } else {
                        //Do nothing
                    }
                    fullScholar = 6000.0;
                } else if (cors.equals(courseArray[3])) {
                    if(laterEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("200.00 kn");
                        price = 200;
                    } else if(earlierEnrolmentRB.isChecked()) {
                        ectsPriceText.setText("100.00 kn");
                        price = 100;
                    } else {
                        //Do nothing
                    }
                    fullScholar = 6000.0;
                } else {
                    //Do nothing
                }
            }

            public void onNothingSelected(AdapterView parent) {
                // Do nothing.
            }
        });
    }

    //Creating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.side_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Selecting items from menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_describe) {
            Intent instructionsIntent = new Intent(MainActivity.this, Instructions.class);
            startActivity(instructionsIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate() {
        //gets entered text from the EditText,and convert to integers.
        if ((!TextUtils.isEmpty(userEcts.getText().toString())) &&
                (!TextUtils.isEmpty(enrolledEcts.getText().toString()))) {
            ects = Double.parseDouble(userEcts.getText().toString());
            enrolled = Double.parseDouble(enrolledEcts.getText().toString());
            yearEcts = enrolled - ects;
            if (yearEcts < 0) {
                Toast.makeText(this, getString(R.string.negative_ects),
                        Toast.LENGTH_SHORT).show();
            } else if(enrolled > 66) {
                Toast.makeText(this, R.string.high_enrolled_ects, Toast.LENGTH_SHORT).show();
            } else {
                if (((yearEcts) >= 55)) {
                    //do the calculation
                    calculatedPrice = (ects * 0) + enrolledCost;
                    summaryEctsText.setText(Double.toString(yearEcts));
                    //set the value to the TextView, to display on screen.
                    summaryPriceText.setText(Double.toString(calculatedPrice) + " kn");
                } else if (((yearEcts) <= 54) && ((yearEcts) >= 31)) {
                    calculatedPrice = (ects * price) + enrolledCost;
                    summaryEctsText.setText(Double.toString(yearEcts));
                    summaryPriceText.setText(Double.toString(calculatedPrice) + " kn");
                } else if ((yearEcts) <= 30) {
                    calculatedPrice = fullScholar + enrolledCost;
                    summaryEctsText.setText(Double.toString(yearEcts));
                    summaryPriceText.setText(Double.toString(calculatedPrice) + " kn");
                } else {
                    //Do nothing
                }
            }
        } else {
            //This will show if user doesn't fill EditText
            Toast.makeText(this, getString(R.string.emtpy_edittext), Toast.LENGTH_SHORT).show();
        }
    }

    //Calling the calculate method
    @Override
    public void onClick(View v) {
        calculate();
    }
}