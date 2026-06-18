package org.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int empId;
    private String empName;
    private String email;
    private double salary;
    private double bonus;
    private int dept_id;


}
