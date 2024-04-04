package swing_sub_class;

import java.util.*;
import javax.swing.*;
import java.awt.Graphics;

import function_graphic.base_graphics.FuntionGraphic;

public class Canvas extends JPanel{
    List<FuntionGraphic> funtionGraphics = new ArrayList<FuntionGraphic>();

    public Canvas(){
        super();
    }

    public void addShape(FuntionGraphic shape){
        funtionGraphics.add(shape);
        System.out.println(funtionGraphics.size() + " shapes in canvas.");
    }

    public void removeShape(FuntionGraphic shape){
        funtionGraphics.remove(shape);
        System.out.println(funtionGraphics.size() + " shapes in canvas.");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(FuntionGraphic funtionGraphic: funtionGraphics){
            funtionGraphic.draw(g);
        }
    }
    
}