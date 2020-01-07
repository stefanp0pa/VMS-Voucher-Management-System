package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class CampaignsAdminFrame extends JFrame implements ActionListener {

    private User user;

    private JButton backButton;
    private JButton listCampaignsButton;
    private JButton addCampaignButton;
    private JButton updateCampaignButton;
    private JButton cancelCampaignButton;
    private JButton detailCampaignButton;
    private JButton sortByNameButton;
    private JButton sortByStartButton;

    private JPanel masterPanel;
    private JPanel detailPanel;

    private JPanel backButtonPanel;
    private JPanel listCampaignsPanel;
    private JPanel addCampaignsPanel;
    private JPanel updateCampaignPanel;
    private JPanel cancelCampaignPanel;
    private JPanel detailCampaignPanel;
    private JPanel sortButtonsPanel;

    private JTable campaignsTable;
    private JScrollPane scrollPane;

    private JButton giveDetailsButton;

    private JLabel campaignIdLabel;
    private JLabel campaignNameLabel;
    private JLabel campaignDescriptionLabel;
    private JLabel campaignStartDateLabel;
    private JLabel campaignEndDateLabel;
    private JLabel totalVouchersLabel;
    private JLabel availableVouchersLabel;
    private JLabel strategyLabel;
    private JLabel campaignStatusLabel;

    private JTextField campaignIdTextField;
    private JTextField campaignNameTextField;
    private JTextField campaignDescriptionTextField;
    private JTextField campaignStartDateTextField;
    private JTextField campaignEndDateTextField;
    private JTextField totalVouchersTextField;
    private JTextField availableVouchersTextField;
    private JTextField strategyTextField;
    private JTextField campaignStatusTextField;

    private JPanel campaignIdPanel;
    private JPanel campaignNamePanel;
    private JPanel campaignDescriptionPanel;
    private JPanel campaignStartDatePanel;
    private JPanel campaignEndDatePanel;
    private JPanel totalVouchersPanel;
    private JPanel availableVouchersPanel;
    private JPanel strategyPanel;
    private JPanel campaignStatusPanel;

    private JButton cancelButton;

    private JFrame previousFrame;

    private Color panelBackgroundColor = new Color(210,234,238);
    private Color buttonBackgroundColor = new Color(73,195,158);

    private String[] columnNames = {
            "ID",
            "Name",
            "Description",
            "Start",
            "End",
            "Strategy",
            "Status"
    };

    public CampaignsAdminFrame(User user,JFrame previousFrame){
        this.user = user;
        this.previousFrame = previousFrame;
        initializeComponents();
        setUpFrame();
    }

    private void setUpFrame(){
        setSize(1200,500);
        setTitle("Voucher Management System - Campaigns Panel");
        centerFrame();
        setLayout(new FlowLayout());
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
        initializeLabels();
        initializeTextField();
        initializePanels();
        addComponentsToMasterPanel();
        //listCampaignsButtonAction();
    }

    private void initializeTextField(){
        campaignIdTextField = new JTextField(3);
        campaignNameTextField = new JTextField(10);
        campaignDescriptionTextField = new JTextField(20);
        campaignStartDateTextField = new JTextField(10);
        campaignEndDateTextField = new JTextField(10);
        totalVouchersTextField = new JTextField(5);
        availableVouchersTextField = new JTextField(5);
        strategyTextField = new JTextField(3);
        campaignStatusTextField = new JTextField(8);

        campaignIdTextField.setBackground(Color.WHITE);
        campaignNameTextField.setBackground(Color.WHITE);
        campaignDescriptionTextField.setBackground(Color.WHITE);
        campaignStartDateTextField.setBackground(Color.WHITE);
        campaignEndDateTextField.setBackground(Color.WHITE);
        totalVouchersTextField.setBackground(Color.WHITE);
        availableVouchersTextField.setBackground(Color.WHITE);
        strategyTextField.setBackground(Color.WHITE);
        campaignStatusTextField.setBackground(Color.WHITE);
    }

    private void initializeLabels(){
        campaignIdLabel = new JLabel("ID: ");
        campaignNameLabel = new JLabel("Campaign Name: ");
        campaignDescriptionLabel = new JLabel("Campaign Description: ");
        campaignStartDateLabel = new JLabel("Start Date: ");
        campaignEndDateLabel = new JLabel("End Date: ");
        totalVouchersLabel = new JLabel("Total Vouchers: ");
        availableVouchersLabel = new JLabel("Available Vouchers: ");
        strategyLabel = new JLabel("Strategy: ");
        campaignStatusLabel = new JLabel("Campaign Status: ");
    }

    private void initializeTable(Object[][] data){
        campaignsTable = new JTable(data,columnNames);
        campaignsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        campaignsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        campaignsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        campaignsTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        campaignsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        campaignsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        campaignsTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        campaignsTable.setPreferredScrollableViewportSize(campaignsTable.getPreferredSize());

        scrollPane = new JScrollPane(campaignsTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        detailPanel.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );

        detailPanel.repaint();
        detailPanel.revalidate();
        //detailPanel.add(campaignsTable);

    }

    private void initializeButtons(){
        backButton = new JButton("Inapoi la menu");
        listCampaignsButton = new JButton("Listeaza campanii");
        addCampaignButton = new JButton("Adauga campanie");
        updateCampaignButton = new JButton("Modifica campanie");
        cancelCampaignButton = new JButton("Anuleaza campanie");
        detailCampaignButton = new JButton("Detaliaza campanie");
        sortByNameButton = new JButton("Sort name");
        sortByStartButton = new JButton("Sort start");
        giveDetailsButton = new JButton("Details!");
        cancelButton = new JButton("Cancel!");

        backButton.setFocusPainted(false);
        listCampaignsButton.setFocusPainted(false);
        addCampaignButton.setFocusPainted(false);
        updateCampaignButton.setFocusPainted(false);
        cancelCampaignButton.setFocusPainted(false);
        detailCampaignButton.setFocusPainted(false);
        sortByStartButton.setFocusPainted(false);
        sortByNameButton.setFocusPainted(false);
        giveDetailsButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);

        backButton.setBackground(buttonBackgroundColor);
        listCampaignsButton.setBackground(buttonBackgroundColor);
        addCampaignButton.setBackground(buttonBackgroundColor);
        updateCampaignButton.setBackground(buttonBackgroundColor);
        cancelCampaignButton.setBackground(buttonBackgroundColor);
        detailCampaignButton.setBackground(buttonBackgroundColor);
        sortByStartButton.setBackground(buttonBackgroundColor);
        sortByNameButton.setBackground(buttonBackgroundColor);
        giveDetailsButton.setBackground(buttonBackgroundColor);
        cancelButton.setBackground(buttonBackgroundColor);

        backButton.addActionListener(this);
        listCampaignsButton.addActionListener(this);
        addCampaignButton.addActionListener(this);
        updateCampaignButton.addActionListener(this);
        cancelCampaignButton.addActionListener(this);
        detailCampaignButton.addActionListener(this);
        sortByNameButton.addActionListener(this);
        sortByStartButton.addActionListener(this);
        giveDetailsButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    private void initializePanels() {
        masterPanel = new JPanel();
        detailPanel = new JPanel();
        backButtonPanel = new JPanel();
        listCampaignsPanel = new JPanel();
        updateCampaignPanel = new JPanel();
        cancelCampaignPanel = new JPanel();
        detailCampaignPanel = new JPanel();
        sortButtonsPanel = new JPanel();

        campaignIdPanel = new JPanel();
        campaignNamePanel = new JPanel();
        campaignDescriptionPanel = new JPanel();
        campaignStartDatePanel = new JPanel();
        campaignEndDatePanel = new JPanel();
        totalVouchersPanel = new JPanel();
        availableVouchersPanel = new JPanel();
        strategyPanel = new JPanel();
        campaignStatusPanel = new JPanel();

        detailPanel.setLayout(new FlowLayout());
        //backButtonPanel.setBorder(new EmptyBorder(20,10,0,10));

        Font font = new Font("SansSerif",Font.PLAIN,15);
        backButton.setFont(font);
        listCampaignsButton.setFont(font);
        updateCampaignButton.setFont(font);
        cancelCampaignButton.setFont(font);
        detailCampaignButton.setFont(font);

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

        masterPanel.setPreferredSize(new Dimension(300, 350));
        detailPanel.setPreferredSize(new Dimension(850, 450));

        getContentPane().setBackground(panelBackgroundColor);
        masterPanel.setBackground(panelBackgroundColor);
        //detailPanel.setBackground(Color.BLUE);
        backButtonPanel.setBackground(panelBackgroundColor);
        listCampaignsPanel.setBackground(panelBackgroundColor);
        updateCampaignPanel.setBackground(panelBackgroundColor);
        cancelCampaignPanel.setBackground(panelBackgroundColor);
        detailCampaignPanel.setBackground(panelBackgroundColor);


        this.add(masterPanel);
        this.add(detailPanel);
        masterPanel.add(backButtonPanel);
        masterPanel.add(listCampaignsPanel);
        masterPanel.add(updateCampaignPanel);
        masterPanel.add(cancelCampaignPanel);
        masterPanel.add(detailCampaignPanel);

    }

    private void addComponentsToMasterPanel(){
        backButtonPanel.add(backButton);
        listCampaignsPanel.add(listCampaignsButton);
        updateCampaignPanel.add(updateCampaignButton);
        cancelCampaignPanel.add(cancelCampaignButton);
        detailCampaignPanel.add(detailCampaignButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            backButtonAction();
            return;
        }
        if(e.getSource()==listCampaignsButton){
            listCampaignsButtonAction();
            return;
        }
        if(e.getSource()==updateCampaignButton){
            return;
        }
        if(e.getSource()==cancelCampaignButton){
            cancelCampaignButtonAction();
            return;
        }
        if(e.getSource()==detailCampaignButton){
            detailCampaignButtonAction();
            return;
        }
        if(e.getSource()==giveDetailsButton){
            giveDetailsButtonAction();
            return;
        }
        if(e.getSource() == cancelButton){
            cancelButtonAction();
            return;
        }
    }

    private void backButtonAction(){
        this.setVisible(false);
        previousFrame.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispose();
    }

    private void listCampaignsButtonAction(){
        emptyDetailPanel();
        Vector<Campaign> campaigns = VMS.getInstance().getCampaigns();
        Object[][] data = new Object[campaigns.size()][7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for(int i = 0; i < campaigns.size(); i++){
            data[i][0] = campaigns.get(i).getCampaignId();
            data[i][1] = campaigns.get(i).getCampaignName();
            data[i][2] = campaigns.get(i).getCampaignDescription();
            data[i][3] = (sdf.format(campaigns.get(i).getStartDate())).toString();
            data[i][4] = (sdf.format(campaigns.get(i).getEndDate())).toString();
            data[i][5] = campaigns.get(i).getStrategyType().strategyName;
            data[i][6] = campaigns.get(i).getCampaignStatusType();
        }
        initializeTable(data);
    }

    private void detailCampaignButtonAction(){
        emptyDetailPanel();
        resetTextFields();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.add(campaignIdPanel);
        detailPanel.add(campaignNamePanel);
        detailPanel.add(campaignDescriptionPanel);
        detailPanel.add(campaignStartDatePanel);
        detailPanel.add(campaignEndDatePanel);
        detailPanel.add(totalVouchersPanel);
        detailPanel.add(availableVouchersPanel);
        detailPanel.add(strategyPanel);
        detailPanel.add(campaignStatusPanel);

        campaignIdPanel.setLayout(new FlowLayout());
        campaignIdPanel.add(campaignIdLabel);
        campaignIdPanel.add(campaignIdTextField);
        campaignIdPanel.add(giveDetailsButton);

        campaignNamePanel.setLayout(new FlowLayout());
        campaignNamePanel.add(campaignNameLabel);
        campaignNamePanel.add(campaignNameTextField);

        campaignDescriptionPanel.setLayout(new FlowLayout());
        campaignDescriptionPanel.add(campaignDescriptionLabel);
        campaignDescriptionPanel.add(campaignDescriptionTextField);

        campaignStartDatePanel.setLayout(new FlowLayout());
        campaignStartDatePanel.add(campaignStartDateLabel);
        campaignStartDatePanel.add(campaignStartDateTextField);

        campaignEndDatePanel.setLayout(new FlowLayout());
        campaignEndDatePanel.add(campaignEndDateLabel);
        campaignEndDatePanel.add(campaignEndDateTextField);

        totalVouchersPanel.setLayout(new FlowLayout());
        totalVouchersPanel.add(totalVouchersLabel);
        totalVouchersPanel.add(totalVouchersTextField);

        availableVouchersPanel.setLayout(new FlowLayout());
        availableVouchersPanel.add(availableVouchersLabel);
        availableVouchersPanel.add(availableVouchersTextField);

        strategyPanel.setLayout(new FlowLayout());
        strategyPanel.add(strategyLabel);
        strategyPanel.add(strategyTextField);

        campaignStatusPanel.setLayout(new FlowLayout());
        campaignStatusPanel.add(campaignStatusLabel);
        campaignStatusPanel.add(campaignStatusTextField);

        detailPanel.repaint();
        detailPanel.revalidate();
    }

    private void cancelCampaignButtonAction(){
        emptyDetailPanel();
        resetTextFields();
        detailPanel.setLayout(new FlowLayout());
        detailPanel.add(campaignIdLabel);
        detailPanel.add(campaignIdTextField);
        detailPanel.add(cancelButton);

        revalidate();
        repaint();
    }

    private void giveDetailsButtonAction(){
        String id = campaignIdTextField.getText();
        if(id == null || id.isEmpty()){
            JOptionPane.showMessageDialog(this,"Provide an ID");
            return;
        }
        Campaign campaign = VMS.getInstance().getCampaign(Integer.parseInt(id));
        if(campaign == null){
            JOptionPane.showMessageDialog(this,"Provide a valid ID");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        campaignNameTextField.setText(campaign.getCampaignName());
        campaignDescriptionTextField.setText(campaign.getCampaignDescription());
        campaignStartDateTextField.setText(sdf.format(campaign.getStartDate()).toString());
        campaignEndDateTextField.setText(sdf.format(campaign.getEndDate()).toString());
        strategyTextField.setText(campaign.getStrategyType().strategyName);
        totalVouchersTextField.setText(campaign.getTotalVouchersCount().toString());
        availableVouchersTextField.setText(campaign.getAvailableVouchersCount().toString());
        campaignStatusTextField.setText(campaign.getCampaignStatusType().toString());
    }

    private void cancelButtonAction(){
        String id = campaignIdTextField.getText();
        if(id == null || id.isEmpty()){
            JOptionPane.showMessageDialog(this,"Provide an ID");
            return;
        }
        Campaign campaign = VMS.getInstance().getCampaign(Integer.parseInt(id));
        if(campaign == null){
            JOptionPane.showMessageDialog(this,"Provide a valid ID");
            return;
        }
        if(campaign.getCampaignStatusType()== Campaign.CampaignStatusType.CANCELLED){
            JOptionPane.showMessageDialog(this,"Campaign already cancelled");
            return;
        }
        VMS.getInstance().cancelCampaign(Integer.parseInt(id));
    }

    private void emptyDetailPanel(){
       detailPanel.removeAll();
    }

    private void resetTextFields(){
        campaignIdTextField.setText(null);
        campaignNameTextField.setText(null);
        campaignDescriptionTextField.setText(null);
        campaignStartDateTextField.setText(null);
        campaignEndDateTextField.setText(null);
        totalVouchersTextField.setText(null);
        availableVouchersTextField.setText(null);
        strategyTextField.setText(null);
        campaignStatusTextField.setText(null);
    }
}
