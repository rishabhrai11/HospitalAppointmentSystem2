package hosp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Doctors {

    private int doctorId;
    private String doctorName;
    private String specialization;
    private int departmentId;

    public Doctors(int doctorId, String doctorName, String specialization, int departmentId) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.departmentId = departmentId;
    }

    // Getters and setters
    public static List<Doctors> getAllDoctors() {
        List<Doctors> doctorsList = new ArrayList<>();
        String query = "SELECT * FROM Doctors";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                doctorsList.add(new Doctors(rs.getInt("doctor_id"), rs.getString("doctor_name"), rs.getString("specialization"), rs.getInt("department_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorsList;
    }

    public static void addDoctor(String doctorName, String specialization, int departmentId) {
        String query = "INSERT INTO Doctors (doctor_name, specialization, department_id) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, doctorName);
            ps.setString(2, specialization);
            ps.setInt(3, departmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    // New method to login
    public static Doctors login(int doctorId) {
        for (Doctors doctor : getAllDoctors()) {
            if (doctor.getDoctorId() == doctorId) {
                return doctor; // return doctor object if ID matches
            }
        }
        return null; // return null if no match found
    }
}
