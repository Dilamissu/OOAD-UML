package function_graphic.base_graphics;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.*;

public abstract class UMLObject implements Selectable, FuntionGraphic{
    protected boolean selected;
    // leftX, leftY is the coordinate of the left top corner of the object
    protected int leftX, leftY, width, height, depth;
    protected List<UMLLine> connectedLinesUp, connectedLinesDown, connectedLinesLeft, connectedLinesRight = new ArrayList<UMLLine>();
    protected String name;    
    int rectOffset = 5;
    protected Rectangle upRect, downRect, leftRect, rightRect = new Rectangle();

    public UMLObject(boolean isUseCase){
        leftX = 0;
        leftY = 0;
        width = 90;
        height = 90;
        selected = false;

        if (isUseCase) {
            width*=2;
        }

        initializeRects();
    }
    public UMLObject(int leftX, int leftY, boolean isUseCase){
        this.leftX = leftX;
        this.leftY = leftY;
        width = 90;
        height = 90;
        selected = false;

        if (isUseCase) {
            width*=2;
        }

        initializeRects();
    }

    public UMLObject(int leftX, int leftY, int depth, boolean isUseCase){
        this.leftX = leftX;
        this.leftY = leftY;
        this.depth = depth;
        width = 90;
        height = 90;
        selected = false;
       
        if (isUseCase) {
            width*=2;
        }

        initializeRects();
    }

    private void initializeRects(){
        upRect = new Rectangle(leftX + width/2 - rectOffset, leftY - 2*rectOffset, 2*rectOffset, 2*rectOffset);
        downRect = new Rectangle(leftX + width/2 - rectOffset, leftY + height, 2*rectOffset, 2*rectOffset);
        leftRect = new Rectangle(leftX - 2*rectOffset, leftY + height/2 - rectOffset, 2*rectOffset, 2*rectOffset);
        rightRect = new Rectangle(leftX + width, leftY + height/2 - rectOffset, 2*rectOffset, 2*rectOffset);
    }

    // Already make sure contain the point
    public Point2D selectPoint(int x,int y) {
        double deltaX = x - leftX;
        double deltaY = y - leftY;
        
        if(deltaY/deltaX > height/width){
            // Down or left
            if((deltaY - height)/deltaX < -height/width){
                return new Point2D.Double(leftX, leftY + height / 2);
            }else{
                return new Point2D.Double(leftX + width / 2, leftY + height);
            }
        }else{
            // Up or right
            if((deltaY - height)/deltaX < -height/width){
                return new Point2D.Double(leftX + width / 2, leftY);
            }else{
                return new Point2D.Double(leftX + width, leftY + height / 2);
            }
        }
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
    public boolean isSelected(){
        return selected;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }
    public void setLeftY(int leftY) {
        this.leftY = leftY;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setDepth(int depth){
        this.depth = depth;
    }
    public void setName(String name){
        this.name = name;
    }

    

}
