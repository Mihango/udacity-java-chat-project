ALTER TABLE REVIEW
ADD CONSTRAINT product_fk
FOREIGN KEY foreign_key_name(PRODUCT_ID)
REFERENCES PRODUCT(ID)
ON DELETE NO ACTION
ON UPDATE CASCADE;


ALTER TABLE COMMENT
ADD CONSTRAINT comment_fk
FOREIGN KEY (REVIEW_ID)
REFERENCES REVIEW(ID)
ON DELETE NO ACTION
ON UPDATE CASCADE;
