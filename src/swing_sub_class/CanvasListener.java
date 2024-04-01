package swing_sub_class;

import java.awt.event.MouseListener;

public class CanvasListener implements MouseListener{
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        System.out.println("Mouse pressed at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        System.out.println("Mouse released at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        System.out.println("Mouse entered at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        System.out.println("Mouse exited at: " + e.getX() + ", " + e.getY());
    }
    
}
