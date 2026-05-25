In this project, I deliberately used _plain JPA_ with _**EntityManager**_ instead of Spring Data JPA to understand the fundamentals deeply.

**JPA Core Concepts:**
Creating and configuring JPA Entities
Understanding _EntityManager_ and _EntityManagerFactory_
Working with Persistence Unit and persistence.xml
Basic _CRUD operations_ using persist(), merge(), remove(), and find()

**Entity Mapping Annotations:**
@Entity, @Table, @Id, @GeneratedValue (strategies: IDENTITY, SEQUENCE, AUTO)
@Column (nullable, unique, length, columnDefinition)
@Enumerated (EnumType.STRING vs EnumType.ORDINAL)
@Temporal and date/time handling
@Lob for large objects (images, files)
@Transient for non-persistent fields
Embeddable types using @Embeddable and @Embedded
