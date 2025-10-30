INSERT INTO users (username, password)
SELECT 'admin', '$2a$12$0oJzHSDQmeBLBl460xom6O6HiWd0RihxdhTeTrrK337dJhnihWA22'
WHERE NOT EXISTS(
    SELECT 1 FROM users WHERE username = 'admin'
);