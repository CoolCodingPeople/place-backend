---

# Pluto Backend

This is the backend repository for the Pluto Student Helper App. Pluto is a web application designed to assist students in managing their daily school routine, staying updated with the weather, receiving announcements from teachers, and organizing their assignments.

## Features

- **Authentication:** Secure user login and authorization using JWT cookies.

- **Data Storage:** Use SQLite Database to store announcements and assignments.

## Technologies Used

- **Server:**
  - Node.js
  - Express.js for building the API
  - SQLite for database storage
  - JWT for user authentication

## Installation

1. Clone this repository to your local machine:

```
git clone https://github.com/yourusername/pluto-backend.git
```

2. Navigate to the project directory:

```
cd pluto-backend
```

3. Install the required dependencies:

```
npm install
```

4. Set up environment variables for configuration (e.g., database connection, JWT secret). Create a `.env` file in the root directory and add the following:

```
DB_CONNECTION_STRING=your_database_connection_string
JWT_SECRET=your_jwt_secret
```

## Usage

1. Start the server:

```
npm start
```

2. The server will run on the specified port, and the API endpoints will be available for the frontend to interact with.

## API Endpoints

- **POST /api/login:** User login and authentication.

- **GET /api/announcements:** Retrieve announcements from teachers.

- **GET /api/assignments:** Retrieve assignments with due dates, priorities, and reminders.

## License

This project is licensed under the [Apache License 2.0](LICENSE).

## Contact

For any questions or feedback related to the backend, feel free to contact the project team:

- Kevin Du
- Orlando Carcamo
- Soham Kamat
- Aniket Chakradeo

## Acknowledgments

We'd like to thank the Nighthawk Coding Society, and especially Mr. Mortensen for providing the tools and resources that made this project possible.
