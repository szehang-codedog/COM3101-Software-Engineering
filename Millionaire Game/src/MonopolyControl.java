
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CTC
 */
public class MonopolyControl {

    private MonopolyView view;
    private MonopolyModel model;

    public void setView(MonopolyView v) {
        this.view = v;
    }

    public void setModel(MonopolyModel m) {
        this.model = m;
    }

    public void initGame() {
        model.loadData();
        view.initGame(model.getPlayers(), model.getSlots(), model.getTurnHolder());
        view.initCheatPanel(model.getPlayers(), model.getSlots(), model.getTurnHolder(), model.getTurn());
        //refreshView
        //System.out.println("refresh");
        view.refreshView(model.getPlayers(), model.getSlots());
        turnStage = 0;
    }

    //0: wait for playerX roll dice
    //1: dice rolled, slot is available, playerX have enought money to buy, waiting for decision
    //11: buy is selected, wait for comfirm turn end
    //12: not but is selected, wait for comfirm turn end
    //2: dice rolled, slot is available, playerX have not enought money to buy, waiting for turn end
    //22: dice rolled, turnHolder's, waiting for turn end
    //31: dice rolled, slot has owner, player can pay rent, waiting for confirm turn end
    //32: dice rolled, slot has owner, player bankrupt, waiting for confirm turn end
    //999: dice rolled, reach goSlot, waiting for turn end
    //-1: some one won, wait for reset game
    private int turnStage = 0;

    public void main() {
        switch (turnStage) {
            case -1:
                turnStage = 0;

                this.initGame();

                view.displayMainMessage("Roll dice to move!");
                view.displayTurnMessage("Player" + model.getTurnHolder().getPlayerID() + "'s Turn");
                view.displaySecondMessage(" ");
                view.displayMainButton("Roll Dice!!!");
                view.displaySecondButton("Let's Trade");
                break;
            case 0:
                //move turnHolder
                int step = (int) (Math.random() * 10) + 1;
                int currentPosition = model.getTurnHolder().getPosition();

                Slot goSlot = null;
                int bonus = 0;
                for (Slot s : model.getSlots()) {
                    if (s.getSlotPrice() <= 0) {
                        goSlot = s;
                        bonus = Math.abs(goSlot.getSlotPrice());
                    }
                }
                if (goSlot != null) {
                    int tmpPosition = currentPosition;
                    for (int i = 0; i < step; i++) {
                        tmpPosition++;
                        if (tmpPosition >= model.getSlots().size()) {
                            tmpPosition = tmpPosition % model.getSlots().size();
                        }
                        if (tmpPosition == goSlot.getSlotID()) {
                            model.getTurnHolder().setBalance(model.getTurnHolder().getBalance() + bonus);
                            //System.out.println(model.getTurnHolder().getPlayerID() + "get bouns");
                        }
                    }

                }

                int newPosition = (currentPosition + step) % model.getSlots().size();
                model.getTurnHolder().setPosition(newPosition);

                //display view message 
                String oldSlotName = model.getSlots().get(currentPosition).getSlotName();
                String newSlotName = model.getSlots().get(newPosition).getSlotName();
                view.displayMainMessage(Integer.toString(step) + " step(s): " + oldSlotName + " -> " + newSlotName);

                if (model.getSlots().get(model.getTurnHolder().getPosition()).getSlotPrice() <= 0) { //check if position is go slot
                    turnStage = 999;
                    view.displaySecondMessage("You get $" + bonus + " bonus!");
                    view.displayMainButton("Turn End!");
                    view.displaySecondButton(null);
                } else {
                    //check slot available or not
                    if (checkLocationOwner() == null) { //slot no owner
                        if (model.getTurnHolder().getBalance() >= model.getSlots().get(newPosition).getSlotPrice()) { //turnHolder has enough balance to buy
                            turnStage = 1;
                            view.displaySecondMessage("The slot is available");
                            view.displayMainButton("Buy!");
                            view.displaySecondButton("Not this time, Turn end");
                        } else { //no money to buy
                            //confirm turn end
                            turnStage = 2;
                            view.displaySecondMessage("not enough money to buy");
                            view.displayMainButton("Turn End!");
                            view.displaySecondButton(null);
                        }
                    } else {
                        Player slotOwner = checkLocationOwner();
                        if (slotOwner != model.getTurnHolder()) {
                            int payedAmount = payRent();
                            if (payedAmount == -1) {//debtor can pay
                                turnStage = 31;
                                view.displaySecondMessage("You payed $" + checkLocationRent() + " to player " + slotOwner.getPlayerID());
                                view.displayMainButton("Turn End!");
                                view.displaySecondButton(null);
                            } else {//debtor bankruot
                                turnStage = 32;
                                view.displaySecondMessage("You are bankrupted, you payed $" + payedAmount + " to player " + slotOwner.getPlayerID());
                                view.displayMainButton("Turn End!");
                                view.displaySecondButton(null);
                            }
                        } else {
                            turnStage = 22;
                            view.displaySecondMessage("The slot is yours already");
                            view.displayMainButton("Turn End!");
                            view.displaySecondButton(null);
                        }
                    }
                }
                break;
            case 1:
                //buy slot
                turnStage = 11;
                int position = model.getTurnHolder().getPosition();
                Slot slot = model.getSlots().get(position);

                model.getTurnHolder().buySlot(slot);
                slot.boughtBy(model.getTurnHolder());

                view.displaySecondMessage("You bought the " + slot.getSlotName() + "!");
                view.displayMainButton("Turn End!");
                view.displaySecondButton(null);
                break;
            case 11:
            case 12:
            case 2:
            case 22:
            case 31:
            case 32:
            case 999:
                turnStage = 0;
                //point to next player
                int nextPlayerID = (model.getTurnHolder().getPlayerID() + 1) % 4;
                Player nextPlayer = null;
                while (nextPlayer == null) {
                    Player p = model.getPlayers().get(nextPlayerID);
                    if (p.isIsBankrupt()) {
                        nextPlayerID = (nextPlayerID + 1) % 4;
                    } else {
                        nextPlayer = p;
                        model.setTurnHolder(nextPlayer);
                    }
                }

                view.displayMainMessage("Roll dice to move!");
                view.displayTurnMessage("Player" + model.getTurnHolder().getPlayerID() + "'s Turn");
                view.displaySecondMessage(" ");
                view.displayMainButton("Roll Dice!!!");
                view.displaySecondButton("Let's Trade");

                int alivePlayers = 0;
                Player winner = null;
                for (Player p : model.getPlayers()) {
                    if (!p.isIsBankrupt()) {
                        alivePlayers++;
                        winner = p;
                    }
                }
                if (alivePlayers <= 1 && winner != null) {
                    turnStage = -1;
                    view.displayMainMessage("Winner is Player: " + winner.getPlayerID());
                    view.displayTurnMessage(" ");
                    view.displaySecondMessage("with balance; $" + winner.getBalance());
                    view.displayMainButton("Reset game");
                    view.displaySecondButton(null);
                }

                break;
            default:
                break;
        }

        //refreshView
        //System.out.println("refresh");
        view.refreshView(model.getPlayers(), model.getSlots());
    }

    public void second() {
        switch (turnStage) {
            case 0:
                //trade()
                view.showTradeDialog(model.getPlayers());
                break;
            case 1:
                //not buy slot
                turnStage = 12;
                int position = model.getTurnHolder().getPosition();
                Slot slot = model.getSlots().get(position);

                view.displaySecondMessage("You did not buy " + slot.getSlotName() + "?!");
                view.displayMainButton("Turn End!");
                view.displaySecondButton(null);
                break;
            default:
                break;
        }

        //refreshView
        view.refreshView(model.getPlayers(), model.getSlots());
    }

    public static void main(String[] args) {
        MonopolyControl c = new MonopolyControl();
    }

    private int checkLocationRent() {
        int position = model.getTurnHolder().getPosition();
        int price = model.getSlots().get(position).getSlotPrice();
        int rent = (int) Math.round(price * 0.1);
        return rent;
    }

    private Player checkLocationOwner() {
        int position = model.getTurnHolder().getPosition();
        Player owner = model.getSlots().get(position).getOwner();
        return owner;
    }

    private int payRent() {
        Player owner = checkLocationOwner();
        int rent = checkLocationRent();
        Player debtor = model.getTurnHolder();

        if (debtor.getBalance() < rent) { //bankrupt
            int payedAmount = debtor.bankrupt(owner);
            return payedAmount;
        } else {
            debtor.setBalance(debtor.getBalance() - rent);
            owner.setBalance(owner.getBalance() + rent);
            return -1;
        }

    }

    public Slot requestSlot(int slotID) {
        Slot s = model.getSlots().get(slotID);
        Slot newS = new Slot(s.getSlotID(), s.getSlotName(), s.getSlotPrice(), s.getOwner());
        return newS;
    }

    public Player requestPlayer(int playerID) {
        Player p = model.getPlayers().get(playerID);
        Player newP = new Player(p.getPlayerID(), p.getBalance(), p.isIsBankrupt(), p.getPosition(), p.getOwnedSlots());
        return newP;
    }

    public int requestTurnHolderID() {
        return model.getTurnHolder().getPlayerID();
    }

    void slotCheat(int slotID, int slotPrice, int slotOwnerID) {
        Slot slot = model.getSlots().get(slotID);

        //set new price to slot
        slot.setSlotPrice(slotPrice);

        Player oldOwner = slot.getOwner();
        //remove slot from old owner's ownedSlots
        if (oldOwner != null) {
            Slot removeSlot = null;
            for (Slot s : oldOwner.getOwnedSlots()) {
                if (s.getSlotID() == slot.getSlotID()) {
                    removeSlot = s;
                }
            }
            oldOwner.removeOwnedSlot(removeSlot);
        }
        //set new owner //slotOwnerID == -1 -> newOwner = null
        Player newOwner = null;
        if (slotOwnerID != -1) {
            for (Player p : model.getPlayers()) {
                if (p.getPlayerID() == slotOwnerID) {
                    newOwner = p;
                }
            }
            //add to new owner's ownedSlots
            newOwner.addOwnedSlot(slot);
        }
        slot.setOwner(newOwner);

        view.refreshView(model.getPlayers(), model.getSlots());
    }

    void playerCheat(int playerID, int balance, boolean isBankrupt, int position, boolean isTurn) {
        Player player = model.getPlayers().get(playerID);

        //set player
        player.setBalance(balance);
        player.setIsBankrupt(isBankrupt);
        player.setPosition(position);
        if (isTurn) {
            model.setTurnHolder(player);
        }

        if (model.getTurnHolder().isIsBankrupt()) {
            int nextPlayerID = (model.getTurnHolder().getPlayerID() + 1) % 4;
            Player nextPlayer = null;
            while (nextPlayer == null) {
                Player p = model.getPlayers().get(nextPlayerID);
                if (p.isIsBankrupt()) {
                    nextPlayerID = (nextPlayerID + 1) % 4;
                } else {
                    nextPlayer = p;
                    model.setTurnHolder(nextPlayer);
                }
            }
        }

        //reset turnStage
        turnStage = 0;

        //reset view for next player to use
        view.displayMainMessage("Roll dice to move!");
        view.displayTurnMessage("Player" + model.getTurnHolder().getPlayerID() + "'s Turn");
        view.displaySecondMessage(" ");
        view.displayMainButton("Roll Dice!!!");
        view.displaySecondButton("Let's Trade");

        view.refreshView(model.getPlayers(), model.getSlots());
    }

    void resetGameRequest() {
        this.initGame();
    }

    void trade(int playerID0, int slotID0, int dib0, int playerID1, int slotID1, int dib1) {
        Player p0 = null;
        Player p1 = null;
        for (Player p : model.getPlayers()) {
            if (p.getPlayerID() == playerID0) {
                p0 = p;
            }
            if (p.getPlayerID() == playerID1) {
                p1 = p;
            }
        }
        Slot s0 = null;
        Slot s1 = null;
        if (slotID0 >= 0) {
            for (Slot s : model.getSlots()) {
                if (s.getSlotID() == slotID0) {
                    s0 = s;

                    p0.removeOwnedSlot(s0);
                    s0.setOwner(p1);
                    p1.addOwnedSlot(s0);
                }
            }
        }
        if (slotID1 >= 0) {
            for (Slot s : model.getSlots()) {
                if (s.getSlotID() == slotID1) {
                    s1 = s;

                    p1.removeOwnedSlot(s1);
                    s1.setOwner(p0);
                    p0.addOwnedSlot(s1);
                }
            }
        }

        if (dib0 >= 0) {
            p0.setBalance(p0.getBalance() - dib0);
            p1.setBalance(p1.getBalance() + dib0);
        }
        if (dib1 >= 0) {
            p1.setBalance(p1.getBalance() - dib1);
            p0.setBalance(p0.getBalance() + dib1);
        }
        
        view.refreshView(model.getPlayers(), model.getSlots());
    }

}
