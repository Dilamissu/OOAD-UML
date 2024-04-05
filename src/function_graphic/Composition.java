package function_graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;

public class Composition extends UMLLine{
    public Composition(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(stratX, startY, endX, endY);
        // TODO add arrow
    }
}
