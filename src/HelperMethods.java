

import javax.swing.JButton;

public class HelperMethods {
    public static JButton createButton(String text, boolean isBordered){
        JButton button = new JButton(text);
        
        if(!isBordered){
            button.setBorderPainted(false);
        }
        return button;
    }
}