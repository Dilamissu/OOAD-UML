package function_graphic.base_objects;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.*;

import javax.swing.JComponent;

import function_graphic.base_interfaces.*;
import helper.HelperMethods;

public abstract class UMLLine extends JComponent implements HavingTwoEnd<UMLObject>{
    protected int stratX, startY, endX, endY;
    protected UMLObject from, to;
    protected double ratio;
    protected List<Shape> shapes = new ArrayList<Shape>();

    /// [StartX, StartY] is the start point of the line
    /// [EndX, EndY] is the end point of the line
    /// from is the object where the line starts
    /// to is the object where the line ends
    public UMLLine(int stratX, int startY, int endX, int endY, UMLObject from, UMLObject to){
        this.stratX = stratX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.from = from;
        this.to = to;

        this.ratio = HelperMethods.ratio(stratX, startY, endX, endY);
    }
    @Override
    public void setFrom(int x, int y, UMLObject from){
        this.from = from;
        Point2D stratP = from.selectPoint(x, y);
        this.stratX = (int)stratP.getX();
        this.startY = (int)stratP.getY();
    }
    @Override
    public void setTo(int x, int y, UMLObject to){
        this.to = to;
        Point2D endP = to.selectPoint(x, y);
        this.endX = (int)endP.getX();
        this.endY = (int)endP.getY();
    }
    public int getStartX() {
        return stratX;
    }
    public int getStartY() {
        return startY;
    }
    public int getEndX() {
        return endX;
    }
    public int getEndY() {
        return endY;
    }
    public double getRatio(){
        return ratio;
    }

    public UMLObject getFrom() {
        return from;
    }
    public UMLObject getTo() {
        return to;
    }
    abstract public void move(int dx, int dy, UMLObject object);
}
