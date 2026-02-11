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


## Question 2: Student Management API

### Key Endpoints & Testing Evidence

#### 1. Get All Students
* **Endpoint:** `GET /api/students`
* **Description:** Retrieves a list of all registered students.
* **Screenshot:**
![Get All Students](question2-student-api/screenshots/1-GET-all-students.png)

#### 2. Get Student By ID
* **Endpoint:** `GET /api/students/{id}`
* **Description:** Retrieves a specific student profile by ID.
* **Screenshot:**
![Get Student By ID](question2-student-api/screenshots/2-GET-student-by-id.png)

#### 3. Search by Major (Computer Science)
* **Endpoint:** `GET /api/students/major/Computer Science`
* **Description:** Filters students enrolled in Computer Science.
* **Screenshot:**
![Search by Major CS](question2-student-api/screenshots/3-GET-by-major-cs.png)

#### 4. Filter by GPA
* **Endpoint:** `GET /api/students/filter?gpa=3.8`
* **Description:** Filters students with a GPA higher than 3.8.
* **Screenshot:**
![Filter by GPA](question2-student-api/screenshots/5-GET-filter-by-gpa-3.8.png)

#### 5. Create Student (POST)
* **Endpoint:** `POST /api/students`
* **Description:** Registers a new student in the system.
* **Screenshot:**
![Create Student](question2-student-api/screenshots/6-POST-create-student.png)

---

## Question 3: Restaurant Menu API

### Key Endpoints & Testing Evidence

#### 1. Get All Menu Items
* **Endpoint:** `GET /api/menu`
* **Description:** Retrieves the full restaurant menu.
* **Screenshot:**
![Get All Menu](question3-restaurant-api/screenshots/GET_all_menu.png)

#### 2. Get Available Items
* **Endpoint:** `GET /api/menu/available`
* **Description:** Lists only items that are currently in stock/available.
* **Screenshot:**
![Get Available Items](question3-restaurant-api/screenshots/GET_available_items.png)

#### 3. Search Menu by Name
* **Endpoint:** `GET /api/menu/search?name={name}`
* **Description:** Searches for a specific dish by name.
* **Screenshot:**
![Search Menu](question3-restaurant-api/screenshots/GET_search_by_name.png)

#### 4. Delete Menu Item
* **Endpoint:** `DELETE /api/menu/{id}`
* **Description:** Removes a dish from the menu.
* **Screenshot:**
![Delete Item](question3-restaurant-api/screenshots/DELETE_menu_item.png)

---

## Question 4: E-commerce Product API

### Key Endpoints & Testing Evidence

#### 1. Get All Products
* **Endpoint:** `GET /api/products`
* **Description:** Displays the entire product catalog.
* **Screenshot:**
![Get All Products](question4-ecommerce-product-api/screenshots/1_GET_all_products.png)

#### 2. Search by Keyword
* **Endpoint:** `GET /api/products/search?q={keyword}`
* **Description:** Search for products matching a keyword.
* **Screenshot:**
![Search Product](question4-ecommerce-product-api/screenshots/2_GET_search_keyword.png)

#### 3. Filter by Category
* **Endpoint:** `GET /api/products/category/{category}`
* **Description:** Retrieves all products within a specific category.
* **Screenshot:**
![Filter Category](question4-ecommerce-product-api/screenshots/3_GET_by_category.png)

#### 4. Check Stock Status
* **Endpoint:** `GET /api/products/stock`
* **Description:** Checks which products are currently in stock.
* **Screenshot:**
![Check Stock](question4-ecommerce-product-api/screenshots/5_GET_in_stock.png)

#### 5. Add New Product
* **Endpoint:** `POST /api/products`
* **Description:** Adds a new item to the inventory.
* **Screenshot:**
![Add Product](question4-ecommerce-product-api/screenshots/6_POST_add_product.png)

---

## Question 5: Task Management API

### Key Endpoints & Testing Evidence

#### 1. Get All Tasks
* **Endpoint:** `GET /api/tasks`
* **Description:** Retrieves all tasks in the system.
* **Screenshot:**
![Get All Tasks](question5-task-management-api/screenshots/GET_all_tasks.png)

#### 2. Get High Priority Tasks
* **Endpoint:** `GET /api/tasks/priority/high`
* **Description:** Filters tasks that are marked as High Priority.
* **Screenshot:**
![Get High Priority](question5-task-management-api/screenshots/GET_priority_high.png)

#### 3. Mark Task as Completed
* **Endpoint:** `PATCH /api/tasks/{id}/complete`
* **Description:** Updates the status of a task to "Completed".
* **Screenshot:**
![Mark Completed](question5-task-management-api/screenshots/PATCH_mark_complete.png)

#### 4. Delete Task
* **Endpoint:** `DELETE /api/tasks/{id}`
* **Description:** Removes a task from the list.
* **Screenshot:**
![Delete Task](question5-task-management-api/screenshots/DELETE_task.png)

---

## Question 6: User Profile API (Bonus)

### Key Endpoints & Testing Evidence

#### 1. Get All Users
* **Endpoint:** `GET /api/users`
* **Description:** Retrieves all user profiles wrapped in a custom API Response.
* **Screenshot:**
![Get All Users](question6-userprofile-api/screenshot/1_GET_all_users.png)

#### 2. Search by Country (Rwanda)
* **Endpoint:** `GET /api/users/search/country/Rwanda`
* **Description:** Filters users based on their country of residence.
* **Screenshot:**
![Search Country](question6-userprofile-api/screenshot/2_GET_search_rwanda.png)

#### 3. Create User Profile
* **Endpoint:** `POST /api/users`
* **Description:** Creates a new user profile.
* **Screenshot:**
![Create User](question6-userprofile-api/screenshot/3_POST_create_user.png)

#### 4. Deactivate User Account
* **Endpoint:** `PATCH /api/users/{id}/deactivate`
* **Description:** Changes user status to inactive without deleting the record.
* **Screenshot:**
![Deactivate User](question6-userprofile-api/screenshot/4_PATCH_deactivate.png)
