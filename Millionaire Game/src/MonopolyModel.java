
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonopolyModel {

    private int turn;
    private Player turnHolder;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Slot> slots = new ArrayList<>();

    public void loadData() {
        ///fake player data
        for (int i = 0; i < 4; i++) {
            players.add(new Player(i, i * 100, 0));
        }
        turnHolder = players.get(0);
        ///

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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public static void main(String[] args) {
        MonopolyModel m = new MonopolyModel();
        m.loadData();

    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Player getTurnHolder() {
        return turnHolder;
    }

    public void setTurnHolder(Player turnHolder) {
        this.turnHolder = turnHolder;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }
}
