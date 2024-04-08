package function_graphic.base_interfaces;

public interface Selectable{
    public abstract boolean isXYInside(int x, int y);
    public abstract void select();
    public abstract void unselect();
    public abstract boolean isSelected();
    public abstract void move(int deltaX, int deltaY);
    public abstract int getDepth();
    public abstract int getLeftX();
    public abstract int getLeftY();
    public abstract int getRightX();
    public abstract int getRightY();
}

