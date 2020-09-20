package ru.nsu.g.akononov.vas.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CardPanel extends JPanel {
    private final ButtonsPanel buttonsPanel;
    private final JLabel carNamePanel = new JLabel("Unknown car", SwingConstants.CENTER);
    private final JPanel attributesPanel = new JPanel();
    private final Map<String, JTextField> fields = new HashMap<>();

    public CardPanel(List<String> attributes) {
        setLayout(new BorderLayout());

        addAttributesFields(attributes);
        add(attributesPanel, BorderLayout.CENTER);

        buttonsPanel = new ButtonsPanel(new ArrayList<>(Arrays.asList("Save", "Remove")));
        add(buttonsPanel, BorderLayout.SOUTH);

        carNamePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        add(carNamePanel, BorderLayout.NORTH);

        initNamePanel();
    }

    private void addAttributesFields(List<String> attributes) {
        attributesPanel.setLayout(new GridBagLayout());

        GridBagConstraints nameConstraint = new GridBagConstraints();
        nameConstraint.fill = GridBagConstraints.HORIZONTAL;
        nameConstraint.gridx = 0;
        nameConstraint.weightx = 1;
        nameConstraint.weighty = 1;

        GridBagConstraints fieldConstraint = new GridBagConstraints();
        fieldConstraint.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraint.gridx = 1;
        fieldConstraint.weightx = 10;

        int index = 0;
        for (String attribute : attributes) {
            nameConstraint.gridy = index;
            fieldConstraint.gridy = index;
            JLabel fieldName = new JLabel(attribute, SwingConstants.RIGHT);
            fieldName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
            JTextField textField = new JTextField();
            fields.put(attribute, textField);
            attributesPanel.add(textField, fieldConstraint);
            attributesPanel.add(fieldName, nameConstraint);
            index++;
        }
    }

    public JTextField getField(String attributeName) {
        return fields.get(attributeName);
    }

    private void initNamePanel() {
        Font currentFont = carNamePanel.getFont();
        Font updatedFont = new Font(currentFont.getName(), currentFont.getStyle(), (int) (currentFont.getSize() * 1.5));
        carNamePanel.setFont(updatedFont);
    }

    public void setCarName(String carName) {
        carNamePanel.setText(carName);
    }

    public JButton getButton(String name) {
        return buttonsPanel.getButton(name.toLowerCase());
    }

    public void setAttributeValue(String fieldName, String value) {
        getField(fieldName).setText(value);
    }
}
