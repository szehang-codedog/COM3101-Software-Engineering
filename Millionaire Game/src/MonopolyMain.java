/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CTC
 */
public class MonopolyMain {
    public static void main(String[] args) {
        MonopolyModel model = new MonopolyModel();
        MonopolyView view = new MonopolyView();
        MonopolyControl control = new MonopolyControl();
        
        view.setControl(control);
        control.setModel(model);
        control.setView(view);
        
        control.initGame();
        view.setVisible(true);
        
        //debug
        /*
        for(Slot s : model.getSlots()) {
            s.setOwner(model.getPlayers().get(2));
        }
        */
    }
}
