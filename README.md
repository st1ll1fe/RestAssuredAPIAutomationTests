# REST Assured API Automation Tests

## Описание проекта

Фреймворк для автоматизированного тестирования REST API на базе **Petstore Swagger API** (https://petstore.swagger.io/).

Проект построен на архитектуре **Client → Service → Test** с использованием современных Java инструментов для тестирования.

---

## 🛠 Технологии

| Технология | Версия | Назначение |
|------------|--------|------------|
| **REST Assured** | 5.5.0 | HTTP клиент для тестирования API |
| **JUnit Jupiter** | 5.10.2 | Фреймворк для написания тестов |
| **Jackson** | 2.17.1 | Сериализация/десериализация JSON |
| **AssertJ** | 3.26.3 | Fluent assertions для проверок |
| **Allure** | 2.24.0 | Генерация отчётов о тестировании |
| **Gradle** | 8.14 | Система сборки |

---

## 📁 Структура проекта

```
RestAssuredAPIAutomationTests/
├── build.gradle                    # Конфигурация сборки и зависимости
├── settings.gradle                 # Настройки Gradle проекта
├── gradlew / gradlew.bat           # Gradle Wrapper
├── README.md                       # Документация проекта
└── src/test/java/
    ├── config/
    │   ├── Config.java             # Конфигурация (URL, утилиты)
    │   └── BaseTest.java           # Базовый класс с настройками HTTP
    ├── models/
    │   ├── Pet.java                # DTO питомца
    │   ├── Order.java              # DTO заказа
    │   ├── User.java               # DTO пользователя
    │   └── *Builder.java           # Builders для каждой модели
    ├── clients/
    │   ├── ApiClientBase.java      # Базовый класс с HTTP-методами
    │   ├── PetApi.java             # Интерфейс Pet API
    │   ├── PetClient.java          # Реализация HTTP-клиента для Pet
    │   ├── StoreApi.java           # Интерфейс Store API
    │   ├── StoreClient.java        # Реализация HTTP-клиента для Store
    │   ├── UserApi.java            # Интерфейс User API
    │   └── UserClient.java         # Реализация HTTP-клиента для User
    ├── services/
    │   ├── PetService.java         # Бизнес-логика для Pet
    │   ├── StoreService.java       # Бизнес-логика для Store
    │   └── UserService.java        # Бизнес-логика для User
    └── tests/
        ├── PetApiTests.java        # Тесты Pet API
        ├── StoreApiTests.java      # Тесты Store API
        └── UserApiTests.java       # Тесты User API
```

---

## 🏗 Архитектура

### Client Layer
Низкоуровневые HTTP-запросы. Каждый клиент инкапсулирует работу с конкретным API:

```java
public class PetClient extends ApiClientBase implements PetApi {
    public Response getPetById(Long petId) {
        return get("/pet/" + petId);
    }
}
```

### Service Layer
Бизнес-логика и комбинирование операций:

```java
public class PetService {
    public Response createRandomPet() {
        Long id = Long.parseLong(generateId());
        return createPet(id, generatePetName(), "available");
    }
}
```

### Test Layer
Тестовые сценарии с использованием assertions:

```java
@Test
void shouldCreatePet() {
    Response response = petService.createRandomPet();
    response.then().statusCode(200);
}
```

---

## 🚀 Запуск тестов

```bash
# Запуск всех тестов
./gradlew test

# Запуск с красивым сообщением
./gradlew runTests

# Запуск конкретного класса
./gradlew test --tests "tests.PetApiTests"

# Генерация Allure отчёта
./gradlew allureServe
```

---

## 📊 Покрытие тестами

| API | Методы | Тестов |
|-----|--------|--------|
| **Pet API** | 8 | CRUD операции, поиск по статусу |
| **Store API** | 6 | Заказы, инвентарь |
| **User API** | 7 | CRUD, логин/логаут |

**Всего:** 21 тест

---

## 🧪 Примеры использования

### Создание тестовых данных через Builder

```java
Pet pet = aPet()
    .withId(123L)
    .withName("Dog")
    .withCategory(1L, "Dogs")
    .withTag(1L, "friendly")
    .withStatus("available")
    .build();
```

### Проверка ответа

```java
response.then()
    .statusCode(200)
    .body("id", equalTo(123))
    .body("name", notNullValue())
    .body("status", equalTo("available"));
```

### Использование soft assertions

```java
new SoftAssertions()
    .assertThat(response.getStatusCode()).isEqualTo(200)
    .assertThat(response.jsonPath().getString("name")).isEqualTo("Dog")
    .assertAll();
```

---

## ⚙️ Конфигурация

### Переменные окружения

```bash
export BASE_URL="https://petstore.swagger.io/v2"
```

### Утилиты (Config.java)

- `generateId()` — уникальный числовой ID
- `generateUsername()` — уникальное имя пользователя
- `generatePetName()` — уникальное имя питомца

---

## 🔧 Расширение проекта

### Добавление нового API

1. Создать DTO модель в `models/`
2. Создать Builder в `models/`
3. Создать интерфейс в `clients/`
4. Создать реализацию клиента в `clients/`
5. Создать сервис в `services/`
6. Добавить тесты в `tests/`

### Добавление тестов

```java
@Test
void shouldDoSomething() {
    // Given - подготовка
    Pet pet = aPet().withName("Test").build();
    
    // When - выполнение
    Response response = petService.createPet(pet);
    
    // Then - проверка
    response.then()
        .statusCode(200)
        .body("name", equalTo("Test"));
}
```

---

## 📝 Особенности реализации

- **Интерфейсы для клиентов** — позволяют подменять реализацию (моки, стабы)
- **Allure интеграция** — автоматическая запись запросов и ответов в отчёт
- **Уникальные тестовые данные** — генерация ID и имён для избежания конфликтов
- **Builder паттерн** — удобное создание тестовых данных

---
