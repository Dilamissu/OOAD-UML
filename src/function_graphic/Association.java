package function_graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;
import function_graphic.enums.Directions;
import helper.HelperMethods;

public class Association extends UMLLine{
    Directions direction;
    public Association(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
        int offset = 10;
        direction = HelperMethods.getDirection(stratX, startY, endX, endY);
        shapes.addAll(HelperMethods.getAssociationLine2Ds(direction, stratX, startY, endX, endY, offset));
    }

    @Override
    public void move(int dx, int dy, UMLObject object) {
        if(object.equals(from)){
            stratX += dx;
            startY += dy;
        }else if(object.equals(to)){
            endX += dx;
            endY += dy;
        }
        shapes.clear();
        int offset = 10;
        direction = HelperMethods.getDirection(stratX, startY, endX, endY);
        shapes.addAll(HelperMethods.getAssociationLine2Ds(direction, stratX, startY, endX, endY, offset));
        System.out.println("Association move");
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Shape shape : shapes){
            g2.draw(shape);
        }
    }    
}
