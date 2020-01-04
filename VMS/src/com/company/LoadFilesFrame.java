package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadFilesFrame extends JFrame implements ActionListener {
    private JLabel usersFileLabel;
    private JLabel campaignsFileLabel;
    private JTextField usersPathTextField;
    private JTextField campaignsPathTextField;
    private JFileChooser usersFileChooser;
    private JFileChooser campaignsFileChooser;
    private JButton loadButton;
    private JButton usersChooserButton;
    private JButton campaignsChooserButton;

    private JPanel mainPanel;
    private JPanel usersLabelPanel;
    private JPanel usersFileChoosePanel;
    private JPanel campaignsLabelPanel;
    private JPanel campaignsFileChoosePanel;
    private JPanel loadButtonPanel;

    private File usersFile = null;
    private File campaignsFile = null;

    private String defaultChooserPath = null;

    public LoadFilesFrame(){

        try {
            defaultChooserPath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeComponents();
        setSize(600,250);
        //setContentPane(mainPanel);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(210,234,238));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        centerFrame();
        this.setTitle("Voucher Management System");
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        initializeLabels();
        initializeFileChoosers();
        initializeButtons();
        initializeTextFields();
        initializePanels();
        addComponentsToPanels();
    }

    private void initializePanels(){
        mainPanel = new JPanel();
        usersLabelPanel = new JPanel(new BorderLayout());
        usersFileChoosePanel = new JPanel();
        campaignsLabelPanel = new JPanel(new BorderLayout());
        campaignsFileChoosePanel = new JPanel();
        loadButtonPanel = new JPanel();

        usersLabelPanel.setBorder(new EmptyBorder(20,55,0,10));
        campaignsLabelPanel.setBorder(new EmptyBorder(10,55,0,10));
        loadButtonPanel.setBorder(new EmptyBorder(20,0,0,0));

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(210,234,238));
        usersLabelPanel.setBackground(new Color(210,234,238));
        usersFileChoosePanel.setBackground(new Color(210,234,238));
        campaignsLabelPanel.setBackground(new Color(210,234,238));
        campaignsFileChoosePanel.setBackground(new Color(210,234,238));
        loadButtonPanel.setBackground(new Color(210,234,238));

        this.add(mainPanel);
        mainPanel.add(usersLabelPanel);
        mainPanel.add(usersFileChoosePanel);
        mainPanel.add(campaignsLabelPanel);
        mainPanel.add(campaignsFileChoosePanel);
        mainPanel.add(loadButtonPanel);
    }

    private void initializeLabels(){
        usersFileLabel = new JLabel("Users File");
        campaignsFileLabel = new JLabel("Campaigns File");

        Font font1 = new Font("SansSerif", Font.PLAIN,15);
        usersFileLabel.setFont(font1);
        campaignsFileLabel.setFont(font1);
    }

    private void initializeFileChoosers(){
        usersFileChooser = new JFileChooser(defaultChooserPath);
        campaignsFileChooser = new JFileChooser(defaultChooserPath);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        usersFileChooser.setFileFilter(filter);
        campaignsFileChooser.setFileFilter(filter);
    }

    private void initializeButtons(){
        loadButton = new JButton("LOAD");
        loadButton.setFocusPainted(false);

        usersChooserButton = new JButton("...");
        usersChooserButton.setFocusPainted(false);

        campaignsChooserButton = new JButton("...");
        campaignsChooserButton.setFocusPainted(false);

        usersChooserButton.setBackground(new Color(235,180,36));
        campaignsChooserButton.setBackground(new Color(235,180,36));
        loadButton.setBackground(new Color(73,195,158));

        loadButton.addActionListener(this);
        usersChooserButton.addActionListener(this);
        campaignsChooserButton.addActionListener(this);
    }

    private void initializeTextFields(){
        usersPathTextField = new JTextField(40);
        //usersPathTextField.setEditable(false);
        usersPathTextField.setBackground(Color.WHITE);

        campaignsPathTextField = new JTextField(40);
        //campaignsPathTextField.setEditable(false);
        campaignsPathTextField.setBackground(Color.WHITE);

        Font font1 = new Font("SansSerif", Font.PLAIN,13);
        usersPathTextField.setFont(font1);
        campaignsPathTextField.setFont(font1);

        usersPathTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignsPathTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
    }

    private void addComponentsToPanels(){
        usersLabelPanel.add(usersFileLabel);
        usersFileChoosePanel.add(usersChooserButton);
        usersFileChoosePanel.add(usersPathTextField);
        campaignsLabelPanel.add(campaignsFileLabel);
        campaignsFileChoosePanel.add(campaignsChooserButton);
        campaignsFileChoosePanel.add(campaignsPathTextField);
        loadButtonPanel.add(loadButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

       if(e.getSource() == usersChooserButton){
           chooseUsersFile();

       }else if(e.getSource() == campaignsChooserButton){
           chooseCampaignsFile();

       }else if(e.getSource() == loadButton){
           loadFiles();
       }
    }

    private void chooseUsersFile(){
        System.out.println("Choosing users file...");
        int returnValue = usersFileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = usersFileChooser.getSelectedFile();
            if(file!=null){
                this.usersFile = file;
                this.usersPathTextField.setText(this.usersFile.getAbsolutePath());
                System.out.println(usersFile.getAbsolutePath());
            }
        }else{
            System.out.println("Nothing selected as users file");
        }
    }

    private void chooseCampaignsFile(){
        System.out.println("Choosing campaigns file...");
        int returnValue = campaignsFileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = campaignsFileChooser.getSelectedFile();
            if(file!=null){
                this.campaignsFile = file;
                this.campaignsPathTextField.setText(this.campaignsFile.getAbsolutePath());
                System.out.println(campaignsFile.getAbsolutePath());
            }
        }else{
            System.out.println("Nothing selected as users file");
        }
    }

    private void loadFiles(){
        System.out.println("Loading files..");
        if(usersFile!=null && campaignsFile!=null){
            JOptionPane.showMessageDialog(this,"Succesfully loaded");
            parseFiles();
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this,"Did not load all necessary files");
        }
    }

    private void parseFiles(){
        try {
            InputParser.parseUsersInput(usersFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputParser.parseCampaignInput(campaignsFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
