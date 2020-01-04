package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;

    private JPanel mainPanel;
    private JPanel usernameLabelPanel;
    private JPanel passwordLabelPanel;
    private JPanel usernameTextFieldPanel;
    private JPanel passwordTextFieldPanel;
    private JPanel loginButtonPanel;

    private Color panelBackgroundColor = new Color(210,234,238);

    /*private String usernameInput = null;
    private String passwordInput = null;*/

    public LoginFrame(){
        initializeComponents();
        setUpFrame();
    }

    private void setUpFrame(){
        setSize(400,220);
        setLayout(new FlowLayout());
        getContentPane().setBackground(panelBackgroundColor);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        centerFrame();
        setTitle("Voucher Management System");
        setVisible(true);
    }

    private void initializeComponents(){
        initializeLabels();
        initializeTextField();
        initializeButtons();
        initializePanels();
        addComponentsToPanels();
    }

    private void initializeLabels(){
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");

        Font font1 = new Font("SansSerif", Font.PLAIN,15);
        usernameLabel.setFont(font1);
        passwordLabel.setFont(font1);
    }

    private void initializeTextField(){
        usernameTextField = new JTextField(20);
        passwordTextField = new JPasswordField(20);

        usernameTextField.setBackground(Color.WHITE);
        passwordTextField.setBackground(Color.WHITE);

        Font font1 = new Font("SansSerif", Font.PLAIN,13);
        usernameTextField.setFont(font1);
        passwordTextField.setFont(font1);

        usernameTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        passwordTextField.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
    }

    private void initializeButtons(){
        loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(73,195,158));
        loginButton.addActionListener(this);
    }

    private void initializePanels(){
        mainPanel = new JPanel();
        usernameLabelPanel = new JPanel();
        passwordLabelPanel = new JPanel();
        usernameTextFieldPanel = new JPanel();
        passwordTextFieldPanel = new JPanel();
        loginButtonPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        mainPanel.setBackground(panelBackgroundColor);
        usernameLabelPanel.setBackground(panelBackgroundColor);
        passwordLabelPanel.setBackground(panelBackgroundColor);
        passwordTextFieldPanel.setBackground(panelBackgroundColor);
        usernameTextFieldPanel.setBackground(panelBackgroundColor);
        loginButtonPanel.setBackground(panelBackgroundColor);

        usernameLabelPanel.setBorder(new EmptyBorder(20,10,0,20));
        passwordLabelPanel.setBorder(new EmptyBorder(20,10,15,20));

        this.add(mainPanel);
        mainPanel.add(usernameLabelPanel);
        //mainPanel.add(usernameTextFieldPanel);
        mainPanel.add(passwordLabelPanel);
        //mainPanel.add(passwordTextFieldPanel);
        mainPanel.add(loginButtonPanel);
    }

    private void addComponentsToPanels(){
        usernameLabelPanel.add(usernameLabel);
        usernameLabelPanel.add(usernameTextField);
        passwordLabelPanel.add(passwordLabel);
        passwordLabelPanel.add(passwordTextField);
        loginButtonPanel.add(loginButton);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String usernameInput = usernameTextField.getText();
            String passwordInput = passwordTextField.getText();
            if(!checkForInvalidInput(usernameInput,passwordInput)){
                User user = VMS.getInstance().getUserByName(usernameInput);
                if(user == null){
                    JOptionPane.showMessageDialog(this,"Invalid username");
                }else{
                    if(passwordInput.compareTo(user.getUserPassword())==0){
                        JOptionPane.showMessageDialog(this, "Login succesful "+ user.getUserType());
                        setVisible(false);
                        if(user.getUserType() == User.UserType.ADMIN){
                            new AdminMainFrame(user);
                        }else{
                            new GuestMainFrame(user);
                        }
                    }else{
                        JOptionPane.showMessageDialog(this,"Wrong password for user " + user.getUserName()+"/n"+user.getUserPassword());
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this,"Invalid input");
            }
        }
    }

    private boolean checkForInvalidInput(String a, String b){
        if(a == null || a.isEmpty())
            return true;
        if(b == null || b.isEmpty())
            return true;
        return false;
    }
}

