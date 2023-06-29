/**
 * This class is a coffee decorator that gives the customers an option to add flavored syrup to their coffee, such as Mocha, Vanilla, or caramel
 *
 */

import java.util.List;
public class WithFlavor extends CoffeeDecorator {
    public enum Syrup { //Enum representing the different syrup flavors offered
        CARAMEL, MOCHA, VANILLA;
    }
    private Syrup syrup;

    /**
     * constructs a new instance
     * @param c the base coffee to decorate
     * @param syrup  the syrup flavor to add
     */
    public WithFlavor(Coffee c, Syrup syrup) {
        super(c);
        this.syrup = syrup;
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    @Override
    protected Ingredient getAdditionalIngredient() {
        return Ingredient.FLAVORED_SYRUP;
    }

    @Override public String printCoffee() {
        return super.printCoffee()+ " with " + syrup + " flavor";
    }
}

