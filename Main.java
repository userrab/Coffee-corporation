/**
 *Amaria Bradley
 * CS160L
 */

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    private static TreeMap<String, Integer> inventory = new TreeMap<>();
    private static List<CoffeeOrder> orders = new ArrayList<>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";
    private static int quantity;

    public static void main(String[] args) { // in the main method it prints out a main menu option so we prompt the users to do what they want too
        System.out.println("Welcome to Java Coffee Co.!");
        loadInventory();
        CoffeeOrder coffeeOrder = new CoffeeOrder();
        try (Scanner input = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {// this method is printing out the menu so customers can pick what they want to do
                System.out.println("\nWelcome to the main menu!:");
                System.out.println("1. New Order");
                System.out.println("2. load Inventory");
                System.out.println("3. Update Inventory");
                System.out.println("4. Update Order Log");
                System.out.println("5. View Statistics");
                System.out.println("6. Would you like to be added to our reward Program for discounts and free drinks?");
                System.out.println("7. I want to reedem my rewards");
                System.out.println("0. Exit Application");

                int option = 0;
                while (option < 1 || option > 7) {
                    System.out.print("Enter your option: ");
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 7) {
                            System.out.println("Please enter a valid option.");
                        }
                    }
                }// Consume the newline after nextInt()

                switch (option) {
                    case 1:
                            CoffeeOrder order = buildOrder();
                            orders.add(order);
                            System.out.println(order.printOrder());
                            break;

                    case 2:
                        loadInventory();
                        printInventory();
                        break;
                    case 3:
                        updateInventory();
                        break;
                    case 4:
                        updateOrderLog();
                        break;
                    case 5:
                        exit = true;
                        if (!orders.isEmpty()) {
                            writeOrderLog(logFile);
                        }
                        updateInventory();
                        System.out.println("Exiting the application...");
                        break;
                    case 6:
                        getrewardsInfo();
                        break;
                    case 7:
                        coffeeOrder.printRewards();
                        break;

                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void loadInventory() { // this is to load the inventory to the logfile
        try {
            inventory = readInventory(inventoryFile);
        } catch (Exception e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    private static CoffeeOrder buildOrder() { // this whole method builds the order starting with the coffee type to the decorators if wanted
        int quantity = 10;
        CoffeeOrder order = new CoffeeOrder();
        boolean addCoffee = true;
        try (Scanner input = new Scanner(System.in)) {
            while (addCoffee) {
                System.out.println("Select your coffee type:");
                System.out.println("1. Black Coffee");
                System.out.println("2. Espresso");

                int option = 0;
                while (option < 1 || option > 2) {
                    System.out.print("Enter your option: ");
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) {
                            System.out.println("Please enter a valid option.");
                        }
                    }
                }
                input.nextLine();

                Coffee coffee = null;
                if (option == 1) {
                    if (quantity > 0) {
                        coffee = new BlackCoffee();
                        updateInventory("Black Coffee");
                    } else {
                        System.out.println("Sorry, Black Coffee is not currently available.");
                    }
                }
                if (option == 2) {
                    if (quantity > 0) {
                        coffee = new Espresso();
                        updateInventory("Espresso");
                    } else {
                        System.out.println("Sorry, Espresso is not currently available.");
                    }
                }

                if (coffee != null) {
                    // Prompt user for any customizations (decorators)
                    int decoratorOption = -1;
                    while (decoratorOption != 0) {
                        System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                        System.out.println("Would you like to add anything to your coffee?");
                        System.out.println("\t1. Flavored Syrup");
                        System.out.println("\t2. Hot Water");
                        System.out.println("\t3. Milk");
                        System.out.println("\t4. Sugar");
                        System.out.println("\t5. Whipped Cream");
                        System.out.println("\t0. NO - Finish Coffee");

                        while (!input.hasNextInt()) {
                            System.out.println("Please enter a valid number.");
                            input.nextLine();
                        }
                        decoratorOption = input.nextInt();
                        input.nextLine();
                        coffee = switch (decoratorOption) {
                            case 1 -> {
                                System.out.println("Please select a flavor:");
                                for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                    System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                                }
                                int max = WithFlavor.Syrup.values().length;
                                int flavorOption = 0;
                                while (flavorOption < 1 || flavorOption > max) {
                                    if (!input.hasNextInt()) {
                                        System.out.println("Please enter a valid number.");
                                        input.nextLine();
                                    } else {
                                        flavorOption = input.nextInt();
                                        if (flavorOption < 1 || flavorOption > max)
                                            System.out.println("Please enter a valid option.");
                                    }
                                }
                                input.nextLine();
                                WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[flavorOption - 1];
                                yield new WithFlavor(coffee, flavor);
                            }
                            case 2 -> new WithHotWater(coffee);
                            case 3 -> new WithMilk(coffee);
                            case 4 -> new WithSugar(coffee);
                            case 5 -> new WithWhippedCream(coffee);
                            default -> {
                                if (decoratorOption != 0) System.out.println("Please enter a valid option.");
                                yield coffee;
                            }
                        };
                    }

                    order.addCoffee(coffee);
                    System.out.println("Do you want to add another coffee to the order? (yes/no)");
                    String addMore = input.nextLine();
                    addCoffee = addMore.equalsIgnoreCase("yes");
                }
                 if(!addCoffee) {
                    break;
                }
            }
        }
        return order;
    }

    private static void printInventory() { // this method prints the current inventory of what is available
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> item : inventory.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }

    private static void updateInventory() { // this updates the inventory after an ingredient is used
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("\nUpdate Inventory:");
            System.out.println("1. Add Stock");
            System.out.println("2. Remove Stock");

            int option = 0;
            while (option < 1 || option > 2) {
                System.out.print("Enter your option: ");
                if (!input.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    input.nextLine();
                } else {
                    option = input.nextInt();
                    if (option < 1 || option > 2) {
                        System.out.println("Please enter a valid option.");
                    }
                }
            }
            input.nextLine(); // Consume the newline after nextInt()

            String coffeeType = "";
            int quantity = 0;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Enter the coffee type: ");
                coffeeType = input.nextLine();
                if (!inventory.containsKey(coffeeType)) {
                    System.out.println("Invalid coffee type. Please enter a valid coffee type.");
                } else {
                    validInput = true;
                }
            }

            validInput = false;
            while (!validInput) {
                System.out.print("Enter the quantity: ");
                if (!input.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    input.nextLine();
                } else {
                    quantity = input.nextInt();
                    if (option == 2 && quantity > inventory.get(coffeeType)) {
                        System.out.println("Cannot remove more stock than available.");
                    } else {
                        validInput = true;
                    }
                }
            }

            if (option == 1) {
                inventory.put(coffeeType, inventory.get(coffeeType) + quantity);
                System.out.println("Inventory added successfully.");
            } else {
                inventory.put(coffeeType, inventory.get(coffeeType) - quantity);
                System.out.println("Inventory removed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating inventory: " + e.getMessage());
        }
    }

    private static void updateInventory(String coffeeType) { // this updates the inventory anything an items is used
        if (inventory.containsKey(coffeeType)) {
            inventory.put(coffeeType, inventory.get(coffeeType) - 1);
        }
    }

    private static TreeMap<String, Integer> readInventory(String filename) throws Exception { // this reads the inventory
        TreeMap<String, Integer> inventory = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String coffeeType = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    inventory.put(coffeeType, quantity);
                }
            }
        }
        return inventory;
    }

    private static void updateOrderLog() { // this updates the order log any time after a new order was taken
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Order log updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating order log: " + e.getMessage());
        }
    }

    private static void writeOrderLog(String filename) { // writes an orderlog
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Order log written successfully.");
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }
    public static void getrewardsInfo() { // gets the customers information for the rewards program so it can be updated to the orderlog
        Scanner input = new Scanner(System.in);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the rewards program!");

        System.out.println(" Please enter your first and last name");
        String name = input.nextLine();

        System.out.println("Please enter your birthday for a special reward!");
        String birth = input.nextLine();
        System.out.println("Your phone number");
        String phone = input.nextLine();
        System.out.println("Great! Is this information correct? please enter yes or no:  " + name + " " + birth + " " + phone);
        String option = input.nextLine();
        if (option.equalsIgnoreCase("yes")) {
            updateOrderLog();
            System.out.println("You have successfully added to our rewards program! ");
        } else {
            System.out.println("Please return to the main menu to try again.");
        }
    }
    }