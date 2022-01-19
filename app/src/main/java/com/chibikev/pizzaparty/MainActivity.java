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
    private EditText mNumAmountEditText;
    private TextView mNumPizzasTextView;
    private TextView mCostTextView;
    private RadioGroup mHowHungryRadioGroup;
    private RadioGroup mToppingsRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate was called");
        // Assign the widgets to fields
        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);
        mNumAmountEditText = findViewById(R.id.num_amount_edit_text);
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);
        mCostTextView = findViewById(R.id.cost_text_view);
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);
        mToppingsRadioGroup = findViewById(R.id.toppings_radio_group);
    }

    public void calculatePizzaClick(View view) {

        // Get the text that was typed into the EditText
        String numAttendStr = mNumAttendEditText.getText().toString();
        String numAmountStr = mNumAmountEditText.getText().toString();

        // Convert the text into an integer
        int numAttend;
        int numAmount;
        try {
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }
        try {
            numAmount = Integer.parseInt(numAmountStr);
        }
        catch (NumberFormatException ex) {
            numAmount = 1;
        }

        // Calculate and show the number of pizzas needed
        int totalPizzas = (int) Math.ceil(numAmount * numAttend / (double) SLICES_PER_PIZZA);
        mNumPizzasTextView.setText("Total pizzas: " + totalPizzas);
    }

    public void calculatePriceClick(View view) {
        // Get the text that was typed into the EditText
        String numAttendStr = mNumAttendEditText.getText().toString();
        String numAmountStr = mNumAmountEditText.getText().toString();

        // Convert the text into an integer
        int numAttend;
        int numAmount;
        try {
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }
        try {
            numAmount = Integer.parseInt(numAmountStr);
        }
        catch (NumberFormatException ex) {
            numAmount = 1;
        }

        int cost = 0;
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.small_radio_button) {
            cost = 8;
        } else if (checkedId == R.id.medium_radio_button) {
            cost = 10;
        } else if (checkedId == R.id.large_radio_button) {
            cost = 12;
        }

        int toppingCost = 0;
        checkedId = mToppingsRadioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.plain_radio_button) {
            toppingCost = 0;
        } else if (checkedId == R.id.pepperoni_radio_button) {
            toppingCost = 2;
        } else if (checkedId == R.id.chicken_radio_button) {
            toppingCost = 2;
        }

        // Calculate and show the cost of pizzas
        int totalPizza = (int) Math.ceil(numAmount * numAttend / (double) SLICES_PER_PIZZA);
        int totalCost = totalPizza * (cost + toppingCost);
        mCostTextView.setText("Total Cost: " + totalCost);
    }
}