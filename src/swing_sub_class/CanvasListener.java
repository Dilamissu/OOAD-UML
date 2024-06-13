package swing_sub_class;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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

    public void group(){
        CanvasObjects.group();
        canvas.repaint();
    }

    public boolean isGroup(){
        return CanvasObjects.isGroup();
    }

    public void ungroup(){
        CanvasObjects.ungroup();
        canvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cleanXY();
        pressedX = e.getX();
        pressedY = e.getY();

        if(toolType.isSelect()){
            try {
                CanvasObjects.select(pressedX, pressedY);
            } catch (Exception ex) {
                CanvasObjects.unselectAllShape();
            }
        }else if(toolType.isObject()){
            CanvasObjects.addObject(pressedX, pressedY, toolType.createObject(pressedX, pressedY));
        }
        canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cleanXY();
        pressedX = e.getX();
        pressedY = e.getY();
        draggedX = pressedX;
        draggedY = pressedY;
        if(toolType.isSelect()){
            try {
                CanvasObjects.select(pressedX, pressedY);
            } catch (Exception ex) {
                CanvasObjects.setIndecateShape(new Rectangle2D.Double(pressedX, pressedY, 0, 0));
                System.out.println("Error: " + ex);
            }
        }else if(toolType.isLine()){
            try{
                from = CanvasObjects.select(pressedX, pressedY);
                if(CanvasObjects.isGroup()){
                    System.out.println("From is a group.");
                    CanvasObjects.unselectAllShape();
                    from = null;
                    cleanXY();
                    return;
                }
            }catch(Exception ex){
                System.out.println("Error: " + ex);
                cleanXY();
                return;
            }
        }
        canvas.repaint();
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
            if(toolType.isLine() && from != null){
                try {
                    CanvasObjects.select(releasedX, releasedY);
                    to = CanvasObjects.getSelectedObject();
                } catch (Exception exception) {
                    CanvasObjects.unselectAllShape();
                    to = null;
                    cleanXY();
                    canvas.repaint();
                    return;
                }
            }
            if(from != null && to != null && from.equals(to)){
                System.out.println("From and to are the same object.");
                return;
            }else if((from == null || to == null) && !toolType.isSelect()){
                return;
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex);
            cleanXY();
            return;
        }
        if(toolType.isSelect()){
                if(CanvasObjects.hasIndecateShape()){
                    CanvasObjects.select(pressedX, pressedY, releasedX,releasedY);
                }
        }else if(toolType.isLine()){
            CanvasObjects.addLine(pressedX, pressedY, releasedX, releasedY, toolType.createLine(pressedX, pressedY, releasedX, releasedY, (UMLObject)from, (UMLObject)to));
        }else{
            CanvasObjects.unselectAllShape();
        }
        cleanXY();
        canvas.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(toolType.isSelect()){
            try{
                CanvasObjects.move(e.getX() - draggedX, e.getY() - draggedY);
                draggedX = e.getX();
                draggedY = e.getY();
            }catch(Exception ex){
                CanvasObjects.setIndecateShape(new Rectangle2D.Double(Math.min(e.getX(), pressedX), Math.min(e.getY(), pressedY), Math.abs(e.getX() - pressedX), Math.abs(e.getY() - pressedY)));
            }
        }else if (toolType.isLine()) {
            if(from != null){
                CanvasObjects.setIndecateShape(new Line2D.Double(pressedX, pressedY, e.getX(), e.getY()));
            }
        }
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public void changeName(String name){
        CanvasObjects.changeName(name);
        canvas.repaint();
    }

    private void cleanXY(){
        pressedX = -1;
        pressedY = -1;
        releasedX = -1;
        releasedY = -1;
        from = null;
        to = null;
        CanvasObjects.setIndecateShape(null);
    }

    private boolean isSameXY(){
        return pressedX == releasedX && pressedY == releasedY;
    }

    public void removeAll(){
        CanvasObjects.removeAll();
        canvas.repaint();
    }

}
