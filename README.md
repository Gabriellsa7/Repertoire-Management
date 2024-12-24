# Band Repertoire Management System 🎵

This project is a **Band Repertoire Management System** developed as part of the Web Development 1 course at Information Systems. The goal of the system is to streamline the management of band repertoires by synchronizing all band members with a designated "lead" user. This ensures that all members follow the same repertoire structure and commands in real-time.

---

## 📝 Features

- **Centralized Management**: 
  - A lead musician can register, create a band, and invite members to join.
  - The lead manages the repertoire (add, reorder, and remove songs), which is synchronized across all band members.

- **Repertoire Handling**:
  - Songs are uploaded as PDF files for easy and consistent display.
  - Removed songs are not permanently deleted but archived for re-inclusion without re-uploading.

- **Multi-Band Membership**:
  - A musician can participate in multiple bands.
  - A band can have only one lead or multiple members as needed.

- **Real-Time Synchronization**:
  - During a live performance, the lead musician can control song transitions for all devices in the band, ensuring synchronization in real time.

- **User Authentication**:
  - All musicians must authenticate to access the system.

---

## 🚀 Technologies Used

- **Backend Framework**: [Spring Framework](https://spring.io/) (Java 21)
- **Database**: [MySQL](https://www.mysql.com/)
- **API Testing Tool**: [Insomnia](https://insomnia.rest/)
- **IDE**: [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Recommended for development)

---

## 📂 Project Structure

The project follows a modular design for scalability and maintainability:

1. **User Module**:
   - Handles musician registration, authentication, and profile management.
2. **Band Module**:
   - Manages band creation, membership, and repertoire synchronization.
3. **Repertoire Module**:
   - Handles song uploads, archiving, and synchronization across devices.
4. **Real-Time Sync Module**:
   - Ensures online, automatic updates for all members during live performances.

---

## ⚙️ How to Run the Project

### Prerequisites
- Java 21
- MySQL
- Spring Boot
- Insomnia (or any API testing tool)

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Gabriellsa7/band-repertoire-management.git
### 2. Set up the database

To configure the MySQL database for the project:

1. **Create a MySQL database** and update the database connection settings in application.properties:
   ```sql
   CREATE DATABASE band_repertoire_management;
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password

### 💬 Contact
For any questions or suggestions, feel free to reach out:

Email: gabrielsantana123santos@gmail.com

GitHub: Gabriellsa7
