package helper;


import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import function_graphic.enums.Directions;

public class HelperMethods {
    public static JButton createButton(String text, boolean isBordered){
        JButton button = new JButton(text);
        
        if(!isBordered){
            button.setBorderPainted(false);
        }
        return button;
    }
    public static double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public static double ratio(int x1, int y1, int x2, int y2){
        return (double)(y2 - y1) / (double)(x2 - x1);
    }

    /// Get the direction of the line by angle in shape
    public static Directions getDirection(int x1, int y1, int x2, int y2){
        double ratio = ratio(x1, y1, x2, y2);
        if (ratio > 2) {
            if(x2 < x1){
                return Directions.UP;
            }else{
                // down
                return Directions.DOWN;
            }
        } else if (ratio > 0) {
            if(x2 < x1){
                // left
                return Directions.LEFT;
            }else{
                // right
                return Directions.RIGHT;
            }
        } else if (ratio > -2) {
            if(x2 < x1){
                //left
                return Directions.LEFT;
            }else{
                //right
                return Directions.RIGHT;
            }
        } else {
            if (x2 < x1) {
                //down
                return Directions.DOWN;
            } else {
                //up
                return Directions.UP;
            }
        }
    }

    public static Directions getRelativePositions(Point2D point, Point2D shapeLeft, int shapeWidth, int shapeHeight){
        if(point.getX() == shapeLeft.getX()){
            return Directions.LEFT;
        }else if(point.getX() == shapeLeft.getX() + shapeWidth){
            return Directions.RIGHT;
        }else if(point.getY() == shapeLeft.getY()){
            return Directions.UP;
        }else if(point.getY() == shapeLeft.getY() + shapeHeight){
            return Directions.DOWN;
        }else{
            throw new IllegalArgumentException("The point is not on the shape");
        }
    }



    public static List<Shape> getAssociationLine2Ds(Directions direction,int x1, int y1, int x2, int y2, int offset){
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

    public static List<Shape> getGeneralizationLine2Ds(Directions direction,int x1, int y1, int x2, int y2, int offset){
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