package swing_sub_class;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canvas extends JPanel{

    public Canvas(){
        super();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        CanvasObjects.paint((Graphics2D)g);
    }
    
}