package sr.unasat.projectmanagementsystem.models.table;

import sr.unasat.projectmanagementsystem.models.Appointment;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AppointmentTableModel extends AbstractTableModel {
    private final List<Appointment> appointmentList;
    private final String[] columnNames = new String[] {
            "Id", "Name", "Description"
    };
    private final Class[] columnClass = new Class[] {
            Integer.class, String.class, String.class
    };

    public AppointmentTableModel(List<Appointment> appointmentList, List<Appointment> appointmentList1) {
        this.appointmentList = appointmentList1;
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
        return appointmentList.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Appointment row = appointmentList.get(rowIndex);
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
