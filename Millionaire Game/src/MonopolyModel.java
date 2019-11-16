
import java.util.*;
import java.io.*;

public class MonopolyModel {

    private int turn;
    private Player turnHolder;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Slot> slots = new ArrayList<>();

    public void loadData() {
        File file = new File("SlotData.csv");
        try {
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                String[] line = inputFile.nextLine().split(",");

                int id = Integer.parseInt(line[0]);
                String name = line[1];
                int price = Integer.parseInt(line[2]);

                Slot slot = new Slot(id, name, price);

                slots.add(slot);
                
                System.out.println(slot.getSlotID() + " " + slot.getSlotName() + " " + slot.getSlotPrice());
            }
            inputFile.close();   
        } catch (FileNotFoundException err) {
            System.out.println("File not found");
        }
    }
    
    public void addPlayer(Player player){
        players.add(player);
    }

    public static void main(String[] args) {
        MonopolyModel m = new MonopolyModel();
        m.loadData();

    }
}
