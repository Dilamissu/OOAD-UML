package function_graphic.base_interfaces;

public interface HavingTwoEnd<T extends Connectable> {
    public abstract void setFrom(int x, int y, T from);
    public abstract void setTo(int x, int y, T to);
    public abstract Connectable getFrom();
    public abstract Connectable getTo();
}
