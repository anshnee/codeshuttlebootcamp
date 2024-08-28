package com.anshu.spring.week2.springmvc.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="Employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;

    private String Role;

    private String Department;
    private Integer age;
    private LocalDate dateOfJoining;
    private double salary;
    private Boolean isActive;
}
