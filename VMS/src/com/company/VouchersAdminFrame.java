package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VouchersAdminFrame extends JFrame implements ActionListener {

    private User user;
    private JFrame previousFrame;

    private JButton backButton;
    private JButton listVouchersButton;
    private JButton addVoucherButton;
    private JButton searchForVoucherButton;

    private JPanel masterPanel;
    private JPanel detailPanel;

    private JPanel backButtonPanel;
    private JPanel listVouchersPanel;
    private JPanel addVoucherPanel;
    private JPanel searchForVoucherPanel;

    private JTable vouchersTable;
    private JScrollPane vouchersTableScrollPane;

    private String[] vouchersTableColumnsName = {
            "ID",
            "Voucher Code",
            "Campaign ID",
            "Email",
            "Voucher Type",
            "Sum",
            "Status",
            "Used date"
    };

    private Color panelBackgroundColor = new Color(210,234,238);
    private Color buttonBackgroundColor = new Color(73,195,158);

    public VouchersAdminFrame(User user, JFrame previousFrame){
        this.user = user;
        this.previousFrame = previousFrame;
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame(){
        setSize(1200,500);
        setTitle("Voucher Management System - Campaigns Panel");
        centerFrame();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        initializeButtons();
        initializePanels();
    }

    private void initializeButtons(){
        backButton = new JButton("Inapoi la menu");
        listVouchersButton = new JButton("Listeaza vouchere");
        addVoucherButton = new JButton("Adauga voucher nou");
        searchForVoucherButton = new JButton("Cauta voucher");

        backButton.setFocusPainted(false);
        listVouchersButton.setFocusPainted(false);
        addVoucherButton.setFocusPainted(false);
        searchForVoucherButton.setFocusPainted(false);

        backButton.setBackground(buttonBackgroundColor);
        listVouchersButton.setBackground(buttonBackgroundColor);
        addVoucherButton.setBackground(buttonBackgroundColor);
        searchForVoucherButton.setBackground(buttonBackgroundColor);

        backButton.addActionListener(this);
        listVouchersButton.addActionListener(this);
        addVoucherButton.addActionListener(this);
        searchForVoucherButton.addActionListener(this);

        Font font = new Font("SansSerif",Font.PLAIN,15);
        backButton.setFont(font);
        listVouchersButton.setFont(font);
        addVoucherButton.setFont(font);
        searchForVoucherButton.setFont(font);
    }

    private void initializePanels(){
        masterPanel = new JPanel();
        detailPanel = new JPanel();

        this.setLayout(new FlowLayout());
        masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.Y_AXIS));
        detailPanel.setLayout(new BoxLayout(detailPanel,BoxLayout.Y_AXIS));

        masterPanel.setPreferredSize(new Dimension(300, 350));
        detailPanel.setPreferredSize(new Dimension(850, 450));

        this.getContentPane().setBackground(panelBackgroundColor);
        masterPanel.setBackground(panelBackgroundColor);

        this.add(masterPanel);
        this.add(detailPanel);

        initializeMasterPanels();
        addComponentsToMasterPanel();
    }

    private void initializeMasterPanels(){
        backButtonPanel = new JPanel();
        listVouchersPanel = new JPanel();
        addVoucherPanel = new JPanel();
        searchForVoucherPanel = new JPanel();

        backButtonPanel.setBackground(panelBackgroundColor);
        listVouchersPanel.setBackground(panelBackgroundColor);
        addVoucherPanel.setBackground(panelBackgroundColor);
        searchForVoucherPanel.setBackground(panelBackgroundColor);
    }

    private void addComponentsToMasterPanel(){
        backButtonPanel.add(backButton);
        listVouchersPanel.add(listVouchersButton);
        addVoucherPanel.add(addVoucherButton);
        searchForVoucherPanel.add(searchForVoucherButton);

        masterPanel.add(backButtonPanel);
        masterPanel.add(listVouchersPanel);
        masterPanel.add(addVoucherPanel);
        masterPanel.add(searchForVoucherPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            backButtonAction();
            return;
        }
        if(e.getSource() == listVouchersButton){
            return;
        }
        if(e.getSource() == addVoucherButton){
            return;
        }
        if(e.getSource() == searchForVoucherButton){
            return;
        }
    }

    private void backButtonAction(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
        this.previousFrame.setVisible(true);
        this.dispose();
    }
}
