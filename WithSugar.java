/**
 *Amaria Bradley
 * CS160L
 */
// This class is a coffee decorator that gives the customers an option to add Sugar to their coffee order
import java.util.List;
public class WithSugar extends CoffeeDecorator {
    /**
     * Constructs a new instance
     * @param c the base coffee to decorate
     */

    public WithSugar(Coffee c) {
        super(c);
    }


    @Override
    public double getCost() {
        return super.getCost() + 0.15;
    }

    @Override
    protected Ingredient getAdditionalIngredient() {
        return Ingredient.SUGAR;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }
}
