# 🛒 Sales Management API - Spring Boot

## 🚀 Overview

Backend REST API developed for managing sales operations in a supermarket chain.

This project demonstrates solid knowledge in **Java backend development**, applying **Spring Boot**, **JPA/Hibernate**, and **functional programming (Streams & Lambdas)** to build a scalable and maintainable system.

---

## 💡 Key Features

* Full **CRUD operations** for products and branches
* Sales registration with multiple items per transaction
* Revenue calculation and reporting
* Identification of **top-selling products**
* Clean separation between domain and API using **DTOs**
* Robust **error handling** with proper HTTP status codes

---

## 🧱 Architecture

The application follows a **layered architecture**:

* **Controller** → Handles HTTP requests
* **Service** → Business logic
* **Repository** → Data access with JPA
* **DTO** → Data transfer layer
* **Exception Handling** → Centralized error management

---

## ⚙️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **H2 / MySQL**
* **Lombok**
* **Maven**

---

## 🧠 Highlights

* Use of **Java Streams API** for data processing:

  * `map`, `filter`, `flatMap`, `groupingBy`
* Implementation of **entity relationships** (OneToMany, ManyToOne)
* Clean and maintainable code following best practices
* RESTful API design principles

---

## 📊 Example: Top-Selling Product Logic

```java
ventas.stream()
    .flatMap(v -> v.getDetalles().stream())
    .collect(Collectors.groupingBy(
        DetalleVenta::getProducto,
        Collectors.summingInt(DetalleVenta::getCantidad)
    ))
    .entrySet()
    .stream()
    .max(Map.Entry.comparingByValue());
```

---

## 🔗 API Endpoints

### Products

* `GET /api/productos`
* `POST /api/productos`
* `PUT /api/productos/{id}`
* `DELETE /api/productos/{id}`

### Branches

* `GET /api/sucursales`
* `POST /api/sucursales`
* `PUT /api/sucursales/{id}`
* `DELETE /api/sucursales/{id}`

### Sales

* `POST /api/ventas`
* `GET /api/ventas`
* `GET /api/ventas/sucursal/{id}`

---

## 🧪 Running the Project

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
mvn spring-boot:run
```

---

## 📈 Future Improvements

* API documentation with Swagger
* Authentication & authorization (Spring Security)
* Unit and integration testing
* Pagination and filtering
* Cloud deployment
* Stock control

---

## 👨‍💻 About Me

Backend developer focused on building scalable APIs using Java and Spring Boot.
Strong understanding of object-oriented design, REST principles, and clean code practices.

---

## ⭐ Why this project stands out

✔ Real-world business case (sales system)
✔ Clean architecture and best practices
✔ Functional programming applied in backend logic
✔ Production-ready structure

---
