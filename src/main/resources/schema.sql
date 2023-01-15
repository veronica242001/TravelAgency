CREATE TABLE IF NOT EXISTS customers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    address VARCHAR(50),
    birth_date datetime,
    gender VARCHAR(50),
    UNIQUE(first_name, last_name),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS agents (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(50),
    salary DOUBLE,
    UNIQUE(first_name, last_name),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS agencies (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) ,
    address VARCHAR(50),
    UNIQUE(name),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS agents_agencies (
    id BIGINT NOT NULL AUTO_INCREMENT,
    agents BIGINT NOT NULL,
    agencies BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (agents) REFERENCES agencies(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (agencies) REFERENCES agents(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS accommodations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) ,
    address VARCHAR(50),
    time_start datetime,
    time_end datetime,
    price DOUBLE,
    UNIQUE(name),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS transportations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) ,
    address_to VARCHAR(50),
    address_FROM VARCHAR(50),
    time_start datetime,
    time_end datetime,
    price DOUBLE,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS offers (
     id BIGINT NOT NULL AUTO_INCREMENT,
     book_date datetime,
     PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS offers_transportations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    offers BIGINT NOT NULL,
    transportations BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (offers) REFERENCES transportations(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (transportations) REFERENCES offers(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
CREATE TABLE IF NOT EXISTS offers_accommodations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    offers BIGINT NOT NULL,
    accommodations BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (offers) REFERENCES accommodations(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (accommodations) REFERENCES offers(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS agents_offers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    agents BIGINT ,
    offers BIGINT ,
    PRIMARY KEY (id),
    FOREIGN KEY (agents) REFERENCES offers(id)  ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (offers) REFERENCES agents(id)  ON DELETE CASCADE ON UPDATE CASCADE
    );
CREATE TABLE IF NOT EXISTS customers_offers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    customers BIGINT,
    offers BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (offers) REFERENCES customers(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (customers) REFERENCES offers(id) ON DELETE CASCADE ON UPDATE CASCADE
    );