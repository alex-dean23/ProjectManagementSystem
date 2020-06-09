package sr.unasat.projectmanagementsystem.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Dashboard {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
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
	public Dashboard() {
		initialize();
	}

	public static void setVisisble(boolean b) {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 133, 361);
		
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 133, 361);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Dashboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 75, 113, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Appointment");
		btnNewButton_1.setBounds(10, 109, 113, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Project");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(10, 143, 113, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/pexels-photo-1629212.jpeg")));
		lblNewLabel.setBounds(132, 0, 402, 361);
		frame.getContentPane().add(lblNewLabel);
	}

	public void setVisible(boolean b) {
	}

	public void setVisible() {
		this.setVisible();
	}

	public void setTitle(String welcome) {
		this.setTitle("Welcome");
	}
}
