package sr.unasat.projectmanagementsystem.db;

import sr.unasat.projectmanagementsystem.models.Appointment;
import sr.unasat.projectmanagementsystem.models.Project;
import sr.unasat.projectmanagementsystem.models.User;
import sr.unasat.projectmanagementsystem.ui.Dashboard;
import sr.unasat.projectmanagementsystem.ui.Login;

import javax.swing.*;
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

            public void insertIntoProjects(String Name, String Description, String Date){
                String sql = "INSERT INTO projects (Proj_name, Proj_description, End_date) VALUES ('"+Name+"','"+Description+"', '"+Date+"')";

                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                            "root", "biga3000");
                    PreparedStatement statement = myConn.prepareStatement(sql);
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

    public void updateProject(Project project){
        String sql = "UPDATE projects SET Proj_name = ?, Proj_description = ?, End_date = ?," +
                "User_id = ? " + "Cat_id"+
                "WHERE Id = ?";

        try {

            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setString(3, project.getDate());
            statement.setInt(4, project.getUserId());
            statement.setInt(5, project.getCategoryId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A project was updated successfully!");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertIntoAppointments( String Name, String Description ) {
        String sql = "INSERT INTO appointments (App_name, App_description) VALUES ( '"+Name+"', '"+Description+"')";

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?verifyServerCertificate=false&useSSL=true",
                    "root", "biga3000");
            PreparedStatement statement =  myConn.prepareStatement(sql);
            int rs = statement.executeUpdate(sql);
            if (rs>0) {
                System.out.println("An Appointment was added successfully!");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void updateAppointment(String Name, String Description){
        String sql = "UPDATE appointments SET App_name = ?, App_description = ?, Proj_id = ? WHERE Id = ?";

        try {
            PreparedStatement statement = myConn.prepareStatement(sql);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A Appointment was updated successfully!");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void deleteEntity(Object obj){
        String sql = String.format("DELETE FROM %s WHERE Id = ?", getClassName(obj.getClass()));

        try {
            if(myConn.isClosed())
                myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pm_system_db?autoReconnect=true&useSSL=false");
            PreparedStatement statement = myConn.prepareStatement(sql);
            Method m = obj.getClass().getMethod("getId");
            Integer id = (Integer) m.invoke(obj);
            statement.setInt(1, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A " + obj.getClass().getName() + " was deleted successfully!");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getListingForProject(){
        String  query = "SELECT * FROM projects";
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
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