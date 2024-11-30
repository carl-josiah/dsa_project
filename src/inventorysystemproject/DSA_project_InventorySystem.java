package inventorysystemproject;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class DSA_project_InventorySystem
{

    public static void main(String[] args) {
        InventoryManager im = new InventoryManager("src/inventorysystemproject/item_inventory.csv");
        String input;
        im.loadItems();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running)
        {
            System.out.println("Inventory Management System:");
            System.out.println("1. Search and Display item by ID");
            System.out.println("2. List all items of a certain category");
            System.out.println("3. Add new item to inventory");
            System.out.println("4. Remove item from inventory");
            System.out.println("5. Update item stock quantity");
            System.out.println("6. Display all items");
            System.out.println("7. Exit");
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("(Enter \"-1\" to exit)");
                    System.out.println("Enter Item ID:");
                    input = scanner.nextLine();
                    if (input.equals("-1"))
                        break;
                    System.out.println(im.findItemById(input));
                    break;
                case 2:
                    System.out.println("(Enter \"-1\" to exit)");
                    System.out.println("Enter Category:");
                    input = scanner.nextLine();
                    if (input.equals("-1"))
                        break;
                    AList<InventoryItem> itemsInCategory = im.findItemsByCategory(input);

                    System.out.printf("%-10s %-20s %-15s %-10s %-10s %-30s%n",
                            "ID", "Product Name", "Category", "Quantity", "Price", "Suppliers");
                    System.out.println("----------------------------------------------------------------------------------------------------------" +
                            "------------------------------------------------------");
                    for (int i = 1; i <= itemsInCategory.getLength(); i++)
                    {
                        InventoryItem item = itemsInCategory.getEntry(i);
                        System.out.printf("%-10s %-20s %-15s %-10d %-10.2f %-30s%n",
                                item.getId(),
                                item.getName(),
                                item.getCategory(),
                                item.getStockQuantity(),
                                item.getPrice(),
                                item.getSuppliers());
                    }
                    break;
                case 3:
                    System.out.println("(Enter \"-1\" to exit)");
                    System.out.println("ID:");
                    String id = scanner.nextLine();
                    if (id.equals("-1"))
                        break;
                    System.out.println("Name:");
                    String name = scanner.nextLine();
                    if (name.equals("-1"))
                        break;
                    System.out.println("Category:");
                    String category = scanner.nextLine();
                    if (category.equals("-1"))
                        break;
                    System.out.println("Suppliers:");
                    String supplier = scanner.nextLine();
                    if (supplier.equals("-1"))
                        break;
                    System.out.println("Stock Quantity:");
                    int stockQuantity = scanner.nextInt();
                    if (stockQuantity == -1)
                        break;
                    System.out.println("Price:");
                    double price = scanner.nextDouble();
                    if (price == -1)
                        break;
                    InventoryItem newItem = new InventoryItem(id, name, category, stockQuantity, price);
                    newItem.addSuppliers(supplier);
                    im.addItem(newItem);
                    System.out.println("Item added: " + newItem.getName());
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("(Enter \"-1\" to exit)");
                    System.out.println("Enter ID:");
                    input = scanner.nextLine();
                    if (input.equals("-1"))
                        break;
                    im.removeItem(input);
                    break;
                case 5:
                    System.out.println("(Enter \"-1\" to exit)");
                    System.out.println("Enter ID:");
                    input = scanner.nextLine();
                    if (input.equals("-1"))
                        break;
                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();
                    if (quantity == -1)
                        break;
                    im.updateStockQuantity(input, quantity);
                    break;
                case 6:
                    im.displayAllItems();
                    break;
                case 7:
                    im.saveItems();
                    System.out.println("Exiting system.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
