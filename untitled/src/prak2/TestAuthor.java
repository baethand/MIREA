package prak2;

public class TestAuthor {
    public static void init(){
        Author author = new Author("Alex", "qwerty@gmail.com", 'М');
        System.out.println(author.getName());
        System.out.println(author.getEmail());
        System.out.println(author.getGender());
        author.setEmail("nice@mail.ru");
        System.out.println(author.getEmail());
        author.toString();

    }
}
