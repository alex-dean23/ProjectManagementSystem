package sr.unasat.projectmanagementsystem.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;

public class projects {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					projects window = new projects();
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
	public projects() {
		initialize();
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
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 133, 361);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Dashboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 73, 113, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Appointment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 107, 113, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Project");
		btnNewButton_2.setBounds(10, 141, 113, 23);
		panel.add(btnNewButton_2);
		
		JTextField textField = new JTextField();
		textField.setBounds(241, 70, 189, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(241, 131, 189, 42);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Project Name");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(241, 50, 83, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Project Description");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(243, 112, 107, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("End Date");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(241, 194, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("SAVE");
		btnNewButton_3.setBackground(Color.GREEN);
		btnNewButton_3.setBounds(241, 253, 83, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("X");
		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setBounds(391, 253, 39, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JFormattedTextField frmtdtxtfldMmddyyyy = new JFormattedTextField();
		frmtdtxtfldMmddyyyy.setBounds(241, 210, 189, 20);
		frame.getContentPane().add(frmtdtxtfldMmddyyyy);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(projects.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/pexels-photo-1629212.jpeg")));
		lblNewLabel_2.setBounds(132, 0, 402, 361);
		frame.getContentPane().add(lblNewLabel_2);
		
	
	}

}
