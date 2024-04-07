package function_graphic;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;
import function_graphic.enums.Directions;
import helper.HelperMethods;

public class Composition extends UMLLine{
    Directions position;
    public Composition(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
        int offset = 10;
        position = HelperMethods.getRelativePositions(
            new Point2D.Double(stratX, startY),
            new Point2D.Double(from.getLeftX(), from.getLeftY()),
            from.getWidth(), from.getHeight()
        );
        shapes.addAll(getLine2Ds(position, stratX, startY, endX, endY, offset));
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
        position = HelperMethods.getRelativePositions(
            new Point2D.Double(stratX, startY),
            new Point2D.Double(from.getLeftX(), from.getLeftY()),
            from.getWidth(), from.getHeight()
        );
        shapes.addAll(getLine2Ds(position, stratX, startY, endX, endY, offset));
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Shape shape : shapes){
            g2.draw(shape);
        }
    }
    public static List<Shape> getLine2Ds(Directions position,int x1, int y1, int x2, int y2, int offset){
        List<Shape> shapes = new ArrayList<Shape>();
        switch (position) {
            case UP:
                shapes.add(new Line2D.Double(x1, y1, x1 - offset/2, y1 - offset/2));
                shapes.add(new Line2D.Double(x1, y1, x1 + offset/2, y1 - offset/2));

                shapes.add(new Line2D.Double(x1, y1 - offset, x1 - offset/2, y1 - offset/2));
                shapes.add(new Line2D.Double(x1, y1 - offset, x1 + offset/2, y1 - offset/2));

                shapes.add(new Line2D.Double(x1, y1 - offset, x2, y2));

                return shapes;
            case DOWN:
                shapes.add(new Line2D.Double(x1, y1, x1 - offset/2, y1 + offset/2));
                shapes.add(new Line2D.Double(x1, y1, x1 + offset/2, y1 + offset/2));

                shapes.add(new Line2D.Double(x1, y1 + offset, x1 - offset/2, y1 + offset/2));
                shapes.add(new Line2D.Double(x1, y1 + offset, x1 + offset/2, y1 + offset/2));

                shapes.add(new Line2D.Double(x1, y1 + offset, x2, y2));

                return shapes;
            case LEFT:
                shapes.add(new Line2D.Double(x1, y1, x1 - offset/2, y1 - offset/2));
                shapes.add(new Line2D.Double(x1, y1, x1 - offset/2, y1 + offset/2));

                shapes.add(new Line2D.Double(x1 - offset, y1, x1 - offset/2, y1 - offset/2));
                shapes.add(new Line2D.Double(x1 - offset, y1, x1 - offset/2, y1 + offset/2));

                shapes.add(new Line2D.Double(x1 - offset, y1, x2, y2));
                
                return shapes;
            case RIGHT:
                shapes.add(new Line2D.Double(x1, y1, x1 + offset/2, y1 - offset/2));
                shapes.add(new Line2D.Double(x1, y1, x1 + offset/2, y1 + offset/2));

                shapes.add(new Line2D.Double(x1 + offset, y1, x1 + offset/2, y1 - offset/2));
                shapes.add(new Line2D.Double(x1 + offset, y1, x1 + offset/2, y1 + offset/2));
            
                shapes.add(new Line2D.Double(x1 + offset, y1, x2, y2));
                
                return shapes;
            default:
                return shapes;
        }
    }
}
