package org.service;

import org.dao.EmployeeDao;
import org.db.DBConnection;
import org.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql ="Update Employee set salary=?,set bonus=? where id=?";
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

    public void EmployeeSalary(){


    }

    public void TopSalary(){

    }

    public void DeptWise(){

    }

}
