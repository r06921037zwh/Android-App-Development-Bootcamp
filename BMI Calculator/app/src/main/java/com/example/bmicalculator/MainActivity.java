package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    /* Class Variables, or Fields */
    private TextView resultTextView;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText inchesEditText;
    private EditText feetEditText;
    private Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonListener();
    }

    /* Use variable to locate the View elements by id */
    private void findViews() {
        resultTextView = findViewById(R.id.text_view_result);
        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        weightEditText = findViewById(R.id.edit_text_weights);
        inchesEditText = findViewById(R.id.edit_text_inches);
        feetEditText = findViewById(R.id.edit_text_feet);
        calculateButton = findViewById(R.id.button_calculate);
    }

    /* Add event listener to react for the button click */
    private void setupButtonListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Show some prompt messages */
                // Toast.makeText(MainActivity.this, "Respond to your button click!", Toast.LENGTH_LONG).show();
                double bmiResult = calculateBmi();

                String ageString = ageEditText.getText().toString();
                int age = Integer.parseInt(ageString);

                if (age > 18) {
                    displayBmi(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private double calculateBmi(){
        String weightString = weightEditText.getText().toString();
        String inchesString = inchesEditText.getText().toString();
        String feetString = feetEditText.getText().toString();

        /* Parse the string into integers */
        int weight = Integer.parseInt(weightString);
        int inches = Integer.parseInt(inchesString);
        int feet = Integer.parseInt(feetString);

        /* Calculate the bmi value */
        int totalInches = (feet * 12) + inches;
        double heightsInMeters = totalInches * 0.0254;
        return weight / (heightsInMeters * heightsInMeters);
    }

    private void displayBmi(double bmi) {
        /* For the bmi value */
        DecimalFormat DF = new DecimalFormat("0.00");
        String formattedBmi = DF.format(bmi);

        /* Judge the bmi range */
        String presentText;
        if (bmi < 18.5) {
            presentText = "Your BMI : " + formattedBmi + ", underweight";
        } else if (bmi > 25) {
            presentText = "Your BMI : " + formattedBmi + ", overweight";
        } else {
            presentText = "Your BMI : " + formattedBmi + ", healthy";
        }

        resultTextView.setText(presentText);
    }

    private void displayGuidance(double bmi) {
        /* For the bmi value */
        DecimalFormat DF = new DecimalFormat("0.00");
        String formattedBmi = DF.format(bmi);

        /* Judge the bmi range */
        String presentText;
        if (maleRadioButton.isChecked()) {
            /* Male */
            presentText = "Your BMI : " + formattedBmi + ", Boy go to doctor ...";
        } else if (femaleRadioButton.isChecked()) {
            presentText = "Your BMI : " + formattedBmi + ", Girl go to doctor ...";
        } else {
            presentText = "Your BMI : " + formattedBmi + ", Human go to doctor ...";
        }
        resultTextView.setText(presentText);
    }

}