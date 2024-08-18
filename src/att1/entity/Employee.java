package att1.entity;

import java.util.Date;

public class Employee extends Person {
    private double salary;
    private int workHours;
    private Date hiringDate;
    private boolean isActive;

    public Employee() {
    }

    public Employee(int id, String fullName, String email, String password, Date birthDate, String cpf,
                    double salary, int workHours, Date hiringDate, boolean isActive) {
        super(id, fullName, email, password, birthDate, cpf);
        this.salary = salary;
        this.workHours = workHours;
        this.hiringDate = hiringDate;
        this.isActive = isActive;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public String toString() {
        return getFullName() + " " + getEmail() + " " + getSalary() + " " + getWorkHours() + " " + isActive;
    }
}
