package hosp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Patients {

    private int patientId;
    private String patientName;
    private int age;
    private String contactNumber;

    public Patients(int patientId, String patientName, int age, String contactNumber) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    // Getters and setters

    public static List<Patients> getAllPatients() {
        List<Patients> patientsList = new ArrayList<>();
        String query = "SELECT * FROM Patients";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                patientsList.add(new Patients(rs.getInt("patient_id"), rs.getString("patient_name"), rs.getInt("age"), rs.getString("contact_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientsList;
    }

    public static void addPatient(String patientName, int age, String contactNumber) {
        String query = "INSERT INTO Patients (patient_name, age, contact_number) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, patientName);
            ps.setInt(2, age);
            ps.setString(3, contactNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
