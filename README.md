# REST Assured API Automation Tests

Минималистичный фреймворк для автоматизированного тестирования REST API (Petstore Swagger).

## Технологии

- **REST Assured** 6.0.0
- **JUnit 5** (Jupiter)
- **Jackson** 2.21.2
- **Hamcrest** 3.0
- **Gradle** 8.14

## Структура проекта

```
src/test/java/
├── clients/                 # HTTP-клиенты API
│   ├── BaseClient.java      # Базовый класс (get, post, put, delete)
│   ├── PetClient.java       # Клиент API питомцев
│   ├── StoreClient.java     # Клиент API магазина
│   └── UserClient.java     # Клиент API пользователей
├── config/                  # Настройки и утилиты
│   └── BaseTest.java       # Базовый класс тестов (URL, генерация ID)
├── models/                  # Модели (DTO)
│   ├── Pet.java            # Модель питомца
│   ├── Order.java          # Модель заказа
│   └── User.java          # Модель пользователя
└── tests/                  # Тесты
    ├── PetApiTests.java     # Тесты API питомцев
    ├── StoreApiTests.java   # Тесты API магазина
    └── UserApiTests.java   # Тесты API пользователей
```

## Запуск тестов

```bash
# Запуск всех тестов
./gradlew test

# Очистка и пересборка
./gradlew clean build
```
