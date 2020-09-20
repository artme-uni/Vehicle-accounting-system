package ru.nsu.g.akononov.vas.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUIView extends JFrame {

    final private CardLayout cardLayout = new CardLayout();
    final private JPanel mainPanel = new JPanel(cardLayout);
    final private ListPanel listPanel = new ListPanel();
    final private CardPanel cardPanel;

    private boolean additionMode = false;
    private boolean editMode = false;


    public GUIView(List<String> attributes) {
        configureFrame();

        cardPanel = new CardPanel(attributes);
        mainPanel.add(listPanel);
        mainPanel.add(cardPanel);
        setVisible(true);
    }

    private void configureFrame() {
        setTitle("Accounting System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMainPanel();
        pack();

        setResizable(true);
        setFocusable(true);
        setLocationRelativeTo(null);
    }

    private void addMainPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 3;
        int height = screenSize.height * 2 / 3;
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.setPreferredSize(new Dimension(width, height));
        add(mainPanel);
    }

    public void switchPanel(){
        cardLayout.next(mainPanel);
    }

    public ListPanel getListPanel(){
        return listPanel;
    }

    public CardPanel getCardPanel(){
        return cardPanel;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public void setAdditionMode(boolean additionMode) {
        this.additionMode = additionMode;
    }

    public boolean isAdditionMode() {
        return additionMode;
    }
}
