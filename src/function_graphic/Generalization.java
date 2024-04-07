package function_graphic;

import java.awt.*;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;
import function_graphic.enums.Directions;
import helper.HelperMethods;

public class Generalization extends UMLLine {
    Directions direction;
    public Generalization(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
        int offset = 7;
        direction = HelperMethods.getDirection(x1, y1, x2, y2);
        shapes.addAll(HelperMethods.getGeneralizationLine2Ds(direction, x1, y1, x2, y2, offset));
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Shape shape : shapes){
            g2.draw(shape);
            g2.fill(shape);
        }
    }  
}
