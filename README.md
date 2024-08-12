# Spring Boot Features and Codebase Description
## Repo referred: [click here](https://github.com/RameshMF/React-Hooks-Spring-Boot-CRUD-Full-Stack-App/tree/main/springboot-backend)

## Spring Boot Features

<details>
<summary><b>Hibernate</b></summary>

- **Hibernate**
    - It is an object-relational mapping (ORM) framework for Java.
    - Simplifies database interactions by mapping Java objects to database tables and handling CRUD operations.
    - It works with JDBC drivers to communicate with the database.

</details>

<details>
<summary><b>Bean</b></summary>

- **Bean**
    - Bean is an object that the Spring framework manages.
    - It's created, configured, and injected by Spring, which handles its lifecycle and dependencies.
    - Think of it as a building block of your application that Spring takes care of for you.
    - Beans can have different scopes:
        - Singleton: One instance per container.
        - Prototype: New instance for each request.
        - Request: Instance per HTTP request.
        - Session: Instance per HTTP session.

</details>

<details>
<summary><b>@Autowired</b></summary>

- **@Autowired**
    - Used for dependency injection.
    - Tells Spring to automatically provide the required dependency by injecting an instance of the bean into the annotated field, constructor, or method.

</details>

<details>
<summary><b>JPA</b></summary>

- **JPA**
    - Java Persistence API.
    - It is a Java specification for managing relational data.
    - It allows developers to interact with databases using Java objects.
    - It provides a framework for object-relational mapping (ORM), queries, and entity management.

</details>

<details>
<summary><b>@Repository</b></summary>

- **@Repository**
    - It is an interface that handles database operations for a specific entity.
    - It simplifies data access by automatically providing methods to perform CRUD operations and custom queries without writing boilerplate code.

</details>

<details>
<summary><b>JpaRepository</b></summary>

- **JpaRepository**
    - It is an interface in Spring Data JPA that provides built-in methods for CRUD operations and pagination on entities, simplifying data access and management.

</details>

<details>
<summary><b>Model</b></summary>

- **Model**
    - Represents the data structure of the application.
    - It is typically a Java class annotated with `@Entity`, which maps to a database table and contains fields corresponding to the table columns.
    - Models are used to define the application's data and interact with the database through repositories.

</details>

<details>
<summary><b>@Table</b></summary>

- **@Table**
    - Annotation in JPA that specifies the name of the database table to which the Java class should be mapped.

</details>

<details>
<summary><b>@Controller</b></summary>

- **@Controller**
    - Component that handles incoming HTTP web requests, processes them, and returns a response.
    - Annotated with `@Controller` or `@RestController`.
    - Contains methods mapped to specific URL patterns using annotations like `@GetMapping`, `@PostMapping`, `@RequestMapping`.
    - These methods handle requests, interact with the service layer if needed, and return a response either as HTML (for `@Controller`) or JSON/XML (for `@RestController`).

</details>

<details>
<summary><b>@RequestBody</b></summary>

- **@RequestBody**
    - Binds the HTTP request body to a method parameter.
    - Allows you to directly access data sent in the request.

</details>

<details>
<summary><b>@ResponseStatus</b></summary>

- **@ResponseStatus**
    - Annotation in Spring that sets the HTTP status code for a response from a controller method.
    - This allows you to specify status like `200 OK`, `404 Not Found` for a specific condition.

</details>

<details>
<summary><b>@Getter</b></summary>

- **@Getter**
    - Automatically generates getter methods for all fields in the class.

</details>

<details>
<summary><b>@Setter</b></summary>

- **@Setter**
    - Automatically generates setter methods for all fields in the class.

</details>

<details>
<summary><b>@NoArgsConstructor</b></summary>

- **@NoArgsConstructor**
    - Generates a no-argument constructor for the class.

</details>

<details>
<summary><b>@AllArgsConstructor</b></summary>

- **@AllArgsConstructor**
    - Generates a constructor with parameters for all fields in the class.

</details>

## Codebase Description

<details>
  <summary><b>src/main/java/com.example.demo/</b></summary>

  - <details>
      <summary><b>Controller/EmployeeController</b></summary>
      
      - **Controller/EmployeeController**
          - `@Autowired` injects the `EmployeeRepo` dependency into the controller, allowing it to perform CRUD operations on `Employee` entities.
          - `@RequestBody Employee employee` binds the HTTP request body to an `Employee` object, allowing you to access the incoming data as a Java object.
          - `@PathVariable long ID` extracts a path variable from the URL and binds it to the `ID` parameter in the method, allowing you to access dynamic values in the URL.
          - We have a REST controller for managing 'Employee' resources.
          - `@RestController` indicates that this class is a Spring REST controller, meaning it will handle HTTP requests and send responses in JSON format.
          - `@RequestMapping(URL)` specifies that all requests mapping in this class will be relative to the URL.
          - `getAllEmployees`: Returns a list of `Employee` objects by calling `employeeRepo.findAll`, and the annotation is used to map HTTP GET requests to this method.
          - `createEmployee`: Accepts an `Employee` object, saves it to the database, and returns the saved `Employee`.
          - `getEmpID`: Returns the employee wrapped in `ResponseEntity`, otherwise throws an exception.
          - `ResponseEntity<Employee>` is a Spring class that represents the entire HTTP response, including the status code, headers, and body, where `Employee` is the type of the response body.
          - `updateEmp`, `deleteEmp`: Methods for updating and deleting employees, respectively.
  
      </details>
  - <details>
    <summary><b>Exception/ResourceNotFoundException</b></summary>
    
    - **Exception/ResourceNotFoundException**
        - Custom exceptions are built here.
        - `@ResponseStatus(value = HttpStatus.NOT_FOUND)` specifies that when the exception is thrown, the HTTP response should have a `404 Not Found` status.
  
    </details>
  - <details>
      <summary><b>Model/Employee</b></summary>
      
      - **Model/Employee**
          - Defines an `Employee` entity.
          - `@Entity` marks this class as a JPA entity and maps it to a database table.
          - `@Table(name)` specifies the table name in the database.
          - `@Id` and `@GeneratedValue` define the primary key `id` with auto-increment.
          - `@Column` maps column fields.
          - `@Getter` and `@Setter` are Lombok annotations that automatically generate getter and setter methods.
          - `@NoArgsConstructor` and `@AllArgsConstructor` are Lombok annotations that automatically generate constructors with no arguments and all arguments, respectively.
    
      </details>
  - <details>
      <summary><b>Repository/EmployeeRepo</b></summary>
      
      - **Repository/EmployeeRepo**
          - Defines a repository interface for `Employee` entities.
          - It extends `JpaRepository`, which provides CRUD operations for `Employee` objects.
          - `@Repository` marks it as a Spring Data repository.
          - `JpaRepository<Employee, Long>` - these are generic parameters specifying that the repository manages `Employee` entities with a primary key of type `Long`.
          - This code file automatically inherits methods for standard CRUD operations without needing additional code.
      
      </details>
  - <details>
      <summary><b>DemoApplication</b></summary>
      
      - **DemoApplication**
          - `@SpringBootApplication` marks the main class for Spring Boot setup.
          - `DemoApplication` implements `CommandLineRunner`, allowing code to execute at startup.
          - In the `run` method, 3 `Employee` objects are created and populated with data, and saved to the database using `EmployeeRepo`.
      
    </details>

</details>

<details>
<summary><b>src/main/resources/application.properties</b></summary>

- **src/main/resources/application.properties**
    - Used for setting up a Spring Boot application to connect to a MySQL database and configure Hibernate, the JPA implementation.
    - JDBC URL, username, and password are specified for database connection.
    - `spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect` configures Hibernate to use MySQL-specific SQL syntax and optimizations.
    - `spring.jpa.hibernate.ddl-auto = update` tells Hibernate to automatically update the database schema to match the current model. This setting helps synchronize the database structure with entity classes but should be used with caution in production environments.

</details>

<details>
<summary><b>pom.xml</b></summary>

- **pom.xml**
    - A configuration file used by Apache Maven, a build automation and dependency management tool for Java projects.
    - Defines dependencies: external libraries and frameworks your project needs.
    - Maven automatically downloads these dependencies and includes them in your project.
    - Configures Maven plugins for tasks like compiling code, running tests, packaging the application.
    - Contains project metadata.
    - Summarized: Helps manage dependencies, configure the build process, and define project details.

</details>
