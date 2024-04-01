import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    final private Font mainFont = new Font("Arial", Font.PLAIN, 20);

    private void addToolbarComponents(Container toolbarPanel){
        JToolBar mainToolBar = new JToolBar();
        mainToolBar.setBorderPainted(true);
        mainToolBar.setFloatable(false);
        mainToolBar.addSeparator(new Dimension(5, 0));
        mainToolBar.setBackground(new Color(204, 204, 204));
        
        
        JButton fileButton = new JButton("File");
        fileButton.setFont(mainFont);
        // fileButton.setBackground(new Color(204, 204, 204));
        fileButton.setBorderPainted(false);

        JButton editButton = new JButton("Edit");
        editButton.setFont(mainFont);
        // editButton.setBackground(new Color(204, 204, 204));
        editButton.setBorderPainted(false);

        mainToolBar.add(fileButton);
        mainToolBar.add(Box.createRigidArea(new Dimension(5, 0)));
        mainToolBar.add(editButton);

        toolbarPanel.add(mainToolBar);

    }

    public MainFrame(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.white);

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel,BoxLayout.X_AXIS));
        toolbarPanel.setBackground(new Color(204, 204, 204));

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new BoxLayout(canvasPanel,BoxLayout.X_AXIS));
        canvasPanel.setBackground(new Color(204, 204, 204));

        setTitle("UML Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addToolbarComponents(toolbarPanel);

        add(toolbarPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}
