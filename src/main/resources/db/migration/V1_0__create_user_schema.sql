CREATE TABLE users
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50) NOT NULL,
    password   VARCHAR(60) NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name  VARCHAR(25) NOT NULL,
    role       VARCHAR(25) NOT NULL
);


INSERT INTO users (id, username, password, first_name, last_name, role)
VALUES (1, 'system', '$2a$12$bzZhkvjreqErTnDPvhWvm.R6Vz4QwivQqmopWkIHAOGMti5GPM2qC', 'system', 'name', 'ROLE_SYSTEM_ADMIN');
INSERT INTO users (id, username, password, first_name, last_name, role)
VALUES (2, 'admin', '$2a$12$aP.viZBtc6k5KVuJ.uaTce1R3wpP3XehfIBVzNL8dOEN6pd/jQmNK', 'admin', 'name', 'ROLE_ADMIN');
INSERT INTO users (id, username, password, first_name, last_name, role)
VALUES (3, 'user', '$2a$12$Tq5Od/5n7TCioqKRFh8Sw.7SRHbSC0ZcEkyAhXRMidggQWw.2MUK6', 'user', 'name', 'ROLE_USER');

