INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_department (name) VALUES ('Epis e Epcs');

INSERT INTO tb_category (name) VALUES ('Botinas');

INSERT INTO tb_brand(name) VALUES ('Usafe');

INSERT INTO tb_product (name, description, price, quantity, img_url) VALUES ('Bonita bico PVC Nº38','Botina de Segurança com elastico bico pvc Solado Bidensidade Nº38', 45.60, 40, 'https://github.com/westorres9/dstools/blob/main/backend/src/main/resources/img/botinapvcusafe.png');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);

INSERT INTO tb_product_department (product_id, department_id) VALUES (1,1);

INSERT INTO tb_product_brand (product_id, brand_id) VALUES (1,1);

INSERT INTO tb_category_department (category_id, department_id) VALUES (1,1);