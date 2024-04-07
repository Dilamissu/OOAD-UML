package function_graphic.base_objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import function_graphic.base_interfaces.*;

public class Group implements FuntionGraphic, Selectable{
    protected int depth;
    protected int leftX, leftY, rightX, rightY;
    protected boolean selected;
    protected List<UMLObject> objects = new ArrayList<UMLObject>();
    protected Rectangle2D rect;
    
    public Group(int depth){
        this.depth = depth;
    }

    public void initialXY(){
        leftX = Integer.MAX_VALUE;
        leftY = Integer.MAX_VALUE;
        rightX = 0;
        rightY = 0;

        System.out.println("Group " + this + " drawing." + objects.size());
        for(UMLObject object: objects){
            System.out.println("Group " + this + " object: " + object);
            leftX = Math.min(leftX, object.getLeftX());
            leftY = Math.min(leftY, object.getLeftY());
            rightX = Math.max(rightX, object.getRightX());
            rightY = Math.max(rightY, object.getRightY());
        }
        rect = new Rectangle2D.Double(leftX, leftY, rightX - leftX, rightY - leftY);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(selected){
            g2.setColor(Color.red);
        }
        g2.draw(rect);
    }

    public void addObject(UMLObject object){
        objects.add(object);
    }

    public void removeObject(UMLObject object){
        objects.remove(object);
    }

    public void removeAll(){
        objects.clear();
    }

    public List<UMLObject> getObjects(){
        return objects;
    }

    @Override
    public void select(){
        selected = true;
    }

    @Override
    public void unselect() {
        selected = false;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean isXYInside(int x, int y) {
        System.out.println("Group " + this + " isXYInside: " + x + ", " + y + " leftX: " + leftX + " rightX: " + rightX + " leftY: " + leftY + " rightY: " + rightY);
        if (x >= leftX && x <= rightX && y >= leftY && y <= rightY){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        leftX += deltaX;
        leftY += deltaY;
        
        rect.setFrame(rect.getX() + deltaX, rect.getY() + deltaY, rect.getWidth(), rect.getHeight());
        for(UMLObject object: objects){
            object.move(deltaX, deltaY);
        }
    }
}
