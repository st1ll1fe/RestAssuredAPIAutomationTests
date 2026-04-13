package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) для пользователя из Petstore API
 * Используется для сериализации/десериализации JSON при работе с User API
 */
public class User {

    // Уникальный идентификатор пользователя
    @JsonProperty("id")
    private Long id;

    // Уникальное имя пользователя (логин)
    @JsonProperty("username")
    private String username;

    // Имя пользователя
    @JsonProperty("firstName")
    private String firstName;

    // Фамилия пользователя
    @JsonProperty("lastName")
    private String lastName;

    // Email пользователя
    @JsonProperty("email")
    private String email;

    // Пароль пользователя
    @JsonProperty("password")
    private String password;

    // Телефон пользователя
    @JsonProperty("phone")
    private String phone;

    // Статус пользователя (0 - активен, и т.д.)
    @JsonProperty("userStatus")
    private Integer userStatus;

    // Конструктор по умолчанию (нужен для Jackson)
    public User() {}

    // Конструктор с ID, username и email
    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Конструктр с username, email и password
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getUserStatus() { return userStatus; }
    public void setUserStatus(Integer userStatus) { this.userStatus = userStatus; }
}