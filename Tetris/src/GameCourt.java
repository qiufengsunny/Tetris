import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameCourt extends JPanel implements ActionListener {

	private int speed = 400;
    private final int WIDTH = 10;
    private final int HEIGHT = 26;
    private Timer timer;
    private boolean finishedFalling = false;
    private boolean started = false;
    private boolean paused = false;
    private int linesRemoved = 0;
    private int currX = 0;
    private int currY = 0;
    private JLabel statusbar;
    private Shape curr;
    private Shape.Mino[] GameCourt;

	/**
	 * Constructor
	 * @param  parent for the statusbar
	 * @author Rajat Bhageria
	 */
    public GameCourt(Game parent) {
    	setBackground(Color.BLACK);

	  setBorder(BorderFactory.createLineBorder(Color.BLACK));

       setFocusable(true);
       curr = new Shape();
       timer = new Timer(speed, this);

       statusbar =  parent.getStatusBar();
       GameCourt = new Shape.Mino[WIDTH * HEIGHT];
       addKeyListener(new Adapter());
       //Get rid of the previous bricks when the 
       //user creates a new instance of the game. 
       for (int i = 0; i < HEIGHT * WIDTH; ++i)
           GameCourt[i] = Shape.Mino.NONE;    }
    
	/**
	 * ActionLister method since this method 
	 * implements ActionListener
	 */
    public void actionPerformed(ActionEvent e) {
        if (finishedFalling) {
            finishedFalling = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

	/**
	 * Width of one of the individual squares in Minos.
	 */
    private int squareWidth = (int) getSize().getWidth() / WIDTH; 

	/**
	 * Height of one of the individual squares in Minos.
	 */
    private int SquareHeight = (int) getSize().getHeight() / HEIGHT; 
    
    
	/**
	 * Returns the Mino at a particular part of a board
	 * @param coordinates x, y
	 * @return Mino at the particular coordinates
	 */
    private Shape.Mino shapeAt(int x, int y) {
    	return GameCourt[(y * WIDTH) + x]; 
    }


	/**
	 * Start the game
	 */
    public void start(){
        if (paused){
        	pause();
        }
        started = true;
        finishedFalling = false;
        linesRemoved = 0;
        //Get rid of the old bricks when the user
        //starts a new game. 
        for (int i = 0; i < HEIGHT * WIDTH; ++i)
            GameCourt[i] = Shape.Mino.NONE;
        newPiece();
        timer.start();
    }


	/**
	 * Pause the game
	 */
    public void pause(){
        paused = !paused;
        if (paused) {
            timer.stop();
            statusbar.setText("Paused: Press 'pause' again");
        }
        else {
            timer.start();
            statusbar.setText("Score: " + String.valueOf(linesRemoved));
        }
        repaint();
    }

	/**
	 * Paint the graphics for the game using Graphics2D
	 */
    public void paint(Graphics g){ 
        super.paint(g);
        int topOfGame = (int) getSize().getHeight() - HEIGHT * SquareHeight;
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                Shape.Mino shape = shapeAt(j, HEIGHT - i - 1);
                if (shape != Shape.Mino.NONE)
                    drawSquare(g, j * squareWidth,
                               topOfGame + i * SquareHeight, shape);
            }
        }
        
        //Drawing the shapes falling
        if (curr.getShape() != Shape.Mino.NONE) {
            for (int i = 0; i < 4; ++i) {
                int newX = currX + curr.getX(i);
                int newY = currY - curr.getY(i);
                drawSquare(g, newX * squareWidth,
                           topOfGame + (HEIGHT - newY - 1) * SquareHeight,
                           curr.getShape());
            }
        }
    }

	/**
	 * Move current Mino down one line at a time
	 */
    private void oneLineDown(){
        if (!tryMove(curr, currX, currY - 1)) pieceDropped();
    }


	/**
	 * Drop the current Mino to the floor if the user presses space
	 */
    private void pieceDropped(){
        for (int i = 0; i < 4; ++i) {
            int newX = currX + curr.getX(i);
            int newY = currY - curr.getY(i);
            GameCourt[(newY * WIDTH) + newX] = curr.getShape();
        }
        removeFull();
        if (!finishedFalling) newPiece();
    }

	/**
	 * Add a new piece after the old one has been set. 
	 */
    private void newPiece(){
        curr.setRandom();
        currX = WIDTH / 2 ;
        currY = HEIGHT - 1 + curr.minY();
        if (!tryMove(curr, currX, currY)) {
            curr.setShape(Shape.Mino.NONE);
            timer.stop();
            started = false;
            statusbar.setText("Game Over! Press 'New Game' ");
        }
    }

	/**
	 * See if can move the given shape to the given coordinates
	 * If you can move it, this method moves the shape. 
	 * @param new shape you're trying to see can move
	 * @param coordinates you're trying to move it to
	 * @return boolean if the program was able to move the shape. 
	 */
    private boolean tryMove(Shape newPiece, int x, int y){
        for (int i = 0; i < 4; ++i) {
            int newX = x + newPiece.getX(i);
            int newY = y - newPiece.getY(i);
            if (newX >= WIDTH || newX < 0 || newY >= HEIGHT || newY < 0 )
                return false;
            if (shapeAt(newX, newY) != Shape.Mino.NONE)
                return false;
        }

        curr = newPiece;
        currX = x;
        currY = y;
        
        repaint();
        return true;
    }

	/**
	 * Remove the lines in the games that are full
	 */
    private void removeFull(){
        int numFullLines = 0;
        for (int i = HEIGHT - 1; i >= 0; --i) {
        	
           boolean lineIsFull = true;
            
             for (int j = 0; j < WIDTH; ++j) {
            	
               if (shapeAt(j, i) == Shape.Mino.NONE) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < HEIGHT - 1; ++k) {
                    for (int j = 0; j < WIDTH; ++j)
                         GameCourt[(k * WIDTH) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            finishedFalling = true;
            while (speed>10) speed -= numFullLines*20;
            statusbar.setText("Score: " + String.valueOf(linesRemoved));
            curr.setShape(Shape.Mino.NONE);
            linesRemoved += numFullLines;
            
            //Repaint after removing all the lines. 
            repaint();
        }
     }

	/**
	 * Draw each individual 
	 * @param Graphics2D
	 * @param Coordinates of shape you're trying to draw
	 * @param Mino you're trying to draw. 
	 */
    private void drawSquare(Graphics g, int x, int y, Shape.Mino shape){
        Color colors[] = { new Color(0, 0, 0), new Color(243, 02, 101), 
            new Color(121, 240, 120), new Color(120, 102, 212), 
            new Color(204, 204, 102), new Color(219, 2, 208), 
            new Color(112, 121, 2), new Color(213, 102, 0)
        };

        Color color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, SquareHeight - 2);
        g.drawLine(x, y + SquareHeight, x, y);
        g.drawLine(x, y, x + squareWidth, y);
        g.drawLine(x , y + SquareHeight,x + squareWidth, y + SquareHeight);
        g.drawLine(x + squareWidth, y + SquareHeight,x + squareWidth, y);
    }

	/**
	 * KeyAdapter extension for key strokes
	 */
    class Adapter extends KeyAdapter {
         public void keyPressed(KeyEvent e) {
             if (!started || curr.getShape() == Shape.Mino.NONE ) return;
             if (paused) return;
             int keycode = e.getKeyCode();
             
             //If user tries to pause the game. 
             if (keycode == 'P') {
                 pause();
                 return;
             }
             if (keycode == 'p') {
                 pause();
                 return;
             }
             switch (keycode) {
	             //If user presses 'd,' the brick goes down one line. 
	             case 'd':
	                 oneLineDown();
	                 break;
	             case 'D':
	                 oneLineDown();
	                 break;
	             //Starting a new game (a different way). 
	             case 'n': 
	            	 start();
	            	 break;
	             case 'N':
	                 start();
	                 break;
	             case KeyEvent.VK_DOWN:
	                 tryMove(curr.rotateRight(), currX, currY);
	                 break;
	             case KeyEvent.VK_UP:
	                 tryMove(curr.rotateLeft(), currX, currY);
	                 break;
	             case KeyEvent.VK_SPACE:
	            	 //if space bar is pressed,
	            	 //make the brick fall completely to the floor. 
	            	 int newY = currY;
	                 while (newY > 0) {
	                     if (!tryMove(curr, currX, newY - 1))
	                         break;
	                     --newY;
	                 }
	                 pieceDropped();	                 
	                 break;
	             case KeyEvent.VK_LEFT:
	                 tryMove(curr, currX - 1, currY);
	                 break;
	             case KeyEvent.VK_RIGHT:
	                 tryMove(curr, currX + 1, currY);
	                 break;
             }
         }
     }
}