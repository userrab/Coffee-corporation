/**
 *Amaria Bradley
 * CS160L
 */
//This class is a coffee decorator that gives the customers an option to add milk to their coffee order
import java.util.List;

public class WithMilk extends CoffeeDecorator {
    /**
     * constructs a new class
     * @param c the base coffee to decorate
     */
    public WithMilk(Coffee c) {
        super (c);
    }
    @Override
    public double getCost() {
        return super.getCost() + 0.55;
    }
    @Override
    protected Ingredient getAdditionalIngredient() {
        return Ingredient.MILK;
    }
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with milk";
    }
}


