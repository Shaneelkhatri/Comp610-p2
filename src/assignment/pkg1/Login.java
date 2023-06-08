package assignment.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JLabel password1, label;
    private JTextField username;
    private JButton button;
    private JPasswordField Password;


    public Login() {
        setTitle("Login");
        setLocation(new Point(500, 300));
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        //----------------------//
        // Username constructor
        label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);
        username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        panel.add(username);

        // Password constructor
        password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        panel.add(password1);
        Password = new JPasswordField();
        Password.setBounds(100, 75, 193, 28);
        panel.add(Password);

        //login button
        button = new JButton("Login");
        button.setBounds(100, 110, 90, 25);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.addActionListener(this);
        panel.add(button);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = username.getText();
        String pass = Password.getText();

        if (user.equals("admin") && pass.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            System.out.println("Login successful!");
            GUIWindow window = new GUIWindow();
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
        }
    }
}
