package function_graphic.base_graphics;

import java.awt.Shape;
import java.util.*;
import helper.HelperMethods;

public abstract class UMLLine implements FuntionGraphic{
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

}
