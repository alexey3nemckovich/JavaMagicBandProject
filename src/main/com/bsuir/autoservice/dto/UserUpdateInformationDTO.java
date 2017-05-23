package main.com.bsuir.autoservice.dto;

import java.util.Objects;

public class UserUpdateInformationDTO {
    private final String name;
    private final String lastName;
    private final String phone;

    public UserUpdateInformationDTO(String name, String lastName, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateInformationDTO that = (UserUpdateInformationDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, phone);
    }
}
