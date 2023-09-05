package prak1;

import java.util.ArrayList;

public class Book {
    private int currentPage;
    private ArrayList<String> pages;
    private int quantityPages;
    private String name;
    private String author;
    private String color;

    public Book(ArrayList<String> pages, int quantityPages, float weight, String name, String breed, String color){
        currentPage = 0;
        this.quantityPages = quantityPages;
        this.name = name;
        this.author = breed;
        this.color = color;
    }

    public String toString(){
        return String.format("Name {1} | prak2.Author {2} | Quantity of pages: {3} | Color {4}", name, author, quantityPages, color);
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

    public void setColor(String color) {
        this.color = color;
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

    public String getColor() {
        return color;
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

}
