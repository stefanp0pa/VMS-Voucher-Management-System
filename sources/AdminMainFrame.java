import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel currUsernameLabel;
    private JButton signOutButton;
    private JButton adminCampaignsButton;
    private JButton adminVouchersButton;
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

    public AdminMainFrame(User user, JFrame previousFrame){
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        optionsCampaignsPanel.add(adminCampaignsButton);
        optionsVouchersPanel.add(adminVouchersButton);
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
        adminCampaignsButton = new JButton("Administreaza campanii");
        adminVouchersButton = new JButton("Administreaza vouchere");
        exitAppButton = new JButton("Iesire aplicatie");

        signOutButton.setFocusPainted(false);
        adminCampaignsButton.setFocusPainted(false);
        adminVouchersButton.setFocusPainted(false);
        exitAppButton.setFocusPainted(false);

        signOutButton.setBackground(panelBackgroundColor);
        adminCampaignsButton.setBackground(buttonBackgroundColor);
        adminVouchersButton.setBackground(buttonBackgroundColor);
        exitAppButton.setBackground(buttonBackgroundColor);

        signOutButton.addActionListener(this);
        adminCampaignsButton.addActionListener(this);
        adminVouchersButton.addActionListener(this);
        exitAppButton.addActionListener(this);

        adminCampaignsButton.setPreferredSize(new Dimension(300,40));
        adminCampaignsButton.setFont(new Font("Dialog",Font.PLAIN,20));

        adminVouchersButton.setPreferredSize(new Dimension(300,40));
        adminVouchersButton.setFont(new Font("Dialog",Font.PLAIN,20));

        exitAppButton.setPreferredSize(new Dimension(300,40));
        exitAppButton.setFont(new Font("Dialog",Font.PLAIN,20));
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
            System.exit(0);
            return;
        }
        if(e.getSource() == adminCampaignsButton){
            this.setVisible(false);
            new CampaignsAdminFrame(user,this);
        }
        if(e.getSource() == adminVouchersButton){
            this.setVisible(false);
            new VouchersAdminFrame(user,this);
        }
    }
}
