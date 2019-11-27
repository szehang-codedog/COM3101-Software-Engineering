/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CTC
 */
class Slot {

    private int slotID;
    private String slotName;
    private int slotPrice;
    private Player owner;

    public Slot(int slotID, String slotName, int slotPrice, Player owner) {
        this.slotID = slotID;
        this.slotName = slotName;
        this.slotPrice = slotPrice;
        this.owner = owner;
    }



    public Slot(int id, String name, int price) {
        this.slotID = id;
        this.slotName = name;
        this.slotPrice = price;
        this.owner = null;
    }
    
    public void boughtBy(Player player) {
        this.owner = player;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public int getSlotPrice() {
        return slotPrice;
    }

    public void setSlotPrice(int slotPrice) {
        this.slotPrice = slotPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
