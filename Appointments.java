package hosp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Appointments {

	public int appointmentId; 
    public int patientId;      
    public int doctorId;
    public String appointmentDate;

    public Appointments(int appointmentId, int patientId, int doctorId, String appointmentDate) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
    }

    public static boolean addAppointment(int patientId, int doctorId, String appointmentDate) {
    	if (!canAddAppointment(doctorId, appointmentDate)) {
    	    System.out.println("Cannot add appointment. Limit of 5 appointments reached for this date.");
    	    return false;
    	}
        String query = "INSERT INTO Appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setString(3, appointmentDate);
            ps.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // New method to get appointments for a specific doctor
    public static List<Appointments> getAppointmentsByDoctor(int doctorId) {
        List<Appointments> appointmentsList = new ArrayList<>();
        String query = "SELECT * FROM Appointments WHERE doctor_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointmentsList.add(new Appointments(rs.getInt("appointment_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getString("appointment_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }
    
    public static boolean canAddAppointment(int doctorId, String appointmentDate) {
        String query = "SELECT COUNT(*) FROM Appointments WHERE doctor_id = ? AND appointment_date = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ps.setString(2, appointmentDate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) < 5; // Limit to 5 appointments
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }
}
