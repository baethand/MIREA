package prak2;

import java.util.ArrayList;

public class TestBook {
    public static void main(String[] args){
        BookShelf bookShelf = new BookShelf();
        Book book1 = new Book(new ArrayList<>(), 123,
                "Три мушкетёра", "Me", 2014);
        bookShelf.bookshelf.add(new SomeBooks(book1, 2));

        Book book2 = new Book(new ArrayList<>(), 321,
                "Четыре мушкетёра", "SomeOne", 2012);
        bookShelf.bookshelf.add(new SomeBooks(book2, 5));


        bookShelf.print(bookShelf.bookshelf);
        System.out.println("--------");
        bookShelf.print(bookShelf.getSortedBookShelf(bookShelf.bookshelf));
    }
}
