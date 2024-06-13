package function_graphic;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

import function_graphic.base_objects.UMLLine;
import function_graphic.base_objects.UMLObject;
import function_graphic.enums.Directions;
import helper.HelperMethods;

public class Association extends UMLLine{
    Directions direction;
    private static final int offset = 10;
    public Association(int x1, int y1, int x2, int y2, UMLObject from, UMLObject to) {
        super(x1, y1, x2, y2, from, to);
        direction = HelperMethods.getDirection(stratX, startY, endX, endY);
        shapes.addAll(getLine2Ds(direction, stratX, startY, endX, endY));
    }

    @Override
    public void setFrom(int x, int y, UMLObject from){
        super.setFrom(x, y, from);
        shapes.clear();
        shapes.addAll(getLine2Ds(direction, stratX, startY, endX, endY));
    }

    @Override
    public void setTo(int x, int y, UMLObject to){
        super.setTo(x, y, to);
        shapes.clear();
        shapes.addAll(getLine2Ds(direction, stratX, startY, endX, endY));
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
        direction = HelperMethods.getDirection(stratX, startY, endX, endY);
        shapes.addAll(getLine2Ds(direction, stratX, startY, endX, endY));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Shape shape : shapes){
            g2.draw(shape);
        }
    }
    
    public static List<Shape> getLine2Ds(Directions direction,int x1, int y1, int x2, int y2){
        List<Shape> shapes = new ArrayList<Shape>();
        switch (direction) {
            case UP:
                shapes.add(new Line2D.Double(x1, y1, x2, y2 + 2*offset));
                shapes.add(new Line2D.Double(x2, y2 + 2*offset, x2, y2));
                shapes.add(new Line2D.Double(x2, y2, x2 + offset, y2 + offset));
                shapes.add(new Line2D.Double(x2, y2, x2 - offset, y2 + offset));
                return shapes;
            case DOWN:
                shapes.add(new Line2D.Double(x1, y1, x2, y2 - 2*offset));
                shapes.add(new Line2D.Double(x2, y2 - 2*offset, x2, y2));
                shapes.add(new Line2D.Double(x2, y2, x2 + offset, y2 - offset));
                shapes.add(new Line2D.Double(x2, y2, x2 - offset, y2 - offset));
                return shapes;
            case LEFT:
                shapes.add(new Line2D.Double(x1, y1, x2 + 2*offset, y2));
                shapes.add(new Line2D.Double(x2 + 2*offset, y2, x2, y2));
                shapes.add(new Line2D.Double(x2, y2, x2 + offset, y2 + offset));
                shapes.add(new Line2D.Double(x2, y2, x2 + offset, y2 - offset));
                return shapes;
            case RIGHT:
                shapes.add(new Line2D.Double(x1, y1, x2 - 2*offset, y2));
                shapes.add(new Line2D.Double(x2 - 2*offset, y2, x2, y2));
                shapes.add(new Line2D.Double(x2, y2, x2 - offset, y2 + offset));
                shapes.add(new Line2D.Double(x2, y2, x2 - offset, y2 - offset));
                return shapes;
            default:
                return shapes;
        }
    }
}
