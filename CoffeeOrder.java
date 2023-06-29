/**
 *Amaria Bradley
 * CS160L
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now(); // Initialize orderDate with current date and time

    }

    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate; // Store the provided orderDate

    }

    public void addCoffee(Coffee c) {
        coffees.add(c);

    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotal() { // this is the math for calculating the total of the coffee/coffees
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    public  void printRewards() { // this uses the total and converts it to points to measure and see if customers have enough points to redeem their free coffee

        int rewardPoints =(int) Math.round(getTotal());

        System.out.println("Your current rewards points: " + rewardPoints);
        if (rewardPoints >= 100)  {
            System.out.println("You have enough points! Would you like to reedem a free coffee?(Y/N)");
            Scanner scnr = new Scanner(System.in);
            String choice = scnr.nextLine();
            if (choice.equals("Y")) {
                rewardPoints -= 100;
                System.out.println("YAY you have redeemed your free coffee!!");
            }
            }

        else{
            System.out.println("Sorry, you don't have enough points :/");
        }
    }

    public String printOrder() {
        StringBuilder order = new StringBuilder();
        order.append("ORDER RECEIPT\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        order.append("Timestamp: ").append(orderDate.format(formatter)).append("\n");
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append("Item ").append(i + 1).append(": ").append(coffee.printCoffee()).append(" - ")
                    .append(String.format("%.2f", coffee.getCost())).append("\n");
        }
        order.append("TOTAL = ").append(String.format("%.2f", getTotal())).append("\n");
        return order.toString();
    }

}


