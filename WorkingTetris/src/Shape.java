import java.util.Random;
import java.lang.Math;

public class Shape {

    enum Mino {NONE, Type1, Type2, Type3, 
               Type4, SQUARE, Type6, Type7 };

    private Shape.Mino curr;
    private int coord[][];
    private int[][][] coordT;


    public Shape() {
        coord = new int[4][2];
        setShape(Shape.Mino.NONE);
    }

    public void setShape(Shape.Mino shape) {
         coordT = new int[][][] {
			{{0,0}, {0,0}, {0,0}, {0,0}}, //NONE Mino
			{{0,-1}, {0,0}, {-1,0}, {-1,1}}, //LSTAIR Mino
			{{0,-1}, {0,0}, {1,0}, {1,1}}, //RSTAIR Mino
			{{0,-1}, {0,0}, {0,1}, {0,2}}, //LONG Mino
			{{-1,0}, {0,0}, {1,0}, {0,1}}, //MIDDLE Mino
			{{0,0}, {1,0}, {0,1}, {1,1}}, //BLOCK Mino
			{{-1,-1}, {0,-1}, {0,0}, {0,1}}, //RTAIL Mino
			{{1,-1}, {0,-1}, {0,0}, {0,1}} //LTAIL Mino
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coord[i][j] = coordT[shape.ordinal()][i][j];
            }
        }
        curr = shape;
    }


    public void setRandom(){
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Shape.Mino[] values = Shape.Mino.values(); 
        setShape(values[x]);
    }

    public int minX(){
      int x = coord[0][0];
      for (int i=0; i < 4; i++) {
          x = Math.min(x, coord[i][0]);
      }
      return x;
    }

    public int minY() {
      int y = coord[0][1];
      for (int i=0; i < 4; i++) {
          y = Math.min(y, coord[i][1]);
      }
      return y;
    }

    public Shape rotateLeft() {
        if (curr == Shape.Mino.SQUARE) return this;
        Shape tgt = new Shape();
        tgt.curr = curr;

        for (int i = 0; i < 4; ++i) {
            tgt.setY(i, -getX(i));
            tgt.setX(i, getY(i));
        }
        return tgt;
    }

    public Shape rotateRight(){
        if (curr == Shape.Mino.SQUARE) return this;
        Shape tgt = new Shape();
        tgt.curr = curr;

        for (int i = 0; i < 4; ++i) {
            tgt.setY(i, getX(i));
            tgt.setX(i, -getY(i));
        }
        return tgt;
    }

    private void setX(int index, int x) {
    	coord[index][0] = x; 
    }
    
    private void setY(int index, int y) {
    	coord[index][1] = y; 
    }
    
    public int getX(int index) {
    	return coord[index][0];
    }
    
    public int getY(int index) {
    	return coord[index][1]; 
    }
    
    public Shape.Mino getShape()  {
    	return curr; 
    }
}