package com.chibikev.pizzaparty;

import android.view.View;

public class PizzaCalculator {
    public enum PizzaSize {
        Small, Medium, Large
    }

    public enum PizzaToppings {
        Plain, Pepperoni, Chicken
    }

    public final static int SLICES_PER_PIZZA = 8;

    private int numAmount;
    private int numAttend;
    private PizzaSize mHowHungry;
    private PizzaToppings mToppings;

    public PizzaCalculator(int numAttend, int numAmount) {
        setNumAttend(numAttend);
        setNumAmount(numAmount);
    }

    public PizzaCalculator(int numAttend, int numAmount, PizzaSize mHowHungry, PizzaToppings mToppings) {
        setNumAttend(numAttend);
        setNumAmount(numAmount);
        setHowHungry(mHowHungry);
        setToppings(mToppings);
    }

    public void setNumAmount(int numAmount){
        this.numAmount = numAmount;
    }

    public void setNumAttend(int numAttend) {
        this.numAttend = numAttend;
    }

    public void setHowHungry(PizzaSize mHowHungry) {
        this.mHowHungry = mHowHungry;
    }

    public void setToppings(PizzaToppings mToppings) {
        this.mToppings = mToppings;
    }

    public int getPizzaAmount() {
        // Calculate and show the number of pizzas needed
        return (int) Math.ceil(numAmount * numAttend / (double) SLICES_PER_PIZZA);
    }

    public int getPizzaCost() {
        int cost = 0;
        switch (mHowHungry) {
            case Small: cost += 8;
                break;
            case Medium: cost += 10;
                break;
            case Large: cost += 12;
                break;
            default: cost += 0;
                break;
        }
        switch (mToppings) {
            case Plain: cost += 0;
                break;
            case Pepperoni: cost += 2;
                break;
            case Chicken: cost += 2;
                break;
            default: cost += 0;
                break;
        }

        // Calculate and show the cost of pizzas
        int totalPizza = (int) Math.ceil(numAmount * numAttend / (double) SLICES_PER_PIZZA);
        int totalCost = totalPizza * cost;
        return totalCost;
    }
}
