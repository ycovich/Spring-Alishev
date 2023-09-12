package by.ycovich.model;

import jakarta.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "enter your full name")
    @Size(min = 2, max = 30, message = "2-30 characters")
    private String name;

    @Min(value = 1, message = "age should be natural number")
    private int age;

    @NotEmpty(message = "enter your email")
    @Email(message = "invalid email format")
    private String email;

    @NotEmpty(message = "enter your address")
    @Pattern(regexp = "([A-Za-z-]+),\\s([A-Za-z-]+),\\s(\\d{6})")
    private String address;

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Person() {}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
