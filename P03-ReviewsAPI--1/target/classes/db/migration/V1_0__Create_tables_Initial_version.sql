CREATE TABLE PRODUCT
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(150),
    PRICE DOUBLE NOT NULL
);

CREATE TABLE REVIEW
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID INT NOT NULL,
    REVIEWER VARCHAR(100),
    REVIEW VARCHAR(150) NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT product_fk FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
);

CREATE TABLE COMMENT
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    REVIEW_ID INT NOT NULL,
    USER VARCHAR(50),
    COMMENT VARCHAR(100) NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT comment_fk FOREIGN KEY (REVIEW_ID) REFERENCES REVIEW(ID)
);