package Lab4;

import java.util.Scanner;

enum Season {
    WINTER(0),
    SPRING(10),
    SUMMER(20) {
        @Override
        public String getDescription() {
            return "Теплое время года";
        }
    },
    AUTUMN(5);
    private int avgtemp;

    Season(int avgtemp) {
        this.avgtemp = avgtemp;
    }

    public String getInfo() {
        switch (this) {
            case AUTUMN:
                return "Мрачное и депрессивное время года с частыми дождями, не рекомендуется находиться в Санкт-петербурге";
            case WINTER:
                return "Самое холодное время года, рекомендуется прятаться под пледом и принимать горячий чай/кофе внутривенно";
            case SUMMER:
                return "Самое теплое время года, рекомендуется уезжать на море и много пить";
            case SPRING:
                return "Цветущее время года, аллергикам рекомендуется не высовываться, а остальным много гулять";
            default:
                return "Информация недоступна";
        }
    }

    public int getAvgtemp() {
        return this.avgtemp;
    }

    public String getDescription() {
        return "Холодное время года";
    }
}

public class Saesons {
    public static void main(String[] args) {
        Season s1 = Season.WINTER;
        System.out.println("Мое любимое время года: " + s1);
        System.out.println("Средняя температура: " + s1.getAvgtemp() + "°C");
        System.out.println("Описание: " + s1.getInfo());

        for (Season season : Season.values()) {
            System.out.println("Время года: " + season);
            System.out.println("Средняя температура: " + season.getAvgtemp() + "°C");
            System.out.println("Описание: " + season.getInfo());
            System.out.println();
        }
    }
}
