import javax.swing.*;

import function_graphic.enums.ToolType;
import helper.HelperMethods;
import swing_sub_class.*;
import swing_sub_class.Canvas;

import java.awt.*;


public class MainFrame extends JFrame{
    int ToolNumber = 6;
    int windowWidth = 800, windowHeight = 600;
    Canvas canvas = new Canvas();
    CanvasListener canvasListener = new CanvasListener(canvas);

    // Toolbar components
    private void addToolbarComponents(Container toolbarPanel){
        JToolBar mainToolBar = new JToolBar();
        mainToolBar.setBorderPainted(true);
        mainToolBar.setFloatable(false);
        mainToolBar.addSeparator(new Dimension(5, 0));
        mainToolBar.setBackground(new Color(204, 204, 204));
        
        
        JButton fileButton = HelperMethods.createButton("File",false);
        JButton editButton = HelperMethods.createButton("Edit",false);
        JButton cleanButton = HelperMethods.createButton("Clean",false);
        cleanButton.addActionListener(e -> {
            canvas.removeAll();
        });

        mainToolBar.add(fileButton);
        mainToolBar.add(Box.createRigidArea(new Dimension(5, 0)));
        mainToolBar.add(editButton);
        mainToolBar.add(Box.createRigidArea(new Dimension(5, 0)));
        mainToolBar.add(cleanButton);

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
            canvasListener.setToolType(ToolType.SELECT);
        });

        JButton associationButton = HelperMethods.createButton("Association",true);
        associationButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.ASSOCIATION);
        });

        JButton generalizationButton = HelperMethods.createButton("Generalization",true);
        generalizationButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.GENERALIZATION);
        });

        JButton compositionButton = HelperMethods.createButton("Composition",true);
        compositionButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.COMPOSITION);
        }); 

        JButton classButton = HelperMethods.createButton("Class",true);
        classButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.CLASS);
        });

        JButton useCaseButton = HelperMethods.createButton("Use Case",true);
        useCaseButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.USECASE);
        });

        selectionPanel.add(selectButton);
        selectionPanel.add(associationButton);
        selectionPanel.add(generalizationButton);
        selectionPanel.add(compositionButton);
        selectionPanel.add(classButton);
        selectionPanel.add(useCaseButton);
        
    }

    private void addCanvasComponents(Container canvasPanel){
        canvas.setBackground(Color.green);
        canvas.addMouseListener(canvasListener);
        canvasPanel.addMouseListener(canvasListener);
        canvasPanel.add(canvas);
    }
    public MainFrame(){

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel,BoxLayout.X_AXIS));
        toolbarPanel.setBackground(new Color(204, 204, 204));
        addToolbarComponents(toolbarPanel);

        JPanel selectionPanel = new JPanel();
        // set to the number of buttons
        selectionPanel.setLayout(new GridLayout(ToolNumber, 1));
        selectionPanel.setBackground(new Color(204, 204, 204));
        // selectionPanel.setPreferredSize(new Dimension(150, windowHeight));
        addSelectionComponents(selectionPanel);

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new BoxLayout(canvasPanel, BoxLayout.X_AXIS));
        canvasPanel.setBackground(Color.white);
        canvasPanel.setPreferredSize(new Dimension(windowWidth - selectionPanel.getPreferredSize().width - 16, windowHeight - toolbarPanel.getHeight()));
        addCanvasComponents(canvasPanel);

        setTitle("UML Editor");
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(selectionPanel, BorderLayout.WEST);
        add(canvasPanel, BorderLayout.EAST);
        add(toolbarPanel, BorderLayout.NORTH);

        setVisible(true);
    }
}
