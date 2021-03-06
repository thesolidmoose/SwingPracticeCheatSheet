package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {
    // Text field and labels tutorial
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel(){
        // Sizing tutorial ----------
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        // Text field and labels tutorial ----------
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        genderGroup = new ButtonGroup();
        okBtn = new JButton("OK");

        // set up mnemonics for Ok button
        okBtn.setMnemonic(KeyEvent.VK_O);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        // setActionCommand lets you retrieve a string
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        maleRadio.setSelected(true);

        // Radio Button tutorial
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Check boxes tutorial
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });

        // List box tutorial. This included models
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Combo box tutorial
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        // Send off an event when the OkButton is clicked
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Field.getText() will make it so that you get the data from
                 the field */
                String name = nameField.getText();
                String occupation = occupationField.getText();
                // For this, you must type cast since we know that it is a string!
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
                String empCat = (String)empCombo.getSelectedItem();
                String taxID = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();
                // This return a string from the button group of radio buttons
                String gender = genderGroup.getSelection().getActionCommand();

                FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat,
                        taxID, usCitizen, gender);

                if(formListener != null){
                    formListener.formEventOccurred(ev);
                }
            }
        });

        // Border tutorial ----------
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void layoutComponents(){
        // GridBag layout tutorial ----------
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ///////////////////////// FIRST ROW ////////////////////////

        // Grid controls the location
        gc.gridx = 0;
        gc.gridy = 0;

        // Weight controls how much space an element takes up
        gc.weightx = 1;
        gc.weighty = 0.1;

        // Anchor controls the side of the cell the elements stick to
        gc.anchor = GridBagConstraints.LINE_END;

        // Fill controls whether or not an element takes up all the space in the cell
        gc.fill = GridBagConstraints.NONE;

        // Insets is how you add space
        gc.insets = new Insets(0,0,0,5);

        // Add allows you to add the element
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        ///////////////////////// SECOND ROW ////////////////////////

        gc.gridx = 0;
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        ///////////////////////// THIRD ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);

        ///////////////////////// FOURTH ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        ///////////////////////// FIFTH ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("US Citizen: "), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheck, gc);

        ///////////////////////// SIXTH ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxField, gc);

        ///////////////////////// SEVENTH ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxField, gc);

        ///////////////////////// EIGHTH ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Gender"), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleRadio, gc);

        ///////////////////////// NINTH ROW ////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadio, gc);

        ///////////////////////// TENTH ROW ////////////////////////

        gc.gridx = 1;
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2.0;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);
    }

    public void setFormListener(FormListener listener){
        this.formListener = listener;
    }
}

class AgeCategory{
    private int id;
    private String text;

    public AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    public String toString(){
        return text;
    }

    public int getId(){
        return id;
    }
}
