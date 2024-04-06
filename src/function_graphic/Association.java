package function_graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;
import function_graphic.enums.ArrowDirections;
import helper.HelperMethods;

public class Association extends UMLLine{
    ArrowDirections direction;
    public Association(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
        int offset = 10;
        direction = HelperMethods.getDirection(x1, y1, x2, y2);
        lines.addAll(HelperMethods.getAssociationLine2Ds(direction, x1, y1, x2, y2, offset));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Line2D line : lines){
            g2.draw(line);
        }
    }    
}
