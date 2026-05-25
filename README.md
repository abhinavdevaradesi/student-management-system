# JPA Fundamentals with EntityManager

This project is built using **plain JPA** with `EntityManager` instead of Spring Data JPA to deeply understand how JPA works internally.

## Concepts Covered

### JPA Core
- JPA setup and configuration
- `EntityManager` & `EntityManagerFactory`
- Persistence Unit & `persistence.xml`
- CRUD operations using:
  - `persist()`
  - `find()`
  - `merge()`
  - `remove()`

### Entity Mapping
- `@Entity`, `@Table`, `@Id`
- `@GeneratedValue`
  - `IDENTITY`
  - `SEQUENCE`
  - `AUTO`
- `@Column`
- `@Enumerated`
- `@Temporal`
- `@Lob`
- `@Transient`
- `@Embeddable` & `@Embedded`

## Goal

The main goal of this project is to strengthen my understanding of **JPA fundamentals**, entity lifecycle management, and how ORM works behind the scenes without relying on abstractions like Spring Data JPA.
