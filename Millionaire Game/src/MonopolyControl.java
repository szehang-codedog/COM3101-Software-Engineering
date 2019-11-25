
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    }

    private boolean turnStarted = false;
    private boolean turnEnd = true;

    public void main() {
        if (!turnStarted && turnEnd) { //no one in turn
            turnStarted = true;
            turnEnd = false;

            //move turnHolder
            int step = (int) (Math.random() * 10) + 1;
            int currentPosition = model.getTurnHolder().getPosition();
            int newPosition = (currentPosition + step) % model.getSlots().size();
            model.getTurnHolder().setPosition(newPosition);

            //display view message 
            String oldSlotName = model.getSlots().get(currentPosition).getSlotName();
            String newSlotName = model.getSlots().get(newPosition).getSlotName();
            view.displayMainMessage(Integer.toString(step) + " step(s): " + oldSlotName + " -> " + newSlotName);

            //check slot available or not
            if (model.getSlots().get(model.getTurnHolder().getPosition()).getOwner() == null) { //slot no owner
                if (model.getTurnHolder().getBalance() >= model.getSlots().get(newPosition).getSlotPrice()) { //turnHolder has enough balance to buy
                    //view.displayMainMessage("The slot is available");
                    view.displayMainButton("Buy!");
                    view.displaySecondButton("Not this time, Turn end");
                } else { //no money to buy
                    //confirm turn end
                }
            } else {
                //payRent()
            }

        } else if (turnStarted && !turnEnd) { //someone rolled dice , in turn
            //testing escape//
                turnStarted = false;
                turnEnd = true;
            //testing escape//
        }
        //refreshView
        view.refreshView(model.getPlayers(), model.getSlots());
    }

    public void second() {
        if (!turnStarted && turnEnd) { //noone in turn
            //trade
        } else if (turnStarted && !turnEnd) { //someone dice , in turn
            turnStarted = false;
            turnEnd = true;

            //point to next player
            int nextPlayer = (model.getTurnHolder().getPlayerID() + 1) % model.getPlayers().size();
            model.setTurnHolder(model.getPlayers().get(nextPlayer));

            //display view message 
            view.displayTurnMessage("Player" + model.getTurnHolder().getPlayerID() + "'s Turn");
            view.displayMainMessage("Roll dice to move!");
        }
    }

    public static void main(String[] args) {
        MonopolyControl c = new MonopolyControl();
    }

}
