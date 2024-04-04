package function_graphic.base_graphics;

public abstract class UMLLine implements FuntionGraphic{
    protected int sratX, startY, endX, endY;
    public UMLLine(){
        sratX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
    }
    public UMLLine(int sratX, int startY, int endX, int endY){
        this.sratX = sratX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}
