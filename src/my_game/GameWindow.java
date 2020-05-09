package my_game;

/**
 *game window.in this window game states are kept..for different states we paint different  window
 * coin and obstacle are added here 
 * collision with coin and obstacles are checked.
 * jumpMove calculated
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
 
public class GameWindow extends JPanel implements ActionListener, Runnable {
    
        JFrame f;
      
	private static final long serialVersionUID = 1L;
	Player p;
        public Image backimg,menuBg,helpBg,Overbg,exitBg,lostpl;
        Timer time;
        static int v = 400;
        private Menu Menu;
        private HelpWindow helpi;
        private Over Over;
        Thread animator;
        int totalScore = 0;
        public static int life = 5;
        public static int lifeval=0;
        public static final int N = 64000;
        public static int SCORE;
        
        Obstacle obs;
        Coin cn;
   
        ArrayList < Obstacle > arr = new ArrayList < Obstacle >();
        ArrayList < Coin > brr = new ArrayList < Coin > ();
        
        boolean lost = false, a = false, done2 = false;
        public static enum STATE {MENU,GAME,HELP,OVER}; ///contains states of game

        public static STATE State = STATE.MENU; ///current state
       
        static Font font = new Font("Comic Sans MS", Font.BOLD, 25);
        
        static Font font1 = new Font("Comic Sans MS", Font.BOLD, 40);
        
        public GameWindow() {
        	
        	this.addMouseListener(new Menu()); 
                 
                this.addMouseListener(new HelpWindow());
            	p = new Player();  
                Menu = new Menu();
               
                addKeyListener(new actionListener());
                setFocusable(true); 
                
                ImageIcon i1 = new ImageIcon("E:\\my_Game\\src\\my_game\\res\\menu.jpg");             
                menuBg = i1.getImage();
                
                ImageIcon i2 = new ImageIcon("E:\\my_Game\\src\\my_game\\res\\forestlong.jpg");
                backimg = i2.getImage();
                
                ImageIcon i3=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\helplast.jpg");
                helpBg=i3.getImage();
                
                ImageIcon i4=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\forestpurple.jpg");
                exitBg=i4.getImage();
                
                ImageIcon i5=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\gameover.gif");
                Overbg=i5.getImage();
                
                ImageIcon i6=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\lost.gif");
                lostpl=i6.getImage();
                
                time = new Timer(3, this);   ///speed
                time.start();
                
                int counter = 0;
                
                ///obstacle adding
                for(int k = 1500 ; k <= N ; k += 500, counter++) {
                	 counter %= 4;
                	 if(counter == 3) {
                		 continue;
                	 }
                	 if(counter == 0) {
                		 	arr.add(new Obstacle(k,420,"E:\\my_Game\\src\\my_game\\res\\snowman (3).png"));
                		 	arr.add(new Obstacle(k+50,420,"E:\\my_Game\\src\\my_game\\res\\snowman (3).png"));
                		 	continue;
                	 }
                	 obs = new Obstacle(k + 100, 420, "E:\\my_Game\\src\\my_game\\res\\snowman (3).png");
                	 arr.add(obs);
      
                }
                
                ////coin adding
                
                for(int k = 1000 ; k <= 1800 ; k += 200) {
                		if(k%900 == 0) {
                			cn = new Coin(k,430,"E:\\my_Game\\src\\my_game\\res\\coinsmall.gif");
                		}
                		else cn = new Coin(k,400,"E:\\my_Game\\src\\my_game\\res\\coinsmall.gif");
                		brr.add(cn);
                }
                
                for(int k = 2000 ; k <= N ; k += 500) {
                		Random rand = new Random();
                		int tot = rand.nextInt(100);
                		tot %= 2;
                		tot ^= 1;
                		if(tot == 1) {
                			cn = new Coin(k+200, 400, "E:\\my_Game\\src\\my_game\\res\\coinsmall.gif");
                			brr.add(cn);
                			cn = new Coin(k+300,430,"E:\\my_Game\\src\\my_game\\res\\coinsmall.gif");
                		}
                		else {
                			cn = new Coin(k+300, 410, "E:\\my_Game\\src\\my_game\\res\\coinsmall.gif");
                			brr.add(cn);
                			cn = new Coin(k+200,400,"E:\\my_Game\\src\\my_game\\res\\coinsmall.gif");
                		}
                		brr.add(cn);	
                }
                
        }
 
        
        public void actionPerformed(ActionEvent e) {
            
                checkCollisions();
               
                p.move();
                
                for(int k = 0 ;k < arr.size() ; k++) {
                    
                		if(p.getShift() >= 1000) {
                                    
                			arr.get(k).move(p.dx); 
                		}
                		if(arr.get(k).getX() <= -20) {
                                    
                			arr.remove(k);
                		} 
                } 
                
                for(int k = 0 ; k < brr.size(); k++) {
                    
                		if(p.getShift() >= 500) {
                                    
                			brr.get(k).move(p.dx);
                		}
                }
                
                repaint();
        }
        
 
		public void checkCollisions()
		{
			 
				Rectangle player = new Rectangle(150 ,(400 + (400 - v)) , 89, 89);
				
				for(int k = 0 ; k < arr.size() ; k++) {
                                    
						Rectangle obst = arr.get(k).getBounds();
						
						if(player.intersects(obst)) {
								//System.out.println("Collision occured!");
								lost = true;
                                                                
                                                                lifeval--;
                                                                
                                                               
						}
                                                
                                             
				}
				
				for(int k = 0 ; k < brr.size(); k++) {
						Rectangle coin = brr.get(k).getBounds();
						if(player.intersects(coin)) {
							brr.get(k).endLife();
							///totalScore = 0;
                                                        //lost = true;
                                                        //lifeval--;
						}
				}
		}
 
                /***
                 * 
                 * paints different windows
                 */
        public void paint(Graphics g) {
            
	        	super.paint(g);
	                Graphics2D g2d = (Graphics2D) g;
                        
                        
			if (lost) { /// After collision!
                                    
			    if(life==0){
                                
                                p.dx=0;
                              
                               
                               GameWindow.State = GameWindow.STATE.OVER;
                                
                            }
                            
			        
                            lost = false;
                            
			}
		        
			if (p.dy == 2 && done2 == false) { 
                            
                               /// p.dy 1/2
			        done2 = true;
			        animator = new Thread(this);
			        animator.start();
			}
	 
	                
	                
	                if(State == STATE.GAME) {   
                            
	                	    g2d.drawImage(backimg,p.getCoorx(),0,null);
                                    
	                	    g2d.drawImage(p.getImage(), 150, v, null);           
	               
	                }
	                else if(State==STATE.MENU){
                                
	                        g.drawImage(menuBg, 0, 0, null);
                                
	                        Menu.render(g);
	                }
                        
                        else if(State==STATE.HELP){
                                
	                        g.drawImage(helpBg, 0, 0, null);
                                
	                        helpi.render(g);
	                }
                        
                        else if(State==STATE.OVER){
                                
	                        g2d.drawImage(backimg,p.getCoorx(),0,null);
                                
	                        Over.render(g);
	                }
                        
                        
	               
	        
	                g2d.setFont(font);
                        
	                g2d.setColor(Color.GREEN);
                        
	                int score = 0;
                        
                        boolean q=false;
                        
                        //System.out.println("lifeval "+lifeval);
                        
                        if(lifeval==-118 || lifeval==-236){
                            
                            life--;
                            
                            q=true;
                            
                            lifeval=0;
                            
                            if(life<=0)
                                life=0;
                        }
                        
	                for(int k = 0 ; k < brr.size(); k++) {
	                		if(!brr.get(k).isAlive()) {
	                			score++;
	                		}
	                }
                        
                        SCORE=score;
                        
	                g2d.drawString("Score: " + score , 850, 24);/// Show tot as 0!
                        
                        g2d.setColor(Color.RED);
                        
                        g2d.drawString("Life: " + life , 850, 60);
	            
	             
	                for(int k = 0 ; k < arr.size() ; k++) {
                            
	                	if(p.getShift() >= 700) {
	                		g2d.drawImage(arr.get(k).getImage(), arr.get(k).getX(), arr.get(k).getY(), null);
	                	}
	                }
	                
	                for(int k = 0 ; k < brr.size(); k++) {
	                	if(brr.get(k).isAlive() && p.getShift() >= 700) {
	                		g2d.drawImage(brr.get(k).getImage(), brr.get(k).getX(), brr.get(k).getY(), null);
	                	}
	                }
        }
 
        private class actionListener extends KeyAdapter {
            
                public void keyReleased(KeyEvent e) {
                        p.keyReleased(e);
                }
 
                public void keyPressed(KeyEvent e) {
                        p.keyPressed(e);
                }
        }
 
        boolean h = false;
        boolean done = false;
 
        public void jumpMove() {
 
                if (h == false)
                        v -= 1;
                if (v == 275)
                        h = true;
                if (h == true && v <= 400) {
                        v += 1;
                        if (v == 400) {
                                done = true;
                        }
                }
        }
 
        
        public void run() {
 
                long beforeTime, timeDiff, sleep;
 
                beforeTime = System.currentTimeMillis();
 
                while (done == false) {
 
                        jumpMove();
 
                        timeDiff = System.currentTimeMillis() - beforeTime;
                        sleep = 10 - timeDiff;
 
                        if (sleep < 0)  sleep = 2;
                        
                        try {
                                Thread.sleep(sleep);
                                
                        } catch (InterruptedException e) {
                        	/// EXCP
                        }
 
                        beforeTime = System.currentTimeMillis();
                }
                
                done = false;
                h = false;
                done2 = false;
        }
}
