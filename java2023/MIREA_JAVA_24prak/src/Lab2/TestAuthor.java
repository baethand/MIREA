package Lab2;

import java.util.Scanner;
public class TestAuthor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Author a1 = new Author("Ivan", "IvanIvanov999@gmail.com", 'g');
        System.out.println(a1.toString());
        System.out.println("Введите почту преподавателя: ");
        String new_email = in.nextLine();
        System.out.println("Имя преподавателя: "+a1.getName());
        System.out.println("Новая почта преподавателя: "+a1.getEmail());
        System.out.println("Пол преподавателя: "+a1.getGender());

    }
}
