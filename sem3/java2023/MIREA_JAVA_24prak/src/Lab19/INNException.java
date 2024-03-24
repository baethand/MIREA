package Lab19;

import java.util.Scanner;

class INNException extends Exception{
    public INNException(String message) {
        super(message);
    }
    public static void isValid(String inn) throws INNException {
        if (inn.length() != 10 && inn.length() != 12) {
            throw new INNException("Короткая длина");
        }
        try {
            Long.parseLong(inn); // Попытка преобразования в число
        } catch (NumberFormatException e) {
            throw new INNException("Не число");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер ИНН в формате 10-12 цифр: ");
        String inn = scanner.next();

        try {
            isValid(inn);
        } catch (INNException e) {
            System.out.println("Неверный формат ИНН: "+ e.getMessage());
        }
    }
}