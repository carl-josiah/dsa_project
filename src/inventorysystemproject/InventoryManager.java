package inventorysystemproject;
import java.io.*;
import java.util.Iterator;

public class InventoryManager
{
    private ListHashTable<String, InventoryItem> items;
    private String filePath;

    public InventoryManager(String filePath)
    {
        this.filePath = filePath;
        this.items = new ListHashTable<>();
        loadItems();
    }

    public void loadItems()
    {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] fields = line.split(",");
                if (fields.length == 6)
                {
                    String id = fields[0];
                    String name = fields[1];
                    String category = fields[2];
                    int quantity = Integer.parseInt(fields[3]);
                    double price = Double.parseDouble(fields[4]);
                    String suppliersInfo = fields[5];
                    InventoryItem newItem = new InventoryItem(id, name, category, quantity, price);
                    newItem.addSuppliers(suppliersInfo);
                    items.add(id, newItem);
                }
            }
        }
        catch (IOException e)
        {
            e.fillInStackTrace();
        }
    }

//    public void saveItems()
//    {
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)))
//        {
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//            Iterator<String> keyIterator = items.getKeyIterator();
//
//            reader.readLine();
//            while (keyIterator.hasNext())
//            {
//                String key = keyIterator.next();
//                InventoryItem item = items.getValue(key);
//                String line = String.format("%s,%s,%s,%d,%.2f,%s",
//                        item.getId(),
//                        item.getName(),
//                        item.getCategory(),
//                        item.getStockQuantity(),
//                        item.getPrice(),
//                        String.join(";", item.getSuppliers()));
//                writer.write(line);
//                writer.newLine();
//            }
//            writer.write("BRO");
//        }
//        catch (IOException e)
//        {
//            e.fillInStackTrace();
//        }
//    }

//    public void saveItems() {
//        try {
//            // First, load existing item IDs from the file
//            ListHashTable<String, InventoryItem> existingItemIds = new ListHashTable<>();
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Skip the first line (header)
//                if (!line.startsWith("ID")) continue;
//
//                String itemId = line.split(",")[0]; // Extract the ID (first column)
//                existingItemIds.add(itemId);
//            }
//            reader.close();
//
//            // Now write only new items to the file
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true)); // Append mode
//            Iterator<String> keyIterator = items.getKeyIterator();
//
//            while (keyIterator.hasNext()) {
//                String key = keyIterator.next();
//                InventoryItem item = items.getValue(key);
//
//                // Skip if the item already exists
//                if (existingItemIds.contains(item.getId())) continue;
//
//                // Write new item to the file
//                String lineToWrite = String.format("%s,%s,%s,%d,%.2f,%s",
//                        item.getId(),
//                        item.getName(),
//                        item.getCategory(),
//                        item.getStockQuantity(),
//                        item.getPrice(),
//                        String.join(";", item.getSuppliers()));
//                writer.write(lineToWrite);
//                writer.newLine();
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void saveItems()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false)))
        {
            writer.write("hello");
        }
        catch (IOException e)
        {
            e.fillInStackTrace();
        }
    }

    public void addItem(InventoryItem item)
    {
        items.add(item.getId(), item);
    }

    public void removeItem(String id)
    {
        items.remove(id);
    }

    public InventoryItem findItemById(String id)
    {
        return(items.getValue(id));
    }

    public void updateStockQuantity(String id, int newQuantity)
    {
        if (newQuantity > 0)
            items.getValue(id).setStockQuantity(newQuantity);
    }

    public void displayAllItems()
    {
        Iterator<String> keyIterator = items.getKeyIterator();

        System.out.printf("%-10s %-20s %-15s %-10s %-10s %-30s%n",
                "ID", "Product Name", "Category", "Quantity", "Price", "Suppliers");
        System.out.println("--------------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------");
        while (keyIterator.hasNext())
        {
            String key = keyIterator.next();
            InventoryItem item = items.getValue(key);

            System.out.printf("%-10s %-20s %-15s %-10d %-10.2f %-30s%n",
                    item.getId(),
                    item.getName(),
                    item.getCategory(),
                    item.getStockQuantity(),
                    item.getPrice(),
                    item.getSuppliers()
            );
        }
    }

    public AList<InventoryItem> findItemsByCategory(String category)
    {
        AList<InventoryItem> itemAList = new AList<>();
        Iterator<String> keyIterator = items.getKeyIterator();

        while (keyIterator.hasNext())
        {
            String key = keyIterator.next();
            InventoryItem item = items.getValue(key);
            if (item.getCategory().equals(category))
                itemAList.add(item);
        }
        return (itemAList);
    }

//    public static void main(String[] args)
//    {
//        InventoryItem item1 = new InventoryItem("ID-001", "Item1", "Category1", 1, 10.0);
//        InventoryItem item2 = new InventoryItem("ID-002", "Item2", "Category1", 1, 10.0);
//        InventoryItem item3 = new InventoryItem("ID-003", "Item3", "Category1", 1, 10.0);
//        InventoryManager im = new InventoryManager("src/inventorysystemproject/item_inventory.csv");
//        im.addItem(item1);
//        im.addItem(item2);
//        im.addItem(item3);
//        im.displayAllItems();
//        System.out.println("--------------------------------------------------------------------------------------");
//        AList<InventoryItem> itemsInCategory = im.findItemsByCategory("Category1");
//        for (int i = 1; i <= itemsInCategory.getLength(); i++)
//        {
//            InventoryItem item = itemsInCategory.getEntry(i);
//            System.out.printf("%-10s %-20s %-15s %-10d %-10.2f %-30s%n",
//                    item.getId(),
//                    item.getName(),
//                    item.getCategory(),
//                    item.getStockQuantity(),
//                    item.getPrice(),
//                    item.getSuppliers());
//        }
//    }

    public static void main(String[] args)
    {
        InventoryManager im = new InventoryManager("src/inventorysystemproject/item_inventory.csv");
        im.loadItems();
        im.saveItems();
    }
}
