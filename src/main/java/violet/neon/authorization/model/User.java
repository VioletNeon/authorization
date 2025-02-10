package violet.neon.authorization.model;

import java.util.Objects;

public class User {
    private int id;
    private int department;
    private String fullName;

    public int getDepartment() {
        return this.department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object entity) {
        if (this == entity) return true;
        if (entity == null || getClass() != entity.getClass()) return false;

        User user = (User) entity;

        return this.department == user.department && this.id == user.id && this.fullName.equals(user.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.fullName, this.department);
    }
}