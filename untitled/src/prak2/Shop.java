package prak2;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    ArrayList<Computer> computers;

    public Shop(){
        computers = new ArrayList<>();
    }

    public void addPC(String cpu, String videoCard,
                      String ram, String mainboard,
                      int storage
    ){
        Computer newComputer = new Computer(cpu,videoCard,ram,mainboard,storage);
    }

    public void addPC(){
        Scanner scanner = new Scanner(System.in);
        String cpu = scanner.next();
        String videoCard = scanner.next();
        String ram = scanner.next();
        String mainboard = scanner.next();
        int storage = scanner.nextInt();
        Computer newComputer = new Computer(cpu, videoCard,ram,mainboard,storage);
        computers.add(newComputer);
        System.out.println( newComputer.toString());
    }

    public void delPC(int num){
        computers.remove(num+1);
    }

    public void printComputers(){
        for (int i = 0; i<computers.size(); i++){
            System.out.println(computers.get(i).toString());
        }
    }
}
