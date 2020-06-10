package sr.unasat.projectmanagementsystem.ui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception{
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 226, 361);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/PMS loginPage(271,361).png")));
		
		textField = new JTextField();
		textField.setBounds(310, 77, 161, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 127, 161, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setBounds(312, 58, 63, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setBounds(310, 108, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);


		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(310, 182, 161, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				String password = passwordField.getText();
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
							"root", "biga3000");

					PreparedStatement st = (PreparedStatement) connection
							.prepareStatement("Select User_name, User_password from notepad_user where User_name=? and User_password=?");

					st.setString(1, userName);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						frame.setVisible(false);
						JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
						Dashboard dashboard = new Dashboard();
						dashboard.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
					}
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		});


	}
	}
