import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class VouchersAdminFrame extends JFrame implements ActionListener {

    private User user;
    private JFrame previousFrame;

    private JButton backButton;
    private JButton listVouchersButton;
    private JButton addVoucherButton;
    private JButton searchForVoucherButton;

    private JButton addButton;
    private JButton searchButton;
    private JButton markButton;

    private JTextField voucherIdTextField;
    private JTextField voucherCodeTextField;
    private JTextField voucherCampaignIdTextField;
    private JTextField voucherEmailTextField;
    private JTextField voucherTypeTextField;
    private JTextField voucherValueTextField;

    private JLabel voucherIdLabel;
    private JLabel voucherCodeLabel;
    private JLabel voucherCampaignIdLabel;
    private JLabel voucherEmailLabel;
    private JLabel voucherTypeLabel;
    private JLabel voucherValueLabel;

    private JLabel voucherFoundLabel;

    private JPanel masterPanel;
    private JPanel detailPanel;

    private JPanel backButtonPanel;
    private JPanel listVouchersPanel;
    private JPanel addVoucherPanel;
    private JPanel searchForVoucherPanel;

    private JPanel voucherIdPanel;
    private JPanel voucherCodePanel;
    private JPanel voucherCampaignIdPanel;
    private JPanel voucherEmailPanel;
    private JPanel voucherTypePanel;
    private JPanel voucherValuePanel;

    private JPanel voucherFoundPanel;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        setTitle("Voucher Management System - Vouchers Panel - " + sdf.format(VMS.getInstance().getApplicationStartDate()));
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
        initializeLabels();
        initializeTextFields();
        initializePanels();
    }

    private void initializeLabels(){
        voucherIdLabel = new JLabel("Voucher ID: ");
        voucherCodeLabel = new JLabel("Voucher Code: ");
        voucherEmailLabel = new JLabel("Voucher email: ");
        voucherCampaignIdLabel = new JLabel("Campaign ID: ");
        voucherTypeLabel = new JLabel("Voucher type: ");
        voucherValueLabel = new JLabel("Voucher value: ");

        voucherFoundLabel = new JLabel();

        Font font = new Font("SansSerif", Font.PLAIN,15);
        voucherIdLabel.setFont(font);
        voucherCodeLabel.setFont(font);
        voucherEmailLabel.setFont(font);
        voucherCampaignIdLabel.setFont(font);
        voucherTypeLabel.setFont(font);
        voucherValueLabel.setFont(font);
        voucherFoundLabel.setFont(font);
    }

    private void initializeTextFields(){
        voucherIdTextField = new JTextField(3);
        voucherCodeTextField = new JTextField(10);
        voucherCampaignIdTextField = new JTextField(3);
        voucherEmailTextField = new JTextField(20);
        voucherTypeTextField = new JTextField(10);
        voucherValueTextField = new JTextField(7);

        voucherIdTextField.setBackground(Color.WHITE);
        voucherCodeTextField.setBackground(Color.WHITE);
        voucherCampaignIdTextField.setBackground(Color.WHITE);
        voucherEmailTextField.setBackground(Color.WHITE);
        voucherTypeTextField.setBackground(Color.WHITE);
        voucherValueTextField.setBackground(Color.WHITE);

        Font font = new Font("SansSerif", Font.PLAIN,13);
        voucherIdTextField.setFont(font);
        voucherCodeTextField.setFont(font);
        voucherCampaignIdTextField.setFont(font);
        voucherEmailTextField.setFont(font);
        voucherTypeTextField.setFont(font);
        voucherValueTextField.setFont(font);

        voucherIdTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        voucherCodeTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        voucherCampaignIdTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        voucherEmailTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        voucherTypeTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        voucherValueTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));

    }

    private void initializeButtons(){
        initializeMasterButtons();
        initializeDetailButtons();
    }
    private void initializeMasterButtons(){
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
    private void initializeDetailButtons(){
        addButton = new JButton("ADD!");
        searchButton = new JButton("SEARCH!");
        markButton = new JButton("MARK!");

        addButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);
        markButton.setFocusPainted(false);

        addButton.setBackground(buttonBackgroundColor);
        searchButton.setBackground(buttonBackgroundColor);
        markButton.setBackground(buttonBackgroundColor);

        addButton.addActionListener(this);
        searchButton.addActionListener(this);
        markButton.addActionListener(this);

        /*Font font = new Font("SansSerif",Font.PLAIN,15);
        addButton.setFont(font);
        searchButton.setFont(font);
        markButton.setFont(font);*/
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
        initializeDetailPanels();
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
    private void initializeDetailPanels(){
        voucherIdPanel = new JPanel();
        voucherCodePanel = new JPanel();
        voucherCampaignIdPanel = new JPanel();
        voucherEmailPanel  = new JPanel();
        voucherTypePanel = new JPanel();
        voucherValuePanel = new JPanel();

        voucherFoundPanel = new JPanel();
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
        vouchersTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        vouchersTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(4).setPreferredWidth(120);
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
            addVoucherButtonAction();
            return;
        }
        if(e.getSource() == addButton){
            addAction();
        }


        if(e.getSource() == searchForVoucherButton){
            searchForVoucherButtonAction();
            return;
        }
        if(e.getSource() == searchButton){
            searchAction();
            return;
        }
        if(e.getSource() == markButton){
            markAction();
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
        emptyPanels();
        resetTextFields();
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

    private void addVoucherButtonAction(){
        emptyPanels();
        resetTextFields();
        detailPanel.setLayout(new BoxLayout(detailPanel,BoxLayout.Y_AXIS));
        detailPanel.add(voucherEmailPanel);
        detailPanel.add(voucherCampaignIdPanel);
        detailPanel.add(voucherTypePanel);
        detailPanel.add(voucherValuePanel);

        voucherCampaignIdPanel.setLayout(new FlowLayout());
        voucherEmailPanel.setLayout(new FlowLayout());
        voucherTypePanel.setLayout(new FlowLayout());
        voucherValuePanel.setLayout(new FlowLayout());

        voucherCampaignIdPanel.add(voucherCampaignIdLabel);
        voucherCampaignIdPanel.add(voucherCampaignIdTextField);

        voucherEmailPanel.add(voucherEmailLabel);
        voucherEmailPanel.add(voucherEmailTextField);
        voucherEmailPanel.add(addButton);

        voucherTypePanel.add(voucherTypeLabel);
        voucherTypePanel.add(voucherTypeTextField);

        voucherValuePanel.add(voucherValueLabel);
        voucherValuePanel.add(voucherValueTextField);

        repaint();
        revalidate();

    }
    private void addAction(){
        if(checkForEmptyAddTextFields()){
            JOptionPane.showMessageDialog(this,"One or more fields are empty");
            return;
        }
        String campaignId = voucherCampaignIdTextField.getText();

        Campaign campaign = VMS.getInstance().getCampaign(Integer.parseInt(campaignId));
        if(campaign == null){
            JOptionPane.showMessageDialog(this,"Attemped to add voucher for invalid campaign");
            return;
        }

        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.CANCELLED){
            JOptionPane.showMessageDialog(this,"Cannot add voucher for cancelled campaign");
            return;
        }

        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.EXPIRED){
            JOptionPane.showMessageDialog(this,"Cannot add voucher for expired campaign");
            return;
        }

        if(campaign.getAvailableVouchersCount() <= 0){
            JOptionPane.showMessageDialog(this,"No more available vouchers");
            return;
        }

        String newVoucherType = voucherTypeTextField.getText();
        String newVoucherEmail = voucherEmailTextField.getText();
        String newVoucherValue = voucherValueTextField.getText();

        User user = VMS.getInstance().getUserByEmail(newVoucherEmail);
        if(user == null){
            JOptionPane.showMessageDialog(this,"No user with such email");
            return;
        }

        if(newVoucherType.equals("GiftVoucher")){
            boolean result = campaign.generateVoucher(
                    newVoucherEmail,
                    "GiftVoucher",
                    Float.parseFloat(newVoucherValue));
            if(result){
                JOptionPane.showMessageDialog(this,"S-a generat un GiftVoucher cu succes!");
            }else{
                JOptionPane.showMessageDialog(this,"Nu s-a generat acest GiftVoucher");
            }
            return;
        }
        if(newVoucherType.equals("LoyaltyVoucher")){
            boolean result = campaign.generateVoucher(
                    newVoucherEmail,
                    "LoyaltyVoucher",
                    Float.parseFloat(newVoucherValue));
            if(result){
                JOptionPane.showMessageDialog(this,"S-a generat un LoyaltyVoucher cu succes!");
            }else{
                JOptionPane.showMessageDialog(this,"Nu s-a generat acest LoyaltyVoucher");
            }
            return;
        }
        JOptionPane.showMessageDialog(this,"Select a valid type for voucher");

    }
    private boolean checkForEmptyAddTextFields(){
        String newVoucherCampaignId = voucherCampaignIdTextField.getText();
        String newVoucherEmail = voucherEmailTextField.getText();
        String newVoucherValue = voucherValueTextField.getText();
        String newVoucherType = voucherTypeTextField.getText();

        if(newVoucherCampaignId == null || newVoucherCampaignId.isEmpty())
            return true;
        if(newVoucherEmail == null || newVoucherEmail.isEmpty())
            return true;
        if(newVoucherType == null || newVoucherType.isEmpty())
            return true;
        if(newVoucherValue == null || newVoucherValue.isEmpty())
            return true;

        return false;
    }

    private void searchForVoucherButtonAction(){
        emptyPanels();
        resetTextFields();
        voucherFoundLabel.setText(null);
        detailPanel.setLayout(new BoxLayout(detailPanel,BoxLayout.Y_AXIS));
        detailPanel.add(voucherCodePanel);
        detailPanel.add(voucherFoundPanel);

        voucherCodePanel.setLayout(new FlowLayout());
        voucherFoundPanel.setLayout(new FlowLayout());

        voucherCodePanel.add(voucherCodeLabel);
        voucherCodePanel.add(voucherCodeTextField);
        voucherCodePanel.add(searchButton);

        voucherFoundPanel.add(voucherFoundLabel);
        voucherFoundPanel.add(markButton);

        markButton.setVisible(false);
    }
    private void searchAction(){
        String code = voucherCodeTextField.getText();
        if(code == null || code.isEmpty()){
            JOptionPane.showMessageDialog(this,"Provide a voucher code");
            return;
        }
        Voucher v = VMS.getInstance().getVoucherByCode(code);

        if(v == null){
            JOptionPane.showMessageDialog(this,"There is no voucher with code " + code);
            return;
        }

        if(v.getVoucherStatusType() == Voucher.VoucherStatusType.USED){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            StringBuilder sb = new StringBuilder();
            sb.append("Code: " + v.getVoucherCode() + " | ");
            sb.append("Email: " + v.getEmail() + " | ");
            sb.append("CampaignId: " + v.getCampaignId() + " | ");
            sb.append("Already MARKED on: " + sdf.format(v.getUsedDate()));
            voucherFoundLabel.setText(sb.toString());
            return;
        }
        if(v.getVoucherStatusType() == Voucher.VoucherStatusType.UNUSED){
            StringBuilder sb = new StringBuilder();
            sb.append("Code: " + v.getVoucherCode() + " | ");
            sb.append("Email: " + v.getEmail() + " | ");
            sb.append("CampaignId: " + v.getCampaignId());
            voucherFoundLabel.setText(sb.toString());
            markButton.setVisible(true);
            return;
        }

    }
    private void markAction(){
        String code = voucherCodeTextField.getText();
        Voucher v = VMS.getInstance().getVoucherByCode(code);
        if(v == null){
            JOptionPane.showMessageDialog(this, "Error when pressed mark");
            return;
        }
        Campaign campaign = VMS.getInstance().getCampaign(v.getCampaignId());

        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.EXPIRED){
            JOptionPane.showMessageDialog(this,"Voucher campaign EXPIRED");
            markButton.setEnabled(false);
            return;
        }
        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.CANCELLED){
            JOptionPane.showMessageDialog(this,"Voucher campaign CANCELLED");
            markButton.setEnabled(false);
            return;
        }
        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.NEW){
            JOptionPane.showMessageDialog(this,"Voucher campaign NEW");
            markButton.setEnabled(false);
            return;
        }
        System.out.println(VMS.getInstance().getApplicationStartDate());
        Date appDate = VMS.getInstance().getApplicationStartDate();
        boolean result = campaign.redeemVoucher(code,VMS.convertDateToLocalDateTime(appDate));
        if(result){
            JOptionPane.showMessageDialog(this,"Marked voucher as USED");
            markButton.setEnabled(false);
            return;
        }else{
            JOptionPane.showMessageDialog(this,"Unable to mark voucher as USED");
            return;
        }
    }

    private void emptyPanels() {
        detailPanel.removeAll();
        voucherIdPanel.removeAll();
        voucherCampaignIdLabel.removeAll();
        voucherCodePanel.removeAll();
        voucherEmailPanel.removeAll();
        voucherTypePanel.removeAll();
        voucherValuePanel.removeAll();
    }
    private void resetTextFields(){
        voucherIdTextField.setText(null);
        voucherCodeTextField.setText(null);
        voucherCampaignIdTextField.setText(null);
        voucherEmailTextField.setText(null);
        voucherTypeTextField.setText(null);
        voucherValueTextField.setText(null);
    }

}
