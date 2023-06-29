/**
 *Amaria Bradley
 * CS160L
 */
// This class is a coffee decorator that gives the customers an option to add whipped cream to their coffee order
import java.util.List;
public class WithWhippedCream extends CoffeeDecorator {
    /**
     * Constructs a new instance
     *
     * @param c the base coffee to decorate
     */

    public WithWhippedCream (Coffee c) {
        super(c);
    }
    @Override
    public double getCost() {
        return super.getCost() + 0.25;
    }

    @Override
    protected Ingredient getAdditionalIngredient() {
        return Ingredient.WHIPPED_CREAM;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with whipped cream";
    }
}
