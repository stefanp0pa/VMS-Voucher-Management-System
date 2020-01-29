package com.company;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.Vector;

public class GuestCampaignsFrame extends JFrame {

    private JTable campaignsTable;
    private JScrollPane campaignsScrollPane;

    private String[] campaignsTableColumnsName = {
            "Campaign ID",
            "Campaign Name",
            "Campaign Status",
            "Vouchers Count"
    };

    private Color panelBackgroundColor = new Color(210,234,238);
    private Color buttonBackgroundColor = new Color(73,195,158);

    private User user;

    public GuestCampaignsFrame(User user){
        this.user = user;
        initializeComponents();
        setUpFrame();
    }
    private void setUpFrame(){
        setSize(600,500);
        setTitle(user.getUserName() + " - Campaigns");
        centerFrame();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        initializeTable();
    }

    private void initializeTable(){
        Set<Integer> keySet = user.getReceivedVouchers().keySet();
        Vector<Integer>  campaignIds = new Vector<>();
        for(Integer i:keySet)
            campaignIds.add(i);
        Object[][] data = new Object[campaignIds.size()][campaignsTableColumnsName.length];
        for(int i = 0; i < campaignIds.size(); i++){
            data[i][0] = campaignIds.get(i);
            data[i][1] = VMS.getInstance().getCampaign(campaignIds.get(i)).getCampaignName();
            data[i][2] = user.getReceivedVouchers().get(data[i][0]).size();
            data[i][3] = VMS.getInstance().getCampaign(campaignIds.get(i)).getCampaignStatusType();
        }
        campaignsTable = new JTable(data,campaignsTableColumnsName);
        campaignsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        campaignsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        campaignsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        campaignsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        campaignsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        campaignsTable.setPreferredScrollableViewportSize(campaignsTable.getPreferredSize());

        campaignsScrollPane = new JScrollPane(campaignsTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(campaignsScrollPane);
        campaignsScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );
        this.repaint();
        this.revalidate();
    }
}
