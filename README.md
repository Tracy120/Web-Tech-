# Question 1: Library Management API

### Key Endpoints & Testing Evidence

#### 1. Get All Books
* **Endpoint:** `GET /api/books`
* **Description:** Retrieves the list of all books in the library.
* **Screenshot:**
![Get All Books](question1-library-api/screenshots/GET_all_books.png)

#### 2. Get Book By ID
* **Endpoint:** `GET /api/books/{id}`
* **Description:** Retrieves a single book using its unique ID.
* **Screenshot:**
![Get Book By ID](question1-library-api/screenshots/GET_book_by_id.png)

#### 3. Search by Title
* **Endpoint:** `GET /api/books/search?title={title}`
* **Description:** Filters books based on a partial title match.
* **Screenshot:**
![Search by Title](question1-library-api/screenshots/GET_search_by_title.png)

#### 4. Add New Book (POST)
* **Endpoint:** `POST /api/books`
* **Description:** Adds a new book to the library.
* **Screenshot:**
![Add Book](question1-library-api/screenshots/POST_add_book.png)

#### 5. Delete Book
* **Endpoint:** `DELETE /api/books/{id}`
* **Description:** Removes a book from the system.
* **Screenshot:**
![Delete Book](question1-library-api/screenshots/DELETE_book.png)