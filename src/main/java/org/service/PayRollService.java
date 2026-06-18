package org.service;

import org.dao.EmployeeDao;
import org.db.DBConnection;
import org.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayRollService {
    public PreparedStatement getConnection(String sql) throws SQLException {
        Connection con= DBConnection.getConnection();
        return con.prepareStatement(sql);
    }

    public void addEmployee(EmployeeDao dao){
        String sql=" Insert INTO EMPLOYEE(emp_name,email,salary,bonus,dept_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps=getConnection(sql);
            ps.setString(1,dao.getEmpName());
            ps.setString(2,dao.getEmail());
            ps.setDouble(3,dao.getSalary());
            ps.setDouble(4,dao.getBonus());
            ps.setInt(5,dao.getDept_id());
            ps.executeUpdate();
            System.out.println("Employee Added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getEmployee(){
        List<Employee>employees=new ArrayList<>();
        String sql ="select * from employee";
        try {
            PreparedStatement ps=getConnection(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Employee emp=new Employee(rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getDouble("bonus"),
                rs.getInt("dept_id"));
                employees.add(emp);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(employees);
        return employees;
    }

    public void deleteEmployee(int id){
        String sql ="Delete from employee where emp_id=?";
        try {
            PreparedStatement ps=getConnection(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("|------ Employee Deleted ------|");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmployee(int id ,double salary,double bonus){
        String sql ="Update Employee set salary=?,bonus=? where emp_id=?";
        try {
            PreparedStatement ps= getConnection(sql);
            ps.setDouble(1,salary);
            ps.setDouble(2,bonus);
            ps.setInt(3,id);
            ps.executeUpdate();
            System.out.println("Employee Details Updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void employeeSalary(int id) {

        for (Employee emp : getEmployee()) {

            if (emp.getEmpId() == id) {

                double total =
                        emp.getSalary()
                                + emp.getBonus();

                System.out.println("------------------------------------------------------");
                System.out.println(
                        " | Employee Name : "+ emp.getEmpName() + " | " +
                        "Total Salary : " + total + " |"
                );
                System.out.println("------------------------------------------------------");

                return;
            }
        }

        System.out.println(
                "Employee not found"
        );
    }

    public void topSalary() {

        getEmployee()
                .stream()
                .sorted(
                        Comparator
                                .comparing(
                                        Employee::getSalary
                                )
                                .reversed()
                )
                .limit(3)
                .forEach(employee -> System.out.println( "| Employee Name : "+employee.getEmpName() + " | Salary : "
                + employee.getSalary()+ " |"));
    }

    public void deptWise() {
        Map<Integer, String> departments =
                Map.of(
                        1, "IT",
                        2, "HR",
                        3, "Finance",
                        4, "Sales"
                );

        getEmployee()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDept_id
                        )
                )
                .forEach(
                        (deptId,
                         employees) -> {

                            System.out.println(
                                    "Department : "
                                            + departments.get(deptId)
                            );
                            System.out.println("---------------------------------------------------");
                            employees.forEach(emp-> System.out.println("| Employee Name :"+emp.getEmpName() +
                                    " | Email " + emp.getEmail() + " |"));
                            System.out.println("---------------------------------------------------");
                        }
                );
    }

}
