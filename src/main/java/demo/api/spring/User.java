package demo.api.spring;

public class User {
    private int id;
    private String name;
    private String message;
    private String created;

    public User(int id, String name, String message, String created) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.created = created;
    }
    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreated( String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
