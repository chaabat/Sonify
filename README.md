# Sonify

A Spring Boot-based music management system with secure REST APIs for managing songs, albums, and users.

## Overview

Sonify is a robust music management platform that allows users to manage songs and albums while providing secure authentication and role-based access control. The system supports both regular users and administrators with different levels of access and functionality.

## Features

- **Authentication & Security**
  - JWT-based authentication
  - Role-based access control (USER and ADMIN roles)
  - Secure password encryption

- **Music Management**
  - Song and album organization
  - Search functionality
  - Pagination support

- **User Management**
  - User registration and login
  - Role management
  - Administrative controls

## Tech Stack

- Spring Boot
- Spring Security
- MongoDB
- JWT
- Maven
- MapStruct
- Lombok

## API Endpoints

### Public Endpoints
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login

### User Endpoints
- `GET /api/user/songs` - List all songs
- `GET /api/user/songs/search` - Search songs
- `GET /api/user/songs/album/{albumId}` - Get album songs

### Admin Endpoints
- `POST /api/admin/songs` - Create song
- `PUT /api/admin/songs/{id}` - Update song
- `DELETE /api/admin/songs/{id}` - Delete song
- `GET /api/admin/users` - Manage users

## Getting Started

1. Prerequisites:
   - Java 11+
   - MongoDB
   - Maven

2. Clone the repository:
```bash
git clone https://github.com/chaabat/sonify.git
```

3. Configure MongoDB connection in `application.properties`

4. Run the application:
```bash
mvn spring-boot:run
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

