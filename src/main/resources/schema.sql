DROP TABLE IF EXISTS product;
  
create table product (
id int IDENTITY PRIMARY KEY, 
brand varchar(255) ,
 model varchar(255) ,
  price decimal(19,2) , 
  purchase_date date,
   serial_number varchar(255), 
   status varchar(255) 
   
   );
   
   