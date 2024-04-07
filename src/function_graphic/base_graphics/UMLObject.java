package function_graphic.base_graphics;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.*;

import function_graphic.enums.Directions;

public abstract class UMLObject implements Selectable, FuntionGraphic{
    protected boolean selected;
    // leftX, leftY is the coordinate of the left top corner of the object
    protected int leftX, leftY, width, height, depth;
    protected List<UMLLine> connectedLinesUp = new ArrayList<UMLLine>();
    protected List<UMLLine> connectedLinesDown = new ArrayList<UMLLine>();
    protected List<UMLLine> connectedLinesLeft = new ArrayList<UMLLine>();
    protected List<UMLLine> connectedLinesRight = new ArrayList<UMLLine>();
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
    // Return the point that will be connected
    public Point2D selectPoint(int x,int y) {
        double deltaX = x - leftX;
        double deltaY = y - leftY;
        double ratio = (double)height/width;
        if(deltaY/deltaX > ratio){
            // Down or left
            if((deltaY - height)/deltaX < -ratio){
                // left
                System.out.println("Select left");
                return new Point2D.Double(leftX, leftY + height / 2);
            }else{
                // down
                System.out.println("Select down");
                return new Point2D.Double(leftX + width / 2, leftY + height);
            }
        }else{
            // Up or right
            if((deltaY - height)/deltaX < -ratio){
                // up
                System.out.println("Select up");
                return new Point2D.Double(leftX + width / 2, leftY);
            }else{
                // right
                System.out.println("Select right");
                return new Point2D.Double(leftX + width, leftY + height / 2);
            }
        }
    }

    public void addConnectedLine(UMLLine line, Directions position){
        System.out.println("Add connected line: " + line + " at position: " + position);
        switch (position) {
            case UP:
                connectedLinesUp.add(line);
                break;
            case DOWN:
                connectedLinesDown.add(line);
                break;
            case LEFT:
                connectedLinesLeft.add(line);
                break;
            case RIGHT:
                connectedLinesRight.add(line);
                break;
            default:
                break;
        }
    }

    public int getLeftX() {
        return leftX;
    }

    public int getLeftY() {
        return leftY;
    }
    
    public int getRightX() {
        return leftX + width;
    }
    
    public int getRightY() {
        return leftY + height;
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

    public void moveRects(int deltaX, int deltaY){
        upRect.setLocation(upRect.x + deltaX, upRect.y + deltaY);
        downRect.setLocation(downRect.x + deltaX, downRect.y + deltaY);
        leftRect.setLocation(leftRect.x + deltaX, leftRect.y + deltaY);
        rightRect.setLocation(rightRect.x + deltaX, rightRect.y + deltaY);
    }

    public void moveLine(int deltaX, int deltaY){
        for(UMLLine line: connectedLinesUp){
            line.move(deltaX, deltaY, this);
        }
        for(UMLLine line: connectedLinesDown){
            line.move(deltaX, deltaY, this);
        }
        for(UMLLine line: connectedLinesLeft){
            line.move(deltaX, deltaY, this);
        }
        for(UMLLine line: connectedLinesRight){
            line.move(deltaX, deltaY, this);
        }
    }
    

}
