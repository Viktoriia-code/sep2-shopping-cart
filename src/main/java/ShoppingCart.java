import java.util.*;

public class ShoppingCart {
    private static double totalCost = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale locale = getLocale(scanner);

        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);

        System.out.println(messages.getString("enter_items_number"));
        int numItems = scanner.nextInt();

        for (int i = 1; i <= numItems; i++) {
            System.out.println(messages.getString("enter_item_price"));
            double price = scanner.nextDouble();

            System.out.println(messages.getString("enter_item_quantity"));
            int quantity = scanner.nextInt();

            totalCost += getItemCost(price, quantity);
        }

        System.out.println(messages.getString("total_cost") + " " + totalCost);
        scanner.close();
    }

    // Method to get the locale based on user input
    private static Locale getLocale(Scanner scanner) {
        System.out.println("Select a language: 1 - English, 2 - Finnish, 3 - Swedish, 4 - Japanese");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: return Locale.US;
            case 2: return new Locale("fi", "FI");
            case 3: return new Locale("sv", "SE");
            case 4: return new Locale("ja", "JP");
            default: return Locale.US;
        }
    }

    // Method to add an item to the shopping cart
    public static double getItemCost(double price, int quantity) {
        return price * quantity;
    }

    // Getter method to get the total cost of the shopping cart
    public static double getTotalCost() {
        return totalCost;
    }
}
