package org.example.server.utility;

import org.example.common.collectionEntities.HumanBeing;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages a collection of HumanBeing objects.
 * Provides methods for adding, removing, updating, and querying the collection.
 * Handles serialization and deserialization of the collection to/from CSV files.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class CollectionManager {

    /**
     * The underlying map storing HumanBeing objects with unique integer IDs.
     */
    private LinkedHashMap<Integer, HumanBeing> humanBeingCollection = new LinkedHashMap<>();

    /**
     * The date when the collection was initialized.
     */
    private final Date dateOfInitialization = new Date();

    /**
     * The path to the file where the collection is saved.
     */
    private String filePath;

//    /**
//     * Constructs a CollectionManager with an initial collection and file path.
//     *
//     * @param collection The initial collection of HumanBeing objects.
//     */
//    public CollectionManager(LinkedHashMap<Integer, HumanBeing> collection) {
//        this.humanBeingCollection = collection;
//    }

    /**
     * Default constructor for CollectionManager.
     */
    public CollectionManager() {
        humanBeingCollection = new LinkedHashMap<>();
    }

    /**
     * Gets the current collection of HumanBeing objects.
     *
     * @return The current collection of HumanBeing objects.
     */
    public LinkedHashMap<Integer, HumanBeing> getCollection() {
        return humanBeingCollection;
    }


    /**
     * Возвращает дату инициализации коллекции
     *
     * @return дата инициализации коллекции
     */
    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }

    /**
     * Sets the entire collection of HumanBeing objects.
     *
     * @param humanBeings The new collection of HumanBeing objects.
     */
    public void setHumanBeingCollection(LinkedHashMap<Integer, HumanBeing> humanBeings) {
        humanBeingCollection = humanBeings;
    }

    /**
     * Returns a string with collection's class, date of creation and size.
     *
     * @return A formatted string with collection information.
     */
    public String collectionInfo() {
        return "Тип коллекции: " + humanBeingCollection.getClass().getName() + "\n"
                + "Дата инициализации: " + dateOfInitialization + "\n"
                + "Количество элементов: " + humanBeingCollection.size();
    }

    /**
     * Adds a HumanBeing object to the collection with a given ID.
     *
     * @param key The ID of the HumanBeing object.
     * @param humanBeing The HumanBeing object to add.
     */
    public void addToCollection(Integer key, HumanBeing humanBeing) {
        humanBeing.setCreationDate(LocalDateTime.now());
        humanBeingCollection.put(key, humanBeing);
    }

    /**
     * Retrieves a HumanBeing object from the collection by its ID.
     *
     * @param id The ID of the HumanBeing object to retrieve.
     * @return The HumanBeing object with the matching ID, or null if not found.
     */
    public HumanBeing getById(int id) {
        return humanBeingCollection.values().stream().filter(v -> v.getId().equals(id)).findFirst().get();
    }

    /**
     * Checks if the collection contains a HumanBeing object with the given ID.
     *
     * @param id The ID to check for in the collection.
     * @return True if the collection contains a HumanBeing object with the given ID, false otherwise.
     */
    public boolean containsId(Integer id) {
        return humanBeingCollection.values().stream().anyMatch(v -> v.getId().equals(id));
    }

    /**
     * Checks if the collection contains a key-value pair with the given key.
     *
     * @param key The key to check for in the collection.
     * @return True if the collection contains a key-value pair with the given key, false otherwise.
     */
    public boolean containsKey(Integer key) {
        return humanBeingCollection.containsKey(key);
    }

    /**
     * Removes a HumanBeing object from the collection by its key.
     *
     * @param key The key of the HumanBeing object to remove.
     */
    public void remove(Integer key) {
        humanBeingCollection.remove(key);
    }

    /**
     * Clears all elements from the collection.
     */
    public void clearCollection() {
        humanBeingCollection.clear();
    }

    // name in its natural form, especially
    /**
     * Removes all HumanBeing objects from the collection that are less than the given object.
     *
     * @param humanBeing The HumanBeing object to compare against.
     */
    public void removeLower(HumanBeing humanBeing) {
        humanBeingCollection.entrySet().removeIf(e -> e.getValue().compareTo(humanBeing) < 0);
    }

    /**
     * Removes all HumanBeing objects from the collection that have keys greater than the given value.
     *
     * @param key The key to compare against.
     */
    public void removeGreaterKey(Integer key) {
        humanBeingCollection.entrySet().removeIf(e -> e.getKey() > key);
    }

    /**
     * Removes all HumanBeing objects from the collection that have keys less than the given value.
     *
     * @param key The key to compare against.
     */
    public void removeLowerKey(Integer key) {
        humanBeingCollection.entrySet().removeIf(e -> e.getKey() < key);
    }

    /**
     * Gets the key corresponding to a specific ID in the collection.
     *
     * @param id The ID to find the key for.
     * @return The key corresponding to the given ID, or null if not found.
     */
    public Integer getKeyById(int id) {
        for (Map.Entry<Integer, HumanBeing> entry : humanBeingCollection.entrySet()) {
            if (entry.getValue().getId() == id) {
                return entry.getKey();
            }
        }
        return null;
    }

    public HumanBeing getByKey(Integer key) {
        return humanBeingCollection.get(key);
    }

    /**
     * Updates an element in the collection with the specified ID.
     *
     * @param key        The ID of the HumanBeing object to update.
     * @param humanBeing The updated HumanBeing object.
     */
    public void update(Integer key, HumanBeing humanBeing) {
        humanBeing.setCreationDate(LocalDateTime.now());
        humanBeingCollection.remove(getKeyById(humanBeing.getId()));
        humanBeingCollection.put(key, humanBeing);
    }

//    /**
//     * Prints the contents of the collection to the console.
//     */
//    public String showCollection() {
//        String result = "";
//        if (humanBeingCollection == null || humanBeingCollection.isEmpty()) {
//            result = "LinkedHashMap пуст или null.";
//            return result;
//        } else {
//            for (Map.Entry<Integer, HumanBeing> entry : humanBeingCollection.entrySet()) {
//                result += entry.getKey() + " : " + entry.getValue() + "\n";
//            }
//            return result;
//        }
//    }

    /**
     * Сортирует коллекцию по умолчанию (по названию - имени)
     */
    public String showSortedCollection() {
        // Convert LinkedHashMap entries to List
        List<Map.Entry<Integer, HumanBeing>> list = new ArrayList<>(humanBeingCollection.entrySet());

        // Sort the List based on the values' names
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        // Create a new sorted LinkedHashMap
        LinkedHashMap<Integer, HumanBeing> sortedMap = new LinkedHashMap<>();

        // Add the sorted entries back to the new LinkedHashMap
        for (Map.Entry<Integer, HumanBeing> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        // Iterate through map entries
        for (Map.Entry<Integer, HumanBeing> entry : sortedMap.entrySet()) {

            // Append key-value pair
            sb.append(entry.getKey()).append(" : ").append(entry.getValue());
            sb.append("\n");

        }
        return sb.toString();
    }

    /**
     * Prints the contents of the collection in ascending order based on the HumanBeing objects themselves.
     */
    public String printCollectionAscending() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        Map<Integer, HumanBeing> sortedMap = humanBeingCollection.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Iterate through map entries
        for (Map.Entry<Integer, HumanBeing> entry : sortedMap.entrySet()) {

            // Append key-value pair
            sb.append(entry.getKey()).append(" : ").append(entry.getValue());
            sb.append("\n");

        }
        return sb.toString();
    }

    /**
     * Prints the contents of the collection in descending order based on the HumanBeing objects themselves.
     */
    public String printCollectionDescending() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        // Sort the entries based on keys in descending order and collect them into a new LinkedHashMap
        Map<Integer, HumanBeing> sortedMap = humanBeingCollection.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Iterate through map entries
        for (Map.Entry<Integer, HumanBeing> entry : sortedMap.entrySet()) {

            // Append key-value pair
            sb.append(entry.getKey()).append(" : ").append(entry.getValue());
            sb.append("\n");

        }
        return sb.toString();
    }

    /**
     * Prints the car field values of HumanBeing instances of the collection in descending order.
     */
    public String printFieldDescendingCar() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        // Sort the entries based on car fields in descending order and collect them into a new LinkedHashMap
        Map<Integer, HumanBeing> sortedMap = humanBeingCollection.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((entry, anotherEntry) ->
                        anotherEntry.getCar().getName().compareTo(entry.getCar().getName())))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Iterate through map entries
        for (Map.Entry<Integer, HumanBeing> entry : sortedMap.entrySet()) {

            // Append key-value pair
            sb.append(entry.getKey()).append(" : ").append(entry.getValue());
            sb.append("\n");

        }
        return sb.toString();
    }

    /**
     * Generates a new ID for a HumanBeing object.
     *
     * @return A new unique ID for a HumanBeing object.
     */
    public Integer generateId() {
        int count = 0;
        int id = 1;
        while (count != humanBeingCollection.size()) {
            for (HumanBeing humanBeing : humanBeingCollection.values()) {
                count++;
                if (humanBeing.getId() == id) {
                    id++;
                    count = 0;
                    break;
                }
            }
        }
        return id;
    }
}