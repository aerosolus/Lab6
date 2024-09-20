package org.example.server.utility;

import org.example.common.collectionEntities.HumanBeing;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Validates a collection of HumanBeing objects.
 * Ensures uniqueness of IDs and checks for null values in required fields.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class Validator {

    /**
     * Map containing HumanBeing objects to be validated.
     */
    LinkedHashMap<Integer, HumanBeing> humanBeings;

    /**
     * Constructs a Validator instance with the given map of HumanBeing objects.
     *
     * @param humanBeings LinkedHashMap containing HumanBeing objects to validate.
     */
    public Validator(LinkedHashMap<Integer, HumanBeing> humanBeings) {
        this.humanBeings = humanBeings;
    }

    /**
     * Performs validation on the collection of HumanBeing objects.
     *
     * @return A new LinkedHashMap containing valid HumanBeing objects after validation.
     * Invalid entries are removed from the returned map.
     */
    public LinkedHashMap<Integer, HumanBeing> validate() {
        Set<Integer> idSet = new HashSet<>();
        Set<Integer> keys = humanBeings.keySet();
        Set<Integer> resultKeys = new HashSet<>();
        for (Integer key : keys) {
            HumanBeing humanBeing = humanBeings.get(key);
            if (humanBeing.getId() <= 0 || !idSet.add(humanBeing.getId())) resultKeys.add(key);
            if (humanBeing.getName() == null || humanBeing.getName().isEmpty()) resultKeys.add(key);
            if (humanBeing.getCreationDate() == null) resultKeys.add(key);
            if (humanBeing.getSoundtrackName() == null) resultKeys.add(key);
            if (humanBeing.getMinutesOfWaiting() == null) resultKeys.add(key);
            if (humanBeing.getCar() == null) resultKeys.add(key);
        }
        for (Integer key : resultKeys) {
            humanBeings.remove(key);
        }
        return humanBeings;
    }
}
