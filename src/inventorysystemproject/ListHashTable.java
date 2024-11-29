package inventorysystemproject;
import java.util.Iterator;

public class ListHashTable<K, V> implements HashTableInterface<K, V>
{
    private AList<K> keys;
    private AList<V> values;
    private int numberOfEntries;
    private int size;
    private static final int defaultCapacity = 1000;
    private static final int maxCapacity = 10000;

    public ListHashTable()
    {
        this(defaultCapacity);
    }

    public ListHashTable(int initialCapacity)
    {
        this.keys = new AList<>(initialCapacity);
        this.values = new AList<>(initialCapacity);
        size = initialCapacity;
        numberOfEntries = 0;
        for (int i = 1; i <= initialCapacity; i++)
        {
            keys.add(i, null);
            values.add(i, null);
        }
    }

//    private int hashString(K key)
//    {
//        String keyString = key.toString();
//        int hashValue = 0;
//        int prime = 31;
//
//        for (int i = 0; i < keyString.length(); i++)
//            hashValue = (hashValue * prime + keyString.charAt(i)  % numberOfEntries);
//        return (hashValue);
//    }
//
//    private int hashInt(K key)
//    {
//        String keyString = key.toString();
//        String numberString = keyString.replace("ID-", "");
//        int number = Integer.parseInt(numberString);
//        return (number % numberOfEntries);
//    }

    public int hash(K key) // for now
    {
        String keyString = key.toString();
        String numberString = keyString.replace("ID-", "");
        int number = Integer.parseInt(numberString);
        return (number % numberOfEntries);
    }

    public V add(K key, V value)
    {
        int i = 1;
        Iterator<K> keyIterator = getKeyIterator();

        while (keyIterator.hasNext())
        {
            K currentKey = keyIterator.next();
            if (currentKey.equals(key))
                return (values.replace(i, value));
            i++;
        }
        keys.replace(i, key);
        values.replace(i, value);
        numberOfEntries++;
        return (null);
    }


    public V remove(K key)
    {
        int i = 1;
        Iterator<K> keyIterator = getKeyIterator();

        while (keyIterator.hasNext())
        {
            K currentKey = keyIterator.next();
            if (currentKey.equals(key))
            {
                V currentValue = values.replace(i, null);
                keys.replace(i, null);
                numberOfEntries--;
                return (currentValue);
            }
            i++;
        }
        return (null);
    }

    public V getValue(K key)
    {
        int i = 1;
        Iterator<K> keyIterator = getKeyIterator();

        while (keyIterator.hasNext())
        {
            K currentKey = keyIterator.next();
            if (currentKey.equals(key))
                return (values.getEntry(i));
            i++;
        }
        return (null);
    }

    public boolean contains(K key)
    {
        Iterator<K> keyIterator = getKeyIterator();

        while (keyIterator.hasNext())
        {
            K currentKey = keyIterator.next();
            if (currentKey.equals(key))
                return (true);
        }
        return (false);
    }

    public boolean isEmpty()
    {
        return (numberOfEntries == 0);
    }

    public int getSize()
    {
        return (size);
    }

    public void clear()
    {
        int i = 1;
        Iterator<K> keyIterator = getKeyIterator();

        while (keyIterator.hasNext())
        {
            K currentKey = keyIterator.next();
            keys.replace(i, null);
            values.replace(i, null);
            i++;
        }
    }

    public Iterator<K> getKeyIterator()
    {
        return (keys.getIterator());
    }

    public Iterator<V> getValueIterator()
    {
        return (values.getIterator());
    }

//    public static void main(String[] args)
//    {
//        ListHashTable<String, String> table = new ListHashTable<>();
//
//        table.add("001", "REMOVEMEEEE");
//        table.add("002", "please");
//        table.add("ID-003", "bro");
//        table.add("ID-004", "eating");
//        table.add("ID-005", "GNX");
//        System.out.println(table.hash("001"));
//        System.out.println(table.hash("002"));
//        System.out.println(table.hash("ID-003"));
//        System.out.println(table.hash("ID-004"));
//        System.out.println(table.hash("ID-005"));
//        System.out.println(table.keys);
//        System.out.println(table.values);
//        table.clear();
//        System.out.println(table.keys);
//        System.out.println(table.values);
//    }
}
