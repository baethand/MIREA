package prak2.zad4;

public class Computer {
    private String cpu;
    private String videoCard;
    private String ram;
    private String mainboard;
    private int storage;


    public Computer(String cpu,
                    String videoCard,
                    String ram,
                    String mainboard,
                    int storage) {
        this.cpu = cpu;
        this.videoCard = videoCard;
        this.ram = ram;
        this.mainboard = mainboard;
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu = '" + cpu + '\'' +
                ", videoCard = '" + videoCard + '\'' +
                ", ram = '" + ram + '\'' +
                ", mainboard = '" + mainboard + '\'' +
                ", storage = " + storage +
                '}';
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getMainboard() {
        return mainboard;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
