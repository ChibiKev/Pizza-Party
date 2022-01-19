package com.chibikev.pizzaparty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    public final static int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate was called");
        // Assign the widgets to fields
        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);
    }

    public void calculateClick(View view) {

        // Get the text that was typed into the EditText
        String numAttendStr = mNumAttendEditText.getText().toString();

        // Convert the text into an integer
        int numAttend;
        try {
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

            // Determine how many slices on average each person will eat
        int slicesPerPerson = 0;
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.light_radio_button) {
            slicesPerPerson = 2;
        } else if (checkedId == R.id.medium_radio_button) {
            slicesPerPerson = 3;
        } else if (checkedId == R.id.ravenous_radio_button) {
            slicesPerPerson = 4;
        }

        // Calculate and show the number of pizzas needed
        int totalPizzas = (int) Math.ceil(numAttend * slicesPerPerson / (double) SLICES_PER_PIZZA);
        mNumPizzasTextView.setText("Total pizzas: " + totalPizzas);
    }
}