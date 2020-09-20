package ru.nsu.g.akononov.vas.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ListPanel extends JPanel {
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> list = new JList<>(listModel);
    private final ButtonsPanel buttonsPanel;

    public ListPanel() {
        setLayout(new BorderLayout());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.clearSelection();
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        list.setFixedCellHeight(30);

        add(new JScrollPane(list), BorderLayout.CENTER);
        buttonsPanel = new ButtonsPanel(new ArrayList<>(Arrays.asList("Add", "Details", "Remove")));
        setButtonsEnabled(new ArrayList<>(Arrays.asList("Details", "remove")));
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }

    private void setButtonsEnabled(ArrayList<String> buttonsNames) {
        for (String name : buttonsNames) {
            getButton(name).setEnabled(false);
        }

        list.addListSelectionListener(e -> {
            if (list.getSelectedIndex() >= 0) {
                for (String name : buttonsNames) {
                    getButton(name).setEnabled(true);
                }
            } else {
                for (String name : buttonsNames) {
                    getButton(name).setEnabled(false);
                }
            }
        });
        buttonsPanel.setVisible(true);
    }

    public JButton getButton(String name) {
        return buttonsPanel.getButton(name.toLowerCase());
    }

    public void addRecord(String carName) {
        listModel.addElement(carName);
    }

    public void removeRecord(int index){
        listModel.remove(index);
    }

    public void clearList() {
        listModel.clear();
    }

    public JList<String> getJList(){
        return list;
    }

    public int getListSize(){
        return listModel.getSize();
    }
}
