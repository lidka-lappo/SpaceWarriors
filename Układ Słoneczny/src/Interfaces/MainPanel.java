package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

    CircleButton[] planet;
    static int money = 0;
    
    JMenu menu;
    JMenuItem i1, i2, i3;
    
    static GameInterface newGame;
    int lvl = 1;
    static int openLevels = 4;
    boolean ifOpen[];
    ///lang
    ShopFrame shopfr;
    static String language = "en";
	static String country = "EN";
	static Locale locale = null;
	static ResourceBundle r = null;

	String nam[];
	Color col[];
	int rr[];
	int dist[];
	
    private static final long serialVersionUID = 1L;

    public MainMenu() throws HeadlessException {
    	
        frame = new JFrame();
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
        
        //lang
        menu=new JMenu("Language");
        i1=new JMenuItem("Polish"); 
        i2=new JMenuItem("English");  

        
        menu.add(i1);
        i1.setActionCommand("3");
        i1.addActionListener(this);
        i1.setSelected(true);
        ///lang
        Image img = new ImageIcon("src/imagess/pol.jpg").getImage();
        i1.setIcon(new ImageIcon(img.getScaledInstance(15, 10, java.awt.Image.SCALE_SMOOTH)));
        
        menu.add(i2);         
        i2.setActionCommand("4");
        i2.addActionListener(this);
        i2.setSelected(true);
      ///lang
        Image img2 = new ImageIcon("src/imagess/ang.jpg").getImage();
        i2.setIcon(new ImageIcon(img2.getScaledInstance(15, 10, java.awt.Image.SCALE_SMOOTH)));

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
   
 ///////////lang   
    public void changeMenuLanguage(String lang, String count) {
    	locale = new Locale(lang, count);
		r = ResourceBundle.getBundle("Bundle", locale);
		newGameButton.setText(r.getString("new_game"));
		shopButton.setText(r.getString("shop"));
		chooseLevel.setText(r.getString("choose_level"));
		i1.setText(r.getString("polish"));
		i2.setText(r.getString("english"));
		menu.setText(r.getString("language"));
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
            case 3://POLSKI
            	language = "pl";
            	country = "PL";
            	changeMenuLanguage(language, country);
               	break;
            case 4://ANGIELSKI
            	language = "en";
            	country = "EN";
            	changeMenuLanguage(language, country);
               	break;
            case 5:
            	break;         
            default:
            	lvl = choice-5;
           
        }

    }
    
    public void changeOpenPlanets() {
    	for(int i =1; i<9;  i++)
    	{
    		if(i<=openLevels)
    			ifOpen[i]=true;
    	}
    		
    }
    
	public void setUpPlanets()
	{
        int w=280;
        int h=110;
        int x=0;
        int y =0; 
        int angle=0;
       
        changeOpenPlanets();
        for(int i = 0; i < 9; i ++)
        {
        	
            angle+=45;
            x=(w-rr[i])+(int)((dist[i])*Math.sin(angle));
            y=(h-rr[i])+(int)((dist[i])*Math.cos(angle));
            planet[i] = new CircleButton(nam[i], col[i],2*rr[i], ifOpen[i]);
            planet[i].addActionListener(this);
            String a= Integer.toString(i+5);
            planet[i].setActionCommand(a);
            planet[i].setBounds(x, y, 2*rr[i]+100, 2*rr[i]+100);
            panelCenter.add(planet[i]);
            
        }
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
