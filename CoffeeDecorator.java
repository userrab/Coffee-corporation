/**
 *Amaria Bradley
 * CS160L
 */
//This class deals with all the additives to the coffee hence why its an abstract class because it needs to be subclassed by other classes to use all the methods.
import java.util.ArrayList;
import java.util.List;

public abstract class CoffeeDecorator implements Coffee {
    private final Coffee coffee;

    /**
     * Constructs a coffeeDecorator object with specified coffee instance
     * @param c coffee instance to decorate
     */

    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    @Override
    public double getCost() { // gets the cost of the decorated coffee
        return coffee.getCost();
    }

    @Override
    public List<Ingredient> getIngredients() { // gets the list of ingredients for the decorator
        List<Ingredient> ingredients = new ArrayList<>(coffee.getIngredients());
        ingredients.add(getAdditionalIngredient());
        return ingredients;
    }

    protected abstract Ingredient getAdditionalIngredient(); // gets the additional ingredient

    @Override
    public String printCoffee() { // generates a string representation of the decorator
        return coffee.printCoffee();
    }

    public enum Ingredient { // an enum for all the ingredients in the coffee decorator
        BLACK_COFFEE("Black Coffee"),
        ESPRESSO("Espresso"),
        WHIPPED_CREAM("Whipped Cream"),
        SUGAR("Sugar"),
        FLAVORED_SYRUP("Flavored Syrup"),
        HOT_WATER("Hot Water"),
        MILK("Milk");

        private final String name;

        Ingredient(String name) {
            this.name = name;
        }
    }
}


