
import javax.swing.*;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CTC
 */
public class Test {

    public static void main(String[] args) {
        //Frame
        JFrame frame = new JFrame("Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

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
        
        //Main Panel
        JPanel mainPanel = new JPanel();
        JButton button = new JButton("Press");
        mainPanel.add(button);
        
        //Layout
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
               
        
        frame.setVisible(true);
    }
}
