package org.example.server.utility;

import org.example.common.collectionEntities.HumanBeing;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Класс Validator предназначен для валидации списка организаций.
 */
public class Validator {

    /**
     * Список организаций, которые необходимо проверить.
     */
    LinkedHashMap<Integer, HumanBeing> humanBeings;

    /**
     * Конструктор класса.
     *
     * @param humanBeings LinkedHashMap объектов HumanBeing, которые необходимо проверить.
     */
    public Validator(LinkedHashMap<Integer, HumanBeing> humanBeings) {
        this.humanBeings = humanBeings;
    }

    /**
     * Метод валидации списка организаций.
     *
     * @return Список организаций, которые прошли валидацию.
     */
    public LinkedHashMap<Integer, HumanBeing> validate() {
        Set<Integer> idSet = new HashSet<>();
        Set<Integer> keys = humanBeings.keySet();
        for (Integer key : keys) {
            HumanBeing humanBeing = humanBeings.get(key);
            if (humanBeing.getId() <= 0 || !idSet.add(humanBeing.getId())) humanBeings.remove(key);
            if (humanBeing.getName() == null || humanBeing.getName().isEmpty()) humanBeings.remove(key);
            if (humanBeing.getCreationDate() == null) humanBeings.remove(key);
            if (humanBeing.getSoundtrackName() == null) humanBeings.remove(key);
            if (humanBeing.getMinutesOfWaiting() == null) humanBeings.remove(key);
            if (humanBeing.getCar() == null) humanBeings.remove(key);
        }
        return humanBeings;
    }
}
