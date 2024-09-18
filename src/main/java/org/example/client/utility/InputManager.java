package org.example.client.utility;

import org.example.client.exceptions.InvalidInputException;
import org.example.client.exceptions.NotNullException;
import org.example.client.exceptions.ScriptException;
import org.example.common.collectionEntities.Car;
import org.example.common.collectionEntities.Coordinates;
import org.example.common.collectionEntities.HumanBeing;
import org.example.common.collectionEntities.WeaponType;
import org.example.common.utility.PrintManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Менеджер ввода информации об организации.
 */
public class InputManager {

    /**
     * Константа строки приглашения для ввода в интерактивном режиме.
     */
    public static final String INPUT_INFO = "> ";

    /**
     * Константа строки приглашения для ввода команд в интерактивном режиме.
     */
    public static final String INPUT_COMMAND = "$ ";

    /**
     * Сканер для ввода.
     */
    private Scanner userScanner;

    /**
     * Режим скрипта.
     */
    private boolean scriptMode;

    /**
     * Шаблон для проверки числа.
     */
    private final String numberPattern = "-?\\d+(\\.\\d+)?";

    /**
     * Создает менеджер для ввода с помощью заданного сканера.
     * @param scanner сканер для ввода.
     * @param scriptMode режим чтения со скрипта
     */
    public InputManager(Scanner scanner, boolean scriptMode) {
        this.userScanner = scanner;
        this.scriptMode = scriptMode;
    }

    /**
     * Метод запрашивает у пользователя параметры HumanBeing и возвращает экземпляр данного класса.
     * @return экземпляр HumanBeing.
     * @throws ScriptException если введен неверный ввод в скрипте.
     */
    public HumanBeing askHumanBeing() throws ScriptException {
        return
                new HumanBeing.HumanBeingBuilder(
                        0, //kinda null
                        askName(),
                        askCoordinates(),
                        askHasToothpick(),
                        askSoundtrackName(),
                        askImpactSpeed(),
                        askMinutesOfWaiting())
                        .setWeaponType(askWeaponType())
                        .setRealHero(askRealHero())
                        .setCar(askCar()).build();
    }

    public Integer askKey() throws ScriptException {
        String strKey;
        Integer key;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите ключ объекта HumanBeing:");
                System.out.print(INPUT_INFO);
                strKey = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strKey);
                if (!strKey.matches(numberPattern)) throw new NumberFormatException();
                key = Integer.parseInt(strKey);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NumberFormatException exception) {
                PrintManager.printErr("Значение поля должно быть типом Integer.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return key;
    }

    /**
     * Метод запрашивает у пользователя имя-название объекта HumanBeing.
     * @return имя-название объекта HumanBeing.
     * @throws ScriptException если введен неверный ввод в скрипте.
     */
    public String askName() throws ScriptException {
        String name;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля name объекта HumanBeing:");
                System.out.print(INPUT_INFO);
                name = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(name);
                if (name.isEmpty()) throw new NotNullException();
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NotNullException exception) {
                PrintManager.printErr("Значение поля не может быть пустым.");
                if (scriptMode) throw new ScriptException();
                if (!userScanner.hasNext()) {
                    PrintManager.printErr("Работа программы завершена.");
                    System.exit(1);
                }
            } catch (IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return name;
    }

    /**
     * Метод для ввода значения координаты X
     * @return x
     * @throws ScriptException если введен неверный ввод в скрипте.
     */
    public double askXcoordinate() throws ScriptException {
        String strX;
        double x;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите координату x объекта HumanBeing:");
                System.out.print(INPUT_INFO);
                strX = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strX);
                if (!strX.matches(numberPattern)) throw new NumberFormatException();
                x = Double.parseDouble(strX);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NumberFormatException exception) {
                PrintManager.printErr("Значение поля должно быть типом double.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return x;
    }

    /**
     * Метод для ввода значения координаты Y
     * @return значение координаты Y
     * @throws ScriptException если была ошибка ввода значения в скрипте
     */
    public long askYcoordinate() throws ScriptException {
        String strY;
        long y;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите координату y объекта HumanBeing:");
                System.out.print(INPUT_INFO);
                strY = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strY);
                if (!strY.matches(numberPattern)) throw new NumberFormatException();
                y = Long.parseLong(strY);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NumberFormatException exception) {
                PrintManager.printErr("Значение поля должно быть типом long.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return y;
    }

    /**
     * Метод для ввода координат
     * @return объект класса Coordinates с заданными координатами
     * @throws ScriptException если была ошибка ввода значения в скрипте
     */
    public Coordinates askCoordinates() throws ScriptException {
        double x = askXcoordinate();
        long y = askYcoordinate();
        return new Coordinates(new Coordinates.CoordinatesBuilder(x, y));
    }

    public boolean askRealHero() throws ScriptException {
        String strRealHero;
        boolean realHero;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля realHero:");
                System.out.print(INPUT_INFO);
                strRealHero = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strRealHero);
                if (!(strRealHero.equals("true") || strRealHero.equals("false"))) throw new InvalidInputException("Некорректный ввод данных.");
                realHero = Boolean.parseBoolean(strRealHero);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (InvalidInputException exception) {
                PrintManager.printErr("Значение поля должно быть true или false.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return realHero;
    }

    public boolean askHasToothpick() throws ScriptException {
        String strHasToothpick;
        boolean hasToothpick;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля hasToothpick:");
                System.out.print(INPUT_INFO);
                strHasToothpick = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strHasToothpick);
                if (!(strHasToothpick.equals("true") || strHasToothpick.equals("false"))) throw new InvalidInputException("Некорректный ввод данных.");
                hasToothpick = Boolean.parseBoolean(strHasToothpick);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (InvalidInputException exception) {
                PrintManager.printErr("Значение поля должно быть true или false.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return hasToothpick;
    }

    /**
     * Метод для ввода значения поля impactSpeed объекта HumanBeing
     * @return значение поля impactSpeed
     * @throws ScriptException если была ошибка ввода значения в скрипте
     */
    public long askImpactSpeed() throws ScriptException {
        String strImpactSpeed;
        long impactSpeed;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля impactSpeed:");
                System.out.print(INPUT_INFO);
                strImpactSpeed = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strImpactSpeed);
                if (!strImpactSpeed.matches(numberPattern)) throw new NumberFormatException();
                impactSpeed = Long.parseLong(strImpactSpeed);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NumberFormatException exception) {
                PrintManager.printErr("Значение поля должно быть типом long.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return impactSpeed;
    }

    /**
     * Метод запрашивает у пользователя значение поля soundtrackName объекта HumanBeing.
     * @return значение поля soundtrackName объекта HumanBeing.
     * @throws ScriptException если введен неверный ввод в скрипте.
     */
    public String askSoundtrackName() throws ScriptException {
        String soundtrackName;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля soundtrackName:");
                System.out.print(INPUT_INFO);
                soundtrackName = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(soundtrackName);
                if (soundtrackName.isEmpty()) throw new NotNullException();
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NotNullException exception) {
                PrintManager.printErr("Значение поля не может быть пустым.");
                if (scriptMode) throw new ScriptException();
                if (!userScanner.hasNext()) {
                    PrintManager.printErr("Работа программы завершена.");
                    System.exit(1);
                }
            } catch (IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return soundtrackName;
    }

    /**
     * Метод для ввода значения координаты Y
     * @return значение координаты Y
     * @throws ScriptException если была ошибка ввода значения в скрипте
     */
    public Double askMinutesOfWaiting() throws ScriptException {
        String strMinutesOfWaiting;
        Double minutesOfWaiting;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля minutesOfWaiting:");
                System.out.print(INPUT_INFO);
                strMinutesOfWaiting = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strMinutesOfWaiting);
                if (!strMinutesOfWaiting.matches(numberPattern)) throw new NumberFormatException();
                minutesOfWaiting = Double.parseDouble(strMinutesOfWaiting);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (NumberFormatException exception) {
                PrintManager.printErr("Значение поля должно быть типом Double.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return minutesOfWaiting;
    }

    /**
     * Метод запрашивает у пользователя тип оружия.
     * @return тип оружия.
     * @throws ScriptException если введенные данные некорректны при работе скрипта.
     */
    public WeaponType askWeaponType() throws ScriptException {
        String strType;
        WeaponType weaponType;
        while (true) {
            try {
                PrintManager.printInfoMessage("Список типов оружия: " + WeaponType.nameToString());
                PrintManager.printInfoMessage("Введите тип оружия - одно из приведенных значений:");
                System.out.print(INPUT_INFO);
                strType = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strType);
                weaponType = WeaponType.valueOf(strType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (IllegalArgumentException exception) {
                PrintManager.printErr("Такого типа оружия нет в списке.");
                if (scriptMode) throw new ScriptException();
            } catch (IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return weaponType;
    }

    /**
     * Запрашивает у пользователя значение поля name объекта Car и возвращает его в виде строки.
     * @return Значение поля name объекта Car, введенное пользователем
     * @throws ScriptException если введенные данные некорректны при работе скрипта.
     */
    public String askCarName() throws ScriptException {
        String carName;
        try {
            PrintManager.printInfoMessage("Введите значение поля name объекта Car:");
            System.out.print(INPUT_INFO);
            carName = userScanner.nextLine().trim();
            if (scriptMode) PrintManager.printInfoMessage(carName);
            return carName;
        } catch (NoSuchElementException exception) {
            PrintManager.printErr("Значение поля непригодно для использования.");
            System.exit(1);
            if (scriptMode) throw new ScriptException();
        } catch (IllegalStateException exception) {
            PrintManager.printErr("Непредвиденная ошибка.");
            System.exit(1);
        }
        return null;
    }

    public boolean askCarCool() throws ScriptException {
        String strCarCool;
        boolean carCool;
        while (true) {
            try {
                PrintManager.printInfoMessage("Введите значение поля cool для объекта Car:");
                System.out.print(INPUT_INFO);
                strCarCool = userScanner.nextLine().trim();
                if (scriptMode) PrintManager.printInfoMessage(strCarCool);
                if (!(strCarCool.equals("true") || strCarCool.equals("false"))) throw new InvalidInputException("Некорректный ввод данных.");
                carCool = Boolean.parseBoolean(strCarCool);
                break;
            } catch (NoSuchElementException exception) {
                PrintManager.printErr("Значение поля непригодно для использования.");
                System.exit(1);
                if (scriptMode) throw new ScriptException();
            } catch (InvalidInputException exception) {
                PrintManager.printErr("Значение поля должно быть true или false.");
                if (scriptMode) throw new ScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                PrintManager.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return carCool;
    }

    /**
     * Запрашивает у пользователя имя объекта класса Car и значение поля cool,
     * чтобы создать объект Car с этими значениями
     * @return Новый объект Car с названием, введенным пользователем, или null, если название пусто, а также значением isCool
     * @throws ScriptException если введенные данные некорректны при работе скрипта.
     */
    public Car askCar() throws ScriptException {
        String carName = askCarName();
        boolean carCool = askCarCool();
        return new Car(new Car.CarBuilder(carName).setCool(carCool));
    }
}
