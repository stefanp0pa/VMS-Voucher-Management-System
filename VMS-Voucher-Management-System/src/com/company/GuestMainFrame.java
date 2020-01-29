package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.Vector;
import javax.swing.border.Border;

public class GuestMainFrame extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel currUsernameLabel;
    private JButton signOutButton;
    private JButton visualizeCampaignsButton;
    private JButton visualizeVouchersButton;
    private JButton exitAppButton;

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel titlePanel;
    private JPanel optionsCampaignsPanel;
    private JPanel optionsVouchersPanel;
    private JPanel optionsExitPanel;

    private User user;

    private Color panelBackgroundColor = new Color(210,234,238);
    private Color buttonBackgroundColor = new Color(73,195,158);

    private JFrame previousFrame;

    public GuestMainFrame(User user, JFrame previousFrame){
        this.user = user;
        this.previousFrame = previousFrame;
        initializeComponents();
        setUpFrame();
    }

    private void setUpFrame(){
        setSize(800,600);
        centerFrame();
        setLayout(new FlowLayout());
        getContentPane().setBackground(panelBackgroundColor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Voucher Management System");
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        initializePanels();
        initializeLabels();
        initializeButtons();
        addComponentsToPanels();
    }

    private void addComponentsToPanels() {
        headerPanel.add(signOutButton, BorderLayout.LINE_START);
        headerPanel.add(currUsernameLabel, BorderLayout.LINE_END);
        titlePanel.add(titleLabel);
        optionsCampaignsPanel.add(visualizeCampaignsButton);
        optionsVouchersPanel.add(visualizeVouchersButton);
        optionsExitPanel.add(exitAppButton);
    }

    private void initializeLabels(){
        titleLabel = new JLabel("                 Voucher Management System                 ");
        currUsernameLabel = new JLabel(user.getUserName() +" as "+user.getUserType());

        Font font1 = new Font("SansSerif", Font.PLAIN,30);
        titleLabel.setFont(font1);
        font1 = new Font("SansSerif", Font.PLAIN,15);
        currUsernameLabel.setFont(font1);

        currUsernameLabel.setForeground(Color.BLUE);
    }

    private void initializeButtons(){
        signOutButton = new JButton("Sign Out");
        visualizeCampaignsButton = new JButton("Vizualizeaza campanii");
        visualizeVouchersButton = new JButton("Vizualizeaza voucherele mele");
        exitAppButton = new JButton("Iesire aplicatie");

        signOutButton.setFocusPainted(false);
        visualizeCampaignsButton.setFocusPainted(false);
        visualizeVouchersButton.setFocusPainted(false);
        exitAppButton.setFocusPainted(false);

        signOutButton.setBackground(panelBackgroundColor);
        visualizeCampaignsButton.setBackground(buttonBackgroundColor);
        visualizeVouchersButton.setBackground(buttonBackgroundColor);
        exitAppButton.setBackground(buttonBackgroundColor);

        signOutButton.addActionListener(this);
        visualizeCampaignsButton.addActionListener(this);
        visualizeVouchersButton.addActionListener(this);
        exitAppButton.addActionListener(this);

        visualizeCampaignsButton.setPreferredSize(new Dimension(300,40));
        visualizeCampaignsButton.setFont(new Font("Dialog",Font.PLAIN,20));

        visualizeVouchersButton.setPreferredSize(new Dimension(300,40));
        visualizeVouchersButton.setFont(new Font("Dialog",Font.PLAIN,20));

        exitAppButton.setPreferredSize(new Dimension(300,40));
        exitAppButton.setFont(new Font("Dialog",Font.PLAIN,20));

        //signOutButton.setPreferredSize(new Dimension(100,50));

    }

    private void initializePanels(){
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        titlePanel = new JPanel();
        optionsCampaignsPanel = new JPanel();
        optionsVouchersPanel = new JPanel();
        optionsExitPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        headerPanel.setLayout(new BorderLayout());

        mainPanel.setSize(800,60);

        mainPanel.setBackground(panelBackgroundColor);
        headerPanel.setBackground(panelBackgroundColor);
        titlePanel.setBackground(panelBackgroundColor);
        optionsCampaignsPanel.setBackground(panelBackgroundColor);
        optionsVouchersPanel.setBackground(panelBackgroundColor);
        optionsExitPanel.setBackground(panelBackgroundColor);

        headerPanel.setBorder(new EmptyBorder(20,10,20,10));
        titlePanel.setBorder(new EmptyBorder(40,10,40,20));

        this.add(mainPanel);
        mainPanel.add(headerPanel);
        mainPanel.add(titlePanel);
        mainPanel.add(optionsCampaignsPanel);
        mainPanel.add(optionsVouchersPanel);
        mainPanel.add(optionsExitPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signOutButton){
            previousFrame.setVisible(true);
            this.dispose();
            return;
        }
        if(e.getSource() == exitAppButton){
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.dispose();
            return;
        }
        if(e.getSource() == visualizeCampaignsButton){
            new GuestCampaignsFrame(user);
            return;
        }
        if(e.getSource() == visualizeVouchersButton){
            new GuestVouchersFrame(user);
            return;
        }
    }
}
