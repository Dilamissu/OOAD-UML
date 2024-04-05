package swing_sub_class;

import java.util.*;
import javax.swing.*;
import java.awt.Graphics;

import function_graphic.Class;
import function_graphic.base_graphics.*;

public class Canvas extends JPanel{
    List<UMLObject> umlObjects = new ArrayList<UMLObject>();
    List<UMLLine> umlLines = new ArrayList<UMLLine>();

    public Canvas(){
        super();
    }

    public void addShape(UMLObject shape){
        shape.setDepth(99-umlObjects.size());
        umlObjects.add(shape);
        System.out.println(umlObjects.size() + " shapes in canvas.");
    }

    public void removeShape(UMLObject shape){
        for(UMLLine umlLine: umlLines){
            if(umlLine.getFrom() == shape || umlLine.getTo() == shape){
                umlLines.remove(umlLine);
            }
        }
        umlObjects.remove(shape);
        System.out.println(umlObjects.size() + " shapes in canvas.");
    }

    public void selectSingleShape(int x, int y){
        boolean found = false;
        UMLObject selectedShape = null;
        for(UMLObject shape: umlObjects){
            shape.unselect();
            if(shape.isXYInside(x, y)){
                if(found == false){
                    selectedShape = shape;
                    found = true;
                }else if (selectedShape.getDepth() > shape.getDepth()){
                    selectedShape = shape;                    
                }
            }
        }
        if(!found){
            System.out.println("No shape selected.");
            return;
        }else{
            selectedShape.select();
        }
        System.out.println("Selected shape: " + selectedShape);
        this.revalidate();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(UMLObject funtionGraphic: umlObjects){
            funtionGraphic.draw(g);
        }
        for(UMLLine umlLine: umlLines){
            umlLine.draw(g);
        }
    }
    
}