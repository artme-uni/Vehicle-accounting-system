package ru.nsu.g.akononov.vas.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ButtonsPanel extends JPanel{
    private final Map<String, JButton> buttons = new HashMap<>();

    public ButtonsPanel(ArrayList<String> buttonsNames) {
        setLayout(new GridLayout(1, buttonsNames.size()));
        for (String name : buttonsNames) {
            JButton button = new JButton(name);
            buttons.put(name.toLowerCase(), button);
            add(button);
        }
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    public JButton getButton(String buttonName){
        return buttons.get(buttonName);
    }
}
