package org.example.client.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

//    public String getMessage() {
//        return "Некорректный ввод данных.";
//    }
}
