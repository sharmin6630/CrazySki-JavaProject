package my_game;

/**
 *This class sets frame size and calls game window constructor 
 * 
 */
import javax.swing.*;
 
public class GameFrame {
 
        public GameFrame(){
            
                JFrame frame = new JFrame("Crazy Ski");
                                
                frame.setSize(1000,600); //size of background
                
                frame.add(new GameWindow()); //calls gamepanel constructor 
                
                frame.setResizable(false);
                
                frame.setVisible(true);
                
                frame.setLocationRelativeTo(null); //center the gui on the screen
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
}
