package att1.entity;

import java.sql.Date;
import java.util.List;

public class Reserve {
    private int id;
    private double price;
    private Room room;
    private Date initialDate;
    private Date leavingDate;
    private Employee employee;
    private List<Client> clients;

    public Reserve() {
    }

    public Reserve(int id, double price, Room room, Date initialDate, Date leavingDate, Employee employee, List<Client> clients) {
        this.id = id;
        this.price = price;
        this.room = room;
        this.initialDate = initialDate;
        this.leavingDate = leavingDate;
        this.employee = employee;
        this.clients = clients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return getId() + " - " + getPrice() + " - " + getRoom().getRoomNumber() + " - " + getInitialDate() + " - " + getLeavingDate() + " - " + getEmployee().getFullName();
    }
}
