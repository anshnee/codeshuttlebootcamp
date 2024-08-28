package com.anshu.spring.week2.springmvc.dto;

import com.anshu.spring.week2.springmvc.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;

    @NotBlank(message = "Name is Required field and it cannot be blank")
    @Size(min = 3, max = 12, message = "Number of character in the name field should be in range: [3,12]")
    private String name;

    @Email(message = "Provided Email id is not valid")
    @NotBlank(message = "Email of Employee cannot be blank")
    private String email;

    @Max(value=80, message = "Age over limit : Maximum age of Employee allowed is 80 years")
    @Min(value=18, message="Age under limit: Minimum age of Employee allowed is 18 years")
    @Positive
    private Integer age;


    @NotBlank(message = "Role of Employee cannot be blank")
    @EmployeeRoleValidation
    private String Role;

    @Pattern(regexp = "^(DEV|TEST|DEVOPS|HR|SUPPORT|OPERATIONS)$",message = "Available Departments are DEV,TEST, DEVOPS, HR, SUPPORT, OPERATIONS")
    private String Department;

    @PastOrPresent(message = "Date of Joining of employee should be in past or present")
    private LocalDate dateOfJoining;

    @NotNull(message = "Employee Salary can be null")
    private double salary;

    @AssertTrue(message = "Employee should be an active employee")
    private Boolean isActive;
}
