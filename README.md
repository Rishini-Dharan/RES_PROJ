# 🍽️ Restaurant Reservation Management System

> A Spring Boot powered restaurant reservation platform designed with one goal in mind:
> **Deliver a seamless booking experience while helping restaurants improve customer satisfaction and retention.**

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-success)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![JPA](https://img.shields.io/badge/Spring_Data_JPA-Hibernate-brightgreen)
![License](https://img.shields.io/badge/License-MIT-green)

---

# 📖 Overview

Most reservation systems simply store bookings.

This project goes beyond that.

The Restaurant Reservation Management System is built around **customer experience** rather than just data management.

The objective was to create a booking system that minimizes booking errors, prevents scheduling conflicts, provides transparent pricing, and builds customer confidence from the very first interaction.

A reliable reservation experience increases customer satisfaction, encourages repeat visits, and ultimately contributes to customer retention.

---

# 💡 Inspiration

While designing this project, I kept asking myself one question:

> **"How can technology make the customer's booking experience effortless?"**

Every feature was designed by thinking from the customer's perspective.

Instead of focusing solely on database operations, I focused on eliminating common frustrations such as:

- Double bookings
- Reservations outside restaurant operating hours
- Hidden pricing
- Poor communication
- Invalid reservation requests

The result is a backend that protects both the restaurant and its customers.

---

# ✨ Features

## 👥 Customer Management

- Register customers
- Store customer details
- Track reservation history

---

## 🏢 Restaurant Management

- Manage restaurant information
- Configure operating hours
- Manage restaurant tables

---

## 🍽️ Table Management

- Premium & Standard tables
- Table availability tracking
- Capacity management

---

## 📅 Reservation Management

- Book tables
- Update reservations
- Cancel reservations
- Reservation status tracking

---

## ⏰ Reservation Validation

Before confirming a reservation, the system validates:

✔ Restaurant operating hours

✔ Table availability

✔ Reservation conflicts

✔ Booking status

---

## 💰 Dynamic Pricing

Reservation prices are calculated dynamically based on:

- Premium table selection
- Experience packages
- Additional reservation services

---

## 🎁 Experience Packages

Customers can enhance their reservation with optional premium experiences.

Examples include:

- Birthday decorations
- Anniversary packages
- Candlelight dining
- Celebration services

---

# 🏗️ Project Architecture

```
                Client
                  │
                  ▼
            Spring MVC Controller
                  │
                  ▼
             Service Layer
        (Business Logic & Validation)
                  │
                  ▼
          Spring Data JPA Repository
                  │
                  ▼
               MySQL Database
```

---

# 🧠 Business Rules

The project isn't just CRUD.

Business rules ensure every reservation is meaningful.

Examples include:

### ✔ Restaurant Open Validation

Reservations are accepted only during operating hours.

---

### ✔ Double Booking Prevention

The system checks whether a table is already reserved for the selected time slot.

---

### ✔ Dynamic Price Calculation

Reservation cost is automatically calculated before confirmation.

---

### ✔ Meaningful Exception Handling

Instead of generic errors, custom exceptions explain exactly what went wrong.

Example:

```
RestaurantClosedException

TableAlreadyReservedException

ReservationNotFoundException
```

---

# 🗂 Project Structure

```
ReservationSystem
│
├── controller
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── exception
│
├── configuration
│
├── resources
│
└── application.properties
```

---

# ⚙️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Programming Language |
| Spring Boot | Backend Framework |
| Spring MVC | REST APIs |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| MySQL | Database |
| Maven | Dependency Management |
| Thymeleaf | View Rendering |

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/Rishini-Dharan/RES_PROJ.git
```

---

## Navigate

```bash
cd RES_PROJ
```

---

## Configure Database

Update

```
application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reservation_db

spring.datasource.username=root

spring.datasource.password=your_password
```

---

## Run

```bash
mvn spring-boot:run
```

Application starts on

```
http://localhost:8080
```

---

# 📸 Workflow

```
Customer

    │

    ▼

Choose Restaurant

    │

    ▼

Select Table

    │

    ▼

Choose Experience Package

    │

    ▼

Reservation Validation

    │

    ├── Restaurant Open?
    ├── Table Available?
    ├── Time Valid?
    └── Calculate Price

    │

    ▼

Reservation Confirmed
```

---

# 🎯 Key Learnings

Building this project changed my perspective on backend development.

I realized that backend systems are not just APIs connected to databases.

They are responsible for protecting the customer experience.

Every validation, pricing rule, and exception exists to ensure that customers can trust the reservation process.

A smooth booking experience is often the first step toward customer loyalty.

---

# 🔮 Future Improvements

- JWT Authentication
- Role-Based Access Control
- Email Notifications
- SMS Confirmation
- AI Table Recommendation
- Loyalty Rewards
- Online Payments
- Reservation Analytics Dashboard
- Docker Support
- Swagger Documentation
- Unit Testing
- Integration Testing

---

# 🤝 Contributing

Contributions are always welcome.

Feel free to fork the repository, create a feature branch, and submit a Pull Request.

---

# ⭐ Support

If you found this project useful,

please consider giving it a ⭐ on GitHub.

It helps others discover the project and motivates future improvements.

---

# 👨‍💻 Authors

**Rishini Dharan**

Passionate about building software that combines technology with meaningful user experiences.

GitHub:
https://github.com/Rishini-Dharan

**Inbanithi R**

Highly self motivated and confident person with very impactful learning and enjoyable journey.

GitHub:
https://github.com/Inbanithi107

---

> **"Great software isn't measured by the number of features it has, but by how effortlessly it solves real problems."**
