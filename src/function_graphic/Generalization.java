package function_graphic;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

import function_graphic.base_graphics.UMLLine;
import function_graphic.base_graphics.UMLObject;
import function_graphic.enums.Directions;
import helper.HelperMethods;

public class Generalization extends UMLLine {
    Directions direction;
    
    public Generalization(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
        int offset = 7;
        direction = HelperMethods.getDirection(stratX, startY, endX, endY);
        shapes.addAll(getLine2Ds(direction, stratX, startY, endX, endY, offset));
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
        int offset = 7;
        direction = HelperMethods.getDirection(stratX, startY, endX, endY);
        shapes.addAll(getLine2Ds(direction, stratX, startY, endX, endY, offset));
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Shape shape : shapes){
            g2.draw(shape);
            g2.fill(shape);
        }
    }
    
    public static List<Shape> getLine2Ds(Directions direction,int x1, int y1, int x2, int y2, int offset){
        List<Shape> shapes = new ArrayList<Shape>();
        Polygon polygon = new Polygon();
        polygon.addPoint(x2, y2);
        switch (direction) {
            case UP:
                polygon.addPoint(x2 + offset, y2 + offset);
                polygon.addPoint(x2 - offset, y2 + offset);

                shapes.add(new Line2D.Double(x1, y1, x2, y2 + offset));
                shapes.add(polygon);

                return shapes;
            case DOWN:
                polygon.addPoint(x2 + offset, y2 - offset);
                polygon.addPoint(x2 - offset, y2 - offset);

                shapes.add(new Line2D.Double(x1, y1, x2, y2 - offset));
                shapes.add(polygon);

                return shapes;
            case LEFT:
                polygon.addPoint(x2 + offset, y2 + offset);
                polygon.addPoint(x2 + offset, y2 - offset);

                shapes.add(new Line2D.Double(x1, y1, x2 + offset, y2));
                shapes.add(polygon);
                
                return shapes;
            case RIGHT:
                polygon.addPoint(x2 - offset, y2 + offset);
                polygon.addPoint(x2 - offset, y2 - offset);
            
                shapes.add(new Line2D.Double(x1, y1, x2 - offset, y2));
                shapes.add(polygon);
                
                return shapes;
            default:
                return shapes;
        }
    }
}
