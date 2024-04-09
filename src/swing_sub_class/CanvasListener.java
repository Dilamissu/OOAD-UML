package swing_sub_class;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import function_graphic.*;
import function_graphic.Class;
import function_graphic.base_interfaces.Selectable;
import function_graphic.base_objects.*;
import function_graphic.enums.ToolType;

public class CanvasListener implements MouseListener, MouseMotionListener{
    ToolType toolType = ToolType.SELECT;
    Canvas canvas;

    protected int pressedX, pressedY;
    protected int releasedX, releasedY;
    protected int draggedX, draggedY;
    protected Selectable from = null, to = null;
    protected Point2D fromPoint2d = null, toPoint2d = null;

    public CanvasListener(Canvas canvas){
        super();
        this.canvas = canvas;
    }

    public void setToolType(ToolType toolType){
        this.toolType = toolType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cleanXY();
        pressedX = e.getX();
        pressedY = e.getY();

        switch (toolType) {
            case SELECT:
                try {
                    canvas.selectSingleShape(pressedX, pressedY);
                } catch (Exception ex) {
                    canvas.unselectAllShape();
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
    public void mousePressed(MouseEvent e) {
        cleanXY();
        pressedX = e.getX();
        pressedY = e.getY();
        draggedX = pressedX;
        draggedY = pressedY;
        if(toolType == ToolType.SELECT){
            try {
                canvas.selectSingleShape(pressedX, pressedY);
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }else if(toolType == ToolType.ASSOCIATION || toolType == ToolType.GENERALIZATION || toolType == ToolType.COMPOSITION){
            try{
                canvas.selectSingleShape(pressedX, pressedY);
                from = canvas.getSelectedShape();
                if(from.getClass() == Group.class){
                    System.out.println("From is a group.");
                    canvas.unselectAllShape();
                    cleanXY();
                    return;
                }else{
                    fromPoint2d = ((UMLObject)from).selectPoint(pressedX, pressedY);
                }
            }catch(Exception ex){
                System.out.println("Error: " + ex);
                cleanXY();
                return;
            }
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasedX = e.getX();
        releasedY = e.getY();

        if(isSameXY()){
            cleanXY();
            return;
        }


        try{
            if(toolType == ToolType.ASSOCIATION || toolType == ToolType.GENERALIZATION || toolType == ToolType.COMPOSITION){
                canvas.selectSingleShape(releasedX, releasedY);
                to = canvas.getSelectedShape();
                if(to.getClass() == Group.class){
                    System.out.println("To is a group.");
                    canvas.unselectAllShape();
                    cleanXY();
                    return;
                }else{
                    toPoint2d = ((UMLObject)to).selectPoint(releasedX, releasedY);
                }
            }
            if(from != null && to != null && from.equals(to)){
                System.out.println("From and to are the same object.");
                return;
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex);
            cleanXY();
            return;
        }
        switch (toolType) {
            case SELECT:
                if(canvas.indecateShape != null){
                    canvas.selectMultipleShapes(new Rectangle2D.Double(Math.min(pressedX, releasedX), Math.min(pressedY, releasedY), Math.abs(pressedX - releasedX), Math.abs(pressedY - releasedY)));
                }
                break;
            case ASSOCIATION:
                canvas.addLine(new Association((int)fromPoint2d.getX(), (int)fromPoint2d.getY(), (int)toPoint2d.getX(), (int)toPoint2d.getY(), (UMLObject)from, (UMLObject)to));
                break;
            case GENERALIZATION:
                canvas.addLine(new Generalization((int)fromPoint2d.getX(), (int)fromPoint2d.getY(), (int)toPoint2d.getX(), (int)toPoint2d.getY(), (UMLObject)from, (UMLObject)to));
                break;
            case COMPOSITION:
                canvas.addLine(new Composition((int)fromPoint2d.getX(), (int)fromPoint2d.getY(), (int)toPoint2d.getX(), (int)toPoint2d.getY(), (UMLObject)from, (UMLObject)to));
                break;
            default:
                canvas.unselectAllShape();
                break;           
        }
        cleanXY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (toolType) {
            case SELECT:
                try{
                    canvas.getSelectedShape().move(e.getX() - draggedX, e.getY() - draggedY);
                    draggedX = e.getX();
                    draggedY = e.getY();
                }catch(Exception ex){
                    canvas.setIndecateShape(new Rectangle2D.Double(Math.min(e.getX(), pressedX), Math.min(e.getY(), pressedY), Math.abs(e.getX() - pressedX), Math.abs(e.getY() - pressedY)));
                }    
                canvas.repaint();
                canvas.revalidate();
                break;
            case ASSOCIATION:
            case GENERALIZATION:
            case COMPOSITION:
                if(from != null){
                    canvas.setIndecateShape(new Line2D.Double(pressedX, pressedY, e.getX(), e.getY()));
                    canvas.repaint();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    private void cleanXY(){
        pressedX = -1;
        pressedY = -1;
        releasedX = -1;
        releasedY = -1;
        from = null;
        to = null;
        canvas.setIndecateShape(null);
    }

    private boolean isSameXY(){
        return pressedX == releasedX && pressedY == releasedY;
    }

}
