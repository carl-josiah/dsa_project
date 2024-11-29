package inventorysystemproject;

public class InventoryItem
{
    private String id;
    private String name;
    private String category;
    private int stockQuantity;
    private double price;
    private AList<String> suppliers;

    public InventoryItem(String id, String name, String category, int stockQuantity, double price)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.suppliers = new AList<>();
    }

    public String getId()
    {
        return (id);
    }

    public String getName()
    {
        return (name);
    }

    public String getCategory()
    {
        return (category);
    }

    public int getStockQuantity()
    {
        return (stockQuantity);
    }

    public double getPrice()
    {
        return (price);
    }

    public AList<String> getSuppliers()
    {
        return (suppliers);
    }

    public void setStockQuantity(int stockQuantity)
    {
        if (stockQuantity > 0)
            this.stockQuantity = stockQuantity;
        else
            throw new IllegalArgumentException("Stock quantity must be higher than zero.");
    }

    public void addSuppliers(String supplierInfo)
    {
        if (supplierInfo != null && !supplierInfo.isEmpty())
            suppliers.add(supplierInfo);
    }

    @Override
    public String toString()
    {
        return ("ID: " + getId() + "\n" +
                "Name: " + getName() + "\n" +
                "Category: " + getCategory() + "\n" +
                "Stock Quantity: " + getStockQuantity() + "\n" +
                "Price: " + getPrice() + "\n" +
                "Suppliers: " + getSuppliers() + "\n");
    }
}
