package att1.entity;

public abstract class Person {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private int age;
    private String cpf;
    private Address address;

    public Person() {
    }

    public Person(int id, String fullName, String email, String password, int age, String cpf, Address address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.cpf = cpf;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmal(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
