package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ShopFrame extends JFrame  implements ActionListener {
    JFrame frame;
    JPanel blackPanel;
    JPanel whitePanel;
    JLabel shopLabel;
    JLabel moneyLabel; 
    JButton colorButton;
    JButton buyButton;
    JButton backButton;
    int width;
    int height;

    int money;
    int price = 40;
    
    Color newRocketColor;

    String poorStatement;
    String colorChooserTitle;
    
    
    private static final long serialVersionUID = 1L;
    public ShopFrame() throws HeadlessException {
        frame = new JFrame();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        money = MainMenu.money;
        width = (int)frame.getSize().getWidth();
        height = (int)frame.getSize().getHeight();
        blackPanel = new JPanel();
        blackPanel.setSize(width, height);
        blackPanel.setBackground(Color.black);
        blackPanel.setLayout(null);
        frame.add(blackPanel, BorderLayout.CENTER);
        shopLabel = new JLabel();
        shopLabel.setForeground(Color.orange);
        shopLabel.setFont(new Font("Ariel", Font.BOLD, 30));
        shopLabel.setText("SHOP");
        shopLabel.setBounds((int)(width*0.43), 5, 100, 100);
        blackPanel.add(shopLabel);
        
        whitePanel = new JPanel();
        whitePanel.setBackground(Color.white);
        int whitePanelWidth = (int)(width*0.77);
        int whitePanelHeight = (int)(height*0.75);
        whitePanel.setBounds((int)(width*0.1), (int)(height*0.15), whitePanelWidth, whitePanelHeight);
        whitePanel.setLayout(new GridLayout(3,1));
        moneyLabel = new JLabel();
        moneyLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        whitePanel.add(moneyLabel);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(Color.white);        
        colorButton = new JButton();
        colorButton.setFont(new Font("Ariel", Font.BOLD, 20));
        colorButton.setText("choose color of your new rocket");
        colorButton.setBackground(Color.yellow);
        colorButton.setBounds((int)(whitePanelWidth*0.1), 30, (int)(whitePanelWidth*0.8), 50);
        colorButton.addActionListener(this);
        colorButton.setActionCommand("color");
        panel2.add(colorButton);
        whitePanel.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBackground(Color.white); 
        buyButton = new JButton();
        buyButton.setFont(new Font("Ariel", Font.BOLD, 25));
        buyButton.setText("BUY");
        buyButton.setBackground(Color.orange);
        buyButton.setBounds((int)(whitePanelWidth*0.3), 0, (int)(whitePanelWidth*0.4), (int)(whitePanelHeight/3*0.8));
        buyButton.addActionListener(this);
        buyButton.setActionCommand("buy");
        panel3.add(buyButton);
        whitePanel.add(panel3);
            
        blackPanel.add(whitePanel);
        backButton = new JButton("back");
        backButton.setBackground(Color.red);    
        backButton.addActionListener(this);
        backButton.setActionCommand("2");
        backButton.setBounds((int)(width*0.1 + whitePanelWidth), height-70, (int)(width - (width*0.1 + whitePanelWidth+15)), 30);
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
        blackPanel.add(backButton);
               
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String choice = arg0.getActionCommand();
        switch (choice) {
        //choose color
            case "color":
                newRocketColor = JColorChooser.showDialog(null, colorChooserTitle, Color.GREEN);
                if(money>=price)
                    buyButton.setBackground(newRocketColor);
                else
                    buyButton.setBackground(Color.red);
            break;
         //change rocket color if enough money
             case "buy":
                 if(money>=price)
                {
                    money-=price;
                    MainPanel.setRocketColor(newRocketColor);
                    MainMenu.money -= price;
                    MainMenu.balance.setText(String.valueOf(MainMenu.money)+"$");
                }
                 else {//inform if there's to little money
                     buyButton.setBackground(Color.red);
                     JOptionPane.showMessageDialog(null, poorStatement, "error", JOptionPane.ERROR_MESSAGE);
                 }
                 System.out.println(money);
                break;
             case "back":
                frame.dispose();
             break;
            
        }

    }

}
