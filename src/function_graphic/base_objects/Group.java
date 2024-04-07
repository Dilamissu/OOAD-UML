package function_graphic.base_objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import function_graphic.base_interfaces.*;

public class Group implements FuntionGraphic, Selectable{
    protected int depth;
    protected int leftX, leftY, rightX, rightY;
    protected boolean selected;
    protected List<UMLObject> objects = new ArrayList<UMLObject>();
    
    public Group(int depth){
        this.depth = depth;
    }
    
    @Override
    public void draw(Graphics g) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        Graphics2D g2 = (Graphics2D) g;
        System.out.println("Group " + this + " drawing." + objects.size());
        for(UMLObject object: objects){
            System.out.println("Group " + this + " object: " + object);
            minX = Math.min(minX, object.getLeftX());
            minY = Math.min(minY, object.getLeftY());
            maxX = Math.max(maxX, object.getRightX());
            maxY = Math.max(maxY, object.getRightY());
        }
        if(selected){
            g2.setColor(Color.red);
        }
        g2.drawRect(minX, minY, maxX - minX, maxY - minY);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}
