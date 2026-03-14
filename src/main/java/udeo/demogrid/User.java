package udeo.demogrid;

public class User {
    private String NIT;
    private String name;
    private String email;

    public User(String NIT, String name, String email) {
        this.NIT = NIT;
        this.name = name;
        this.email = email;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
