package function_graphic;

import java.awt.Graphics;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;

public class Generalization extends UMLLine {
    public Generalization(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
    }
    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }  
}