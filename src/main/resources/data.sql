INSERT IGNORE INTO usuario (name, email, pass, logged_in, role, eliminado) 
VALUES (
    'Administrador',
    'admin@sistema.com',
    '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
    0, -- false (no está logueado)
    'ADMIN',
    0  -- false (no está eliminado)
);