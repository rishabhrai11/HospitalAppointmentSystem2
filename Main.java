package hosp;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===============================");
            System.out.println("    Hospital Appointment System");
            System.out.println("===============================\n");
            System.out.println("1. View All Departments");
            System.out.println("2. Add Department");
            System.out.println("3. View All Doctors");
            System.out.println("4. Add Doctor");
            System.out.println("5. View All Patients");
            System.out.println("6. Add Patient");
            System.out.println("7. Add Appointment");
            System.out.println("8. Doctor Login");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    // View all departments
                    System.out.println("\n--- Departments ---");
                    List<Department> departments = Department.getAllDepartments();
                    if (departments.isEmpty()) {
                        System.out.println("No departments found.");
                    } else {
                        for (Department dept : departments) {
                            System.out.printf("Department ID: %d | Name: %s%n", dept.getDepartmentId(), dept.getDepartmentName());
                        }
                    }
                    break;

                case 2:
                    // Add a new department
                    System.out.print("Enter Department Name: ");
                    String departmentName = scanner.nextLine();
                    Department.addDepartment(departmentName);
                    System.out.println("Department added successfully!");
                    break;

                case 3:
                    // View all doctors
                    System.out.println("\n--- Doctors ---");
                    List<Doctors> doctors = Doctors.getAllDoctors();
                    if (doctors.isEmpty()) {
                        System.out.println("No doctors found.");
                    } else {
                        for (Doctors doc : doctors) {
                            System.out.printf("Doctor ID: %d | Name: %s | Specialization: %s%n",
                                    doc.getDoctorId(), doc.getDoctorName(), doc.getSpecialization());
                        }
                    }
                    break;

                case 4:
                    // Add a new doctor
                    System.out.print("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialization = scanner.nextLine();
                    System.out.print("Enter Department ID: ");
                    int departmentId = scanner.nextInt();
                    scanner.nextLine();
                    Doctors.addDoctor(doctorName, specialization, departmentId);
                    System.out.println("Doctor added successfully!");
                    break;

                case 5:
                    // View all patients
                    System.out.println("\n--- Patients ---");
                    List<Patients> patients = Patients.getAllPatients();
                    if (patients.isEmpty()) {
                        System.out.println("No patients found.");
                    } else {
                        for (Patients patient : patients) {
                            System.out.printf("Patient ID: %d | Name: %s | Age: %d | Contact: %s%n",
                                    patient.getPatientId(), patient.getPatientName(), patient.getAge(), patient.getContactNumber());
                        }
                    }
                    break;

                case 6:
                    // Add a new patient
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter Contact Number: ");
                    scanner.nextLine(); // Consume newline
                    String contactNumber = scanner.nextLine();
                    Patients.addPatient(patientName, age, contactNumber);
                    System.out.println("Patient added successfully!");
                    break;

                case 7:
                    // Add a new appointment
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    scanner.nextLine(); // Consume newline
                    String appointmentDate = scanner.nextLine();
                    if (Appointments.addAppointment(patientId, doctorId, appointmentDate))
                        System.out.println("Appointment added successfully!");
                    break;

                case 8:
                    // Doctor login
                    System.out.print("Enter Doctor ID: ");
                    int loginDoctorId = scanner.nextInt();
                    Doctors loggedInDoctor = Doctors.login(loginDoctorId);
                    if (loggedInDoctor != null) {
                        System.out.println("\n--- Welcome, " + loggedInDoctor.getDoctorName() + " ---");
                        // View appointments for the logged-in doctor
                        List<Appointments> appointments = Appointments.getAppointmentsByDoctor(loggedInDoctor.getDoctorId());
                        if (appointments.isEmpty()) {
                            System.out.println("No appointments found for today.");
                        } else {
                            System.out.println("\n--- Appointments for " + loggedInDoctor.getDoctorName() + " ---");
                            for (Appointments appointment : appointments) {
                                System.out.printf("Appointment with Patient ID: %d on %s%n",
                                        appointment.patientId, appointment.appointmentDate);
                            }
                        }
                    } else {
                        System.out.println("Invalid Doctor ID.");
                    }
                    break;

                case 9:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
