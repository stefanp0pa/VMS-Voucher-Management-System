import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadFilesFrame extends JFrame implements ActionListener {
    private JLabel usersFileLabel;
    private JLabel campaignsFileLabel;
    private JLabel eventsFileLabel;

    private JTextField usersPathTextField;
    private JTextField campaignsPathTextField;
    private JTextField eventsPathTextField;

    private JFileChooser usersFileChooser;
    private JFileChooser campaignsFileChooser;
    private JFileChooser eventsFileChooser;

    private JButton loadButton;
    private JButton usersChooserButton;
    private JButton campaignsChooserButton;
    private JButton eventsChooserButton;

    private JPanel mainPanel;
    private JPanel usersLabelPanel;
    private JPanel usersFileChoosePanel;
    private JPanel campaignsLabelPanel;
    private JPanel campaignsFileChoosePanel;
    private JPanel eventsLabelPanel;
    private JPanel eventsFileChoosePanel;
    private JPanel loadButtonPanel;

    private File usersFile = null;
    private File campaignsFile = null;
    private File eventsFile = null;

    private String defaultChooserPath = null;

    private Color panelBackgroundColor = new Color(210,234,238);
    private Color fileChooserBackgroundColor = new Color(235,180,36);
    private Color buttonBackgroundColor = new Color(73,195,158);

    public LoadFilesFrame(){
        try {
            defaultChooserPath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeComponents();
        setUpFrame();
    }

    private void setUpFrame(){
        setSize(600,350);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(210,234,238));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        centerFrame();
        setTitle("Voucher Management System");
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        initializeLabels();
        initializeFileChoosers();
        initializeButtons();
        initializeTextFields();
        initializePanels();
        addComponentsToPanels();
    }

    private void initializePanels(){
        mainPanel = new JPanel();
        usersLabelPanel = new JPanel(new BorderLayout());
        usersFileChoosePanel = new JPanel();
        campaignsLabelPanel = new JPanel(new BorderLayout());
        campaignsFileChoosePanel = new JPanel();
        eventsLabelPanel = new JPanel(new BorderLayout());
        eventsFileChoosePanel = new JPanel();
        loadButtonPanel = new JPanel();

        usersLabelPanel.setBorder(new EmptyBorder(20,55,0,10));
        campaignsLabelPanel.setBorder(new EmptyBorder(10,55,0,10));
        eventsLabelPanel.setBorder(new EmptyBorder(10,55,0,10));
        loadButtonPanel.setBorder(new EmptyBorder(20,0,0,0));

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBackground(panelBackgroundColor);
        usersLabelPanel.setBackground(panelBackgroundColor);
        usersFileChoosePanel.setBackground(panelBackgroundColor);
        campaignsLabelPanel.setBackground(panelBackgroundColor);
        campaignsFileChoosePanel.setBackground(panelBackgroundColor);
        eventsLabelPanel.setBackground(panelBackgroundColor);
        eventsFileChoosePanel.setBackground(panelBackgroundColor);
        loadButtonPanel.setBackground(panelBackgroundColor);

        this.add(mainPanel);
        mainPanel.add(usersLabelPanel);
        mainPanel.add(usersFileChoosePanel);
        mainPanel.add(campaignsLabelPanel);
        mainPanel.add(campaignsFileChoosePanel);
        mainPanel.add(eventsLabelPanel);
        mainPanel.add(eventsFileChoosePanel);
        mainPanel.add(loadButtonPanel);
    }

    private void initializeLabels(){
        usersFileLabel = new JLabel("Users File");
        campaignsFileLabel = new JLabel("Campaigns File");
        eventsFileLabel = new JLabel("Events File");

        Font font = new Font("SansSerif", Font.PLAIN,15);
        usersFileLabel.setFont(font);
        campaignsFileLabel.setFont(font);
        eventsFileLabel.setFont(font);
    }

    private void initializeFileChoosers(){
        usersFileChooser = new JFileChooser(defaultChooserPath);
        campaignsFileChooser = new JFileChooser(defaultChooserPath);
        eventsFileChooser = new JFileChooser(defaultChooserPath);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        usersFileChooser.setFileFilter(filter);
        campaignsFileChooser.setFileFilter(filter);
        eventsFileChooser.setFileFilter(filter);
    }

    private void initializeButtons(){
        loadButton = new JButton("LOAD");
        loadButton.setFocusPainted(false);

        usersChooserButton = new JButton("...");
        usersChooserButton.setFocusPainted(false);

        campaignsChooserButton = new JButton("...");
        campaignsChooserButton.setFocusPainted(false);

        eventsChooserButton = new JButton("...");
        eventsChooserButton.setFocusPainted(false);

        usersChooserButton.setBackground(fileChooserBackgroundColor);
        campaignsChooserButton.setBackground(fileChooserBackgroundColor);
        eventsChooserButton.setBackground(fileChooserBackgroundColor);
        loadButton.setBackground(buttonBackgroundColor);

        loadButton.addActionListener(this);
        usersChooserButton.addActionListener(this);
        campaignsChooserButton.addActionListener(this);
        eventsChooserButton.addActionListener(this);
    }

    private void initializeTextFields(){
        usersPathTextField = new JTextField(40);
        //usersPathTextField.setEditable(false);
        usersPathTextField.setBackground(Color.WHITE);

        campaignsPathTextField = new JTextField(40);
        //campaignsPathTextField.setEditable(false);
        campaignsPathTextField.setBackground(Color.WHITE);

        eventsPathTextField = new JTextField(40);
        eventsPathTextField.setBackground(Color.WHITE);

        Font font = new Font("SansSerif", Font.PLAIN,13);
        usersPathTextField.setFont(font);
        campaignsPathTextField.setFont(font);
        eventsPathTextField.setFont(font);

        usersPathTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        campaignsPathTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        eventsPathTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
    }

    private void addComponentsToPanels(){
        usersLabelPanel.add(usersFileLabel);
        usersFileChoosePanel.add(usersChooserButton);
        usersFileChoosePanel.add(usersPathTextField);
        campaignsLabelPanel.add(campaignsFileLabel);
        campaignsFileChoosePanel.add(campaignsChooserButton);
        campaignsFileChoosePanel.add(campaignsPathTextField);
        eventsLabelPanel.add(eventsFileLabel);
        eventsFileChoosePanel.add(eventsChooserButton);
        eventsFileChoosePanel.add(eventsPathTextField);
        loadButtonPanel.add(loadButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       if(e.getSource() == usersChooserButton){
           chooseUsersFile();
            return;
       }
       if(e.getSource() == campaignsChooserButton){
           chooseCampaignsFile();
           return;
       }
       if(e.getSource() == eventsChooserButton){
            chooseEventsFile();
            return;
       }
       if(e.getSource() == loadButton){
           loadFiles();
           return;
       }
    }

    private void chooseUsersFile(){
        System.out.println("Choosing users file...");
        int returnValue = usersFileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = usersFileChooser.getSelectedFile();
            if(file!=null){
                this.usersFile = file;
                this.usersPathTextField.setText(this.usersFile.getAbsolutePath());
                System.out.println(usersFile.getAbsolutePath());
            }
        }else{
            System.out.println("Nothing selected as users file");
        }
    }

    private void chooseCampaignsFile(){
        System.out.println("Choosing campaigns file...");
        int returnValue = campaignsFileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = campaignsFileChooser.getSelectedFile();
            if(file!=null){
                this.campaignsFile = file;
                this.campaignsPathTextField.setText(this.campaignsFile.getAbsolutePath());
                System.out.println(campaignsFile.getAbsolutePath());
            }
        }else{
            System.out.println("Nothing selected as campaigns file");
        }
    }

    private void chooseEventsFile(){
        System.out.println("Choosing events file...");
        int returnValue = eventsFileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = eventsFileChooser.getSelectedFile();
            if(file!=null){
                this.eventsFile = file;
                this.eventsPathTextField.setText(this.eventsFile.getAbsolutePath());
                System.out.println(eventsFile.getAbsolutePath());
            }
        }else{
            System.out.println("Nothing selected as events file");
        }
    }

    private void loadFiles(){
        System.out.println("Loading files..");
        if(usersFile!=null && campaignsFile!=null){
            JOptionPane.showMessageDialog(this,"Succesfully loaded");
            parseFiles();
            this.setVisible(false);
            new LoginFrame();
        }else{
            JOptionPane.showMessageDialog(this,"Did not load all necessary files");
        }
    }

    private void parseFiles(){
        try{
            InputParser.parseUsersInput(usersFile.getAbsolutePath());
            InputParser.parseCampaignInput(campaignsFile.getAbsolutePath());
            InputParser.parseEventsInput(eventsFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
