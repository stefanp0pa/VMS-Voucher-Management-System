package com.company;

import javax.swing.*;
import java.awt.*;

public class LoadFilesFrame extends JFrame {
    private JLabel usersFileJLabel;
    private JLabel campaignsFileJLabel;
    private JTextField usersPathTextField;
    private JTextField campaignsPathTextField;
    private JButton loadButton;
    private JPanel mainPanel;

    public LoadFilesFrame(){
        initializeComponents();
        setSize(600,300);
        //setContentPane(mainPanel);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(210,234,238));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        centerFrame();
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        usersFileJLabel = new JLabel("Users File");
        campaignsFileJLabel = new JLabel("Campaigns File");
        usersPathTextField = new JTextField(20);
        campaignsPathTextField = new JTextField(20);
        loadButton = new JButton("LOAD");
        add(usersFileJLabel);
        add(usersPathTextField);
        add(campaignsFileJLabel);
        add(campaignsPathTextField);
        add(loadButton);
        /*mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(usersFileJLabel);
        mainPanel.add(usersPathTextField);
        mainPanel.add(campaignsFileJLabel);
        mainPanel.add(campaignsPathTextField);
        mainPanel.add(loadButton);*/
    }

}
