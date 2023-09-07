package prak2;

public class SomeBooks {
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
