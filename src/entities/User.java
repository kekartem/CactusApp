package entities;

public class User {
    private Long id;
    private String login;
    private String password;
    private String lastName;
    private String name;
    private String email;
    private String address;
    private String photoPath;


    public User(Long id, String login, String password, String lastName, String name, String email, String address, String photoPath) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.name = name;
        this.email = email;
        this.address = address;
        this.photoPath = photoPath;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
