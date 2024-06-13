package function_graphic.base_interfaces;

public interface Connectable<T extends HavingTwoEnd> {
    public abstract void connectedAt(T TwoEnd, boolean isStart);
}
