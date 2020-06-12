package sr.unasat.projectmanagementsystem.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Project {
    private int id;
    private String name;
    private String description;
    private String date;
    private int userId;
    private int categoryId;

    public Project(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Project(int id){
        this.id = id;
    }

    public Project(ResultSet rs) {
        try {
            this.id = rs.getInt("Proj_id");
            this.name = rs.getString("Proj_name");
            this.description = rs.getString("Proj_Description");
            this.date = rs.getString("End_date");
            this.userId = rs.getInt("User_id");
            this.categoryId = rs.getInt("Cat_id");
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

