package function_graphic;

import java.awt.Graphics;

import function_graphic.base_graphics.UMLObject;

public class UseCase extends UMLObject{
    public UseCase(){
        super();
    }
    public UseCase(int leftX, int leftY){
        super(leftX, leftY);
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
        g.drawOval(leftX, leftY, width, height);
    }
    
}
