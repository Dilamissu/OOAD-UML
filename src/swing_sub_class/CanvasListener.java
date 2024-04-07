package swing_sub_class;

import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import function_graphic.*;
import function_graphic.Class;
import function_graphic.base_graphics.*;
import function_graphic.enums.ToolType;

public class CanvasListener implements MouseListener{
    ToolType toolType = ToolType.SELECT;
    Canvas canvas;

    protected int pressedX, pressedY;
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
        pressedY = e.getY();

        switch (toolType) {
            case SELECT:
                try {
                    canvas.selectSingleShape(pressedX, pressedY);
                } catch (Exception ex) {
                    // TODO: handle exception
                    System.out.println("Error: " + ex);
                }
                break;
            case CLASS:
                canvas.addObject(new Class(pressedX, pressedY));
                break;
            case USECASE:
                canvas.addObject(new UseCase(pressedX, pressedY));
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
        pressedY = e.getY();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        releasedX = e.getX();
        releasedY = e.getY();
        if(isSameXY()){
            cleanXY();
            return;
        }

        UMLObject from = null, to = null;
        Point2D fromPoint2d = null, toPoint2d = null;

        try{
            if(toolType == ToolType.ASSOCIATION || toolType == ToolType.GENERALIZATION || toolType == ToolType.COMPOSITION){
                canvas.selectSingleShape(pressedX, pressedY);
                from = canvas.getSelectedShape();
                fromPoint2d = from.selectPoint(pressedX, pressedY);

                canvas.selectSingleShape(releasedX, releasedY);
                to = canvas.getSelectedShape();
                toPoint2d = to.selectPoint(releasedX, releasedY);

                System.out.println("When mouse release, FromPoint: " + fromPoint2d + " toPoint: " + toPoint2d);
            }
            if(from != null && to != null && from.equals(to)){
                System.out.println("From and to are the same object.");
                return;
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex);
            return;
        }
        switch (toolType) {
            case SELECT:
                try{
                    canvas.selectSingleShape(pressedX, pressedY);
                    canvas.getSelectedShape().move(releasedX - pressedX, releasedY - pressedY);
                    canvas.repaint();
                }catch(Exception ex){
                    canvas.selectMultipleShapes(new Rectangle2D.Double(pressedX, pressedY, releasedX - pressedX, releasedY - pressedY));
                }
                break;
            case ASSOCIATION:
                canvas.addLine(new Association((int)fromPoint2d.getX(), (int)fromPoint2d.getY(), (int)toPoint2d.getX(), (int)toPoint2d.getY(), from, to));
                break;
            case GENERALIZATION:
                canvas.addLine(new Generalization((int)fromPoint2d.getX(), (int)fromPoint2d.getY(), (int)toPoint2d.getX(), (int)toPoint2d.getY(), from, to));
                break;
            case COMPOSITION:
                canvas.addLine(new Composition((int)fromPoint2d.getX(), (int)fromPoint2d.getY(), (int)toPoint2d.getX(), (int)toPoint2d.getY(), from, to));
                break;
            default:
                canvas.unselectAllShape();
                break;           
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        cleanXY();
    }

    private void cleanXY(){
        pressedX = -1;
        pressedY = -1;
        releasedX = -1;
        releasedY = -1;
    }

    private boolean isSameXY(){
        return pressedX == releasedX && pressedY == releasedY;
    }
    
}
