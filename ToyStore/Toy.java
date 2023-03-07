package ToyStore;

import java.io.*;
import java.util.ArrayList;

public class Toy implements Serializable{
    private int id;
    private String toyName;
    private int toysCount;
    private int frequencyRepeatingToys;
    static int countId = 0;
    static String filename = "toys.dat";
    static ArrayList<Toy> totalNumberOfToys = readFile();

    public Toy(String toyName, int toysCount, int frequencyRepeatingToys) {
        totalNumberOfToys = readFile();

        Toy toys = findToyAllToys(toyName);

        if (toys == null) {
            countId += 1;
            this.id = countId;
            this.toyName = toyName;
            this.toysCount = toysCount;
            this.frequencyRepeatingToys = frequencyRepeatingToys;
            totalNumberOfToys.add(this);
            saveFile();
            return;
        }

        toys.toysCount += toysCount;

        if(toys.toysCount < 0 ){
            toys.toysCount = 0;
        }

        toys.frequencyRepeatingToys = frequencyRepeatingToys;
        saveFile();
    }

     public String getName() {
        return toyName;
    }

    @Override
    public String toString(){
        return "id - " + this.id + ", наименование - " + this.toyName + ", количество - " + this.toysCount + ", частота - " + this.frequencyRepeatingToys;
    }

    public static void saveFile(){
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            output.writeObject(totalNumberOfToys);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<Toy> readFile(){
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename)))
        {
            totalNumberOfToys = ((ArrayList<Toy>)input.readObject());
            countId = totalNumberOfToys.size();
        }
        catch(Exception ex){

            totalNumberOfToys = new ArrayList<>();
            System.out.println(ex.getMessage());
        }

        return totalNumberOfToys;

    }

    public Toy findToyAllToys(String name) {

        if(totalNumberOfToys != null) {
            for (Toy toy : totalNumberOfToys) {
                if (toy.getName().equals(name)) {
                    return toy;
                }
            }
        }

        return null;
    }

    static void getPrizeToy(int prize) {
        int totalFrequency = 0;

        totalNumberOfToys = readFile();

        for (Toy toy : totalNumberOfToys) {
            if(toy.toysCount > 0) {
                totalFrequency += toy.frequencyRepeatingToys;
            }
        }

        if(totalFrequency == 0){
            return;
        }

        int randomNumber = (int) (Math.random() * totalFrequency);

        int currentFrequency = 0;

            for (Toy t : totalNumberOfToys) {
                if(prize <= 0){
                    break;
                }
                if (t.toysCount <= 0) {
                    continue;
                }
                currentFrequency += t.frequencyRepeatingToys;
                if (currentFrequency >= randomNumber) {
                    prize -= 1;
                    System.out.println(t.toString());
                    t.toysCount -= 1;
                    saveFile();
                }
            }
            if (prize > 0){
                getPrizeToy(prize);
            }
    }


    public static void testData(){
        Toy toy1 = new Toy("Мягкая игрушка", 1, 10);
        Toy toy2 = new Toy("Жесткая игрушка", 2, 15);
        Toy toy3 = new Toy("Погремушка", 2, 5);
        Toy toy4 = new Toy("Очень жесткая игрушка", 2, 50);
        Toy toy5 = new Toy("Опасная игрушка", 2, 60);
        Toy toy6 = new Toy("Страшная игрушка", 2, 70);
    }
}