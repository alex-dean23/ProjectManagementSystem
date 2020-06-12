package sr.unasat.projectmanagementsystem.ui;

import sr.unasat.projectmanagementsystem.db.DBConn;
import sr.unasat.projectmanagementsystem.models.Appointment;
import sr.unasat.projectmanagementsystem.models.Project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.time.Period;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class Dashboard {

	/*private DBConn dbConn;
	private JFrame frame;
	private JLayeredPane layeredPane;
	private JTextField appointmentName;
	private JTextField appointmentDescription;
	private JTextField projectId;
	private JTextField projectName;
	private JTextField projectDescription;
	private JTextField projectEndDate;
	private JTextField userId;
	private JTextField categoryId;
	private JPanel projectPanel;
	private JPanel appointmentPanel;
	private JPanel dashboardPanel;
	private boolean visible;
	*/
	private JFrame frame;
	private JLayeredPane layeredPane;
	private JTextField appointmentName;
	private JTextField appointmentDescription;
	private JTextField projectName;
	private JTextField projectDescription;
	private JPanel projectPanel;
	private JPanel appointmentPanel;
	private JPanel dashboardPanel;
	private JTextField projectId;
	private JTextField textField_5;
	private JTextField categoryId;
	private JTextField textField_7;
	private JTextField userId;


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

	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();


	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		 final String DRIVER = "com.mysql.jdbc.Driver";
		 final String URL = "localhost:3306";
		 final String DATABASE = "pm_system_db";
		 final String USER = "root";
		 final String PWD = "biga3000";



		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		//Panel
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(133, 0, 401, 361);
		frame.getContentPane().add(cardPanel);
		cardPanel.setLayout(null);

		//Layerd
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 401, 361);
		cardPanel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		//dash
		dashboardPanel = new JPanel();
		dashboardPanel.setLayout(null);
		layeredPane.add(dashboardPanel, "name_16632743230200");

		JLabel label_7 = new JLabel("New label");
		label_7.setIcon(new ImageIcon(Dashboard.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/pexels-photo-1629212.jpeg")));
		label_7.setBounds(0, 0, 402, 361);
		dashboardPanel.add(label_7);

		//appoitm
		appointmentPanel = new JPanel();
		appointmentPanel.setLayout(null);
		layeredPane.add(appointmentPanel, "name_16632775647900");

		projectId = new JTextField();
		projectId.setBounds(88, 110, 189, 20);
		appointmentPanel.add(projectId);
		projectId.setColumns(10);

		appointmentName = new JTextField();
		appointmentName.setColumns(10);
		appointmentName.setBounds(88, 161, 189, 20);
		appointmentPanel.add(appointmentName);

		appointmentDescription = new JTextField();
		appointmentDescription.setColumns(10);
		appointmentDescription.setBounds(88, 212, 189, 42);
		appointmentPanel.add(appointmentDescription);

		JLabel lblNewLabel = new JLabel("Project ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(88, 91, 189, 14);
		appointmentPanel.add(lblNewLabel);

		JLabel label = new JLabel("Appointment Name");
		label.setForeground(Color.WHITE);
		label.setBounds(88, 141, 189, 14);
		appointmentPanel.add(label);

		JLabel label_1 = new JLabel("Appointment Description");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(89, 192, 188, 14);
		appointmentPanel.add(label_1);

		JButton button = new JButton("SAVE");
		// button add row
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Appointment appointment = new Appointment(appointmentName.getText(),appointmentDescription.getText());
				DBConn dbConn = new DBConn();
				dbConn.insertIntoAppointments(appointmentName.getText(),appointmentDescription.getText());

			}
		});
		button.setBackground(Color.GREEN);
		button.setBounds(88, 271, 83, 23);
		appointmentPanel.add(button);

		JButton button_1 = new JButton("X");
		button_1.setBackground(Color.RED);
		button_1.setBounds(227, 271, 50, 23);
		appointmentPanel.add(button_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon(Dashboard.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/pexels-photo-1629212.jpeg")));
		label_2.setBounds(0, 0, 402, 361);
		appointmentPanel.add(label_2);


		//projp


		projectPanel = new JPanel();
		projectPanel.setLayout(null);
		layeredPane.add(projectPanel, "name_16632791671800");

		JLabel lblNewLabel_1 = new JLabel("Category ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(88, 99, 186, 14);
		projectPanel.add(lblNewLabel_1);

		userId = new JTextField();
		userId.setBounds(88, 68, 186, 20);
		projectPanel.add(userId);
		userId.setColumns(10);

		categoryId = new JTextField();
		categoryId.setBounds(88, 117, 186, 20);
		projectPanel.add(categoryId);
		categoryId.setColumns(10);

		projectName = new JTextField();
		projectName.setColumns(10);
		projectName.setBounds(88, 167, 189, 20);
		projectPanel.add(projectName);

		projectDescription = new JTextField();
		projectDescription.setColumns(10);
		projectDescription.setBounds(88, 216, 189, 35);
		projectPanel.add(projectDescription);

		JFormattedTextField Date = new JFormattedTextField();
		Date.setBounds(88, 275, 186, 20);
		projectPanel.add(Date);

		JLabel lblNewLabel_2 = new JLabel("User ID");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(88, 49, 186, 14);
		projectPanel.add(lblNewLabel_2);

		JLabel label_3 = new JLabel("Project Name");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(88, 148, 189, 14);
		projectPanel.add(label_3);

		JLabel label_4 = new JLabel("Project Description");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(88, 198, 186, 14);
		projectPanel.add(label_4);

		JLabel label_5 = new JLabel("End Date YYYY-MM-DD");
		label_5.setForeground(Color.WHITE);
		label_5.setBackground(Color.WHITE);
		label_5.setBounds(88, 257, 190, 14);
		projectPanel.add(label_5);

		JButton button_2 = new JButton("SAVE");
		// button add row
		button_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Project project = new Project(projectName.getText(),projectDescription.getText(),Date.getText());
				DBConn dbConn = new DBConn();
				dbConn.insertIntoProjects(projectName.getText(),projectDescription.getText(),Date.getText());

			}
		});
		button_2.setBackground(Color.GREEN);
		button_2.setBounds(88, 306, 83, 23);
		projectPanel.add(button_2);

		JButton button_3 = new JButton("X");
		button_3.setBackground(Color.RED);
		button_3.setBounds(227, 306, 50, 23);
		projectPanel.add(button_3);



		JLabel label_6 = new JLabel("New label");
		label_6.setIcon(new ImageIcon(Dashboard.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/pexels-photo-1629212.jpeg")));
		label_6.setBounds(0, 0, 402, 361);
		projectPanel.add(label_6);




		JPanel Menu = new JPanel();
		Menu.setBounds(0, 0, 133, 361);

		Menu.setBackground(Color.DARK_GRAY);
		Menu.setBounds(0, 0, 133, 361);
		frame.getContentPane().add(Menu);
		Menu.setLayout(null);

		JButton btnNewButton = new JButton("Dashboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switchPanels(dashboardPanel);

			}
		});
		btnNewButton.setBounds(10, 75, 113, 23);
		Menu.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Appointment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switchPanels(appointmentPanel);

			}
		});
		btnNewButton_1.setBounds(10, 109, 113, 23);
		Menu.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Project");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switchPanels(projectPanel);


			}
		});
		btnNewButton_2.setBounds(10, 143, 113, 23);
		Menu.add(btnNewButton_2);

	}




	protected void despose() {
		// TODO Auto-generated method stub

	}

	protected void close() {
		// TODO Auto-generated method stub

	}


	public void setVisible(boolean b) {
		frame.setVisible(b);

	}
}
