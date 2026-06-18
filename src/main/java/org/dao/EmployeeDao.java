package org.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDao {
    private String empName;
    private String email;
    private double salary;
    private double bonus;
    private int dept_id;
}
