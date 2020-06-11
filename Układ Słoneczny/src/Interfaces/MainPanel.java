package Interfaces;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import static java.lang.Math.*;


public class MainPanel extends JPanel implements Runnable, ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    
    JPanel mainPanel;   
    Planet planets[];
    static Planet destinationPlanet;
    int wid;
    int heig;
    public static boolean czynny =true;
    static Rocket rocket;
    static Color rocketColor= Color.blue;
  
    double velx = 0;
    double vely = 0;
    Timer t = new Timer(10, this);
    static int fuel;
    static int level;
    boolean winner;
   
    BufferedImage image;
    boolean bye = false;
    boolean boom = false;
    boolean win = false;
    Point centerPoint;

    
    public MainPanel(int width, int height, Planet pl[]) {
        
        wid = width;
        heig = height;
        fuel = 100;
        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, width, height);
        
        planets = pl.clone();
        rocket = new Rocket(new Point(500,300),  rocketColor);
   //     rocket = new Rocket(new Point(200,400),  rocketColor);
        
        t.restart();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    }

    static void setRocketColor(Color c) {
        rocketColor = c;
    }
    
    static int getFuel() {
        return fuel;
    }
    
    void setLevel(int lvl) {
        level = lvl;
    }
    
    void setDestinationPlanet() {
        destinationPlanet = planets[level];
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    
        if(bye) {
            try {
                image = ImageIO.read(new File("src/imagess/bye.jpg"));
                super.paintComponent(g);
                g.drawImage(image, (int)(centerPoint.getX()-(0.5*image.getWidth())), (int)(centerPoint.getY()-(0.5*image.getHeight())), null);
            } catch (IOException e) {       
                e.printStackTrace();
            }
        }
        else if(boom) {
            try {
                image = ImageIO.read(new File("src/imagess/boom.jpg"));
                super.paintComponent(g);
                g.drawImage(image, (int)(centerPoint.getX()-(0.5*image.getWidth())), (int)(centerPoint.getY()-(0.5*image.getHeight())), null);
            } catch (IOException e) {       
                e.printStackTrace();
            }
        }
        else if(win) {
            try {
                image = ImageIO.read(new File("src/imagess/success.jpg"));
                super.paintComponent(g);
                g.drawImage(image, (int)(centerPoint.getX()-(0.5*image.getWidth())), (int)(centerPoint.getY()-(0.5*image.getHeight())), null);
            } catch (IOException e) {       
                e.printStackTrace();
            }
        }
        else {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(0, 0, wid, heig);
            rocket.paintRocket(g);
        
            // planets
            for (int i = 0; i<9; i++) {
                planets[i].paintPlanet(g);  
            }
        }
    
    }
    

    
    public void run() {

        double tmpSunDist=0;
        
        double tmpA = 0;
        double tmpAV = 0;
        
        int tmpX = 0; 
        int tmpY = 0;
        Point tmpPXY;
        
        double Dist =0;
        double DistX =0;
        double DistY =0;
        
        int tmpRX = 0; 
        int tmpRY = 0;
        
        double tmpVX = 0; 
        double tmpVY = 0;
        
        double tmpaX = 0; 
        double tmpaY = 0;

        double sina =0;
        double cosa =0;

        while(czynny) {
            tmpRX=(int)rocket.getApex().getX();
            tmpRY=(int)rocket.getApex().getY();
                        
            tmpVX=rocket.getVx();
            tmpVY=rocket.getVy();
            
            DistX= planets[0].getXY().getX() - tmpRX;
            DistY= planets[0].getXY().getY() - tmpRY;
            Dist = (int) Math.pow(DistX*DistX+DistY*DistY, 0.5);
            sina = DistY/Dist;
            cosa = DistX/Dist;
            tmpaX = cosa*planets[0].getMass()/(Dist*Dist); 
            tmpaY = sina*planets[0].getMass()/(Dist*Dist);
            
            for (int i = 1; i<planets.length; i++)
            {
                //ruch planet
                tmpSunDist=planets[i].getSDist();
                
                tmpA=planets[i].getAngle();
                tmpAV=planets[i].getAngleVelocity();
                tmpA+=tmpAV;
                if(tmpA>360)
                    tmpA=0;
                planets[i].setAngle(tmpA);
                
                tmpX=(int)planets[i].getXY().getX();
                tmpY=(int)planets[i].getXY().getY();
                tmpX=(int)planets[0].getXY().getX()+(int) (tmpSunDist*Math.cos(tmpA));
                tmpY=(int)planets[0].getXY().getY()+(int) (tmpSunDist*Math.sin(tmpA));
    
                tmpPXY = new Point(tmpX, tmpY);
                planets[i].setXY(tmpPXY);   
                
                //ruch rakiety  
                DistX= tmpX - tmpRX;
                DistY= tmpY - tmpRY;
                Dist = (int) Math.pow(DistX*DistX+DistY*DistY, 0.5);
                sina = DistY/Dist;
                cosa = DistX/Dist;              
                
                tmpaX= tmpaX + cosa*planets[i].getMass()/(Dist*Dist);
                tmpaY= tmpaY + sina*planets[i].getMass()/(Dist*Dist);
                
                
            }
            //ruch rakiety
            tmpVX+=tmpaX;
            tmpVY+=tmpaY;
            if(tmpVX>10) //ograniczenie prĂ„â„˘dkoÄąâ€şci Äąâ€şwiatÄąâ€ša
                tmpVX=10;
            if(tmpVX<-10)
                tmpVX=-10;
            
            if(tmpVY>10)
                tmpVY=10;
            if(tmpVY<-10)
                tmpVY=-10;

            tmpRX+=tmpVX;
            tmpRY+=tmpVY;
            
            tmpPXY = new Point(tmpRX, tmpRY);
            rocket.setApex(tmpPXY); 
            
            rocket.setVx(tmpVX);    
            rocket.setVy(tmpVY);    
            
            DataPanel.velocityLabel.setText(" "+GameInterface.velocity+": ["+MainPanel.rocket.getVx()+", "+(-1*MainPanel.rocket.getVy())+"]");//lang
            
            this.repaint();
            //check if ok
            czynny = checkIfOutOfBounds();
            
            for(int i = 0; i<planets.length; i++) {
                if(czynny == true) {
                    czynny = checkPlanetCollision(planets[i]);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        }
    }
    boolean checkPlanetCollision(Planet p){
            if(pow((pow(rocket.getApex().getX()-p.getXY().getX(), 2)+pow(rocket.getApex().getY()-p.getXY().getY(), 2)), 0.5)<p.radius) {//zderzenie ze złą planetą
                if(p != destinationPlanet) {
                    winner = false;
                    boom = true;
                }
                else {//dobra planeta
                    winner = true;
                    MainMenu.money+=50;//nagroda
                    MainMenu.balance.setText(String.valueOf(MainMenu.money)+"$");
                    if((MainMenu.nextLevelToOpen>0) &&(level==(MainMenu.nextLevelToOpen+1))) {
                        MainMenu.planet[MainMenu.nextLevelToOpen].setOpen();
                        MainMenu.nextLevelToOpen-=1;
                        
                    }
                    win = true;
                }
                return false;
            }
            else
                return true;//can continue
    }

    boolean checkIfOutOfBounds() {//czy rakieta nie odleciała poza ekran      
        if((rocket.getApex().getX()<0)||(rocket.getApex().getX()>wid)||(rocket.getApex().getY()<0)||(rocket.getApex().getY()>heig)) {
            System.out.println("farewell!");
            bye = true;
            winner = false;
            return false;
        }
        else
            return true;//can continue
    }

    void updateDataPanel() {
        DataPanel.fuelBarWidth = (int)(fuel*DataPanel.w/100);
        DataPanel.fuelLabel.setText(" "+GameInterface.fuelStr+": "+fuel+"%");
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(fuel>0) {
            if(code == KeyEvent.VK_UP) {
                up();    
            }
            if(code == KeyEvent.VK_DOWN) {
                down();
            }
            if(code == KeyEvent.VK_LEFT) {
                left();
            }
            if(code == KeyEvent.VK_RIGHT) {
                right();
            }
            if(!(bye||boom||win)==true)
                fuel--;
            updateDataPanel();
            Point p = new Point((int)(rocket.getApex().getX()+rocket.getVx()), (int)(rocket.getApex().getY()+rocket.getVy()));
            rocket.setApex(p);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
    public void up() {
        vely = -1;
        double tmpvy = rocket.getVy();
        //ograniczenie prędkości
        if(tmpvy+vely>-10)
            rocket.setVy(tmpvy+vely);
        else
            rocket.setVy(-10);
        
    }   
    public void down() {
        vely = 1;
        double tmpvy = rocket.getVy();
    
        if(tmpvy+vely<10)
            rocket.setVy(tmpvy+vely);
        else
            rocket.setVy(10);
    }
    public void left() {
        velx = -1;          
        double tmpvx = rocket.getVx();
        
        if(tmpvx+velx>-10)
            rocket.setVx(tmpvx+velx);
        else            
            rocket.setVx(-10);  
    }
    public void right() {
        velx += 1;
        double tmpvx = rocket.getVx();
    
        if(tmpvx+velx<10)
            rocket.setVx(tmpvx+velx);
        else            
            rocket.setVx(10);
    }
    
}

