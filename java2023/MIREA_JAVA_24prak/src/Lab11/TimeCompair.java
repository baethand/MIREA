package Lab11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.time.LocalTime;

import static java.time.LocalTime.*;

public class TimeCompair {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите дату в формате dd.mm.yyyy:");
        String date = in.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date userDate = null;
        boolean flag = true;
        while (flag)
            try {
                //Parsing the String
                userDate = dateFormat.parse(date);
                flag = false;
            } catch (ParseException e) {
                System.out.println("Вы ввели неправильную дату, попробуйте снова: ");
                date = in.next();
            }
        System.out.println(userDate);
        Date nowDate = new Date(System.currentTimeMillis());
        assert userDate != null;
        if(userDate.compareTo(nowDate) > 0){
            System.out.println("Дата пользователя позже текущей даты");
        }else{
            System.out.println("Дата пользователя раньше текушей даты");
        }

    }
}
