
import java.util.ArrayList;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CTC
 */
public class ViewSlotPanelSet {
    private JPanel slotPanel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel ownerLabel;
    private ArrayList<JLabel> playerSpaces;

    public ViewSlotPanelSet(JPanel slotPanel, JLabel nameLabel, JLabel priceLabel, JLabel ownerLabel, ArrayList<JLabel> playerSpaces) {
        this.slotPanel = slotPanel;
        this.nameLabel = nameLabel;
        this.priceLabel = priceLabel;
        this.ownerLabel = ownerLabel;
        this.playerSpaces = playerSpaces;
    }

    public JPanel getSlotPanel() {
        return slotPanel;
    }

    public void setSlotPanel(JPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public JLabel getOwnerLabel() {
        return ownerLabel;
    }

    public void setOwnerLabel(JLabel ownerLabel) {
        this.ownerLabel = ownerLabel;
    }

    public ArrayList<JLabel> getPlayerSpaces() {
        return playerSpaces;
    }

    public void setPlayerSpaces(ArrayList<JLabel> playerSpaces) {
        this.playerSpaces = playerSpaces;
    }
    
}
