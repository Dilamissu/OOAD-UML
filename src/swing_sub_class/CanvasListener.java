package swing_sub_class;

import java.awt.event.MouseListener;


public class CanvasListener implements MouseListener{
    ToolType toolType = ToolType.SELECT;

    protected int pressedX, presedY;
    protected int releasedX, releasedY;

    public CanvasListener(){
        super();
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
        System.out.println("Mouse clicked at: " + pressedX + ", " + presedY);
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
    
}
