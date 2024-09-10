package att1.entity;

import java.util.Date;

public class Client extends Person {
    private boolean isReserving;
    private boolean isBanned;
    private Date lastReservationDate;
    private String employeeNotes;

    public Client() {
    }

    public Client(int id, String fullName, String email, String password, int age, String cpf, Address address, boolean isReserving, boolean isBanned, Date lastReservationDate, String employeeNotes) {
        super(id, fullName, email, password, age, cpf, address);
        this.isReserving = isReserving;
        this.isBanned = isBanned;
        this.lastReservationDate = lastReservationDate;
        this.employeeNotes = employeeNotes;
    }

    public boolean isReserving() {
        return isReserving;
    }

    public void setReserving(boolean reserving) {
        isReserving = reserving;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public java.sql.Date getLastReservationDate() {
        return (java.sql.Date) lastReservationDate;
    }

    public void setLastReservationDate(Date lastReservationDate) {
        this.lastReservationDate = lastReservationDate;
    }

    public String getEmployeeNotes() {
        return employeeNotes;
    }

    public void setEmployeeNotes(String employeeNotes) {
        this.employeeNotes = employeeNotes;
    }

    @Override
    public String toString() {
        return getFullName() + " " + getEmail() + " " + getCpf() + " " + getLastReservationDate() + " " + isBanned();
    }
}