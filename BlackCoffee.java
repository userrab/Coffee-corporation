import java.util.List;
import java.util.Arrays;

public class BlackCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.00;
    }

    @Override
    public List<CoffeeDecorator.Ingredient> getIngredients() {
        return Arrays.asList(CoffeeDecorator.Ingredient.BLACK_COFFEE);
    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}
