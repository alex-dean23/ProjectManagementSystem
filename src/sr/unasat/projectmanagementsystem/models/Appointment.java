package sr.unasat.projectmanagementsystem.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment {
    private int id;
    private String name;
    private String description;
    private int projectId;

    public Appointment(int id, String name, String description, int projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectId = projectId;
    }

    public Appointment(int id){
        this.id = id;
    }

    public Appointment(ResultSet rs) {
        try {
            this.id = rs.getInt("App_id");
            this.name = rs.getString("App_name");
            this.description = rs.getString("App_Description");
            this.projectId = rs.getInt("Proj_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
