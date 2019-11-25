
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
public class PlayerUiSet {
    private int playerID;
    private JPanel playerPanel;
    private JLabel playerNameLabel;
    private JLabel playerPosistionLabel;
    private JLabel playerBalanceLabel;

    public PlayerUiSet(int playerID, JPanel playerPanel, JLabel playerNameLabel, JLabel playerPosistionLabel, JLabel playerBalanceLabel) {
        this.playerID = playerID;
        this.playerPanel = playerPanel;
        this.playerNameLabel = playerNameLabel;
        this.playerPosistionLabel = playerPosistionLabel;
        this.playerBalanceLabel = playerBalanceLabel;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public JPanel getPlayerPanel() {
        return playerPanel;
    }

    public void setPlayerPanel(JPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    public JLabel getPlayerNameLabel() {
        return playerNameLabel;
    }

    public void setPlayerNameLabel(JLabel playerNameLabel) {
        this.playerNameLabel = playerNameLabel;
    }

    public JLabel getPlayerPosistionLabel() {
        return playerPosistionLabel;
    }

    public void setPlayerPosistionLabel(JLabel playerPosistionLabel) {
        this.playerPosistionLabel = playerPosistionLabel;
    }

    public JLabel getPlayerBalanceLabel() {
        return playerBalanceLabel;
    }

    public void setPlayerBalanceLabel(JLabel playerBalanceLabel) {
        this.playerBalanceLabel = playerBalanceLabel;
    }

    
    
    
}
