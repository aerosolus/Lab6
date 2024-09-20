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
 * This class manages input operations for various entities in the application.
 * It provides methods for collecting user input for HumanBeing, Car, and other related objects,
 * handling validation, and converting inputs to appropriate types.
 *
 * <p>This class is part of the utility layer, acting as an intermediary between
 * the user interface and the core logic of the application.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class InputManager {

    /**
     * Constant string used as a prompt for interactive mode input.
     */
    public static final String INPUT_INFO = "> ";

    /**
     * Constant string used as a prompt for command input in interactive mode.
     */
    public static final String INPUT_COMMAND = "$ ";

    /**
     * Scanner object for user input.
     */
    private Scanner userScanner;

    /**
     * Flag indicating whether the program is running in script mode.
     */
    private boolean scriptMode;

    /**
     * Regular expression pattern for validating numeric inputs.
     */
    private final String numberPattern = "-?\\d+(\\.\\d+)?";

    /**
     * Constructs an InputManager object with the specified scanner and script mode.
     *
     * @param scanner Scanner object for user input.
     * @param scriptMode Flag indicating whether the program is running in script mode.
     */
    public InputManager(Scanner scanner, boolean scriptMode) {
        this.userScanner = scanner;
        this.scriptMode = scriptMode;
    }

    /**
     * Prompts the user for HumanBeing parameters and returns a HumanBeing object.
     *
     * @return A HumanBeing object with validated and converted input values.
     * @throws ScriptException if invalid input is detected in script mode.
     */
    public HumanBeing askHumanBeing() throws ScriptException {
        return
                new HumanBeing.HumanBeingBuilder(
                        0, //kinda default
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

    /**
     * Prompts the user for a key value and validates it as an integer.
     *
     * @return The parsed integer value of the input.
     * @throws ScriptException if invalid input is detected in script mode.
     */
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
     * Prompts the user for the name of the HumanBeing object.
     *
     * @return The name of the HumanBeing object.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for the x-coordinate of the HumanBeing object.
     *
     * @return The parsed double value of the x-coordinate.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for the y-coordinate of the HumanBeing object.
     *
     * @return The parsed long value of the y-coordinate.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for coordinates and returns a Coordinates object.
     *
     * @return A Coordinates object with the entered coordinates.
     * @throws ScriptException if invalid input is detected in script mode.
     */
    public Coordinates askCoordinates() throws ScriptException {
        double x = askXcoordinate();
        long y = askYcoordinate();
        return new Coordinates(new Coordinates.CoordinatesBuilder(x, y));
    }

    /**
     * Prompts the user for the realHero value and returns a boolean.
     *
     * @return The parsed boolean value of the realHero input.
     * @throws ScriptException if invalid input is detected in script mode.
     */
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

    /**
     * Prompts the user for the hasToothpick value of the HumanBeing object.
     *
     * @return The boolean value representing whether the HumanBeing has a toothpick.
     * @throws ScriptException if invalid input is detected in script mode.
     */
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
     * Prompts the user for the impactSpeed value of the HumanBeing object.
     *
     * @return The long value representing the impact speed.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for the soundtrackName value of the HumanBeing object.
     *
     * @return The string value representing the soundtrack name.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for the minutesOfWaiting value of the HumanBeing object.
     *
     * @return The double value representing the waiting time in minutes.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for the weapon type of the HumanBeing object.
     *
     * @return WeaponType enum item representing the selected weapon type.
     * @throws ScriptException if invalid input is detected in script mode.
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
     * Prompts the user for the name of the Car object.
     *
     * @return The string value representing the car name.
     * @throws ScriptException if invalid input is detected in script mode.
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

    /**
     * Prompts the user for the cool status of the Car object.
     *
     * @return The boolean value representing whether the car is cool.
     * @throws ScriptException if invalid input is detected in script mode.
     */
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
     * Prompts the user for both the name and cool status of the Car object.
     *
     * @return A new Car object with the entered name and cool status.
     * @throws ScriptException if invalid input is detected in script mode.
     */
    public Car askCar() throws ScriptException {
        String carName = askCarName();
        boolean carCool = askCarCool();
        return new Car(new Car.CarBuilder(carName).setCool(carCool));
    }
}
