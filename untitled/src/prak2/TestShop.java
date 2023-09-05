package prak2;

import java.util.Scanner;

public class TestShop {
    public static void init(){
        Shop shop = new Shop();
        int button = -1;
        Scanner mainScan = new Scanner(System.in);
        button = mainScan.nextInt();
        switch (button){
            case (1):
                mainScan.close();
                shop.addPC();
                button = mainScan.nextInt();
            case (2):
                mainScan.close();
                shop.printComputers();
                button = mainScan.nextInt();
            case (0):
            default:
        }
    }
}
