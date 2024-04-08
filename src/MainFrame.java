import javax.swing.*;

import function_graphic.base_objects.Group;
import function_graphic.base_objects.UMLObject;
import function_graphic.enums.ToolType;
import helper.HelperMethods;
import swing_sub_class.*;
import swing_sub_class.Canvas;

import java.awt.*;


public class MainFrame extends JFrame{
    int ToolNumber = 6;
    // int windowWidth = 1920, windowHeight = 1080;
    int windowWidth = 1280, windowHeight = 720;
    Canvas canvas = new Canvas();
    CanvasListener canvasListener = new CanvasListener(canvas);

    // Toolbar components
    private void addToolbarComponents(Container toolbarPanel){
        JMenuBar mainToolBar = new JMenuBar();
        mainToolBar.setBorderPainted(true);
        mainToolBar.setBackground(new Color(204, 204, 204));
        
        
        JMenu fileMenuButton = new JMenu("File");
        JMenu editMenuButton = new JMenu("Edit");

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(e -> {
            canvas.removeAll();
        });

        fileMenuButton.add(newItem);

        JMenuItem changeObjectNameItem = new JMenuItem("Change Object Name");
        changeObjectNameItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String newName = "";
                if(canvas.getSelectedShape() instanceof UMLObject){
                    newName = JOptionPane.showInputDialog(getParent(),  "Please input new component name", ((UMLObject)canvas.getSelectedShape()).getName());
                    if(newName == null){
                        return;
                    }
                    ((UMLObject)canvas.getSelectedShape()).setName(newName);
                    canvas.repaint();
                }
            }
        });

        JMenuItem groupItem = new JMenuItem("Group");
        groupItem.addActionListener(e -> {
            canvas.groupSelectedShapes();
        });

        JMenuItem ungroupItem = new JMenuItem("Ungroup");
        ungroupItem.addActionListener(e -> {
            if(canvas.getSelectedShape() instanceof Group){
                canvas.ungroupSelectedGroup();
            }
        });

        editMenuButton.add(groupItem);
        editMenuButton.add(ungroupItem);
        editMenuButton.add(changeObjectNameItem);
        // JButton cleanButton = HelperMethods.createButton("Clean",false);
        // cleanButton.addActionListener(e -> {
        //     canvas.removeAll();
        // });
        // JButton groupButton = HelperMethods.createButton("Group",false);
        // groupButton.addActionListener(e -> {
        //     canvas.groupSelectedShapes();
        // });
        // JButton ungroupButton = HelperMethods.createButton("Ungroup",false);
        // ungroupButton.addActionListener(e -> {
        //     if(canvas.getSelectedShape() instanceof Group){
        //         canvas.ungroupSelectedGroup();
        //     }
        // });

        mainToolBar.add(fileMenuButton);
        mainToolBar.add(Box.createRigidArea(new Dimension(5, 0)));
        mainToolBar.add(editMenuButton);

        toolbarPanel.add(mainToolBar);
    }
    // Selection panel
    private void addSelectionComponents(Container selectionPanel){
        JButton[] buttons = new JButton[ToolNumber];
        final Color selectedColor = Color.gray;
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
        final Color unselectedColor = selectButton.getBackground();
        selectButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.SELECT);
            for(int i = 0; i < ToolNumber; i++){
                buttons[i].setBackground(unselectedColor);
            }
            selectButton.setBackground(selectedColor);
        });
        buttons[0] = selectButton;

        JButton associationButton = HelperMethods.createButton("Association",true);
        associationButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.ASSOCIATION);
            for(int i = 0; i < ToolNumber; i++){
                buttons[i].setBackground(unselectedColor);
            }
            associationButton.setBackground(selectedColor);
        });
        buttons[1] = associationButton;

        JButton generalizationButton = HelperMethods.createButton("Generalization",true);
        generalizationButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.GENERALIZATION);
            for(int i = 0; i < ToolNumber; i++){
                buttons[i].setBackground(unselectedColor);
            }
            generalizationButton.setBackground(selectedColor);
        });
        buttons[2] = generalizationButton;

        JButton compositionButton = HelperMethods.createButton("Composition",true);
        compositionButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.COMPOSITION);
            for(int i = 0; i < ToolNumber; i++){
                buttons[i].setBackground(unselectedColor);
            }
            compositionButton.setBackground(selectedColor);
        });
        buttons[3] = compositionButton;

        JButton classButton = HelperMethods.createButton("Class",true);
        classButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.CLASS);
            for(int i = 0; i < ToolNumber; i++){
                buttons[i].setBackground(unselectedColor);
            }
            classButton.setBackground(selectedColor);
        });
        buttons[4] = classButton;

        JButton useCaseButton = HelperMethods.createButton("Use Case",true);
        useCaseButton.addActionListener(e -> {
            canvasListener.setToolType(ToolType.USECASE);
            for(int i = 0; i < ToolNumber; i++){
                buttons[i].setBackground(unselectedColor);
            }
            useCaseButton.setBackground(selectedColor);
        });
        buttons[5] = useCaseButton;

        for (int i = 0; i < ToolNumber; i++) {
            selectionPanel.add(buttons[i]);
        }
        // selectionPanel.add(selectButton);
        // selectionPanel.add(associationButton);
        // selectionPanel.add(generalizationButton);
        // selectionPanel.add(compositionButton);
        // selectionPanel.add(classButton);
        // selectionPanel.add(useCaseButton);
        
    }

    private void addCanvasComponents(Container canvasPanel){
        canvas.setBackground(Color.white);
        canvas.addMouseListener(canvasListener);
        canvas.addMouseMotionListener(canvasListener);
        canvasPanel.addMouseListener(canvasListener);
        canvasPanel.addMouseMotionListener(canvasListener);
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
