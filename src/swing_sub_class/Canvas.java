package swing_sub_class;

import java.util.*;
import javax.swing.*;
import java.awt.Graphics;

import function_graphic.base_graphics.*;

public class Canvas extends JPanel{
    boolean found = false;
    UMLObject selectedShape = null;
    List<UMLObject> umlObjects = new ArrayList<UMLObject>();
    List<UMLLine> umlLines = new ArrayList<UMLLine>();

    public Canvas(){
        super();
    }

    public void addObject(UMLObject shape){
        shape.setDepth(99-umlObjects.size());
        umlObjects.add(shape);
        selectSingleShape(shape.getLeftX(), shape.getLeftY());
        System.out.println(umlObjects.size() + " shapes in canvas.");
        this.repaint();
    }

    public void removeObject(UMLObject shape){
        for(UMLLine umlLine: umlLines){
            if(umlLine.getFrom() == shape || umlLine.getTo() == shape){
                umlLines.remove(umlLine);
            }
        }
        umlObjects.remove(shape);
        this.repaint();
        System.out.println(umlObjects.size() + " shapes in canvas.");
    }

    public void addLine(UMLLine umlLine){
        umlLines.add(umlLine);
        this.repaint();
        this.revalidate();
        System.out.println(umlLines.size() + " lines in canvas.");
    }

    public void selectSingleShape(int x, int y){
        found = false;
        selectedShape = null;
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
        this.repaint();
        this.revalidate();
    }

    public UMLObject getSelectedShape(){
        return selectedShape;
    }

    public void moveSelectedShape(int deltaX, int deltaY){
        if(selectedShape == null){
            System.out.println("No shape selected.");
            return;
        }
        selectedShape.move(deltaX, deltaY);
        this.repaint();
        this.revalidate();
    }

    public void unselectAllShape(){
        for(UMLObject shape: umlObjects){
            if (shape.isSelected()) {
                shape.unselect();
            }
        }
        this.repaint();
        this.revalidate();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(UMLObject funtionGraphic: umlObjects){
            System.out.println("Drawing shape: " + funtionGraphic);
            funtionGraphic.draw(g);
        }
        for(UMLLine umlLine: umlLines){
            System.out.println("Drawing line: " + umlLine);
            umlLine.draw(g);
        }
    }
    
}