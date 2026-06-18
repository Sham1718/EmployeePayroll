package org;


import org.dao.EmployeeDao;
import org.model.Employee;
import org.service.PayRollService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);
        PayRollService service =new PayRollService();
        int empId;
        String empName;
        String email;
        double salary;
        double bonus;
        int dept_id;
        while (true){
            System.out.println("""
                    1.Add Employee
                    2.View Employee
                    3.Update Employee
                    4.Delete Employee
                    5.Calculate Salary
                    6.Department Wise Report
                    7.Top 3 Highest salary
                    8.Exit
                    Enter your Choice :""");
            int ch=sc.nextInt();

            switch (ch){
                case 1:
                    System.out.println("Enter Employee name:");
                    sc.nextLine();
                    empName=sc.nextLine();
                    System.out.println("Enter Employee email:");
                    email=sc.next();
                    System.out.println("Enter Employee Salary:");
                    salary=sc.nextDouble();
                    System.out.println("Enter Employee bonus:");
                    bonus=sc.nextDouble();
                    System.out.println("""
                                   ---------------------
                                   | deptId | deptName |
                                   |    1   |    IT    |
                                   |    2   |    HR    |
                                   |    3   |  Finance |
                                   |    4   |   Sales  |
                                   ---------------------""");

                    System.out.println("Enter Employee dept_Id:");
                    dept_id= sc.nextInt();
                    service.addEmployee(new EmployeeDao(empName,email,salary,bonus,dept_id));
                    break;
                case 2:
                    List < Employee> employees =service.getEmployee();
                    employees.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter employee id:");
                    empId= sc.nextInt();
                    System.out.println("Enter Employee Salary:");
                    salary=sc.nextDouble();
                    System.out.println("Enter Employee bonus:");
                    bonus=sc.nextDouble();
                    service.updateEmployee(empId,salary,bonus);
                    break;
                case 4:
                    System.out.println("Enter Employee id to delete:");
                    int id= sc.nextInt();
                    service.deleteEmployee(id);
                    break;
                case 5:
                    //salary
                    break;
                case 6:
                    //dept
                    break;
                case 7:
                    //top3
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid choice");
            }

        }

    }
}
