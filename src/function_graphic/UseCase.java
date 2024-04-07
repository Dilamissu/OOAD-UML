package function_graphic;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import function_graphic.base_objects.UMLObject;

public class UseCase extends UMLObject{
    Ellipse2D ellipse2d;

    public UseCase(){
        super(true);
    }
    
    public UseCase(int leftX, int leftY){
        super(leftX, leftY, true);
        ellipse2d = new Ellipse2D.Double(leftX, leftY, width, height);
    }
    
    public UseCase(int leftX, int leftY, int depth){
        super(leftX, leftY, depth, true);
        ellipse2d = new Ellipse2D.Double(leftX, leftY, width, height);
    }

    @Override
    public void select() {
        selected = true;
    }

    @Override
    public void unselect() {
        selected = false;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        leftX += deltaX;
        leftY += deltaY;
        
        ellipse2d.setFrame(ellipse2d.getX() + deltaX, ellipse2d.getY() + deltaY, ellipse2d.getWidth(), ellipse2d.getHeight());
        moveRects(deltaX, deltaY);
        moveLine(deltaX, deltaY);
    }

    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.fill(ellipse2d);
        g2.setColor(Color.black);
        g.drawOval(leftX, leftY, width, height);
        if (selected) {
            g2.setColor(Color.black);
            g2.fill(upRect);
            g2.fill(downRect);
            g2.fill(leftRect);
            g2.fill(rightRect);
        }
    }

    @Override
    public boolean isXYInside(int x, int y) {
        return ellipse2d.contains(x, y);
    }
    
}
