# BookStore
BookStore is a online bookstore inventory system. The inventory consists of books with the following information: id, author, quantity, title, price.
***
## Tools/Framework
<ol>
  <li>Framwork: Java Spring Boot</li>
  <li>MYSQL: MYSQL is used for the database</li>
  <li>Java Persistence API(JPA): Java programming interface specification</li>
  <li>Lombok: Project Lombok is a java library to create Getter, Setters, and Constructors using annotation</li>
  <li>ModelMapper: Used to map object</li>
  <li>Swagger: Framework for UI to consume RESTful Web Services</li>
</ol>

***
## Functionality
BookStore uses MySql and consists of database named “bookstore” and a table named:"book".
<ol>
  <li>Add a book to the inventory:Endpoint: "/api/add-new-book"</li>
  <li>Remove a book from the inventory:Endpoint: "/api/remove-book/{id}"</li>
  <li>Update the quantity in stock for a given book:Endpoint: "api/update-book/{id}/{quantityToUpdate}".</li>
  <li>Retrieve the quantity in stock for a given book:Endpoint: "api/number-of-books/{id}". Id is the path variable, if there is no book, it will throw an exception</li>
  <li>List all books in the inventory:Endpoint: "/api/books-list". Get all the books in the database</li>
  <li>(Search and Filter Functionality) Retrieve all books by Author and filter by price range: Endpoint: "/api/author-price-range". Get all the books for the given Author within the specified price range</li>
  <li>(Authentication and Authorization) Basic Authentication has been set on Swagger to allow only authenticated users to perform Adding/Removing a book and update the book quantity</li>
</ol>
***

## Steps to Run the Application
<ol>
  <li>Lombok is required for this application, please install the plugin in your IDE</li>
  <li>In your MySQL workbench make a database called: bookstoreinventory</li>
  <li>In your IDE, open the application.properties file, and change the spring.datasource url, username,password. The current spring datasource and username are set as "jdbc:mysql://localhost:3306/bookstoreinventory" and root, which are likely the same as your case. If not, please update them. Please put in your database password in the spring.datasource.password field. </li>
  <li>Now you can run your application. The table "book" will be created in your "bookstoreinventory" database. By default, it will run on port:8888. You can open up the swagger UI using the following link: http://localhost: 8888/swagger-ui.html</li>
  <li>For admin access to perform adding/removing books, both the username and password are "bookstore". Click the Authorize button on the top right to enter your username/password to authorize the operations</li>
</ol>




