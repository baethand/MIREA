package prak1;

public class Dog {
    private float height;
    private float weight;
    private int age;
    private String name;
    private String breed;
    private String color;

    public Dog() {}

    public Dog(int age, float height, float weight, String name, String breed, String color){
        this.age = age;
        this.height = height ;
        this.weight= weight;
        this.name = name ;
        this.breed= breed;
        this.color = color;
    }

    public String toString(){
        String res = String.format("Name: {1} | Age: {2} | Height: {3} | Weight: {4} | Breed: {5} | Color: {6}", name,age,height,weight,breed,color);
        return res;
    }

    public int geyHumanAge(){
        return age*7;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }
}
