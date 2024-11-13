# Customer Records Web Application

This project is a Java web application to manage customer records using a MySQL database. It is built using Eclipse IDE and runs on Apache Tomcat.

## Prerequisites

Before you begin, ensure you have the following installed:
- [Eclipse IDE](https://www.eclipse.org/downloads/)
- [Apache Tomcat Server](https://tomcat.apache.org/download-90.cgi)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)
- MySQL Connector (mysql-connector-j-8.0.33.jar)

## Steps to Run the Project

### 1. Clone or Download the Project

  ```bash
  git clone https://github.com/Sambarr3/Customer-Records.git
  ```

### 2. Open the Project in Eclipse

1. Open Eclipse IDE.
2. Go to **File > Open Projects from File System...**.
3. Select the directory where the project files are located and click **Finish**.

### 3. Configure the Database

1. Make sure MySQL is installed and running.
2. Open MySQL Workbench or your preferred MySQL client and create a new database for the application (e.g., `registration_db`).
3. Create a table to store customer records using SQL similar to the following:

    ```sql
    CREATE TABLE users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        username VARCHAR(30) NOT NULL UNIQUE,
        phone VARCHAR(15) NOT NULL UNIQUE,
        email VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```

4. Update the database connection credentials in the project code if necessary. Look for database configuration settings in the `web.xml` file (or similar).

### 4. Add MySQL Connector

1. Download the MySQL Connector JAR file (mysql-connector-j-8.0.33.jar) if you haven't already.
2. Place the `mysql-connector-j-8.0.33.jar` file in the `WEB-INF/lib` folder of the project. This enables JDBC connections to MySQL.

### 5. Run the Application

1. Right-click the project in Eclipse.
2. Select **Run As > Run on Server** and choose Apache Tomcat as the server.
3. Open a browser and go to `http://localhost:8080/Customer_Records`.

## Troubleshooting

- **Database Connection Errors**: Ensure MySQL is running, and check the database credentials in the Java files.
- **Server Errors**: Make sure Apache Tomcat is correctly installed and configured in Eclipse.

## License

This project is licensed under the MIT License.
