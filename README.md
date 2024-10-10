# HospitalAppointmentSystem2

## Overview

The **HospitalAppointmentSystem2** is a Java-based application that allows patients to book appointments with doctors in a hospital. It provides functionalities to manage departments, doctors, patients, and appointments, making the process more efficient and user-friendly.

## Features

- **View and Manage Departments**: Users can view all hospital departments and add new ones.
- **Doctor Management**: Users can view all doctors, add new doctors, and manage their specializations and department affiliations.
- **Patient Management**: Users can view all patients and add new patients.
- **Appointment Scheduling**: Patients can schedule appointments with doctors, with restrictions on the number of appointments per doctor per day.
- **Doctor Login**: Doctors can log in to view their scheduled appointments for the day.

## Technologies Used

- **Java**: The primary programming language for the application.
- **MySQL**: Used for database management to store information about departments, doctors, patients, and appointments.
- **JDBC**: Java Database Connectivity for database interactions.

## Database Schema

The following tables are created in the MySQL database:

- **Department**: Stores information about hospital departments.
- **Doctors**: Contains details about doctors, including their specialization and department.
- **Patients**: Stores patient information, such as name, age, and contact number.
- **Appointments**: Records appointments between patients and doctors.

### SQL Schema

```sql
-- Create the database
CREATE DATABASE HospitalDB;

-- Use the database
USE HospitalDB;

-- Table for departments
CREATE TABLE Department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL
);

-- Table for doctors
CREATE TABLE Doctors (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Department(department_id)
);

-- Table for patients
CREATE TABLE Patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(255) NOT NULL,
    age INT,
    contact_number VARCHAR(15)
);

-- Table for appointments
CREATE TABLE Appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id)
);
