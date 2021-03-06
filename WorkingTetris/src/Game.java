/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game extends JFrame {

    JLabel statusbar;
    public Game() {

        statusbar = new JLabel("Score: 0");
        add(statusbar, BorderLayout.SOUTH);
        
        board = new GameCourt(this);
        add(board, BorderLayout.CENTER);

        setSize(210, 500);
        setTitle("Tetris by Rajat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        createMenu();
   }
   private final GameCourt board;

   public JLabel getStatusBar() {
       return statusbar;
   }

    public static void main(String[] args) {
        Game game = new Game();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    } 
    
    private void createMenu(){

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newAction = new JMenuItem("New Game");
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.start();
            }
        });
        
        JMenuItem directionsAction = new JMenuItem("Directions");
        final String directions = "This is how to play Tetris by Rajat Bhageria\n"
        		+ "\n"
        		+ "To start a new game, go to 'File' and then press 'New Game'\n"
        		+ "To pause a game, you can go to 'File' press 'Pause/Play'\n"
        		+ "To start a paused game, press 'Pause/Play' again\n"
        		//+ "You can also pause the game by pressing the 'p' key\n"
        		+ "\n"
        		+ "The 'Left Arrow Key' pushes the current block left\n"
        		+ "The 'Right Arrow Key' pushes the current block right\n"
        		+ "The 'Up Arrow Key' rotates the current block left\n"
        		+ "The 'Down Arrow Key' rotates the current block right\n"
        		+ "\n"
        		+ "The score for your current game is shown right below the Game\n"
        		+ "If you press 'Space Bar' the falling block will drop down completely\n"
        		+ "Pressing the 'd' key makes the current block fall down one line\n"
        		+ "If your blocks reach the top of the playing screen, press 'New Game' to play again!";
        
        directionsAction.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, directions);
    		}
    	});
        
        JMenuItem pauseAction = new JMenuItem("Pause/Play");
        pauseAction.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	board.pause();
        	}
    	});
      
        fileMenu.add(newAction);
        fileMenu.add(directionsAction);
        fileMenu.add(pauseAction);
        
        menuBar.add(fileMenu);
        add(menuBar, BorderLayout.NORTH);

        this.setJMenuBar(menuBar);
    }
    
}

