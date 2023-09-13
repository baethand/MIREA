package prak2;

import java.util.ArrayList;

public class BookShelf {
    ArrayList <SomeBooks> bookshelf = new ArrayList<>();

    public ArrayList<SomeBooks> getSortedBookShelf(ArrayList <SomeBooks> bookshelf){
        for (int out = bookshelf.size() - 1; out >= 1; out--){
            for (int in = 0; in < out; in++){
                if(bookshelf.get(in).getBook().getAge() > bookshelf.get(in+1).getBook().getAge())
                    toSwap(in, in + 1);
            }
        }
        return bookshelf;
    }

    private void toSwap(int first, int second){
        SomeBooks dummy = bookshelf.get(first);
        bookshelf.set(first, bookshelf.get(second));
        bookshelf.set(second, dummy);
    }

    public void print(ArrayList <SomeBooks> bookshelf){
        for (int i = 0; i< bookshelf.size(); i++){
            System.out.println(bookshelf.get(i).getBook().getAge() + " - " + bookshelf.get(i).getQuantity());
        }
    }
}

class SomeBooks {
    private Book book;
    private int quantity;

    public Book getBook() {
        return book;
    }

    public SomeBooks(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Book {
    private int currentPage;
    private ArrayList<String> pages;
    private int quantityPages;
    private String name;
    private String author;
    private int age;

    public Book(ArrayList<String> pages,
                int quantityPages,
                String name,
                String author,
                int age) {
        this.pages = pages;
        this.quantityPages = quantityPages;
        this.name = name;
        this.author = author;
        this.age = age;
    }

    public String toString(){
        return String.format("Name {1} | prak2.Author {2} | Quantity of pages: {3}", name, author, quantityPages);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPages(ArrayList<String> pages) {
        this.pages = pages;
    }

    public void setQuantityPages(int quantityPages) {
        this.quantityPages = quantityPages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ArrayList<String> getPages() {
        return pages;
    }

    public int getQuantityPages() {
        return quantityPages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setCurPage(int num){
        currentPage = num-1;
    }
    public void nextPage(){
        currentPage++;
    }

    public void prevPage(){
        currentPage--;
    }

    public void printPage(int num){
        System.out.println(pages.get(num));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
