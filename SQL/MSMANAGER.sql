CREATE SCHEMA MSMANAGER;

CREATE TABLE EMPLOYEE(
	SSN INT NOT NULL,
    Fname VARCHAR(20) NOT NULL,
    Lname VARCHAR(20) NOT NULL,
    PhoneNumber CHAR(8) NOT NULL,
    Salary INT NOT NULL,
    Address VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NULL,
    JobPosition VARCHAR(20) NOT NULL,
    ContractType VARCHAR(20) NOT NULL,
    PRIMARY KEY (SSN)
);

CREATE TABLE MALL(
	MallName VARCHAR(20) NOT NULL,
    Location VARCHAR(50) NOT NULL,
    Size VARCHAR(50) NULL,
    OpeningHours VARCHAR(20) NOT NULL,
    PRIMARY KEY (MallName, Location)
);

CREATE TABLE SHOP(
	ShopName VARCHAR(20) NOT NULL,
    ShopType VARCHAR(50) NOT NULL,
    OpeningHours VARCHAR(20) NOT NULL,
    AnnualSales INT NULL,
    PRIMARY KEY (ShopName)
);

CREATE TABLE MALL_EMPLOYEE(
	Employee_SSN INT NOT NULL,
    Mall_Name VARCHAR(20) NOT NULL,
    Mall_Location VARCHAR(50) NOT NULL,
    PRIMARY KEY (Employee_SSN, Mall_Name, Mall_Location),
    FOREIGN KEY (Employee_SSN) REFERENCES EMPLOYEE(SSN) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (Mall_Name, Mall_Location) REFERENCES MALL(MallName, Location) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE SHOP_EMPLOYEE(
	Employee_SSN INT NOT NULL,
    Shop_Name VARCHAR(20) NOT NULL,
    PRIMARY KEY (Employee_SSN, Shop_Name),
    FOREIGN KEY (Employee_SSN) REFERENCES EMPLOYEE(SSN) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (Shop_Name) REFERENCES SHOP(ShopName) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE HAS(
	Mall_Name VARCHAR(20) NOT NULL,
    Mall_Location VARCHAR(50) NOT NULL,
    Shop_Name VARCHAR(20) NOT NULL,
    PRIMARY KEY (Mall_Name, Mall_Location, Shop_Name),
    FOREIGN KEY (Mall_Name, Mall_Location) REFERENCES MALL(MallName, Location) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (Shop_Name) REFERENCES SHOP(ShopName) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE PRODUCT_OFFERED(
	Shop_Name VARCHAR(20) NOT NULL,
    ProductType VARCHAR(20) NOT NULL,
    PRIMARY KEY (Shop_Name, ProductType),
    FOREIGN KEY (Shop_Name) REFERENCES SHOP(ShopName) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE CUSTOMER(
	CustomerName VARCHAR(20) NOT NULL,
    PhoneNumber CHAR(8) NOT NULL,
    Email VARCHAR(50) NULL,
    BirthDate VARCHAR(20) NULL,
    PRIMARY KEY (CustomerName, PhoneNumber)
);

CREATE TABLE BUYS_FROM(
	Shop_Name VARCHAR(20) NOT NULL,
    Customer_Name VARCHAR(20) NOT NULL,
    Customer_PhoneNumber CHAR(8) NOT NULL,
    PRIMARY KEY (Shop_Name, Customer_Name, Customer_PhoneNumber),
    FOREIGN KEY (Shop_Name) REFERENCES SHOP(ShopName) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (Customer_Name, Customer_PhoneNumber) REFERENCES CUSTOMER(CustomerName, PhoneNumber) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE VIEW WhereToFindProductX AS
SELECT p.ProductType, m.MallName, m.Location, COUNT(p.Shop_Name)
FROM MALL m, HAS h, PRODUCT_OFFERED p
WHERE h.Mall_Name = m.MallName AND h.Mall_Location = m.Location AND h.Shop_Name = p.Shop_Name
GROUP BY p.ProductType, m.MallName, m.Location
ORDER BY p.ProductType, m.MallName, m.Location;

CREATE VIEW MALLSHOPPING AS
SELECT Mall_Name, Mall_Location, Shop_Name
FROM HAS
ORDER BY Mall_Name, Mall_Location, Shop_Name;

CREATE VIEW SHOPEMP AS
SELECT e.Fname, e.Lname, e.JobPosition, s.Shop_Name
FROM EMPLOYEE e, SHOP_EMPLOYEE s
WHERE e.SSN = s.Employee_SSN
ORDER BY s.Shop_Name;

CREATE INDEX INDX_NAMLOC ON MALL(MallName, Location); #primary index
CREATE INDEX INDX_EMPFNAM ON EMPLOYEE(FName); #clustered index
CREATE INDEX INDX_EMPLNAM ON EMPLOYEE(LName); #clustered secondary index
CREATE INDEX INDX_HASMAL ON HAS(Mall_Name); #clustered index
CREATE INDEX INDX_HASEMP ON HAS(Shop_Name); #secondary clustered index

DELIMITER //
CREATE PROCEDURE returnCustomers (shName VARCHAR(20))
BEGIN
	SELECT c.CustomerName, c.PhoneNumber, c.Email, c.BirthDate
    FROM CUSTOMER c, BUYS_FROM b
    WHERE c.CustomerName = b.Customer_Name AND c.PhoneNumber = b.Customer_PhoneNumber AND b.Shop_Name = shName ;
END //
DELIMITER ;
