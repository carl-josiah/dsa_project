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
        return ;
    }

    public void saveItems()
    {
        return ;
    }

    public void addItem(InventoryItem item)
    {
        String itemId = item.getId();
        items.add(itemId, item);
    }

    public void removeItem(String id)
    {
        InventoryItem removedItem = items.remove(id);
    }

    public InventoryItem findItemById(String id)
    {
        return (null);
    }

    public void updateStockQuantity(String id, int newQuantity)
    {
        return ;
    }

    public void displayAllItems()
    {
        Iterator<String> keyIterator = items.getKeyIterator();

        System.out.println(String.format("%-10s %-20s %-15s %-10s %-10s %-30s",
                "ID", "Product Name", "Category", "Quantity", "Price", "Suppliers"));
        System.out.println("----------------------------------------------------------------------------------------------------------" +
                "------------------------------------------------------");
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
        return (null);
    }

    public static void main(String[] args)
    {
        InventoryItem item1 = new InventoryItem("ID-101", "Name", "Category", 1, 10.0);
        item1.addSuppliers("Mister");
        item1.addSuppliers("Mister1");
        item1.addSuppliers("Mister2");
        System.out.println(item1.getSuppliers());
        InventoryManager inventoryManager = new InventoryManager("src/inventorysystemproject/item_inventory.csv");
        inventoryManager.addItem(item1);
        inventoryManager.displayAllItems();
    }
}
