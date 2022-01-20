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
            numAmount = Integer.parseInt(numAmountStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
            numAmount = 1;
        }
        PizzaCalculator myCalculations = new PizzaCalculator(numAttend, numAmount);
        int totalPizzas = myCalculations.getPizzaAmount();
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
            numAmount = Integer.parseInt(numAmountStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
            numAmount = 1;
        }

        PizzaCalculator.PizzaSize pizzaSize = PizzaCalculator.PizzaSize.Small;
        switch (mHowHungryRadioGroup.getCheckedRadioButtonId()) {
            case R.id.medium_radio_button: pizzaSize = PizzaCalculator.PizzaSize.Medium;
                break;
            case R.id.large_radio_button: pizzaSize = PizzaCalculator.PizzaSize.Large;
                break;
            default:
                break;
        }

        PizzaCalculator.PizzaToppings pizzaToppings = PizzaCalculator.PizzaToppings.Plain;
        switch (mToppingsRadioGroup.getCheckedRadioButtonId()) {
            case R.id.pepperoni_radio_button: pizzaToppings = PizzaCalculator.PizzaToppings.Pepperoni;
                break;
            case R.id.chicken_radio_button: pizzaToppings = PizzaCalculator.PizzaToppings.Chicken;
                break;
            default:
                break;
        }

        PizzaCalculator myCalculations = new PizzaCalculator(numAttend, numAmount, pizzaSize, pizzaToppings);

        // Calculate and show the cost of pizzas
        int totalCost = myCalculations.getPizzaCost();
        mCostTextView.setText("Total Cost: " + totalCost);
    }
}