import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class CampaignsAdminFrame extends JFrame implements ActionListener {

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
    private JScrollPane campaignScrollPane;

    private JButton detailButton;
    private JButton cancelButton;
    private JButton editButton;
    private JButton addButton;

    private JLabel campaignIdLabel;
    private JLabel campaignNameLabel;
    private JLabel campaignDescriptionLabel;
    private JLabel campaignStartDateLabel;
    private JLabel campaignEndDateLabel;
    private JLabel campaignTotalLabel;
    private JLabel campaignAvailableLabel;
    private JLabel campaignStrategyLabel;
    private JLabel campaignStatusLabel;

    private JTextField campaignIdTextField;
    private JTextField campaignNameTextField;
    private JTextField campaignDescriptionTextField;
    private JTextField campaignStartDateTextField;
    private JTextField campaignEndDateTextField;
    private JTextField campaignTotalTextField;
    private JTextField campaignAvailableTextField;
    private JTextField campaignStrategyTextField;
    private JTextField campaignStatusTextField;

    private JPanel campaignIdPanel;
    private JPanel campaignNamePanel;
    private JPanel campaignDescriptionPanel;
    private JPanel campaignStartDatePanel;
    private JPanel campaignEndDatePanel;
    private JPanel campaignTotalPanel;
    private JPanel campaignAvailablePanel;
    private JPanel campaignStrategyPanel;
    private JPanel campaignStatusPanel;


    private User user;
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
            "Status",
            "Total",
            "Avail."
    };

    public CampaignsAdminFrame(User user,JFrame previousFrame){
        this.user = user;
        this.previousFrame = previousFrame;
        initializeComponents();
        setUpFrame();
    }

    private void setUpFrame(){
        setSize(1200,500);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        setTitle("Voucher Management System - Campaigns Panel - " + sdf.format(VMS.getInstance().getApplicationStartDate()));
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
        //listCampaignsButtonAction();
    }

    private void initializeTextField(){
        campaignIdTextField = new JTextField(3);
        campaignNameTextField = new JTextField(10);
        campaignDescriptionTextField = new JTextField(20);
        campaignStartDateTextField = new JTextField(15);
        campaignEndDateTextField = new JTextField(15);
        campaignTotalTextField = new JTextField(5);
        campaignAvailableTextField = new JTextField(5);
        campaignStrategyTextField = new JTextField(3);
        campaignStatusTextField = new JTextField(8);

        campaignIdTextField.setBackground(Color.WHITE);
        campaignNameTextField.setBackground(Color.WHITE);
        campaignDescriptionTextField.setBackground(Color.WHITE);
        campaignStartDateTextField.setBackground(Color.WHITE);
        campaignEndDateTextField.setBackground(Color.WHITE);
        campaignTotalTextField.setBackground(Color.WHITE);
        campaignAvailableTextField.setBackground(Color.WHITE);
        campaignStrategyTextField.setBackground(Color.WHITE);
        campaignStatusTextField.setBackground(Color.WHITE);

        Font font = new Font("SansSerif", Font.PLAIN,13);
        campaignIdTextField.setFont(font);
        campaignNameTextField.setFont(font);
        campaignDescriptionTextField.setFont(font);
        campaignStartDateTextField.setFont(font);
        campaignEndDateTextField.setFont(font);
        campaignTotalTextField.setFont(font);
        campaignAvailableTextField.setFont(font);
        campaignStrategyTextField.setFont(font);
        campaignStatusTextField.setFont(font);

        campaignIdTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignNameTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignDescriptionTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignStartDateTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignEndDateTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignTotalTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignAvailableTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignStrategyTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignStatusTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
    }

    private void initializeLabels(){
        campaignIdLabel = new JLabel("ID: ");
        campaignNameLabel = new JLabel("Campaign Name: ");
        campaignDescriptionLabel = new JLabel("Campaign Description: ");
        campaignStartDateLabel = new JLabel("Start Date: ");
        campaignEndDateLabel = new JLabel("End Date: ");
        campaignTotalLabel = new JLabel("Total Vouchers: ");
        campaignAvailableLabel = new JLabel("Available Vouchers: ");
        campaignStrategyLabel = new JLabel("Strategy: ");
        campaignStatusLabel = new JLabel("Campaign Status: ");

        Font font = new Font("SansSerif", Font.PLAIN,15);
        campaignIdLabel.setFont(font);
        campaignNameLabel.setFont(font);
        campaignDescriptionLabel.setFont(font);
        campaignStartDateLabel.setFont(font);
        campaignEndDateLabel.setFont(font);
        campaignTotalLabel.setFont(font);
        campaignAvailableLabel.setFont(font);
        campaignStrategyLabel.setFont(font);
        campaignStatusLabel.setFont(font);
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
        campaignsTable.getColumnModel().getColumn(6).setPreferredWidth(80);
        campaignsTable.getColumnModel().getColumn(7).setPreferredWidth(60);
        campaignsTable.getColumnModel().getColumn(8).setPreferredWidth(60);
        campaignsTable.setPreferredScrollableViewportSize(campaignsTable.getPreferredSize());

        campaignScrollPane = new JScrollPane(campaignsTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        detailPanel.add(campaignScrollPane);
        campaignScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );

        detailPanel.repaint();
        detailPanel.revalidate();
    }

    private void initializeButtons(){
        initializeMasterButtons();
        initializeDetailButtons();
    }
    private void initializeMasterButtons(){
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

        Font font = new Font("SansSerif",Font.PLAIN,15);
        backButton.setFont(font);
        listCampaignsButton.setFont(font);
        addCampaignButton.setFont(font);
        updateCampaignButton.setFont(font);
        cancelCampaignButton.setFont(font);
        detailCampaignButton.setFont(font);
    }
    private void initializeDetailButtons(){
        detailButton = new JButton("Get Details");
        cancelButton = new JButton("Cancel Campaign");
        editButton = new JButton("Edit Campaign");
        addButton = new JButton("Add Campaign");

        detailButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);
        editButton.setFocusPainted(false);
        addButton.setFocusPainted(false);

        detailButton.setBackground(buttonBackgroundColor);
        cancelButton.setBackground(buttonBackgroundColor);
        editButton.setBackground(buttonBackgroundColor);
        addButton.setBackground(buttonBackgroundColor);

        detailButton.addActionListener(this);
        cancelButton.addActionListener(this);
        editButton.addActionListener(this);
        addButton.addActionListener(this);
    }


    private void initializePanels() {
        masterPanel = new JPanel();
        detailPanel = new JPanel();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

        masterPanel.setPreferredSize(new Dimension(300, 350));
        detailPanel.setPreferredSize(new Dimension(850, 450));

        getContentPane().setBackground(panelBackgroundColor);
        masterPanel.setBackground(panelBackgroundColor);
        //detailPanel.setBackground(Color.BLUE);

        this.add(masterPanel);
        this.add(detailPanel);

        initializeMasterPanels();
        initializeDetailPanels();
    }
    private void initializeMasterPanels(){
        backButtonPanel = new JPanel();
        listCampaignsPanel = new JPanel();
        addCampaignsPanel = new JPanel();
        updateCampaignPanel = new JPanel();
        cancelCampaignPanel = new JPanel();
        detailCampaignPanel = new JPanel();
        sortButtonsPanel = new JPanel();

        backButtonPanel.add(backButton);
        listCampaignsPanel.add(listCampaignsButton);
        addCampaignsPanel.add(addCampaignButton);
        updateCampaignPanel.add(updateCampaignButton);
        cancelCampaignPanel.add(cancelCampaignButton);
        detailCampaignPanel.add(detailCampaignButton);

        backButtonPanel.setBackground(panelBackgroundColor);
        listCampaignsPanel.setBackground(panelBackgroundColor);
        addCampaignsPanel.setBackground(panelBackgroundColor);
        updateCampaignPanel.setBackground(panelBackgroundColor);
        cancelCampaignPanel.setBackground(panelBackgroundColor);
        detailCampaignPanel.setBackground(panelBackgroundColor);

        masterPanel.add(backButtonPanel);
        masterPanel.add(listCampaignsPanel);
        masterPanel.add(addCampaignsPanel);
        masterPanel.add(updateCampaignPanel);
        masterPanel.add(cancelCampaignPanel);
        masterPanel.add(detailCampaignPanel);
    }
    private void initializeDetailPanels(){
        campaignIdPanel = new JPanel();
        campaignNamePanel = new JPanel();
        campaignDescriptionPanel = new JPanel();
        campaignStartDatePanel = new JPanel();
        campaignEndDatePanel = new JPanel();
        campaignTotalPanel = new JPanel();
        campaignAvailablePanel = new JPanel();
        campaignStrategyPanel = new JPanel();
        campaignStatusPanel = new JPanel();
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

        if(e.getSource() == addCampaignButton){
            addCampaignButtonAction();
            return;
        }
        if(e.getSource() == addButton){
            addAction();
            return;
        }


        //update buttons
        if(e.getSource()==updateCampaignButton){
            updateCampaignButtonAction();
            return;
        }
        if(e.getSource()==editButton){
            editAction();
            return;
        }

        //detail buttons
        if(e.getSource()==detailCampaignButton){
            detailCampaignButtonAction();
            return;
        }
        if(e.getSource()==detailButton){
            detailAction();
            return;
        }

        //cancel buttons
        if(e.getSource()==cancelCampaignButton){
            cancelCampaignButtonAction();
            return;
        }
        if(e.getSource() == cancelButton){
            cancelAction();
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
        emptyPanels();
        Vector<Campaign> campaigns = VMS.getInstance().getCampaigns();
        Object[][] data = new Object[campaigns.size()][columnNames.length];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(int i = 0; i < campaigns.size(); i++){
            data[i][0] = campaigns.get(i).getCampaignId();
            data[i][1] = campaigns.get(i).getCampaignName();
            data[i][2] = campaigns.get(i).getCampaignDescription();
            data[i][3] = (sdf.format(campaigns.get(i).getStartDate()));
            data[i][4] = (sdf.format(campaigns.get(i).getEndDate()));
            data[i][5] = campaigns.get(i).getStrategyType().strategyName;
            data[i][6] = campaigns.get(i).getCampaignStatusType();
            data[i][7] = campaigns.get(i).getTotalVouchersCount();
            data[i][8] = campaigns.get(i).getAvailableVouchersCount();
        }
        initializeTable(data);
    }

    private void addCampaignButtonAction(){
        emptyPanels();
        resetTextFields();
        detailPanel.setLayout(new BoxLayout(detailPanel,BoxLayout.Y_AXIS));
        detailPanel.add(campaignNamePanel);
        detailPanel.add(campaignDescriptionPanel);
        detailPanel.add(campaignStartDatePanel);
        detailPanel.add(campaignEndDatePanel);
        detailPanel.add(campaignTotalPanel);
        detailPanel.add(campaignStrategyPanel);

        campaignNamePanel.setLayout(new FlowLayout());
        campaignNamePanel.add(campaignNameLabel);
        campaignNamePanel.add(campaignNameTextField);
        campaignNamePanel.add(addButton);

        campaignDescriptionPanel.setLayout(new FlowLayout());
        campaignDescriptionPanel.add(campaignDescriptionLabel);
        campaignDescriptionPanel.add(campaignDescriptionTextField);

        campaignStartDatePanel.setLayout(new FlowLayout());
        campaignStartDatePanel.add(campaignStartDateLabel);
        campaignStartDatePanel.add(campaignStartDateTextField);
        campaignStartDateTextField.setText("yyyy-MM-dd HH:mm");

        campaignEndDatePanel.setLayout(new FlowLayout());
        campaignEndDatePanel.add(campaignEndDateLabel);
        campaignEndDatePanel.add(campaignEndDateTextField);
        campaignEndDateTextField.setText("yyyy-MM-dd HH:mm");

        campaignTotalPanel.setLayout(new FlowLayout());
        campaignTotalPanel.add(campaignTotalLabel);
        campaignTotalPanel.add(campaignTotalTextField);

        campaignStrategyPanel.setLayout(new FlowLayout());
        campaignStrategyPanel.add(campaignStrategyLabel);
        campaignStrategyPanel.add(campaignStrategyTextField);

        repaint();
        revalidate();
    }
    private void addAction(){
        if(checkEmptyTextFieldsAdd()){
            JOptionPane.showMessageDialog(this,"One or more textfields are empty");
            return;
        }
        String newCampaignName = campaignNameTextField.getText();
        Campaign campaign = VMS.getInstance().getCampaignName(newCampaignName);
        if(campaign!=null){
            JOptionPane.showMessageDialog(this,"Already a campaign with this name");
            return;
        }
        String newCampaignDescription = campaignDescriptionTextField.getText();
        Date newStartDate = null;
        Date newEndDate = null;
        try {
            newStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(campaignStartDateTextField.getText());
            newEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(campaignEndDateTextField.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error date input format");
            return;
        }

        if(newStartDate.after(newEndDate)){
            JOptionPane.showMessageDialog(this,"Start date cannot be after end date");
            return;
        }

        String newCampaignTotal = campaignTotalTextField.getText();
        String newCampaignStrategy = campaignStrategyTextField.getText();
        VMS.getInstance().addCampaign(new Campaign(
                VMS.generateNewCampaignId(),
                newCampaignName,
                newCampaignDescription,
                newStartDate,
                newEndDate,
                Integer.parseInt(newCampaignTotal),
                Campaign.decideStrategyType(newCampaignStrategy)
        ));
        JOptionPane.showMessageDialog(this,"Campaign succesfully added!");
    }
    private boolean checkEmptyTextFieldsAdd(){
        String newName = campaignNameTextField.getText();
        String newDescription = campaignDescriptionTextField.getText();
        String newStartDate = campaignStartDateTextField.getText();
        String newEndDate = campaignEndDateTextField.getText();
        String newStrategy = campaignStrategyTextField.getText();
        String newTotal = campaignTotalTextField.getText();

        if(newName == null || newName.isEmpty())
            return true;
        if(newDescription == null || newDescription.isEmpty())
            return true;
        if(newStartDate == null || newStartDate.isEmpty())
            return true;
        if(newEndDate == null || newEndDate.isEmpty())
            return true;
        if(newStrategy == null || newStrategy.isEmpty())
            return true;
        if(newTotal == null || newTotal.isEmpty())
            return true;

        return false;
    }

    // edit methods
    private void updateCampaignButtonAction(){
        emptyPanels();
        resetTextFields();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.add(campaignIdPanel);
        detailPanel.add(campaignNamePanel);
        detailPanel.add(campaignDescriptionPanel);
        detailPanel.add(campaignStartDatePanel);
        detailPanel.add(campaignEndDatePanel);
        detailPanel.add(campaignTotalPanel);

        campaignIdPanel.setLayout(new FlowLayout());
        campaignIdPanel.add(campaignIdLabel);
        campaignIdPanel.add(campaignIdTextField);
        campaignIdPanel.add(editButton);

        campaignNamePanel.setLayout(new FlowLayout());
        campaignNamePanel.add(campaignNameLabel);
        campaignNamePanel.add(campaignNameTextField);

        campaignDescriptionPanel.setLayout(new FlowLayout());
        campaignDescriptionPanel.add(campaignDescriptionLabel);
        campaignDescriptionPanel.add(campaignDescriptionTextField);

        campaignStartDatePanel.setLayout(new FlowLayout());
        campaignStartDatePanel.add(campaignStartDateLabel);
        campaignStartDatePanel.add(campaignStartDateTextField);
        campaignStartDateTextField.setText("yyyy-MM-dd HH:mm");

        campaignEndDatePanel.setLayout(new FlowLayout());
        campaignEndDatePanel.add(campaignEndDateLabel);
        campaignEndDatePanel.add(campaignEndDateTextField);
        campaignEndDateTextField.setText("yyyy-MM-dd HH:mm");

        campaignTotalPanel.setLayout(new FlowLayout());
        campaignTotalPanel.add(campaignTotalLabel);
        campaignTotalPanel.add(campaignTotalTextField);

        detailPanel.repaint();
        detailPanel.revalidate();
    }
    private void editAction(){
        /*if(checkEmptyTextFieldsEdit()){
            JOptionPane.showMessageDialog(this,"One or more textfields are empty");
            return;
        }*/

        String campaignId = campaignIdTextField.getText();

        if(campaignId == null || campaignId.isEmpty()){
            JOptionPane.showMessageDialog(this,"Provide an ID");
            return;
        }

        Campaign campaign = VMS.getInstance().getCampaign(Integer.parseInt(campaignId));
        if(campaign == null){
            JOptionPane.showMessageDialog(this,"No campaign with such ID");
            return;
        }

        String campaignName = campaignNameTextField.getText();
        if(campaignName == null || campaignName.isEmpty()){
            campaignName = campaign.getCampaignName();
        }

        String campaignDescription = campaignDescriptionTextField.getText();
        if(campaignDescription == null || campaignDescription.isEmpty()){
            campaignDescription = campaign.getCampaignDescription();
        }

        Campaign c = VMS.getInstance().getCampaignName(campaignName);
        if(c!=null && !c.equals(campaign)){
            JOptionPane.showMessageDialog(this,"Already a campaign with this name");
            return;
        }

        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.CANCELLED){
            JOptionPane.showMessageDialog(this,"Cannot update a cancelled campaign");
            return;
        }
        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.EXPIRED){
            JOptionPane.showMessageDialog(this,"Cannot update an expired campaign");
            return;
        }
        Date campaignStartDate = null;
        Date campaignEndDate = null;
        try {
            campaignStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .parse(campaignStartDateTextField.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            campaignStartDate = campaign.getStartDate();
        }

        try{
            campaignEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .parse(campaignEndDateTextField.getText());
        }catch(ParseException e){
            e.printStackTrace();
            campaignEndDate = campaign.getEndDate();
        }


        if(campaignStartDate.after(campaignEndDate)){
            JOptionPane.showMessageDialog(this,"Start date cannot be after end date");
            return;
        }

        String campaignTotal = campaignTotalTextField.getText();
        if(campaignTotal == null || campaignTotal.isEmpty()){
            campaignTotal = String.valueOf(campaign.getTotalVouchersCount());
        }else{
            int currentTotal = campaign.getTotalVouchersCount();
            int currentAvail = campaign.getAvailableVouchersCount();
            if(Integer.parseInt(campaignTotal) < (currentTotal-currentAvail)){
                JOptionPane.showMessageDialog(this,"Total cannot be lower than the given count");
                return;
            }
        }


        boolean result = VMS.getInstance().updateCampaign(Integer.parseInt(campaignId),
                new Campaign(
                   campaign.getCampaignId(),
                   campaignName,
                        campaignDescription,
                        campaignStartDate,
                        campaignEndDate,
                        Integer.parseInt(campaignTotal),
                        campaign.getStrategyType()
                ));

        if(result){
            JOptionPane.showMessageDialog(this,"Campaign succesfully updated");
            return;
        }else{
            JOptionPane.showMessageDialog(this,"Could not update campaign");
            return;
        }
    }
    public boolean checkEmptyTextFieldsEdit(){
        String campaignId = campaignIdTextField.getText();
        String campaignName = campaignNameTextField.getText();
        String campaignDescription = campaignDescriptionTextField.getText();
        String campaignStartDate = campaignStartDateTextField.getText();
        String campaignEndDate = campaignEndDateTextField.getText();
        String campaignTotal = campaignTotalTextField.getText();

        if(campaignId == null || campaignId.isEmpty())
            return true;
        if(campaignDescription == null || campaignDescription.isEmpty())
            return true;
        if(campaignName == null || campaignName.isEmpty())
            return true;
        if(campaignStartDate == null || campaignStartDate.isEmpty())
            return true;
        if(campaignEndDate == null || campaignEndDate.isEmpty())
            return true;
        if(campaignTotal == null || campaignTotal.isEmpty())
            return true;
        return false;
    }

    // detail methods
    private void detailCampaignButtonAction(){
        emptyPanels();
        resetTextFields();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.add(campaignIdPanel);
        detailPanel.add(campaignNamePanel);
        detailPanel.add(campaignDescriptionPanel);
        detailPanel.add(campaignStartDatePanel);
        detailPanel.add(campaignEndDatePanel);
        detailPanel.add(campaignTotalPanel);
        detailPanel.add(campaignAvailablePanel);
        detailPanel.add(campaignStrategyPanel);
        detailPanel.add(campaignStatusPanel);

        campaignIdPanel.setLayout(new FlowLayout());
        campaignIdPanel.add(campaignIdLabel);
        campaignIdPanel.add(campaignIdTextField);
        campaignIdPanel.add(detailButton);

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

        campaignTotalPanel.setLayout(new FlowLayout());
        campaignTotalPanel.add(campaignTotalLabel);
        campaignTotalPanel.add(campaignTotalTextField);

        campaignAvailablePanel.setLayout(new FlowLayout());
        campaignAvailablePanel.add(campaignAvailableLabel);
        campaignAvailablePanel.add(campaignAvailableTextField);

        campaignStrategyPanel.setLayout(new FlowLayout());
        campaignStrategyPanel.add(campaignStrategyLabel);
        campaignStrategyPanel.add(campaignStrategyTextField);

        campaignStatusPanel.setLayout(new FlowLayout());
        campaignStatusPanel.add(campaignStatusLabel);
        campaignStatusPanel.add(campaignStatusTextField);

        detailPanel.repaint();
        detailPanel.revalidate();
    }
    private void detailAction(){
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        campaignNameTextField.setText(campaign.getCampaignName());
        campaignDescriptionTextField.setText(campaign.getCampaignDescription());
        campaignStartDateTextField.setText(sdf.format(campaign.getStartDate()).toString());
        campaignEndDateTextField.setText(sdf.format(campaign.getEndDate()).toString());
        campaignStrategyTextField.setText(campaign.getStrategyType().strategyName);
        campaignTotalTextField.setText(campaign.getTotalVouchersCount().toString());
        campaignAvailableTextField.setText(campaign.getAvailableVouchersCount().toString());
        campaignStatusTextField.setText(campaign.getCampaignStatusType().toString());
    }


    // cancel methods
    private void cancelCampaignButtonAction(){
        emptyPanels();
        resetTextFields();
        detailPanel.setLayout(new FlowLayout());
        detailPanel.add(campaignIdLabel);
        detailPanel.add(campaignIdTextField);
        detailPanel.add(cancelButton);

        revalidate();
        repaint();
    }
    private void cancelAction(){
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
        if(user.getUserType() == User.UserType.ADMIN){
            VMS.getInstance().cancelCampaign(Integer.parseInt(id));
            JOptionPane.showMessageDialog(this,"Campaign succesfully cancelled");
            cancelButton.setEnabled(false);
            return;
        }
        JOptionPane.showMessageDialog(this,"Only ADMIN can cancel campaign");
    }


    // procedure methods
    private void emptyPanels(){
       detailPanel.removeAll();
       campaignIdPanel.removeAll();
       campaignNamePanel.removeAll();
       campaignDescriptionPanel.removeAll();
       campaignStatusPanel.removeAll();
       campaignStartDatePanel.removeAll();
       campaignEndDatePanel.removeAll();
       campaignTotalPanel.removeAll();
       campaignStrategyPanel.removeAll();
    }
    private void resetTextFields(){
        campaignIdTextField.setText(null);
        campaignNameTextField.setText(null);
        campaignDescriptionTextField.setText(null);
        campaignStartDateTextField.setText(null);
        campaignEndDateTextField.setText(null);
        campaignTotalTextField.setText(null);
        campaignAvailableTextField.setText(null);
        campaignStrategyTextField.setText(null);
        campaignStatusTextField.setText(null);
    }
}
