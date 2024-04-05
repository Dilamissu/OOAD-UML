package function_graphic;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import function_graphic.base_graphics.UMLObject;

public class UseCase extends UMLObject{
    Ellipse2D ellipse2d;

    public UseCase(){
        super();
    }
    public UseCase(int leftX, int leftY){
        super(leftX, leftY);
        ellipse2d = new Ellipse2D.Double(super.leftX, super.leftY, super.width, super.height);
    }
    public UseCase(int leftX, int leftY, int depth){
        super(leftX, leftY, depth);
        ellipse2d = new Ellipse2D.Double(super.leftX, super.leftY, super.width, super.height);
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

    @Override
    public boolean isXYInside(int x, int y) {
        return Math.pow(x - leftX, 2) + Math.pow(y - leftY, 2) <= Math.pow(width, 2);
    }
    
}
