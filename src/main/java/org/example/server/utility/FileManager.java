package org.example.server.utility;

import org.example.common.collectionEntities.Car;
import org.example.common.collectionEntities.Coordinates;
import org.example.common.collectionEntities.HumanBeing;
import org.example.common.collectionEntities.WeaponType;
import org.example.common.utility.PrintManager;
import org.example.server.ServerApplication;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Класс FileManager, который отвечает за запись коллекции в файл и чтение из файла.
 */
public class FileManager {

    /**
     * Название файла
     */
    private final String fileName;

    /**
     * Создает новый экземпляр класса FileManager с указанным именем файла и настройками XStream.
     *
     * @param fileName имя файла
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Переменная, в которой хранится путь к папке с файлами.
     */
    public static String path;

    /**
     * Метод для получения имени файла из переменной окружения.
     * @return имя файла
     */
    public static String getFileName(){
        try {
            path = System.getenv("STORAGE_FILE"); // STORAGE_FILE - полный путь до файла, включая его название
            String[] checkPaths = path.split(";");
            if (checkPaths.length > 1) {
                PrintManager.printErr("В этой переменной содержится более одного пути к файлам. Работа сервера завершена.");
                System.exit(1);
            }
        } catch (NullPointerException e) {
            PrintManager.printErr("Некорректная переменная окружения. Работа сервера завершена.");
            System.exit(1);
        }
        File name = new File(path);
        return name.getName();
    }

    /**
     * Constant string representing the header row of the CSV file.
     * Contains column names for each field in the HumanBeing object.
     */
    static final String FILE_HEADER = "KEY" +
            "\t" + "ID"
            + "\t" + "NAME"
            + "\t" + "X"
            + "\t" + "Y"
            + "\t" + "CREATION_DATE"
            + "\t" + "REAL_HERO"
            + "\t" + "HAS_TOOTHPICK"
            + "\t" + "IMPACT_SPEED"
            + "\t" + "SOUNDTRACK_NAME"
            + "\t" + "MINUTES_OF_WAITING"
            + "\t" + "WEAPON_TYPE"
            + "\t" + "CAR_NAME"
            + "\t" + "CAR_COOL" + "\n";


    /**
     * Writes the given map of HumanBeing objects to a CSV file.
     *
     * @param humanBeings Map of HumanBeing objects to be written to the file.
     * @throws IOException if there's an error during file writing.
     */
    public void writeCollection(LinkedHashMap<Integer, HumanBeing> humanBeings) {
        if (!fileName.isEmpty()) {
            try{
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));
                out.write(FILE_HEADER.getBytes());
                byte[] buff = lines(humanBeings).getBytes();
                out.write(buff);
                out.close();
                PrintManager.printInfoMessage("Коллекция сохранена в файл.");
            } catch (IOException exception) {
                PrintManager.printErr("Файл не может быть открыт или является директорией.");
            }
        } else {
            PrintManager.printErr("Файл поврежден или его название указано неверно.");
        }
    }

    /**
     * Generates a single line of CSV data for a given HumanBeing object.
     *
     * @param humanBeing The HumanBeing object whose data should be converted to CSV format.
     * @return A string representation of the HumanBeing object in CSV format.
     */
    private String line(HumanBeing humanBeing){
        return "\t" + humanBeing.getId()
                + "\t" + humanBeing.getName()
                + "\t" + humanBeing.getCoordinates().getX()
                + "\t" + humanBeing.getCoordinates().getY()
                + "\t" + humanBeing.getCreationDate()
                + "\t" + humanBeing.isRealHero()
                + "\t" + humanBeing.isHasToothpick()
                + "\t" + humanBeing.getImpactSpeed()
                + "\t" + humanBeing.getSoundtrackName()
                + "\t" + humanBeing.getMinutesOfWaiting()
                + "\t" + humanBeing.getWeaponType()
                + "\t" + humanBeing.getCar().getName()
                + "\t" + humanBeing.getCar().isCool();
    }

    /**
     * Converts a map of HumanBeing objects to a CSV-formatted string.
     *
     * @param humanBeings Map of HumanBeing objects to be converted to CSV format.
     * @return A string representation of all HumanBeing objects in CSV format.
     */
    private String lines(LinkedHashMap<Integer, HumanBeing> humanBeings){
        String result = "";
        for (Map.Entry<Integer, HumanBeing> entry : humanBeings.entrySet()) {
            Integer key = entry.getKey();
            HumanBeing value = entry.getValue();
            result = result + key + line(value) + "\n";
        }
        return result;
    }

    /**
     * Reads a CSV file and returns a map of HumanBeing objects.
     *
     * @return linkedHashMap of HumanBeing objects keyed by their ID.
     * @throws IOException if there's an error during file reading.
     */
    public void readCollection() {
        if (!fileName.isEmpty()) {
            try {
                LinkedHashMap<Integer, HumanBeing> linkedHashMap = new LinkedHashMap<>();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                String line;
                if ((reader.readLine() + "\n").equals(FILE_HEADER)) {
                    while ((line = reader.readLine()) != null) {
                        String[] properties = line.split("\t"); // Assuming tab symbol as the delimiter
                        Integer key = Integer.parseInt(properties[0]); // First value is the key
                        HumanBeing result = new HumanBeing();
                        result.setId(Integer.valueOf(properties[1]));
                        if ((properties[2].equals("null")) || properties[2].isEmpty()) {
                            result.setName("standardName"); //if null, then set it to smt standard
                        } else {
                            result.setName(properties[2]);
                        }
                        result.setCoordinates(new Coordinates(
                                new Coordinates.CoordinatesBuilder(Double.parseDouble(properties[3]),
                                        Long.parseLong(properties[4]))));
                        if (properties[5].equals("null")) {
                            result.setCreationDate(LocalDateTime.now()); //if null, then set it to smt standard
                        } else {
                            result.setCreationDate(LocalDateTime.parse(properties[5]));
                        }
                        result.setRealHero(Boolean.parseBoolean(properties[6]));
                        result.setHasToothpick(Boolean.parseBoolean(properties[7]));
                        result.setImpactSpeed(Long.parseLong(properties[8]));
                        if (properties[9].equals("null")) {
                            result.setSoundtrackName("standardSoundtrackName"); //if null, then set it to smt standard
                        } else {
                            result.setSoundtrackName(properties[9]);
                        }
                        if (properties[10].equals("null")) {
                            result.setMinutesOfWaiting(Double.valueOf(0)); //if null, then set it to smt standard
                        } else {
                            result.setMinutesOfWaiting(Double.valueOf(properties[10]));
                        }
                        if (properties[11].equals("null")) {
                            result.setWeaponType(WeaponType.AXE); //if null, then set it to smt standard
                        } else {
                            result.setWeaponType(WeaponType.valueOf(properties[11]));
                        }
                        if (properties[12].equals("null")) {
                            Car car = new Car(new Car.CarBuilder("standardCarName")); //if null, then set it to smt standard
                            car.setCool(Boolean.parseBoolean(properties[13]));
                            result.setCar(car);
                        } else {
                            Car car = new Car(new Car.CarBuilder(properties[12]));
                            car.setCool(Boolean.parseBoolean(properties[13]));
                            result.setCar(car);
                        }
                        linkedHashMap.put(key, result);
                    }
                }
                ServerApplication.collectionManager.setHumanBeingCollection(linkedHashMap);
            } catch (IOException exception) {
                PrintManager.printInfoMessage("Файл не найден или доступ запрещен. Работа сервера завершена.");
                System.exit(1);
            } catch (NoSuchElementException exception) {
                PrintManager.printInfoMessage("Файл пуст. Работа сервера завершена.");
                System.exit(1);
            } catch (NullPointerException | NumberFormatException exception) {
                PrintManager.printInfoMessage("Неверный формат коллекции в файле. Работа сервера завершена.");
                System.exit(1);
            } catch (IllegalStateException exception) {
                PrintManager.printInfoMessage("Непредвиденная ошибка. Работа сервера завершена.");
                System.exit(1);
            }
        } else {
            PrintManager.printInfoMessage("Файл поврежден или ошибка в названии. Работа сервера завершена.");
            System.exit(1);
        }
    }
}
