
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
        view.initGame(model.getPlayers(), model.getSlots());
    }

    public void move() {
        int step = (int) (Math.random() * 10) + 1;
        int currentPosition = model.getTurnHolder().getPosition();
        int newPosition = (currentPosition + step) % model.getSlots().size();
        model.getTurnHolder().setPosition(newPosition);
        view.refreshView(model.getPlayers(), model.getSlots());

        String oldSlotName = model.getSlots().get(currentPosition).getSlotName();
        String newSlotName = model.getSlots().get(newPosition).getSlotName();
        
        view.displayDiceMessage(Integer.toString(step) + " step(s) from " + oldSlotName + " to " + newSlotName);
        
        //point to next player
        int nextPlayer = (model.getTurnHolder().getPlayerID() + 1) % model.getPlayers().size();
        model.setTurnHolder(model.getPlayers().get(nextPlayer));
    }

    public static void main(String[] args) {
        MonopolyControl c = new MonopolyControl();
    }

}
