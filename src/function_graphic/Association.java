package function_graphic;

import java.awt.Graphics;

import function_graphic.base_graphics.UMLLine;

public class Association extends UMLLine{
    public Association(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }
    
    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
    
}
