package function_graphic.base_graphics;

public interface Selectable{
    public abstract boolean isXYInside(int x, int y);
    public abstract void select();
    public abstract void unselect();
    public abstract void move(int deltaX, int deltaY);
    public abstract void group();
    public abstract void ungroup();
}

