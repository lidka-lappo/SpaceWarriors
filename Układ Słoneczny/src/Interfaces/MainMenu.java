package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class MainMenu extends JFrame implements ActionListener {

    static JFrame frame;

    JPanel panelTop;
    JPanel panelBottom;
    static JPanel panelCenter;

    JButton shopButton;
    JButton newGameButton;
    
    static JLabel balance;
    JLabel chooseLevel;
    JLabel shop;

    static CircleButton[] planet;
    static int money = 0;
    
    JMenu langg;
    JMenuItem ang, pol;
    
    
    JMenu menu;
    JMenuItem save, open, newg;
    
    static GameInterface newGame;
    int lvl = 7;
    static int nextLevelToOpen = 6;
    boolean ifOpen[];
   
    ShopFrame shopfr;
    static String language = "en";
    static String country = "EN";
    static Locale locale = null;
    static ResourceBundle r = null;

    String nam[];
    Color col[];
    int rr[];
    int dist[];
    
    String wrongStatement ="sorry, you haven't unlock this planet yet:(" ;

    
    private static final long serialVersionUID = 1L;

    public MainMenu() throws HeadlessException {
        
        frame = new JFrame("SPACE WARRIORS");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelTop = new JPanel(new FlowLayout());
        panelTop.setBackground(Color.black);
        chooseLevel = new JLabel("Choose Level");
        chooseLevel.setForeground(Color.white);
        panelTop.add(chooseLevel);
        frame.add(panelTop, BorderLayout.PAGE_START);

        panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,3));
        balance = new JLabel("Balance: ");
        balance.setText(String.valueOf(money)+"$");
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
 
        nam = new String[] {"SUN", "MERCURY", "VENUS", "EARTH", "MARS", "JUPITER", "SATURN", "URANUS", "NEPTUNE"};
        col = new Color[] {Color.red, Color.gray, Color.orange, Color.blue.darker(), Color.red.darker(), Color.yellow, Color.orange, Color.blue, Color.lightGray};
        rr = new int[] {40, 8, 12, 12, 8, 40, 40, 24, 24};
        dist = new int[] {0, 70, 90, 110, 130, 160, 210, 240, 300};
        ifOpen = new boolean[] {false, false, false, false, false, false, false, false, false};
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.black);
        panelCenter.setLayout(null);
        planet = new CircleButton[9];
        setUpPlanets();

        frame.add(panelCenter, BorderLayout.CENTER);
        
        JMenuBar menuBar=new JMenuBar();  
        

        
        //language
        langg=new JMenu("Language");
        pol=new JMenuItem("Polish"); 
        ang=new JMenuItem("English");  

        langg.add(pol);
        pol.setActionCommand("3");
        pol.addActionListener(this);
        pol.setSelected(true);
        Image img = new ImageIcon("src/imagess/pol.jpg").getImage();
        pol.setIcon(new ImageIcon(img.getScaledInstance(15, 10, java.awt.Image.SCALE_SMOOTH)));
        
        langg.add(ang);         
        ang.setActionCommand("4");
        ang.addActionListener(this);
        ang.setSelected(true);
        Image img2 = new ImageIcon("src/imagess/ang.jpg").getImage();
        ang.setIcon(new ImageIcon(img2.getScaledInstance(15, 10, java.awt.Image.SCALE_SMOOTH)));


        
        menu=new JMenu("Menu");
        save=new JMenuItem("Save"); 
        newg=new JMenuItem("New Game");
        open=new JMenuItem("Open");  

       
        menu.add(newg);         
        newg.setActionCommand("5");
        newg.addActionListener(this);
        newg.setSelected(true);
        
        menu.add(open);         
        open.setActionCommand("6");
        open.addActionListener(this);
        open.setSelected(true);
        
        menu.add(save);
        save.setActionCommand("7");
        save.addActionListener(this);
        save.setSelected(true);


        menuBar.add(menu);
        menuBar.add(langg);
        
        
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
   
 //change language  
    public void changeMenuLanguage(String lang, String count) {
        locale = new Locale(lang, count);
        r = ResourceBundle.getBundle("Bundle", locale);
        newGameButton.setText(r.getString("new_game"));
        shopButton.setText(r.getString("shop"));
        chooseLevel.setText(r.getString("choose_level"));
        pol.setText(r.getString("polish"));
        ang.setText(r.getString("english"));
        langg.setText(r.getString("language"));
        newg.setText(r.getString("newg"));
        open.setText(r.getString("open"));
        save.setText(r.getString("save"));

        
        wrongStatement = r.getString("level");
        planet[0].setText(r.getString("sun"));
        planet[1].setText(r.getString("mer"));
        planet[2].setText(r.getString("ven"));
        planet[3].setText(r.getString("ear"));
        planet[4].setText(r.getString("mar"));
        planet[5].setText(r.getString("jup"));
        planet[6].setText(r.getString("sat"));
        planet[7].setText(r.getString("ura"));
        planet[8].setText(r.getString("nep"));
        
    }
    public void changeGameIntLanguage(GameInterface gi) {
        if(locale == null) {
            locale = new Locale(language, country);
            r = ResourceBundle.getBundle("Bundle", locale);
        }
        gi.endButton.setText(r.getString("end"));
        gi.startButton.setText(r.getString("start"));
        GameInterface.velocity = r.getString("velocity");
        GameInterface.fuelStr = r.getString("fuel");
    }
    public void changeShopLanguage(ShopFrame sf) {
        if(locale == null) {
            locale = new Locale(language, country);
            r = ResourceBundle.getBundle("Bundle", locale);
        }
        sf.frame.setTitle(r.getString("shop_title"));
        sf.shopLabel.setText(r.getString("SHOP"));
        sf.moneyLabel.setText("\t \t "+r.getString("you_ve_got")+" "+sf.money+"$, "+r.getString("new_rocket_costs")+" "+sf.price+"$");
        sf.colorButton.setText(r.getString("choose_color"));
        sf.buyButton.setText(r.getString("BUY"));
        sf.backButton.setText(r.getString("back"));
        sf.poorStatement = r.getString("poor");
        sf.colorChooserTitle = r.getString("colorChooserTitle");
    }


    public void actionPerformed(ActionEvent arg0) {
        int choice = Integer.parseInt(arg0.getActionCommand());
        switch (choice) {
            case 1:
                newGame = new GameInterface();  
                changeGameIntLanguage(newGame);
                System.out.println("0");
                newGame.mainPanel.setLevel(lvl); 
                newGame.mainPanel.setDestinationPlanet();
            break;
             case 2:  
               shopfr = new ShopFrame();
                changeShopLanguage(shopfr);             
                break;
            case 3://Polish
                language = "pl";
                country = "PL";
                changeMenuLanguage(language, country);
                break;
            case 4://English
                language = "en";
                country = "EN";
                changeMenuLanguage(language, country);
                break;
            case 5:
                nextLevelToOpen = 6;
                changeOpenPlanets();
                break;     
            case 6:
                nextLevelToOpen = open();
                changeOpenPlanets();
                System.out.println(nextLevelToOpen);
                break; 
            case 7:
                save(nextLevelToOpen);
                break; 
            case 8:
                break; 
            default:
                if(planet[choice-8].ifOpen == false) 
                    JOptionPane.showMessageDialog(null, wrongStatement, "error", JOptionPane.ERROR_MESSAGE);
                else
                    lvl = choice-8;
           
        }

    }
    
    
    public void setUpPlanets()
    {
        int w=280;
        int h=110;
        int x=0;
        int y =0; 
        int angle=0;
       
      
        for(int i = 0; i < 9; i ++)
        {
            
            angle+=45;
            x=(w-rr[i])+(int)((dist[i])*Math.sin(angle));
            y=(h-rr[i])+(int)((dist[i])*Math.cos(angle));
            planet[i] = new CircleButton(nam[i], col[i],2*rr[i]);
            planet[i].addActionListener(this);
            String a= Integer.toString(i+8);
            planet[i].setActionCommand(a);
            planet[i].setBounds(x, y, 2*rr[i]+100, 2*rr[i]+100);
            panelCenter.add(planet[i]);
            
        }
        changeOpenPlanets();
    }
    
    public void changeOpenPlanets(){
         for(int i = 0; i < 9; i ++)
            {
             if(i>nextLevelToOpen)
                planet[i].setOpen();
             else
                planet[i].setClose();
            }
    }
    
    

    public void save(int lev) {
        String str = String.valueOf(lev) ;
        File outputFile = new File("zapis.txt");
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(
                  new FileOutputStream(outputFile),
                  Charset.forName("UTF-8").newEncoder() 
              );
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        }
        try {
            osw.write(str);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            osw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int open() {
        File inputFile =  new File("zapis.txt");
        
        InputStreamReader isr=null;
        int lev=6;
        try {
            isr = new InputStreamReader(
                new FileInputStream(inputFile),
                Charset.forName("UTF-8").newDecoder() 
            );
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        }
        
        BufferedReader reader = new BufferedReader(isr);
        try {
            lev = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return lev;
        
    }
    
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run() {
                        new MainMenu();
                    }
            });
    }

}

