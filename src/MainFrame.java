import javax.swing.*;

import swing_sub_class.CanvasListener;

import java.awt.*;

enum Tool{
    SELECT,
    ASSOCIATION,
    GENERALIZATION,
    COMPOSITION,
    CLASS,
    USE_CASE
}


public class MainFrame extends JFrame{
    public Tool currentTool = Tool.SELECT;
    int ToolNumber = 6;

    // Toolbar components
    private void addToolbarComponents(Container toolbarPanel){
        JToolBar mainToolBar = new JToolBar();
        mainToolBar.setBorderPainted(true);
        mainToolBar.setFloatable(false);
        mainToolBar.addSeparator(new Dimension(5, 0));
        mainToolBar.setBackground(new Color(204, 204, 204));
        
        
        JButton fileButton = HelperMethods.createButton("File",false);
        JButton editButton = HelperMethods.createButton("Edit",false);

        mainToolBar.add(fileButton);
        mainToolBar.add(Box.createRigidArea(new Dimension(5, 0)));
        mainToolBar.add(editButton);

        toolbarPanel.add(mainToolBar);
    }
    // Selection panel
    private void addSelectionComponents(Container selectionPanel){
        /* 
         * Function to add selection components to the selection panel
         * select
         * association
         * generalization
         * composition
         * class
         * use case
         */
        JButton selectButton = HelperMethods.createButton("Select",true);
        selectButton.addActionListener(e -> {
            currentTool = Tool.SELECT;
            System.out.println("Select button clicked");
        });

        JButton associationButton = HelperMethods.createButton("Association",true);
        associationButton.addActionListener(e -> {
            currentTool = Tool.ASSOCIATION;
            System.out.println("Association button clicked");
        });

        JButton generalizationButton = HelperMethods.createButton("Generalization",true);
        generalizationButton.addActionListener(e -> {
            currentTool = Tool.GENERALIZATION;
            System.out.println("Generalization button clicked");
        });

        JButton compositionButton = HelperMethods.createButton("Composition",true);
        compositionButton.addActionListener(e -> {
            currentTool = Tool.COMPOSITION;
            System.out.println("Composition button clicked");
        }); 

        JButton classButton = HelperMethods.createButton("Class",true);
        classButton.addActionListener(e -> {
            currentTool = Tool.CLASS;
            System.out.println("Class button clicked");
        });

        JButton useCaseButton = HelperMethods.createButton("Use Case",true);
        useCaseButton.addActionListener(e -> {
            currentTool = Tool.USE_CASE;
            System.out.println("Use Case button clicked");
        });

        selectionPanel.add(selectButton);
        selectionPanel.add(associationButton);
        selectionPanel.add(generalizationButton);
        selectionPanel.add(compositionButton);
        selectionPanel.add(classButton);
        selectionPanel.add(useCaseButton);
        
    }

    private void addCanvasComponents(Container canvasPanel){
        Canvas canvas = new Canvas();
        canvas.addMouseListener(new CanvasListener());
        canvasPanel.addMouseListener(new CanvasListener());
        canvasPanel.add(canvas);
    }

    public MainFrame(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.white);

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel,BoxLayout.X_AXIS));
        toolbarPanel.setBackground(new Color(204, 204, 204));

        JPanel selectionPanel = new JPanel();
        // set to the number of buttons
        selectionPanel.setLayout(new GridLayout(ToolNumber, 1));
        selectionPanel.setBackground(new Color(204, 204, 204));

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new BoxLayout(canvasPanel,BoxLayout.X_AXIS));
        canvasPanel.setBackground(Color.white);
        canvasPanel.setPreferredSize(new Dimension(getMaximumSize().width-selectionPanel.getSize().width, getMaximumSize().height-toolbarPanel.getSize().height));

        setTitle("UML Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addToolbarComponents(toolbarPanel);
        addSelectionComponents(selectionPanel);
        addCanvasComponents(canvasPanel);

        add(toolbarPanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.WEST);
        add(canvasPanel, BorderLayout.EAST);
        add(mainPanel);
        setVisible(true);
    }
}
