package entity;

import java.util.Date;

public class Employee extends Person {
    private double salary;
    private int workHours;
    private double bonus;
    private Date hiringDate;
    private boolean isActive;

    public Employee() {
    }

    public Employee(int id, String fullName, String email, String password, Date birthDate, String cpf,
                    double salary, int workHours, double bonus, Date hiringDate) {
        super(id, fullName, email, password, birthDate, cpf);
        this.salary = salary;
        this.workHours = workHours;
        this.bonus = bonus;
        this.hiringDate = hiringDate;
        this.isActive = true;
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

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }
}
