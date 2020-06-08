package sr.unasat.projectmanagementsystem.db;

import sr.unasat.projectmanagementsystem.models.Appointment;
import sr.unasat.projectmanagementsystem.models.Project;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConn {

    private Connection myConn;
        private final String DRIVER = "com.mysql.jdbc.Driver";
        private final String URL = "localhost:3306";
        private final String DATABASE = "pm_system_db";
        private final String USER = "root";
        private final String PWD = "biga3000";

        public DBConn(Project project) {

            try {
                //Get connection to database
                Class.forName("com.mysql.jdbc.Driver");
                myConn = DriverManager.getConnection(
                        String.format("jdbc:mysql://%s/%s", URL, DATABASE), USER, PWD);
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
            public void insertIntoProjects(Project project){
                String sql = "INSERT INTO projects (Proj_name, Proj_description, End_date, User_id, Cat_id) VALUES (?, ?, ?, ?, ?)";

                try {
                    PreparedStatement statement = myConn.prepareStatement(sql);
                    statement.setString(1, project.getName());
                    statement.setString(2, project.getDescription());
                    statement.setString(3, project.getDate());
                    statement.setInt(4, project.getUserId());
                    statement.setInt(5, project.getCategoryId());

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new product was inserted successfully!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
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

    public void insertIntoAppointments(Appointment appointment){
        String sql = "INSERT INTO appointments (App_name, App_description, Proj_id) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.setString(1, appointment.getName());
            statement.setString(2, appointment.getDescription());
            statement.setInt(3, appointment.getProjectId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAppointment(Project project){
        String sql = "UPDATE appointments SET App_name = ?, App_description = ?, Proj_id = ? WHERE Id = ?";

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
    public void deleteEntity(Object obj){
        String sql = String.format("DELETE FROM %s WHERE Id = ?", getClassName(obj.getClass()));

        try {
            if(myConn.isClosed())
                myConn = DriverManager.getConnection(
                        String.format("jdbc:mysql://%s/%s", URL, DATABASE), USER, PWD);
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