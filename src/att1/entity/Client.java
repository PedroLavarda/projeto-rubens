package att1.entity;

import java.util.Date;

public class Client extends Person {
    private boolean isReserving;
    private boolean isBanned;
    private Date lastReservationDate;
    private String employeeNotes;

    public Client() {
    }

    public Client(int id, String fullName, String email, String password, Date birthDate, String cpf) {
        super(id, fullName, email, password, birthDate, cpf);
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

    public Date getLastReservationDate() {
        return lastReservationDate;
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
}