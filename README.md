# springbootstartert1

### Описание проекта
Starter для логирования HTTP-запросов/ответов, а также методов и времени их выполнения.
Учебный проект по заданию от T1 образовательной школы по Java направлению.

#### Основные особенности проекта:
* Разработан с использованием фрэймворка Spring Boot 3.3.2
* Используется Filter и AOP
* 5 доступных свойств для включения и отключения функций стартера
* Документация JavaDoc

### Содержание:
1. [Техническое задание](#техническое-задание)
2. [Стэк проекта](#стэк-проекта)
3. [API стартера](#api-стартера)
4. [Инструкция к применению](#инструкция-к-применению)
5. [Полезные ссылки](#полезные-ссылки)
6. [Автор](#автор)

### Техническое задание
* [Тех-задание](docs/dz4.txt)

### Стэк проекта
* Java 17, Spring Boot 3.3.2 (Web, AOP), Lombok, Slf4j, JUnit 5 

### API стартера
#### HTTP-логирование
* kory.logging.starter.enable=true (активация стартера "Logging Starter")
* kory.logging.starter.http-logging-enable=true (активация http логирования "Http Logging", по умолчанию включено базовое логирование)
* kory.logging.starter.http-logging-type=full (изменить тип логирования на полный)
* kory.logging.starter.http-logging-type=basic (изменить тип логирования на базовый)

#### Логирование методов
* kory.logging.starter.method-tracing-enable=true (активация логирования методов "Method Tracing")
* @Loggable (аннотация для логирования методов)

### Инструкция к применению
* Добавьте "springbootstartert1" как модуль к проекту;
* Подключите зависимость используя Maven или Gradle.
#### Maven:
```
<dependency>
    <groupId>ru.koryruno</groupId>
    <artifactId>springbootstartert1</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
#### Gradle:
```
dependencies {
    implementation 'ru.koryruno:logging-starter:0.0.1-SNAPSHOT'
}
```
* В вашем проекте в директории src/main/resources подключите логирование через стандартные файлы конфигурации Spring Boot такие, 
как application.properties или application.yml;

#### Базовое логирование
* В файле настроек проекта активируйте "Logging Starter" и "Http Logging":

##### application.properties
```
kory.logging.starter.enable=true
kory.logging.starter.http-logging-enable=true
```
##### application.yml
```
kory:
  logging:
    starter:
      enable: true
      http-logging-enable: true
```

#### Детальное логирование
* Для более детального логирования с помощью "Http Logging", задайте тип http-логирования на full:

##### application.properties
```
kory.logging.starter.enable=true
kory.logging.starter.http-logging-enable=true
kory.logging.starter.http-logging-type=full
```
##### application.yml
```
kory:
  logging:
    starter:
      enable: true
      http-logging-enable: true
      http-logging-type: full
```

#### Логирование методов с помощью аннотации @Loggable
* Для логирования вызова метода подключите "Method Tracing":

##### application.properties
```
kory.logging.starter.enable=true
kory.logging.starter.method-tracing-enable=true
```
##### application.yml
```
kory:
  logging:
    starter:
      enable: true
      method-tracing-enable: true
```
* Используйте аннотацию "@Loggable" над методом или классом;
* В параметрах аннотации укажите тип логирования. Если не указать тип, будет применен дефолтный "level = LogLevel.INFO"; 
* Параметр "level" может принимать следующие значения (TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF);
* Можно использовать различный тип логирования для класса и отдельно для методов.

Пример:
```
@Loggable(level = LogLevel.INFO)
public class MyService {

    @Loggable(level = LogLevel.ERROR)
    public void createAction() {
        // Some code here..
    }
}
```

#### Полное логирование используя весь функционал:
* В файле настроек проекта активируйте "Logging Starter", "Http Logging" и "Method Tracing":
##### application.properties
```
kory.logging.starter.enable=true
kory.logging.starter.http-logging-enable=true
kory.logging.starter.http-logging-type=full
kory.logging.starter.method-tracing-enable=true
```
##### application.yml
```
kory:
  logging:
    starter:
      enable: true
      http-logging-enable: true
      http-logging-type: full
      method-tracing-enable: true
```

### Полезные ссылки
* Используемые зависимости в проекте: [pom.xml](pom.xml)
* Тестирование: [тесты](src/test/java/ru/koryruno/springbootstartert1)

### Автор
* "KoryRunoMain"