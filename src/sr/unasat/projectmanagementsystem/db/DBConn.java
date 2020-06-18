package sr.unasat.projectmanagementsystem.db;

import sr.unasat.projectmanagementsystem.models.Appointment;
import sr.unasat.projectmanagementsystem.models.Project;
import sr.unasat.projectmanagementsystem.models.User;
import sr.unasat.projectmanagementsystem.ui.Dashboard;
import sr.unasat.projectmanagementsystem.ui.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConn {

     Connection myConn;
        private final String DRIVER = "com.mysql.jdbc.Driver";
        private final String URL = "localhost:3306";
        private final String DATABASE = "pm_system_db";
        private final String USER = "root";
        private final String PWD = "biga3000";



    public DBConn (){

            try {
                //Get connection to database
                Class.forName("com.mysql.jdbc.Driver");
                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                "root", "biga3000");
                //Create Statement
                Statement myStmt = myConn.createStatement();

                //Execute query
                ResultSet myRs = myStmt.executeQuery("Select * from notepad_user ");
                System.out.println("Connection Successful");
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
            }
        }

            public void insertIntoProjects(String Name, String Description, String Date, int UserId, int CategoryId){
                String sql = "INSERT INTO projects (Proj_name, Proj_description, End_date, User_id, Cat_id) VALUES (?, ?, ?, ?, ?)";

                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                            "root", "biga3000");
                    PreparedStatement statement = myConn.prepareStatement(sql);
                    statement.setString(1,Name);
                    statement.setString(2,Description);
                    statement.setString(3,Date);
                    statement.setInt(4,UserId);
                    statement.setInt(5,CategoryId);
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new project was inserted successfully!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }

    public void updateProject(int id, String name, String description, String endDate, int userId, int catId)  {
        String sql = "UPDATE projects SET Proj_name = '"+name+"', Proj_description = '"+description+"', End_date = '"+endDate+"',User_id = "+userId+", Cat_id = "+catId+" WHERE Proj_id = "+id;
        try {
             myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                    "root", "biga3000");
            PreparedStatement statement = myConn.prepareStatement(sql);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A project was updated successfully!");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertIntoAppointments( String Name, String Description, int ProjectId ) {
        String sql = "INSERT INTO appointments (App_name, App_description, Proj_id) VALUES ( ?, ?, ?)";

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                    "root", "biga3000");
            PreparedStatement statement =  myConn.prepareStatement(sql);
            statement.setString(1,Name);
            statement.setString(2,Description);
            statement.setInt(3,ProjectId);
            int rs = statement.executeUpdate();
            if (rs>0) {
                System.out.println("An Appointment was added successfully!");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void updateAppointment(int id, String name, String description, int projId){
        String sql = "UPDATE appointments SET App_name = '"+name+"', App_description = '"+description+"', Proj_id = "+projId+" WHERE App_id = "+id;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                    "root", "biga3000");
            PreparedStatement statement = myConn.prepareStatement(sql);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A project was updated successfully!");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteAppointment(Appointment appointment){
        String sql = "DELETE FROM appointments WHERE App_id = ?";

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true","root","biga3000");
            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.setInt(1, appointment.getId());
            statement.executeUpdate();
            System.out.println("Appointment "+appointment.getName()+"Is Deleted");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteProject(Project project){
        String sql = "DELETE FROM projects WHERE Proj_id = ?";

        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true","root","biga3000");
            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.setInt(1, project.getId());
            statement.executeUpdate();
            System.out.println("Project "+project.getName()+"Is Deleted");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public List<Project> getListingForProject(){
        String  query = "SELECT * FROM projects";
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                    "root", "biga3000");
            Statement st = myConn.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery(query);

            while (rs.next())
            {
                Project project = new Project(rs);
                System.out.println(project.toString());
                projects.add(project);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  projects;
    }

    public List<Appointment> getListingForAppointment() {
        String query = "SELECT * FROM appointments";
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                    "root", "biga3000");
            Statement st = myConn.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery(query);

            while (rs.next()) {
                Appointment appointment = new Appointment(rs);
                System.out.println(appointment.toString());
                appointments.add(appointment);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }


    private static String getClassName(Class c) {
        String FQClassName = c.getName();
        int firstChar;
        firstChar = FQClassName.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            FQClassName = FQClassName.substring(firstChar);
        }
        return FQClassName;
    }



}