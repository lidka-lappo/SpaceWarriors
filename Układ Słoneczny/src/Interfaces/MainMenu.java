package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainMenu extends JFrame implements ActionListener {

    JFrame frame;

    JPanel panelTop;
    JPanel panelBottom;
    JPanel panelCenter;

    JButton shopButton;
    JButton newGameButton;
    
    JLabel balance;
    JLabel chooseLevel;
    JLabel shop;

    CircleButton planet1;
    CircleButton planet2;
    CircleButton planet3;
    CircleButton planet4;
    int money;
    
    JMenu menu;
    JMenuItem i1, i2, i3;

    private static final long serialVersionUID = 1L;

    public MainMenu() throws HeadlessException {
        frame = new JFrame();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        panelTop = new JPanel(new FlowLayout());
        panelTop.setBackground(Color.black);
        chooseLevel = new JLabel("Choose Level");
        chooseLevel.setForeground(Color.white);
        panelTop.add(chooseLevel);
        frame.add(panelTop, BorderLayout.PAGE_START);

        panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,3));
        balance = new JLabel("Balance: ");
        balance.setText(String.valueOf(money));
        panelBottom.add(balance);
       
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.green);
        newGameButton.addActionListener(this);
        newGameButton.setActionCommand("1");      
        panelBottom.add(newGameButton);

        shopButton = new JButton("Shop");
        newGameButton.setBackground(Color.yellow);
        shopButton.addActionListener(this);
        shopButton.setActionCommand("2");
        panelBottom.add(shopButton);

        frame.add(panelBottom, BorderLayout.PAGE_END);

    

        panelCenter = new JPanel();
        panelCenter.setBackground(Color.black);
        planet1 = new CircleButton("  Saturn  ", Color.yellow);
        planet1.addActionListener(this);
        planet1.setActionCommand("5");
        panelCenter.add(planet1);
        
        planet2 = new CircleButton("    Jowisz   ", Color.orange);
        planet2.addActionListener(this);
        planet2.setActionCommand("6");
        panelCenter.add(planet2);
        
        planet3 = new CircleButton("Ziemia", Color.blue);
        planet3.addActionListener(this);
        planet3.setActionCommand("7");
        panelCenter.add(planet3);
        
        planet4 = new CircleButton("Mars", Color.red);
        planet4.addActionListener(this);
        planet4.setActionCommand("8");
        panelCenter.add(planet4);

        frame.add(panelCenter, BorderLayout.CENTER);
        
        JMenuBar menuBar=new JMenuBar();  
        
        menu=new JMenu("Menu");
        i1=new JMenuItem("Polski");  
        i2=new JMenuItem("Angielski");  

        
        menu.add(i1);
        i1.setActionCommand("3");
        i1.addActionListener(this);
        i1.setSelected(true);
        
        menu.add(i2); 
        
        i2.setActionCommand("4");
        i2.addActionListener(this);
        i2.setSelected(true);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent arg0) {
        int choice = Integer.parseInt(arg0.getActionCommand());
        switch (choice) {
        	case 1:
                frame.setVisible(false);
            	new GameInterface();
        		System.out.println("0");
            break;
             case 2:
                frame.setVisible(false);   
                new ShopFrame();
            	System.out.println("1");
                break;
            case 3:
            	System.out.println("2");
            	break;
        }

    }


    public static void main(String[] args) {
        new MainMenu();
    }
}
