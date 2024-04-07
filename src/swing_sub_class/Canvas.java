package swing_sub_class;

import java.util.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import function_graphic.base_graphics.*;
import helper.HelperMethods;

public class Canvas extends JPanel{
    boolean found = false;
    UMLObject selectedShape = null;
    List<UMLObject> umlObjects = new ArrayList<UMLObject>();
    List<UMLLine> umlLines = new ArrayList<UMLLine>();
    Shape indecateShape = null;

    public Canvas(){
        super();
    }

    public void addObject(UMLObject shape){
        shape.setDepth(99-umlObjects.size());
        umlObjects.add(shape);
        selectShape(shape);
        System.out.println(umlObjects.size() + " objects in canvas.");
        repaint();
    }

    public void addLine(UMLLine umlLine){
        umlLines.add(umlLine);
        repaint();
        revalidate();
        UMLObject from = umlLine.getFrom();
        UMLObject to = umlLine.getTo();

        from.addConnectedLine(umlLine, HelperMethods.getRelativePositions(
                    new Point2D.Double(umlLine.getStartX(), umlLine.getStartY()),
                    new Point2D.Double(from.getLeftX(), from.getLeftY()),
                    from.getWidth(), from.getHeight())
                );
        to.addConnectedLine(umlLine, HelperMethods.getRelativePositions(
                    new Point2D.Double(umlLine.getEndX(), umlLine.getEndY()),
                    new Point2D.Double(to.getLeftX(), to.getLeftY()),
                    to.getWidth(), to.getHeight())
                );

        System.out.println(umlLines.size() + " lines in canvas.");
    }

    public void removeObject(UMLObject shape){
        for(UMLLine umlLine: umlLines){
            if(umlLine.getFrom() == shape || umlLine.getTo() == shape){
                umlLines.remove(umlLine);
            }
        }
        umlObjects.remove(shape);
        repaint();
        System.out.println(umlObjects.size() + " objects in canvas.");
    }

    public void removeAll(){
        umlObjects.clear();
        umlLines.clear();
        repaint();
        System.out.println("All shapes removed.");
    }

    public void selectShape(UMLObject shape){
        unselectAllShape();
        shape.select();
        repaint();
        revalidate();
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
            throw new IllegalArgumentException("No shape found at: " + x + ", " + y);
        }else{
            selectedShape.select();
        }
        System.out.println("Selected shape: " + selectedShape);
        repaint();
        revalidate();
    }

    public void selectMultipleShapes(Rectangle2D selectionArea){
        for(UMLObject shape: umlObjects){
            if(selectionArea.contains(shape.getLeftX(), shape.getLeftY()) && selectionArea.contains(shape.getRightX(), shape.getRightY())){
                shape.select();
            }
        }
        repaint();
        revalidate();
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
        repaint();
        revalidate();
    }

    public void unselectAllShape(){
        for(UMLObject shape: umlObjects){
            if (shape.isSelected()) {
                shape.unselect();
            }
        }
        repaint();
        revalidate();
    }

    public void setIndecateShape(Shape shape){
        indecateShape = shape;
        repaint();
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(indecateShape != null){
            g2.draw(indecateShape);
        }
        for(UMLObject funtionGraphic: umlObjects){
            // System.out.println("Drawing shape: " + funtionGraphic);
            funtionGraphic.draw(g2);
        }
        for(UMLLine umlLine: umlLines){
            // System.out.println("Drawing line: " + umlLine);
            umlLine.draw(g2);
        }
    }
    
}