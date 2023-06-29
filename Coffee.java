/**
 *Amaria Bradley
 * CS160L
 */
//This interface is one that is used throughout the coffee order lab, to store the methods that are implemented especially since every class is very similar.
import java.util.List;

public interface Coffee {
    /**
     * calculates and returns the cost of the coffee
     * @return the cost of the cofeee
     */
    public double getCost();
    // gets the cost of the coffee

    public List<CoffeeDecorator.Ingredient> getIngredients();
    //gets the list of ingredients used in the coffee

    public String printCoffee();
}