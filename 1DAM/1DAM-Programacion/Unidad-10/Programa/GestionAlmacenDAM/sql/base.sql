CREATE TABLE IF NOT EXISTS "Article" (
                                            "PIC" TEXT  NOT NULL,
                                            "article" TEXT NOT NULL,
                                            "description" TEXT NOT NULL,
                                            "location" TEXT NOT NULL,
                                            "price" REAL NOT NULL,
                                            "stock" INTEGER NOT NULL,
                                            "isActive" BOOLEAN NOT NULL,
                                            "photo" TEXT NOT NULL,
                                            PRIMARY KEY("PIC"));

CREATE TABLE IF NOT EXISTS "Customer" (
                                         "CIC" TEXT  NOT NULL,
                                         "name" TEXT NOT NULL,
                                         "surname" TEXT NOT NULL,
                                         "cif" TEXT NOT NULL,
                                         "direction" TEXT NOT NULL,
                                         "nickname" TEXT NOT NULL,
                                         "password" TEXT NOT NULL,
                                         "telephoneNumber" String NOT NULL,
                                         "email" TEXT NOT NULL,
                                         "photo" TEXT NOT NULL,
                                         "createdAt" TEXT NOT NULL,
                                         "isActive" BOOLEAN NOT NULL,
                                         PRIMARY KEY("CIC"));

CREATE TABLE IF NOT EXISTS "Employee" (
                                         "EIC" TEXT  NOT NULL,
                                         "name" TEXT NOT NULL,
                                         "surname" TEXT NOT NULL,
                                         "nif" TEXT NOT NULL,
                                         "email" TEXT NOT NULL,
                                         "photo" TEXT NOT NULL,
                                         "nickname" TEXT NOT NULL,
                                         "password" TEXT NOT NULL,
                                         "isManager" BOOLEAN NOT NULL,
                                         "createdAt" TEXT NOT NULL,
                                         "isActive" BOOLEAN NOT NULL,
                                         PRIMARY KEY("EIC"));

CREATE TABLE IF NOT EXISTS "LineOrder" (
                                         "OLIC" TEXT  NOT NULL,
                                         "article" TEXT NOT NULL,
                                         "load" INTEGER NOT NULL,
                                         "unitPrice" REAL NOT NULL,
                                         "totalPrice" REAL NOT NULL,
                                         "BelongsOrder" TEXT NOT NULL,
                                         PRIMARY KEY("OLIC"),
                                         FOREIGN KEY ("BelongsOrder") REFERENCES "Order" ("OIC")
                                         );

CREATE TABLE IF NOT EXISTS "LineReception" (
                                          "RLIC" TEXT  NOT NULL,
                                         "article" TEXT NOT NULL,
                                         "load" INTEGER NOT NULL,
                                         "unitPrice" REAL NOT NULL,
                                         "totalPrice" REAL NOT NULL,
                                          "BelongsReception" TEXT NOT NULL,
                                         PRIMARY KEY("RLIC"),
                                         FOREIGN KEY("BelongsReception") REFERENCES Reception ("RIC")
                                           );

CREATE TABLE IF NOT EXISTS "Order" (
                                         "OIC" TEXT  NOT NULL,
                                         "Customer" TEXT NOT NULL,   
                                         "Price" REAL NOT NULL,
                                         "Pay" TEXT NOT NULL,
                                         PRIMARY KEY("OIC"));

CREATE TABLE IF NOT EXISTS "Reception" (
                                         "RIC" TEXT  NOT NULL,
                                         "Supplier" TEXT NOT NULL,   
                                         "Carrier" TEXT NOT NULL,
                                         "Cost" REAL NOT NULL,
                                         PRIMARY KEY("RIC"));

CREATE TABLE IF NOT EXISTS "Supplier" (
                                         "SIC" TEXT  NOT NULL,
                                         "nameSupplier" TEXT NOT NULL,   
                                         "direction" TEXT NOT NULL,
                                         "telephoneNumber" TEXT NOT NULL,
					                    "email" TEXT NOT NULL,
                                         PRIMARY KEY("SIC"));
