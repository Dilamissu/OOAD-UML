package function_graphic;

import java.awt.*;

import function_graphic.base_graphics.UMLObject;

public class Class extends UMLObject{
    Rectangle rect;
    public Class(){
        super();
        rect = new Rectangle(super.leftX, super.leftY, super.width, super.height);
    }
    public Class(int leftX, int leftY){
        super(leftX, leftY);
        rect = new Rectangle(super.leftX, super.leftY, super.width, super.height);
    }
    public Class(int leftX, int leftY, int depth){
        super(leftX, leftY, depth);
        rect = new Rectangle(super.leftX, super.leftY, super.width, super.height);
    }
    @Override
    public void select() {
        super.selected = true;
    }
    @Override
    public void unselect() {
        super.selected = false;
    }
    @Override
    public void move(int deltaX, int deltaY) {
        super.leftX += deltaX;
        super.leftY += deltaY;
    }
    @Override
    public void group() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'group'");
    }
    @Override
    public void ungroup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ungroup'");
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
    }

    public boolean isXYInside(int x, int y){
        return rect.contains(x, y);
    }

}
