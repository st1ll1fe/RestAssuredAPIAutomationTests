package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder для создания объектов User
 * Позволяет удобно конструировать объекты пользователей с помощью цепочки методов
 */
public class UserBuilder {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    /**
     * Создает новый экземпляр UserBuilder
     * @return новый UserBuilder
     */
    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    /**
     * Устанавливает ID пользователя
     * @param id - ID пользователя
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Устанавливает имя пользователя (логин)
     * @param username - уникальное имя пользователя
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Устанавливает имя пользователя
     * @param firstName - имя
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Устанавливает фамилию пользователя
     * @param lastName - фамилия
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Устанавливает email пользователя
     * @param email - email адрес
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Устанавливает пароль пользователя
     * @param password - пароль
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Устанавливает телефон пользователя
     * @param phone - номер телефона
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Устанавливает статус пользователя
     * @param userStatus - статус пользователя
     * @return текущий UserBuilder для цепочки вызовов
     */
    public UserBuilder withUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    /**
     * Собирает и возвращает объект User
     * @return готовый объект User
     */
    public User build() {
        User user = new User(id, username, email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);
        return user;
    }
}