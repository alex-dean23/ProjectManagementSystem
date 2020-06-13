package sr.unasat.projectmanagementsystem.ui;

import sr.unasat.projectmanagementsystem.db.DBConn;
import sr.unasat.projectmanagementsystem.models.Appointment;
import sr.unasat.projectmanagementsystem.models.Project;
import sr.unasat.projectmanagementsystem.models.table.ProjectTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import java.time.Period;

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

		// create an array of objects to set the row data
		Object []row = new Object[5];

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
		
		JLabel lblNewLabel_3 = new JLabel("Appointments Table");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(44, 28, 316, 14);
		dashboardPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Projects Table");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(44, 201, 316, 14);
		dashboardPanel.add(lblNewLabel_4);
		
		JButton btnNewButton_3 = new JButton("UPDATE");

		btnNewButton_3.setBackground(Color.BLUE);
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setBounds(44, 143, 110, 23);
		dashboardPanel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("DELETE");

		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setBounds(250, 143, 110, 23);
		dashboardPanel.add(btnNewButton_4);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 45, 318, 87);
		dashboardPanel.add(scrollPane);
		
		JTable Appointments_table = new JTable();
		String columnsAppointment [] ={"App Id","App Name","App Description","Proj Id"};

		DefaultTableModel appointmentModel = new DefaultTableModel(new Object[][]{}, columnsAppointment);
		appointmentModel.setColumnIdentifiers(columnsAppointment);
		AppointmentpopulateTable(appointmentModel);
		Appointments_table.setModel(appointmentModel);

		Appointments_table.setBackground(Color.LIGHT_GRAY);
		Appointments_table.setForeground(Color.black);
		Font font = new Font("",1,22);
		Appointments_table.setFont(font);
		Appointments_table.setRowHeight(30);

		this.add(Appointments_table);
		scrollPane.setViewportView(Appointments_table);

		// create JTextFields

		JLabel AId = new JLabel("App Id");
		JLabel AN = new JLabel("App Name");
		JLabel AD = new JLabel("App Description");
		JLabel PId1 = new JLabel("Proj Id");


		AId.setBounds(20, 220, 100, 25);
		AN.setBounds(20, 250, 100, 25);
		AD.setBounds(20, 280, 100, 25);
		PId1.setBounds(20, 310, 100, 25);

		scrollPane.setViewportView(Appointments_table);

		// get selected row data From table to textfields
		Appointments_table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e){

				// i = the index of the selected row
				int i = Appointments_table.getSelectedRow();

				appointmentName.setText(appointmentModel.getValueAt(i, 0).toString());
				appointmentDescription.setText(appointmentModel.getValueAt(i, 1).toString());

			}
		});



		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = Appointments_table.getSelectedRow();
				Appointment appointment = new Appointment();

				appointment.setId(Integer.parseInt(Appointments_table.getModel().getValueAt(row,0).toString()));
				appointment.setName(String.valueOf(Appointments_table.getModel().getValueAt(row,1).toString()));
				appointment.setDescription((String)Appointments_table.getModel().getValueAt(row,2));
				appointment.setProjectId(Integer.parseInt(Appointments_table.getModel().getValueAt(row,3).toString()));

				DBConn dbConn = new DBConn();
				dbConn.updateAppointment(appointment, Appointments_table);
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// i = the index of the selected row
				int i = Appointments_table.getSelectedRow();
				if(i >= 0){
					int id = (int)appointmentModel.getValueAt(i, 0);
					dbConn.deleteAppointment(new Appointment(id));
					appointmentModel.removeRow(i);
				}
				else{
					System.out.println("Delete Error");
				}
				AppointmentpopulateTable(appointmentModel);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(44, 218, 316, 87);
		dashboardPanel.add(scrollPane_1);



		JTable Projects_Table = new JTable();
		String columnsProject [] ={"Project Id","Project Name","Project Description","End Date","User Id","Category Id"};

		DefaultTableModel projectModel = new DefaultTableModel(new Object[][]{}, columnsProject);
		projectModel.setColumnIdentifiers(columnsProject);
		ProjectpopulateTable(projectModel);
		Projects_Table.setModel(projectModel);

		Projects_Table.setBackground(Color.LIGHT_GRAY);
		Projects_Table.setForeground(Color.black);
		Font font1 = new Font("",1,22);
		Projects_Table.setFont(font1);
		Projects_Table.setRowHeight(30);

		this.add(Projects_Table);
		scrollPane_1.setViewportView(Projects_Table);

		// create JTextFields

		JLabel PId = new JLabel("Project Id");
		JLabel PN = new JLabel("Project Name");
		JLabel PD = new JLabel("Project Description");
		JLabel ED = new JLabel("End Date");
		JLabel UId = new JLabel("User Id");
		JLabel CId = new JLabel("Category Id");

		PId.setBounds(20, 220, 100, 25);
		PN.setBounds(20, 250, 100, 25);
		PD.setBounds(20, 280, 100, 25);
		ED.setBounds(20, 310, 100, 25);
		UId.setBounds(20, 340, 100, 25);
		CId.setBounds(20, 340, 100, 25);






		JButton btnNewButton_5 = new JButton("UPDATE");
		btnNewButton_5.setBackground(Color.BLUE);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = Projects_Table.getSelectedRow();
				Project project = new Project();

				project.setId((Integer)Projects_Table.getModel().getValueAt(row,0));
				project.setName((String)Projects_Table.getModel().getValueAt(row,1));
				project.setDescription((String) Projects_Table.getModel().getValueAt(row,2));
				project.setDate((String) Projects_Table.getModel().getValueAt(row,3));
				project.setUserId((Integer)Projects_Table.getModel().getValueAt(row,4));
				project.setCategoryId((Integer)Projects_Table.getModel().getValueAt(row,5));

				DBConn dbConn = new DBConn();
				try {
					dbConn.updateProject(project);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(44, 313, 110, 23);
		dashboardPanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("DELETE");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// i = the index of the selected row
				int i = Projects_Table.getSelectedRow();
				if(i >= 0){
					int id = (int)projectModel.getValueAt(i, 0);
					dbConn.deleteProject(new Project(id));
					projectModel.removeRow(i);
				}
				else{
					System.out.println("Delete Error");
				}
				AppointmentpopulateTable(projectModel);
			}
		});
		btnNewButton_6.setBackground(Color.RED);
		btnNewButton_6.setBounds(250, 316, 110, 23);
		dashboardPanel.add(btnNewButton_6);

		JLabel label_7 = new JLabel("New label");
		label_7.setIcon(new ImageIcon(Dashboard.class.getResource("/sr/unasat/projectmanagementsystem/ui/images/pexels-photo-1629212.jpeg")));
		label_7.setBounds(0, 0, 402, 361);
		dashboardPanel.add(label_7);

		//appoitm
		appointmentPanel = new JPanel();
		appointmentPanel.setLayout(null);
		layeredPane.add(appointmentPanel, "name_16632775647900");

		JSpinner ProjectId = new JSpinner();
		ProjectId.setBounds(88, 110, 189, 20);
		appointmentPanel.add(ProjectId);

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

				Appointment appointment = new Appointment(appointmentName.getText(),appointmentDescription.getText(),(Integer)(ProjectId.getValue()));
				DBConn dbConn = new DBConn();
				dbConn.insertIntoAppointments(appointmentName.getText(),appointmentDescription.getText(),(Integer)(ProjectId.getValue()));


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

		JSpinner UserId = new JSpinner();
		UserId.setBounds(88, 68, 186, 20);
		projectPanel.add(UserId);
		
		JSpinner CategoryId = new JSpinner();
		CategoryId.setBounds(88, 117, 186, 20);
		projectPanel.add(CategoryId);

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
				Project project = new Project(projectName.getText(),projectDescription.getText(),Date.getText(),(Integer)(UserId.getValue()),(Integer) (CategoryId.getValue()));
				DBConn dbConn = new DBConn();
				dbConn.insertIntoProjects(projectName.getText(),projectDescription.getText(),Date.getText(),(Integer)(UserId.getValue()),(Integer) (CategoryId.getValue()));

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


		// get selected row data From table to textfields
		Projects_Table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e){

				// i = the index of the selected row
				int i = Projects_Table.getSelectedRow();

				projectName.setText(appointmentModel.getValueAt(i, 0).toString());
				projectDescription.setText(appointmentModel.getValueAt(i, 1).toString());
				Date.setText(appointmentModel.getValueAt(i, 2).toString());

			}
		});


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
	DBConn dbConn = new DBConn();

	private void ProjectpopulateTable(DefaultTableModel projectModel){
		for(int i = 0; i < projectModel.getRowCount(); i++){
			projectModel.removeRow(i);
		}
		List<Project> projectList = dbConn.getListingForProject();
		Object[] rowData = new Object[6];
		for(int i = 0; i < projectList.size(); i++){
			rowData[0] = projectList.get(i).getId();
			rowData[1] = projectList.get(i).getName();
			rowData[2] = projectList.get(i).getDescription();
			rowData[3] = projectList.get(i).getDate();
			rowData[4] = projectList.get(i).getUserId();
			rowData[5] = projectList.get(i).getCategoryId();
			projectModel.addRow(rowData);
		}
	}

	private void AppointmentpopulateTable(DefaultTableModel appointmentModel){
		for(int i = 0; i < appointmentModel.getRowCount(); i++){
			appointmentModel.removeRow(i);
		}
		List<Appointment> appointmentList = dbConn.getListingForAppointment();
		Object[] rowData = new Object[5];
		for(int i = 0; i < appointmentList.size(); i++){
			rowData[0] = appointmentList.get(i).getId();
			rowData[1] = appointmentList.get(i).getName();
			rowData[2] = appointmentList.get(i).getDescription();
			rowData[3] = appointmentList.get(i).getProjectId();
			appointmentModel.addRow(rowData);
		}
	}

	private void add(JTable projects_table) {

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
