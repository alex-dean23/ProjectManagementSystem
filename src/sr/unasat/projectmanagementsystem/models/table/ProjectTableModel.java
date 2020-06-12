package sr.unasat.projectmanagementsystem.models.table;

import sr.unasat.projectmanagementsystem.models.Appointment;
import sr.unasat.projectmanagementsystem.models.Project;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProjectTableModel extends AbstractTableModel {
    private final List<Project> projectList;
    private final String[] columnNames = new String[] {
            "Id", "Name", "Description"
    };
    private final Class[] columnClass = new Class[] {
            Integer.class, String.class, String.class
    };


    public ProjectTableModel(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public int getRowCount()
    {
        return projectList.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Project row = projectList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getName();
        }
        else if(2 == columnIndex) {
            return row.getDescription();
        }
        return null;
    }



}
