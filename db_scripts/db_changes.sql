ALTER TABLE SHOP_ADMIN.Felhasznalo MODIFY hash VARCHAR2(60);
ALTER TABLE SHOP_ADMIN.Felhasznalo drop column so;

drop sequence felhasznalo_seq;
CREATE SEQUENCE felhasznalo_seq START WITH 31; 
ALTER TABLE SHOP_ADMIN.felhasznalo MODIFY (id DEFAULT felhasznalo_seq.NEXTVAL); 