
# Music Streaming Service API
This project is a music streaming service API that allows users to manage songs and playlists. It provides CRUD operations for songs and user playlists, with separate permissions for normal and admin users. The API is implemented using Java 11 with the Spring Boot framework and includes Swagger documentation for easy exploration of the available endpoints.

This is a Music Streaming Service API that allows two types of users: Normal and Admin. Admin users have full CRUD (Create, Read, Update, Delete) access to songs, while normal users can manage their playlists and add songs to them.

## Features

- User Roles: Admin and Normal user roles with different permissions.
- CRUD Operations: Admin users can perform CRUD operations on songs. Normal users can manage their playlists.
- Pagination: Optional pagination feature to fetch a limited number of songs from the user's playlist at a time.

## Prerequisites

- Java 17
- MySQL Database
- Internet connectivity

## API Documentation
The API documentation is generated using Swagger. You can explore the available endpoints using the Swagger UI interface.

To access the Swagger UI, open a web browser and go to http://localhost:8080/swagger-ui.html.

The Swagger UI provides a user-friendly interface to view and interact with the API endpoints. You can try out the different operations, view request and response details, and even make test requests directly from the UI.

## Usage
The API provides the following endpoints:

* GET /songs: Get a list of all songs.
* POST /songs: Create a new song (admin only).
* GET /songs/{id}: Get details of a specific song.
* PUT /songs/{id}: Update details of a specific song (admin only).
* DELETE /songs/{id}: Delete a specific song (admin only).
* GET /users/{userId}/playlists: Get a list of playlists for a specific user.
* POST /users/{userId}/playlists: Create a new playlist for a specific user.
* GET /users/{userId}/playlists/{playlistId}: Get details of a specific playlist for a specific user.
* PUT /users/{userId}/playlists/{playlistId}: Update details of a specific playlist for a specific user.
* DELETE /users/{userId}/playlists/{playlistId}: Delete a specific playlist for a specific user.
Please note that certain operations (marked as admin only) require admin user permissions.
## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:

* spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
* spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
* spring.datasource.username = <userName>
* spring.datasource.password = <password>
* spring.jpa.show-sql = true
* spring.jpa.hibernate.ddl-auto = update

* spring.jpa.properties.hibernate.show_sql=true
* spring.jpa.properties.hibernate.use_sql_comments=true
* spring.jpa.properties.hibernate.format_sql=true
## Language Used
Java
  
Certainly! Here's an updated version of the README file that includes a project summary:

Music Streaming Service API
The Music Streaming Service API is a Java-based backend application that provides a comprehensive set of endpoints for managing songs and user playlists. It is built using Java 11 and the Spring Boot framework, offering a scalable and robust solution for a music streaming service.

## Project Summary
The Music Streaming Service API offers the following features:

* User Management:

Supports two types of users: normal users and admin users.
User authentication and authorization using JSON Web Tokens (JWT).
Normal users can manage their playlists, while admin users have additional permissions to perform CRUD operations on songs.
* Song Management:

Admin users can perform CRUD operations on songs.
Endpoints for creating, reading, updating, and deleting songs.
Annotation-based validation ensures data integrity and prevents invalid inputs.
* Playlist Management:

Users can create, read, update, and delete their playlists.
Endpoints for managing user playlists, including CRUD operations.
Pagination feature to fetch a limited number of songs from a playlist at a time.
* Documentation:

Integration with Swagger to generate comprehensive API documentation.
Swagger UI provides an interactive interface for exploring and testing the API endpoints.
