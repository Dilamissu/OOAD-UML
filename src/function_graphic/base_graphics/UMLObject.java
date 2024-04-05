package function_graphic.base_graphics;

import java.awt.Rectangle;
import java.util.*;

public abstract class UMLObject implements Selectable, FuntionGraphic{
    protected boolean selected;
    // leftX, leftY is the coordinate of the left top corner of the object
    protected int leftX, leftY, width, height, depth;
    protected List<Integer> connectedLinesUp, connectedLinesDown, connectedLinesLeft, connectedLinesRight = new ArrayList<Integer>();
    protected String name;    
    int rectOffset = 5;
    protected Rectangle upRect, downRect, leftRect, rightRect = new Rectangle();

    public UMLObject(){
        leftX = 0;
        leftY = 0;
        width = 90;
        height = 90;
        selected = false;

        initializeRects();
    }
    public UMLObject(int leftX, int leftY){
        this.leftX = leftX;
        this.leftY = leftY;
        width = 90;
        height = 90;
        selected = false;

        initializeRects();
    }

    public UMLObject(int leftX, int leftY, int depth){
        this.leftX = leftX;
        this.leftY = leftY;
        this.depth = depth;
        width = 90;
        height = 90;
        selected = false;

        initializeRects();
    }

    private void initializeRects(){
        upRect = new Rectangle(leftX + width/2 - rectOffset, leftY - 2*rectOffset, 2*rectOffset, 2*rectOffset);
        downRect = new Rectangle(leftX + width/2 - rectOffset, leftY + height, 2*rectOffset, 2*rectOffset);
        leftRect = new Rectangle(leftX - 2*rectOffset, leftY + height/2 - rectOffset, 2*rectOffset, 2*rectOffset);
        rightRect = new Rectangle(leftX + width, leftY + height/2 - rectOffset, 2*rectOffset, 2*rectOffset);
    }

    public int getLeftX() {
        return leftX;
    }

    public int getLeftY() {
        return leftY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getDepth() {
        return depth;
    }
    public String getName(){
        return name;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }
    public void setLeftY(int leftY) {
        this.leftY = leftY;
    }
    public void setDepth(int depth){
        this.depth = depth;
    }
    public void setName(String name){
        this.name = name;
    }

    

}
