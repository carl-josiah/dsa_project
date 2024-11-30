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

    public String getSuppliers()
    {
        StringBuilder supplierList = new StringBuilder();

        for (int i = 1; i <= suppliers.getLength(); i++)
            supplierList.append(suppliers.getEntry(i)).append("; ");
        if (!supplierList.isEmpty())
            supplierList.setLength(supplierList.length() - 2);
        return (supplierList.toString());
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
        return (String.format("%-10s %-20s %-15s %-10d %-10.2f %-30s%n",
                getId(),
                getName(),
                getCategory(),
                getStockQuantity(),
                getPrice(),
                getSuppliers()
        ));
    }

//    public static void main(String[] args)
//    {
//        InventoryItem item = new InventoryItem("ID-102",
//                "Name", "Category", 5, 10);
//        item.addSuppliers("JONNY KIM");
//        item.addSuppliers("MR DAVIS");
//        System.out.println(item.getSuppliers());
//    }
}