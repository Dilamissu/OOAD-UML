package function_graphic.base_graphics;

public abstract class UMLObject implements Selectable, FuntionGraphic{
    protected boolean selected;
    // leftX, leftY is the coordinate of the left top corner of the object
    protected int leftX, leftY, width, height;
    public UMLObject(){
        leftX = 0;
        leftY = 0;
        width = 20;
        height = 20;
        selected = false;
    }
    public UMLObject(int leftX, int leftY){
        this.leftX = leftX;
        this.leftY = leftY;
        width = 20;
        height = 20;
        selected = false;
    }
}
