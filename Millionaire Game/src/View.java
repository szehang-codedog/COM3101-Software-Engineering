
import java.awt.*;
import javax.swing.*;
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
public class View {

    /*
    private Control control;
    
    public void setControl(Control control) {
        this.control = control;
    }
     */
    private JFrame frame = null;
    private ArrayList<JPanel> slotPanels = null;

    public static void main(String[] args) {
        View v = new View();

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

        v.initialUi(testSlots, testPlayers);
    }

    private void initialUi(ArrayList<Slot> slots, ArrayList<Player> players) {
        //frame setting
        JFrame mainFrame = new JFrame("Project");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 500);

        //layout
        mainFrame.setLayout(new BorderLayout());

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

        mainFrame.add(mb, BorderLayout.NORTH);

        //Map Panel
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridBagLayout());
        GridBagConstraints c;
        //generate slotPanel
        this.slotPanels = new ArrayList<JPanel>();
        for (Slot s : slots) {
            JPanel slotPanel = buildSlot(s);
            slotPanels.add(slotPanel);
            //allocatPanel
            c = new GridBagConstraints();
            c.gridx = s.getSlotID();
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 20;
            c.weighty = 20;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.CENTER;
            mapPanel.add(slotPanel, c);
        }

        mainFrame.add(mapPanel, BorderLayout.CENTER);

        //
        mainFrame.setVisible(true);
    }

    public JPanel buildSlot(Slot slot) {
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

        Dimension d = new Dimension(500, 500);
        slotPanel.setSize(d);

        return slotPanel;
    }
}
