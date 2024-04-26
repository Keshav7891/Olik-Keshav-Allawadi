# Olik Java Assignment

Create a Spring Boot application that provides RESTful endpoints to manage a book library. The application should support CRUD operations for books, authors, and book rentals.

## Prerequisites

```bash
- Java
- PostgreSQL (CREATE DATABASE library)
```

## Update Environment Variables in application.properties

```
spring.application.name=Library

# Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update


# Show SQL query in console
spring.jpa.show-sql=false
```

## Schema

#### users
```
                        Table "public.users"
  Column  |          Type          | Collation | Nullable | Default 
----------+------------------------+-----------+----------+---------
 id       | uuid                   |           | not null | 
 username | character varying(255) |           |          | 
Indexes:
    "users_pkey" PRIMARY KEY, btree (id)
    "users_username_key" UNIQUE CONSTRAINT, btree (username)
```

#### Authors
```
                       Table "public.authors"
  Column   |          Type          | Collation | Nullable | Default 
-----------+------------------------+-----------+----------+---------
 id        | uuid                   |           | not null | 
 biography | character varying(255) |           |          | 
 name      | character varying(255) |           |          | 
Indexes:
    "authors_pkey" PRIMARY KEY, btree (id)
    "authors_name_key" UNIQUE CONSTRAINT, btree (name)

```

#### Books
````
                            Table "public.books"
      Column      |          Type          | Collation | Nullable | Default 
------------------+------------------------+-----------+----------+---------
 publication_year | integer                |           |          | 
 author_id        | uuid                   |           |          | 
 id               | uuid                   |           | not null | 
 rental_id        | uuid                   |           |          | 
 isbn             | character varying(255) |           |          | 
 title            | character varying(255) |           |          | 
Indexes:
    "books_pkey" PRIMARY KEY, btree (id)
    "books_title_key" UNIQUE CONSTRAINT, btree (title)

````

- rental_id - Belongs To Rentals
- author_id - Belongs To Authors

#### Rentals
````
                        Table "public.rentals"
   Column    |          Type          | Collation | Nullable | Default 
-------------+------------------------+-----------+----------+---------
 rental_date | date                   |           |          | 
 return_date | date                   |           |          | 
 book_id     | uuid                   |           |          | 
 id          | uuid                   |           | not null | 
 renter_id   | uuid                   |           |          | 
 status      | character varying(255) |           |          | 
Indexes:
    "rentals_pkey" PRIMARY KEY, btree (id)
````

- book_id - Belongs To Books
- renter_id - Belongs To User
- status - Can Be RENTED / RETURNED

## Asumptions
- All Book Title are unique
- All Authors are unique
- All username are unique
- A author is required to create a book
- A user is required to rent / return a book
- While Renting a book, Rental Date is kept as current date
- While Returning a book, Return Date of rental is updated to current date
- All the validations must be applied before saving in DB
- We keep track of rented/return books in rental DB

## Logic

- Logic for creating user, author are independent.
- Logic for creating a book is dependent on a created author.
- Rent A BOOK : ![Untitled Diagram (1)](https://github.com/Keshav7891/Olik-Keshav-Allawadi/assets/66181614/9de1ae65-e13a-4112-a3e6-3ea2718c4a51)

- Return A Book : ![Return drawio](https://github.com/Keshav7891/Olik-Keshav-Allawadi/assets/66181614/ff1c27ee-0e0a-4850-9207-1f106c4ac23c)

- Get All Books Available To Rent : All the books Which are not assigned with Rental Id are available to rent.

- Get All Books Rented : All the books assigned with rental Id are the ones rentad

- Get Rented Book By a User : Get all the active rentals (Status : Rented) of a user and get the books from it.

- Check Dues : Get All the Active Rentals of a user which are overdue return date and present date by 14 days.

# Usage

Once The server is started, use the either user swagger documentation linked to read / use API, or use Postman 

#### Swagger
```
  http://localhost:8080/swagger-ui/index.html
```

#### Postman
Import ```OlikTask_Postman.json``` Collection to Postman.

## API Reference

#### End-point: Online Status
##### Method: GET
>```
>localhost:8080/api/online-status
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Create User
##### Method: POST
>```
>http://localhost:8080/api/v1/users/
>```
#### Body (**raw**)

```json
{
    "username" : "awakefx"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Create Author
##### Method: POST
>```
>http://localhost:8080/api/v1/authors/
>```
#### Body (**raw**)

```json
{
    "name" : "Keshav",
    "biography" : "SDE"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get All Authors
##### Method: GET
>```
>http://localhost:8080/api/v1/authors/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get Author
##### Method: GET
>```
>http://localhost:8080/api/v1/authors/Keshav/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Update Author
##### Method: PUT
>```
>http://localhost:8080/api/v1/authors/Keshav/
>```
##### Body (**raw**)

```json
{
    "name" : "Keshav",
    "biography" : "Video Editor"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Delete Author
##### Method: DELETE
>```
>http://localhost:8080/api/v1/authors/Keshav/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Create Book
##### Method: POST
>```
>http://localhost:8080/api/v1/books/
>```
#### Body (**raw**)

```json
{
  "title": "The Title of the Book",
  "author": "Keshav",
  "isbn": "978-3-16-148410-0",
  "publicationYear": "2023"
}

```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get All Books
##### Method: GET
>```
>http://localhost:8080/api/v1/books/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get A Book By Title
##### Method: GET
>```
>http://localhost:8080/api/v1/books/The%20Title%20of%20the%20Book/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Update A Book
##### Method: PUT
>```
>http://localhost:8080/api/v1/books/The%20Title%20of%20the%20Book/
>```
#### Body (**raw**)

```json
{
  "title": "TheBook",
  "author": "Keshav",
  "isbn": "978-3-16-148410-0",
  "publicationYear": "2023"
}

```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Delete A Book
##### Method: DELETE
>```
>http://localhost:8080/api/v1/books/TheBook/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get Books By Author
##### Method: GET
>```
>http://localhost:8080/api/v1/books/author/Keshav/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get Books Available To Rent
##### Method: GET
>```
>http://localhost:8080/api/v1/books/available/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get Rented Books
##### Method: GET
>```
>http://localhost:8080/api/v1/books/rented/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Rent A Book
##### Method: POST
>```
>http://localhost:8080/api/v1/rentals/rent/
>```
#### Body (**raw**)

```json
{
  "book": "TheBook",
  "renterUsername": "awakefx",
  "rentalDate": "2024-05-01",
  "returnDate": "2024-05-08"
}

```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Return A Book
##### Method: PUT
>```
>http://localhost:8080/api/v1/rentals/return/TheBook/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get All Rentals
##### Method: GET
>```
>http://localhost:8080/api/v1/rentals/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get All Rental Transactions Of A Book
##### Method: GET
>```
>http://localhost:8080/api/v1/rentals/all/TheBook/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: get Active Rental Transaction Of A Book
##### Method: GET
>```
>http://localhost:8080/api/v1/rentals/active/TheBook/
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

#### End-point: Get User Dues
##### Method: GET
>```
>http://localhost:8080/api/v1/users/dues/awakefx
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃




[MIT](https://choosealicense.com/licenses/mit/)
