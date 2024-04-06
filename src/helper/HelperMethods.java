package helper;


import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import function_graphic.enums.ArrowDirections;

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
    public static ArrowDirections getDirection(int x1, int y1, int x2, int y2){
        double ratio = ratio(x1, y1, x2, y2);
        if (ratio > 2) {
            if(x2 < x1){
                return ArrowDirections.UP;
            }else{
                // down
                return ArrowDirections.DOWN;
            }
        } else if (ratio > 0) {
            if(x2 < x1){
                // left
                return ArrowDirections.LEFT;
            }else{
                // right
                return ArrowDirections.RIGHT;
            }
        } else if (ratio > -2) {
            if(x2 < x1){
                //left
                return ArrowDirections.LEFT;
            }else{
                //right
                return ArrowDirections.RIGHT;
            }
        } else {
            if (x2 < x1) {
                //down
                return ArrowDirections.DOWN;
            } else {
                //up
                return ArrowDirections.UP;
            }
        }
    }



    public static List<Line2D> getAssociationLine2Ds(ArrowDirections direction,int x1, int y1, int x2, int y2, int offset){
        List<Line2D> lines = new ArrayList<Line2D>();
        switch (direction) {
            case UP:
                lines.add(new Line2D.Double(x1, y1, x2, y2 + 2*offset));
                lines.add(new Line2D.Double(x2, y2 + 2*offset, x2, y2));
                lines.add(new Line2D.Double(x2, y2, x2 + offset, y2 + offset));
                lines.add(new Line2D.Double(x2, y2, x2 - offset, y2 + offset));
                return lines;
            case DOWN:
                lines.add(new Line2D.Double(x1, y1, x2, y2 - 2*offset));
                lines.add(new Line2D.Double(x2, y2 - 2*offset, x2, y2));
                lines.add(new Line2D.Double(x2, y2, x2 + offset, y2 - offset));
                lines.add(new Line2D.Double(x2, y2, x2 - offset, y2 - offset));
                return lines;
            case LEFT:
                lines.add(new Line2D.Double(x1, y1, x2 + 2*offset, y2));
                lines.add(new Line2D.Double(x2 + 2*offset, y2, x2, y2));
                lines.add(new Line2D.Double(x2, y2, x2 + offset, y2 + offset));
                lines.add(new Line2D.Double(x2, y2, x2 + offset, y2 - offset));
                return lines;
            case RIGHT:
                lines.add(new Line2D.Double(x1, y1, x2 - 2*offset, y2));
                lines.add(new Line2D.Double(x2 - 2*offset, y2, x2, y2));
                lines.add(new Line2D.Double(x2, y2, x2 - offset, y2 + offset));
                lines.add(new Line2D.Double(x2, y2, x2 - offset, y2 - offset));
                return lines;
            default:
                return lines;
        }
    }
}