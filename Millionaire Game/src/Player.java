
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CTC
 */
public class Player {
    private int playerID;
    private int balance;
    private boolean isBankrupt;
    private int position;
    private ArrayList<Slot> ownedSlots;
    
    public Player(int id, int initialBalance, int initialPosition) {
        this.playerID = id;
        this.balance = initialBalance;
        this.isBankrupt = false;
        this.position = initialPosition;
        this.ownedSlots = new ArrayList<Slot>();
    }
    
    public int bankrupt(Player loaner) {
        this.isBankrupt = true;
        int payedAmount = this.balance;
        loaner.setBalance(loaner.getBalance() + this.balance);
        this.balance = 0;
        
        for(Slot s : ownedSlots) {
            s.setOwner(null);
        }
        ownedSlots = null;
        
        return payedAmount;
    }
    
    public void buySlot(Slot slot) {
        this.balance = this.balance - slot.getSlotPrice();
        this.ownedSlots.add(slot);
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(boolean isBankrupt) {
        this.isBankrupt = isBankrupt;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Slot> getOwnedSlots() {
        return ownedSlots;
    }

    public void setOwnedSlots(ArrayList<Slot> ownedSlots) {
        this.ownedSlots = ownedSlots;
    }
}
