package vn.edu.hcmuaf.fit.doancuoiki.model;

public class Role {
    private int id;
    private String nameRole;
    private String description;

    public Role() {
    }

    public Role(String description, int id, String nameRole) {
        this.description = description;
        this.id = id;
        this.nameRole = nameRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
