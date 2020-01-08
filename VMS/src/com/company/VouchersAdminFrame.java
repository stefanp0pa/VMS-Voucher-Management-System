package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

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
            "Value",
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

    private void initializeTable(Object[][] data){
        vouchersTable = new JTable(data,vouchersTableColumnsName);
        vouchersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        vouchersTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        vouchersTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        vouchersTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        vouchersTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        vouchersTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        vouchersTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        vouchersTable.getColumnModel().getColumn(7).setPreferredWidth(120);
        vouchersTable.setPreferredScrollableViewportSize(vouchersTable.getPreferredSize());

        vouchersTableScrollPane = new JScrollPane(vouchersTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        detailPanel.add(vouchersTableScrollPane);
        vouchersTableScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );
        detailPanel.repaint();
        detailPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            backButtonAction();
            return;
        }
        if(e.getSource() == listVouchersButton){
            listVouchersButtonAction();
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

    private void listVouchersButtonAction(){
        emptyDetailPanel();
        Vector<Campaign> campaigns = VMS.getInstance().getCampaigns();
        Vector<Voucher> vouchers = new Vector<>();
        for(int i = 0; i < campaigns.size(); i++){
            Campaign currCampaign = campaigns.get(i);
            Vector<Voucher> currCampaignVouchers = currCampaign.getVouchers();
            for(int j = 0; j < currCampaignVouchers.size(); j++){
                vouchers.add(currCampaignVouchers.get(j));
            }
        }
        Object[][] data = new Object[vouchers.size()][vouchersTableColumnsName.length];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for(int i =0; i < vouchers.size(); i++){
            data[i][0] = vouchers.get(i).getVoucherId();
            data[i][1] = vouchers.get(i).getVoucherCode();
            data[i][2] = vouchers.get(i).getCampaignId();
            data[i][3] = vouchers.get(i).getEmail();
            data[i][4] = vouchers.get(i).getVoucherType();
            data[i][5] = vouchers.get(i).getValue();
            data[i][6] = vouchers.get(i).getVoucherStatusType();
            if(vouchers.get(i).getUsedDate()!=null){
                data[i][7] = (sdf.format(vouchers.get(i).getUsedDate())).toString();
            }else{
                data[i][7] = null;
            }

        }
        initializeTable(data);
    }

    private void emptyDetailPanel(){
        detailPanel.removeAll();
    }
}
