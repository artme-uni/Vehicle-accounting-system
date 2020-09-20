package ru.nsu.g.akononov.vas.controller;

import ru.nsu.g.akononov.vas.view.gui.CardPanel;
import ru.nsu.g.akononov.vas.view.gui.ListPanel;
import ru.nsu.g.akononov.vas.view.gui.GUIView;
import ru.nsu.g.akononov.vas.model.Storage;

import javax.swing.*;
import java.awt.event.*;

public class GUIController {
    private final Storage storage;
    private final GUIView window;
    private final ListPanel listPanel;
    private final CardPanel cardPanel;

    public GUIController(Storage storage) {
        this.storage = storage;
        window = new GUIView(storage.getAttributesNames());
        listPanel = window.getListPanel();
        cardPanel = window.getCardPanel();

        reloadList();
        addButtonListeners();
        addFieldListeners();
        addListListener();
        addExitListener();
    }


    private void setFieldValue(String fieldName) {
        int index = listPanel.getSelectedIndex();
        try {
            storage.setAttributeValue(index, fieldName, cardPanel.getField(fieldName).getText());
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null,
                    "Sorry, " + exception.getMessage(), "Incorrect input",
                    JOptionPane.ERROR_MESSAGE);
            cardPanel.getField(fieldName).setText(storage.getAttributeValue(index, fieldName));
        }
    }


    private void addFieldListeners() {
        for (String fieldName : storage.getAttributesNames()) {
            JTextField currentField = cardPanel.getField(fieldName);
            currentField.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                }
                public void focusLost(FocusEvent e) {
                    if (window.isEditMode() || window.isAdditionMode()) {
                        setFieldValue(fieldName);
                    }
                }
            });
        }
    }

    private void addListListener() {
        JList<String> list = listPanel.getJList();
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    switchToCard();
                }
            }
        });
    }

    private void addExitListener() {
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.setVisible(false);
                storage.saveData();
                System.exit(0);
            }
        });
    }

    private void addButtonListeners() {
        listPanel.getButton("details").addActionListener(e -> {
            switchToCard();
        });
        listPanel.getButton("remove").addActionListener(e -> {
            removeRecord();
        });
        listPanel.getButton("add").addActionListener(e -> {
            createNewRecord();
            window.setAdditionMode(true);
        });
        cardPanel.getButton("save").addActionListener(e -> {
            switchToList();
        });

        cardPanel.getButton("remove").addActionListener(e -> {
            removeRecord();
            window.switchPanel();
            reloadList();
            window.setAdditionMode(false);
            window.setEditMode(false);
        });
    }

    private void switchToCard() {
        cardPanel.setCarName(storage.getCarName(listPanel.getSelectedIndex()));
        initFieldValues(listPanel.getSelectedIndex());
        window.switchPanel();
        window.setEditMode(true);
    }

    private void switchToList() {
        if (window.isAdditionMode()) {
            if (isCorrectInput()) {
                window.switchPanel();
                reloadList();
                window.setAdditionMode(false);
                window.setEditMode(false);
            }
        } else{
            window.switchPanel();
            reloadList();
        }
    }

    private boolean isCorrectInput() {
        try {
            if (window.isAdditionMode()) {
                for (String fieldName : storage.getAttributesNames()) {
                    int index = listPanel.getSelectedIndex();
                    storage.setAttributeValue(index, fieldName, cardPanel.getField(fieldName).getText());
                }
            }
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null,
                    "Sorry, you have to fill in all fields correctly", "Incorrect input",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void createNewRecord() {
        storage.addDefaultCar();
        reloadList();
        listPanel.getJList().setSelectedIndex(listPanel.getListSize() - 1);
        cardPanel.setCarName("Enter details");
        initFieldValues();
        window.switchPanel();
    }

    private void removeRecord() {
        int index = listPanel.getSelectedIndex();
        if (index >= 0) {
            listPanel.removeRecord(index);
            storage.removeCar(index);
        }
    }

    private void initFieldValues(int carNumber) {
        for (String fieldName : storage.getAttributesNames()) {
            cardPanel.getField(fieldName).setText(storage.getAttributeValue(carNumber, fieldName));
        }
    }

    private void initFieldValues() {
        for (String fieldName : storage.getAttributesNames()) {
            cardPanel.getField(fieldName).setText("");
        }
    }

    public void reloadList() {
        listPanel.clearList();
        for (int i = 0; i < storage.getStorageSize(); i++) {
            listPanel.addRecord(storage.getCarName(i));
        }
    }

}
