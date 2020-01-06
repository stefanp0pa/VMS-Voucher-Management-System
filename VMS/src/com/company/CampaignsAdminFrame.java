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

    private JFrame previousFrame;

    private Color panelBackgroundColor = new Color(210,234,238);
    private Color buttonBackgroundColor = new Color(73,195,158);

    private String[] columnNames = {
            "ID",
            "Name",
            "Description",
            "Start",
            "End",
            "Strategy"
    };

    public CampaignsAdminFrame(User user,JFrame previousFrame){
        this.user = user;
        this.previousFrame = previousFrame;
        initializeComponents();
        setUpFrame();
    }

    private void setUpFrame(){
        setSize(1200,435);
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
        initializePanels();
        addComponentsToMasterPanel();
        //listCampaignsButtonAction();
    }

    private void initializeTable(Object[][] data){
        campaignsTable = new JTable(data,columnNames);
        campaignsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        campaignsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        campaignsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        campaignsTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        campaignsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        campaignsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        campaignsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
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

        backButton.setFocusPainted(false);
        listCampaignsButton.setFocusPainted(false);
        addCampaignButton.setFocusPainted(false);
        updateCampaignButton.setFocusPainted(false);
        cancelCampaignButton.setFocusPainted(false);
        detailCampaignButton.setFocusPainted(false);
        sortByStartButton.setFocusPainted(false);
        sortByNameButton.setFocusPainted(false);

        backButton.setBackground(buttonBackgroundColor);
        listCampaignsButton.setBackground(buttonBackgroundColor);
        addCampaignButton.setBackground(buttonBackgroundColor);
        updateCampaignButton.setBackground(buttonBackgroundColor);
        cancelCampaignButton.setBackground(buttonBackgroundColor);
        detailCampaignButton.setBackground(buttonBackgroundColor);
        sortByStartButton.setBackground(buttonBackgroundColor);
        sortByNameButton.setBackground(buttonBackgroundColor);

        backButton.addActionListener(this);
        listCampaignsButton.addActionListener(this);
        addCampaignButton.addActionListener(this);
        updateCampaignButton.addActionListener(this);
        cancelCampaignButton.addActionListener(this);
        detailCampaignButton.addActionListener(this);
        sortByNameButton.addActionListener(this);
        sortByStartButton.addActionListener(this);
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

        masterPanel.setPreferredSize(new Dimension(300, 300));
        detailPanel.setPreferredSize(new Dimension(850, 400));

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
            return;
        }
        if(e.getSource()==detailCampaignButton){
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
        Object[][] data = new Object[campaigns.size()][6];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 0; i < campaigns.size(); i++){
            data[i][0] = campaigns.get(i).getCampaignId();
            data[i][1] = campaigns.get(i).getCampaignName();
            data[i][2] = campaigns.get(i).getCampaignDescription();
            data[i][3] = (sdf.format(campaigns.get(i).getStartDate())).toString();
            data[i][4] = (sdf.format(campaigns.get(i).getEndDate())).toString();
            data[i][5] = campaigns.get(i).getStrategyType().strategyName;
        }
        for(int i = 0; i < campaigns.size(); i++){
            for(int j = 0; j < 6; j++){
                System.out.print(data[i][j] + "  ");
            }
            System.out.println();
        }

        initializeTable(data);
    }

    private void emptyDetailPanel(){
        while(detailPanel.getComponentCount()>0){
            detailPanel.remove(0);
        }
    }
}
