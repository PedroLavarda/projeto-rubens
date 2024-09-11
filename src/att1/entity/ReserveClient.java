package att1.entity;

public class ReserveClient {
    private int id;
    private int idClient;
    private int idReserve;

    public ReserveClient() {
    }

    public ReserveClient(int id, int idClient, int idReserve) {
        this.id = id;
        this.idClient = idClient;
        this.idReserve = idReserve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdReserve() {
        return idReserve;
    }

    public void setIdReserve(int idReserve) {
        this.idReserve = idReserve;
    }
}
