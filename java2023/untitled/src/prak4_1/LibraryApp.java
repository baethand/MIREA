package prak4_1;

import java.util.Arrays;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " (" + author + ")";
    }
}

class Reader {
    private String fullName;
    private int readerCardNumber;
    private String faculty;
    private String dateOfBirth;
    private String phoneNumber;

    public Reader(String fullName, int readerCardNumber, String faculty, String dateOfBirth, String phoneNumber) {
        this.fullName = fullName;
        this.readerCardNumber = readerCardNumber;
        this.faculty = faculty;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public void takeBook(int count) {
        System.out.println(fullName + " взял " + count + " книги");
    }

    public void takeBook(String... bookNames) {
        System.out.println(fullName + " взял книги: " + Arrays.toString(bookNames));
    }

    public void takeBook(Book... books) {
        String[] bookTitles = Arrays.stream(books).map(Book::toString).toArray(String[]::new);
        System.out.println(fullName + " взял книги: " + Arrays.toString(bookTitles));
    }

    public void returnBook(int count) {
        System.out.println(fullName + " вернул " + count + " книги");
    }

    public void returnBook(String... bookNames) {
        System.out.println(fullName + " вернул книги: " + Arrays.toString(bookNames));
    }

    public void returnBook(Book... books) {
        String[] bookTitles = Arrays.stream(books).map(Book::toString).toArray(String[]::new);
        System.out.println(fullName + " вернул книги: " + Arrays.toString(bookTitles));
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Book book1 = new Book("Приключения", "Иванов");
        Book book2 = new Book("Словарь", "Петров");
        Book book3 = new Book("Энциклопедия", "Сидоров");

        Reader reader1 = new Reader("Петров В. В.", 12345, "Факультет А", "01.01.1990", "+123456789");
        Reader reader2 = new Reader("Иванов А. А.", 54321, "Факультет Б", "02.02.1985", "+987654321");

        reader1.takeBook(3);
        reader1.takeBook("Приключения", "Словарь", "Энциклопедия");
        reader1.takeBook(book1, book2, book3);

        reader2.returnBook(2);
        reader2.returnBook("Приключения", "Словарь");
        reader2.returnBook(book1, book2);
    }
}

