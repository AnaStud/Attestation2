import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public void changeWeight(double newWeight) {
        this.weight = newWeight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}

class ToyShop {
    private List<Toy> toys = new ArrayList<>();

    public void addNewToy(Toy toy) {
        toys.add(toy);
    }

    public void playLottery() {
        List<Toy> lotteryToys = new ArrayList<>();
        Random random = new Random();

        for (Toy toy : toys) {
            double randomValue = random.nextDouble() * 100;
            if (randomValue < toy.getWeight()) {
                lotteryToys.add(toy);
            }
        }

        try (FileWriter fileWriter = new FileWriter("lottery_winners.txt")) {
            for (Toy lotteryToy : lotteryToys) {
                fileWriter.write(lotteryToy.getName() + "\n");
                lotteryToy.setQuantity(lotteryToy.getQuantity() - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        toys.removeAll(lotteryToys);
    }

    public Toy getPrizeToy() {
        Toy prizeToy = toys.get(0);
        toys.remove(0);
        return prizeToy;
    }
}

public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        Toy toy1 = new Toy(1, "Teddy Bear", 10, 20);
        Toy toy2 = new Toy(2, "Doll", 15, 30);
        Toy toy3 = new Toy(3, "Car", 20, 50);

        toyShop.addNewToy(toy1);
        toyShop.addNewToy(toy2);
        toyShop.addNewToy(toy3);

        toyShop.playLottery();

        Toy prizeToy = toyShop.getPrizeToy();
        System.out.println("The prize toy is: " + prizeToy.getName());
    }
}