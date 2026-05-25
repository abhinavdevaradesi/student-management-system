package com.studentmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    //embedded address
    @Embedded
    private Address address;

    @Lob
    @Column(name = "profile_photo")
    private byte[] profilephoto;

    @Transient
    private String temporaryNote;

    public Student() {}

    public Student(String name, String email, String phone, Gender gender,Department department, LocalDate dateOfBirth, LocalDate admissionDate, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.address=address;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id=id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Department getDepartment() { return department; }
    public void setDepartment() { this.department = department; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public byte[] getProfilephoto() { return profilephoto; }
    public void setProfilephoto(byte[] profilephoto) { this.profilephoto = profilephoto; }

    public String getTemporaryNote() { return temporaryNote; };
    public void setTemporaryNote(String temporaryNote) { this.temporaryNote = temporaryNote; }

    @Override
    public String toString() {
        return "Student{"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                ", address=" + address +
                "}";
    }
}
