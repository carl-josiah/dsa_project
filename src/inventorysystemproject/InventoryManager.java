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

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            reader.readLine();
            while ((line = reader.readLine()) != null)
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

    public void saveItems()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false)))
        {
            Iterator<String> keyIterator = items.getKeyIterator();

            writer.write("ID,Name,Category,Stock Quantity,Price,Supplier Information");
            writer.newLine();
            while (keyIterator.hasNext())
            {
                String key = keyIterator.next();
                InventoryItem item = items.getValue(key);
                System.out.println(items.getValue(key));
                String line = String.format("%s,%s,%s,%d,%.2f,%s",
                        item.getId(),
                        item.getName(),
                        item.getCategory(),
                        item.getStockQuantity(),
                        item.getPrice(),
                        item.getSuppliers());
                writer.write(line);
                writer.newLine();
            }
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
//        InventoryManager im = new InventoryManager("src/inventorysystemproject/item_inventory.csv");
//        InventoryItem item1 = new InventoryItem("ID-001", "Item1", "Category1", 1, 10.0);
//        item1.addSuppliers("BRO1");
//        InventoryItem item2 = new InventoryItem("ID-002", "Item2", "Category2", 1, 10.0);
//        item2.addSuppliers("BRO2");
//        InventoryItem item3 = new InventoryItem("ID-003", "Item3", "Category3", 1, 10.0);
//        item3.addSuppliers("BRO3");
//        im.loadItems();
//        im.addItem(item1);
//        im.addItem(item2);
//        im.addItem(item3);
//        im.saveItems();
//        System.out.println(im.findItemById("ID-001"));
//    }
}
