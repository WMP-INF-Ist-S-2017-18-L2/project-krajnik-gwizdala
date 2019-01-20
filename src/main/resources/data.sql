INSERT INTO admin (email, password, role)
SELECT 'admin@admin.com', 'secret', TRUE
WHERE NOT EXISTS (SELECT * FROM users WHERE firstname='admin');