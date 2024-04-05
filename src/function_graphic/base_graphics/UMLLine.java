package function_graphic.base_graphics;

public abstract class UMLLine implements FuntionGraphic{
    protected int sratX, startY, endX, endY;
    protected UMLObject from, to;
    public UMLLine(int sratX, int startY, int endX, int endY, UMLObject from, UMLObject to){
        this.sratX = sratX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.from = from;
        this.to = to;
    }

    public UMLObject getFrom() {
        return from;
    }
    public UMLObject getTo() {
        return to;
    }

}
