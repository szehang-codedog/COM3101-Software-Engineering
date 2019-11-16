
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CTC
 */
public class OldView {


    private MonopolyControl control;
    
    public void setControl(MonopolyControl control) {
        this.control = control;
    }

    private JFrame mainFrame = null;
    private ArrayList<OldViewSlotPanelSet> slotPanelSets = null;

    public static void main(String[] args) {
        OldView v = new OldView();

        ArrayList<Player> testPlayers = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
            Player p = new Player(i, 1000, 0);
            testPlayers.add(p);
        }

        ArrayList<Slot> testSlots = new ArrayList<Slot>();
        for (int i = 0; i < 23; i++) {
            Slot s = new Slot(i, "" + i, i * 100);
            testSlots.add(s);
        }
        testSlots.get(0).setSlotName("go");
        testSlots.get(0).setSlotPrice(-2000);

        v.initialUi(testSlots, testPlayers);
    }

    private void initialUi(ArrayList<Slot> slots, ArrayList<Player> players) {

        //frame setting
        this.mainFrame = new JFrame("Project");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1300, 500);

        //layout
        this.mainFrame.setLayout(new BorderLayout());

        //MenuBar
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Menu");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("set slot data file");
        JMenuItem m12 = new JMenuItem("reset game");
        JMenuItem m13 = new JMenuItem("testing panel");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);

        this.mainFrame.add(mb, BorderLayout.NORTH);

        //Map Panel
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridBagLayout());
        GridBagConstraints c;
        //generate slotPanel
        this.slotPanelSets = new ArrayList<OldViewSlotPanelSet>();
        for (Slot s : slots) {
            OldViewSlotPanelSet slotPanelSet = buildSlot(s, players.size());
            this.slotPanelSets.add(slotPanelSet);
            //allocatePanel
            c = new GridBagConstraints();
            c.gridx = s.getSlotID();
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 20;
            c.weighty = 20;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.CENTER;
            mapPanel.add(slotPanelSet.getSlotPanel(), c);
        }

        //allcate players to goSlot
        int goSlot = -1;
        for (Slot s : slots) {
            if (s.getSlotPrice() < 0) {
                goSlot = s.getSlotID();
            }
        }
        if (goSlot == -1) {
            //error message here
        } else {
            for (int i = 0; i < players.size(); i++) {
                slotPanelSets.get(goSlot).getPlayerSpaces().get(i).setText("player " + i);
            }
        }

        this.mainFrame.add(mapPanel, BorderLayout.CENTER);

        /*
        ////test button
        JButton button = new JButton("kill all player");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < slots.size(); i++) {
                    for (int j = 0; j < players.size(); j++) {
                        slotPanelSets.get(i).getPlayerSpaces().get(j).setText(" ");
                    }
                }
            }
        });
        this.mainFrame.add(button, BorderLayout.SOUTH);
        */
        
        ////roll button
        JButton rollButton = new JButton("roll dice");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //int step = 1 + (int)(Math.random()*10);
            control.move();
            }
        });
        this.mainFrame.add(rollButton, BorderLayout.SOUTH);
        ////

        //display mainFrame
        this.mainFrame.setVisible(true);

    }

    public OldViewSlotPanelSet buildSlot(Slot slot, int numOfPlayer) {
        JPanel slotPanel = new JPanel();
        slotPanel.setLayout(new GridBagLayout());
        slotPanel.setBorder(BorderFactory.createEtchedBorder());
        GridBagConstraints c;

        JLabel nameLabel = new JLabel(slot.getSlotName(), SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        slotPanel.add(nameLabel, c);

        JLabel priceLabel = new JLabel(String.valueOf(slot.getSlotPrice()), SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        slotPanel.add(priceLabel, c);

        JLabel ownerLabel = new JLabel("free", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        slotPanel.add(ownerLabel, c);

        ArrayList<JLabel> playerLabels = new ArrayList<JLabel>();
        for (int i = 0; i < numOfPlayer; i++) {
            JLabel playerLabel = new JLabel(" ", SwingConstants.CENTER);
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 3 + i;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.CENTER;
            playerLabels.add(playerLabel);
            slotPanel.add(playerLabel, c);
        }

        //Dimension d = new Dimension(500, 500);
        //slotPanel.setSize(d);
        OldViewSlotPanelSet slotPanelSet = new OldViewSlotPanelSet(slotPanel, nameLabel, priceLabel, ownerLabel, playerLabels);

        return slotPanelSet;
    }
}
