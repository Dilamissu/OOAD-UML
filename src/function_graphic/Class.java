package function_graphic;

import java.awt.*;

import function_graphic.base_objects.UMLObject;

public class Class extends UMLObject{
    Rectangle rect;
    
    public Class(){
        super(false);
        setName("Class " + depth);
        rect = new Rectangle(leftX, leftY, width, height);
    }
    
    public Class(int leftX, int leftY){
        super(leftX, leftY, false);
        setName("Class " + depth);
        rect = new Rectangle(leftX, leftY, width, height);
    }
    
    public Class(int leftX, int leftY, int depth){
        super(leftX, leftY, depth, false);
        setName("Class " + depth);
        rect = new Rectangle(leftX, leftY, width, height);
    }

    @Override
    public void move(int deltaX, int deltaY) {
        System.out.println("Class move from " + leftX + ", " + leftY + " to " + (leftX + deltaX) + ", " + (leftY + deltaY));
        leftX += deltaX;
        leftY += deltaY;
        
        rect.setLocation(rect.x + deltaX, rect.y + deltaY);
        moveRects(deltaX, deltaY);
        moveLine(deltaX, deltaY);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.fill(rect);
        g2.setColor(Color.black);
        for (int i = 0; i < 3; i++) {
            int height = rect.height/3;
            int positionY = rect.y + height * i;
            g2.drawRect(rect.x, positionY, rect.width, (rect.height)/3);
        }
        if (selected) {
            g2.setColor(Color.black);
            g2.fill(upRect);
            g2.fill(downRect);
            g2.fill(leftRect);
            g2.fill(rightRect);
        }
        g2.drawString(getName(), leftX + width/4, leftY + height/4);
    }

    @Override
    public boolean isXYInside(int x, int y){
        return rect.contains(x, y);
    }

}
