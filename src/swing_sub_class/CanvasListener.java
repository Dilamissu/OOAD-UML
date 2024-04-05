package swing_sub_class;

import java.awt.Rectangle;
import java.awt.event.MouseListener;

import function_graphic.*;
import function_graphic.Class;
import function_graphic.base_graphics.*;

public class CanvasListener implements MouseListener{
    ToolType toolType = ToolType.SELECT;
    Canvas canvas;

    protected int pressedX, presedY;
    protected int releasedX, releasedY;

    public CanvasListener(Canvas canvas){
        super();
        this.canvas = canvas;
    }

    public void setToolType(ToolType toolType){
        System.out.println("Tool type set to: " + toolType);
        this.toolType = toolType;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        cleanXY();
        pressedX = e.getX();
        presedY = e.getY();

        switch (toolType) {
            case SELECT:
                canvas.selectSingleShape(pressedX, presedY);
                break;
            case CLASS:
                canvas.addObject(new Class(pressedX, presedY));
                break;
            case USECASE:
                canvas.addObject(new UseCase(pressedX, presedY));
                break;
            default:
            System.out.println("Tool type not supported.");
                break;
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        cleanXY();
        pressedX = e.getX();
        presedY = e.getY();
        System.out.println("Mouse pressed at: " + pressedX + ", " + presedY);
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        releasedX = e.getX();
        releasedY = e.getY();
        System.out.println("Mouse released at: " + releasedX + ", " + releasedY);
        if(isSameXY()){
            cleanXY();
            return;
        }

        switch (toolType) {
            case ASSOCIATION:
                canvas.selectSingleShape(pressedX, presedY);
                UMLObject from = canvas.getSelectedShape();
                Rectangle fromRect = from.selectPoint(pressedX, presedY);
                
                canvas.selectSingleShape(releasedX, releasedY);
                UMLObject to = canvas.getSelectedShape();
                Rectangle toRect = to.selectPoint(releasedX, releasedY);
                canvas.addLine(new Association((int)fromRect.getCenterX(), (int)fromRect.getCenterY(), (int)toRect.getCenterX(), (int)toRect.getCenterY(), from, to));
                break;
            case GENERALIZATION:
                canvas.selectSingleShape(pressedX, presedY);
                from = canvas.getSelectedShape();
                fromRect = from.selectPoint(pressedX, presedY);

                canvas.selectSingleShape(releasedX, releasedY);
                to = canvas.getSelectedShape();
                toRect = to.selectPoint(releasedX, releasedY);

                canvas.addLine(new Generalization((int)fromRect.getCenterX(), (int)fromRect.getCenterY(), (int)toRect.getCenterX(), (int)toRect.getCenterY(), from, to));
                break;
            case COMPOSITION:
                canvas.selectSingleShape(pressedX, presedY);
                from = canvas.getSelectedShape();
                fromRect = from.selectPoint(pressedX, presedY);

                canvas.selectSingleShape(releasedX, releasedY);
                to = canvas.getSelectedShape();
                toRect = to.selectPoint(releasedX, releasedY);
            
                canvas.addLine(new Composition((int)fromRect.getCenterX(), (int)fromRect.getCenterY(), (int)toRect.getCenterX(), (int)toRect.getCenterY(), from, to));
                break;
            default:
                System.out.println("Tool type not supported.");
                break;
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        System.out.println("Mouse entered at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        cleanXY();
        System.out.println("Mouse exited at: " + e.getX() + ", " + e.getY());
    }

    private void cleanXY(){
        pressedX = -1;
        presedY = -1;
        releasedX = -1;
        releasedY = -1;
    }

    private boolean isSameXY(){
        return pressedX == releasedX && presedY == releasedY;
    }
    
}
