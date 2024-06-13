package swing_sub_class;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


import function_graphic.base_interfaces.Selectable;
import function_graphic.base_objects.UMLGroup;
import function_graphic.base_objects.UMLLine;
import function_graphic.base_objects.UMLObject;

public final class CanvasObjects {
    private static int depth = 0;
    // This is just for visual indacation
    private static Shape indecateShape = null;
    private static ArrayList<UMLObject> objects = new ArrayList<UMLObject>();
    private static ArrayList<UMLLine> lines = new ArrayList<UMLLine>();
    private static ArrayList<UMLGroup> groups = new ArrayList<UMLGroup>();
    private static ArrayList<Selectable> selected = new ArrayList<Selectable>();

    public static void addObject(int x, int y, UMLObject shape){
        shape.setDepth(depth);
        depth++;
        objects.add(shape);
        unselectAllShape();
        shape.select();
        select(shape);
    }

    public static void addLine(int xPressed, int yPressed, int xReleased, int yReleased, UMLLine line){
        if(hasObject(xPressed, yPressed) == false || hasObject(xReleased, yReleased) == false){
            return;
        }
        try{
            UMLObject from = (UMLObject)select(xPressed, yPressed);
            UMLObject to = (UMLObject)select(xReleased, yReleased);

            line.setFrom(xPressed, yPressed, from);
            line.setTo(xReleased, yReleased, to);

            from.connectedAt(line, true);
            to.connectedAt(line, false);

            lines.add(line);
        }catch(Exception e){
            lines.remove(line);
            System.out.println("Line is not connected.");
        }
    }
    
    public static  void group(){
        int count = 0;
        UMLGroup group = new UMLGroup(depth);
        depth++;
        for(UMLObject shape: objects){
            if(shape.isSelected()){
                shape.group(group);
                shape.unselect();
                shape.unselectable();
                group.addObject(shape);
                count++;
            }
        }
        for(UMLGroup g: groups){
            if(g.isSelected()){
                g.group(group);
                g.unselect();
                group.addObject(g);
                count++;
            }
        }
        unselectAllShape();
        selected.add(group);
        group.select();
        if(count == 1){
            ungroup();
            return;
        }
        select(group);
        groups.add(group);
    }

    public static void removeObject(UMLObject shape){
        for(UMLLine umlLine: lines){
            if(umlLine.getFrom() == shape || umlLine.getTo() == shape){
                lines.remove(umlLine);
            }
        }
        if(objects.size() > 0){
            depth = objects.get(0).getDepth();
        }
        getNewDepth();
        objects.remove(shape);
    }

    public static void getNewDepth(){
        for(UMLObject umlObject: objects){
            depth = Math.max(depth, umlObject.getDepth());
        }
        for(UMLGroup group: groups){
            depth = Math.max(depth, group.getDepth());
        }
    }

    public static void ungroup(){
        UMLGroup group = (UMLGroup)getSelectedShape();
        for(UMLObject shape: objects){
            if(shape.isGrouped() && shape.getGroup() == group){
                // Remove group info in objects
                shape.ungroup();
                shape.selectable();
            }
        }
        group.ungroup();
        groups.remove(group);
        unselectAllShape();

        getNewDepth();
    }
    
    public static void move(int deltaX, int deltaY){
        assert selected.size() == 1;
        Selectable shape = selected.get(0);
        shape.move(deltaX, deltaY);

    }
    
    public static boolean hasObject(int x, int y){
        for(UMLObject object:objects){
            if(object.isXYInside(x, y)){
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Selectable> select(int leftX, int upY, int rigntX, int downY){
        Rectangle2D selectionArea = new Rectangle2D.Double(Math.min(leftX, rigntX), Math.min(upY, downY), Math.abs(leftX - rigntX), Math.abs(upY - downY));
        selected.clear();
        for(UMLObject shape: objects){
            if(selectionArea.contains(shape.getLeftX(), shape.getLeftY()) && selectionArea.contains(shape.getRightX(), shape.getRightY()) && shape.isSelectable()){
                selected.add(shape);
                shape.select();
            }
            System.out.println("Shape: " + shape.getName() + " is " + (shape.isSelected() ? "selected": "not selected"));
        }
        for(UMLGroup group: groups){
            if(selectionArea.contains(group.getLeftX(), group.getLeftY()) && selectionArea.contains(group.getRightX(), group.getRightY()) && !group.isGrouped()){
                selected.add(group);
                group.select();
            }
        }
        return selected;
    }
    
    public static Selectable select(int x, int y){
        boolean found = false;
        selected.clear();
        for(UMLObject shape: objects){
            shape.unselect();
            if(shape.isXYInside(x, y) && shape.isSelectable()){
                if(found == false){
                    selected.add(shape);
                    shape.select();
                    found = true;
                }else if (getSelectDepth() < shape.getDepth()){
                    selected.clear();
                    unselectAllShape();
                    selected.add(shape);                    
                }
            }
        }
        for(UMLGroup group: groups){
            group.unselect();
            if(group.isXYInside(x, y)){
                if(found == false){
                    selected.add(group);
                    group.select();
                    found = true;
                }else if (getSelectDepth() < group.getDepth()){
                    selected.clear();
                    unselectAllShape();
                    selected.add(group);
                    group.select();               
                }
            }
        }
        if(!found){
            throw new IllegalArgumentException("No shape found at: " + x + ", " + y);
        }else{
            assert selected.size() == 1;
            return selected.get(0);
        }
    }
    
    public static void select(Selectable shape){
        selected.clear();
        selected.add(shape);
    }

    public static ArrayList<Selectable> getSelectedObjects(){
        return selected;
    }
    
    public static UMLObject getSelectedObject(){
        assert selected.size() == 1;
        return (UMLObject)selected.get(0);
    }

    public static Selectable getSelectedShape(){
        assert selected.size() == 1;
        return selected.get(0);
    }

    public static int getSelectDepth(){
        assert selected.size() == 1;
        return selected.get(0).getDepth();
    }

    public static void unselectAllShape(){
        for(UMLObject shape: objects){
            if (shape.isSelected()) {
                shape.unselect();
            }
        }
        for(UMLGroup group: groups){
            if (group.isSelected()) {
                group.unselect();
            }
        }
        selected.clear();
    }

    public static boolean isGroup(){
        assert selected.size() == 1; 
        return selected.get(0) instanceof UMLGroup;

    }

    public static void changeName(String name){
        getSelectedObject().setName(name);
    }
    
    public static void paint(Graphics2D g){
        if(indecateShape != null){
            g.draw(indecateShape);
        }
        for(UMLObject funtionGraphic: objects){
            funtionGraphic.paint(g);
        }
        for(UMLLine umlLine: lines){
            umlLine.paint(g);
        }
        for(UMLGroup group: groups){
            group.paint(g);
        }
    }

    public static void setIndecateShape(Shape shape){
        indecateShape = shape;
    }

    public static boolean hasIndecateShape(){
        return indecateShape != null;
    }

    public static void removeIndecateShape(){
        indecateShape = null;
    }

    public static void removeAll(){
        objects.clear();
        lines.clear();
        groups.clear();
    }

}
