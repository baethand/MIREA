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
