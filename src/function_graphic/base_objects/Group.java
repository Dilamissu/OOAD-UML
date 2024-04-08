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
    protected List<Selectable> objects = new ArrayList<Selectable>();
    protected Rectangle2D rect;
    protected Group parentGroup = null, childGroup = null;
    
    public Group(int depth){
        this.depth = depth;
    }

    public void initialXY(){
        leftX = Integer.MAX_VALUE;
        leftY = Integer.MAX_VALUE;
        rightX = 0;
        rightY = 0;

        if(objects.size() == 2){
           System.out.println("Group " + this + " initialXY " + objects.get(0) + " " + objects.get(1)); 
        }

        for(Selectable object: objects){
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
        if(parentGroup == null){
            if(selected){
                g2.setColor(Color.red);
            }else{
                g2.setColor(Color.black);
            }
            g2.draw(rect);
        }else{
            parentGroup.draw(g2);
        }
    }

    public void addObject(Selectable object){
        System.out.println("Object added to group");
        System.out.println("Group before " + this + " leftX: " + leftX + " rightX: " + rightX);
        System.out.println("Object " + object + " leftX: " + object.getLeftX() + " rightX: " + object.getRightX());
        objects.add(object);
        initialXY();
        System.out.println("Group after " + this + " leftX: " + leftX + " rightX: " + rightX);
    }

    public void removeObject(UMLObject object){
        objects.remove(object);
    }

    public void removeAll(){
        objects.clear();
    }

    public List<Selectable> getObjects(){
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

    public int getLeftX(){
        return leftX;
    }

    public int getLeftY(){
        return leftY;
    }

    public int getRightX(){
        return rightX;
    }

    public int getRightY(){
        return rightY;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        leftX += deltaX;
        leftY += deltaY;
        rightX += deltaX;
        rightY += deltaY;
        
        rect.setFrame(rect.getX() + deltaX, rect.getY() + deltaY, rect.getWidth(), rect.getHeight());
        System.out.println("Group moved");
        for(Selectable object: objects){
            System.out.println("Group " + this + " move object: " + object);
            object.move(deltaX, deltaY);
        }
    }

    public void group(Group parentGroup) {
        this.parentGroup = parentGroup;
        this.parentGroup.childGroup = this;
        this.parentGroup.initialXY();
    }

    public boolean isGrouped(){
        return parentGroup != null;
    }

    public void ungroup() {
        if(parentGroup != null){
            parentGroup.ungroup();
        }else{
            childGroup.parentGroup = null;
            childGroup = null;
            removeAll();
            initialXY();
        }
    }

    public int getWidth(){
        return rightX - leftX;
    }

    public int getHeight(){
        return rightY - leftY;
    }

}
