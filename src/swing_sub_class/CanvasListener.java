package swing_sub_class;

import java.awt.event.MouseListener;
import function_graphic.Class;
import function_graphic.UseCase;

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
                canvas.repaint();
                break;
            case CLASS:
                canvas.addShape(new Class(pressedX, presedY));
                canvas.repaint();
                break;
            case USECASE:
                canvas.addShape(new UseCase(pressedX, presedY));
                canvas.repaint();
                break;
            default:
                break;
        }

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
        if(isSameXY()){
            cleanXY();
            return;
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
