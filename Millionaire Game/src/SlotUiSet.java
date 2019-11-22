
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
class SlotUiSet {
    private int slotID;
    private JPanel panel;
    private JLabel nameLabel;
    private JLabel spaceLabel;
    private JLabel priceLabel;
    private JLabel ownerLabel;

    public SlotUiSet(int slotID, JPanel panel, JLabel nameLabel, JLabel spaceLabel, JLabel priceLabel, JLabel ownerLabel) {
        this.slotID = slotID;
        this.panel = panel;
        this.nameLabel = nameLabel;
        this.spaceLabel = spaceLabel;
        this.priceLabel = priceLabel;
        this.ownerLabel = ownerLabel;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getSpaceLabel() {
        return spaceLabel;
    }

    public void setSpaceLabel(JLabel spaceLabel) {
        this.spaceLabel = spaceLabel;
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
 
}
