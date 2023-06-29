import java.util.List;
import java.util.Arrays;

public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 1.75;
    }

    @Override
    public List<CoffeeDecorator.Ingredient> getIngredients() {
        return Arrays.asList(CoffeeDecorator.Ingredient.ESPRESSO);
    }

    @Override
    public String printCoffee() {
        return "An espresso";
    }
}


