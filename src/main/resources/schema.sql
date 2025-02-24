CREATE TABLE IF NOT EXISTS public."user"
(
    id VARCHAR(255) PRIMARY KEY,
    full_name VARCHAR(255),
    department INTEGER
);

ALTER TABLE IF EXISTS public."user"
    OWNER to "authorization";