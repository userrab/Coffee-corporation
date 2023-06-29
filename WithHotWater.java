/**
 *Amaria Bradley
 * CS160L
 */
//This class is a coffee decorator that gives the customers an option to add hot water to their coffee making it a hot coffee
import java.util.List;

public class WithHotWater extends CoffeeDecorator {
    /**
     * constructs a new class
     * @param c the base coffee to decorate
     */
    public WithHotWater(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.00;
    }

    @Override
    public Ingredient getAdditionalIngredient() {
        return Ingredient.HOT_WATER;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}

