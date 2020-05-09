package my_game;

/**
 *class contains obstacle image and dimension of game character 
 * 
 * key events for left right and up key is selected
 * 
 * sets the moves of the player
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
 
public class Player{
    
        int  dy, pause = 0;
        static int dx = 0;
        Image moving;
        boolean firstpress = false;
        
        int corx, cory, shift; //player coordinates
        
        
        
        ImageIcon m = new ImageIcon("E:\\my_Game\\src\\my_game\\res\\playerrun.gif");
        ImageIcon j= new ImageIcon("E:\\my_Game\\src\\my_game\\res\\jump.gif");

        
        public Player() {
        
        	
        	shift = 0;
        	corx = 0;
        	cory = 400;
                moving = m.getImage();
       
        }
        
        public int getCoorx() {
            
        	return corx;
        }
        
        public int getCoory() {
            
        	return cory;
        }
       
        public void move() {
            
        	corx -= dx;
        	shift += dx;     
        }
 
 
        public Image getImage() {
            
                return moving;
        }
        
        public int getShift() {
        	return shift;
        }
        
        public int getPauseValue() {
        	return pause;
        }
        
        public void keyPressed(KeyEvent e) {
        	
                int key = e.getKeyCode();
                
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){ 
                    
                	if(firstpress == false) {
                            
                		dx = 1;
                		firstpress = true;
                	}
                	moving = m.getImage();          
                }
               
                if (key == KeyEvent.VK_UP){
                   
                        dy = 2;      /// changed to 2 from 1
                        moving = j.getImage();
                }    
                
                if(key == KeyEvent.VK_SPACE) {
                    
                	
                	dx=0;
                }
                
                if(key == KeyEvent.VK_ENTER) {
                    
                	
                	dx=1; 
                }
           }
 
         public void keyReleased(KeyEvent e) {
        			
                int key = e.getKeyCode();
 
                if (key == KeyEvent.VK_UP){
                    
                	dy = 0;
                        
                        moving = m.getImage();
                }
         }
 }
