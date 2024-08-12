package screens;

import DB.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class employeeLogin {
    private JTextField adminEmail;
    private JPanel loginPanel;
    private JPasswordField adminPassword;
    private JButton submitLogin;

    public employeeLogin() {
        submitLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = DB.getConnection();

                if(new String(adminPassword.getPassword()).isBlank() || adminEmail.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Enter password or email");
                    return;
                }

                try {
                    PreparedStatement stmt = conn.prepareStatement(
                            "select * from clients where email = ? and password = ?"
                    );

                    stmt.setString(1, adminEmail.getText());
                    stmt.setString(2, new String(adminPassword.getPassword()));

                    ResultSet rs = stmt.executeQuery();

                    if(!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "Invalid password or email");
                        rs.close();
                        return;
                    }

                } catch (SQLException ex) {
                    System.out.println("Error: " + e);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");

        frame.setContentPane(new employeeLogin().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Employee Login Page");
        frame.setBounds(960, 540, 500, 500);
        frame.pack();
        frame.setVisible(true);
    }
}
