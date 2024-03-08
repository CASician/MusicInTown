/*
 Delete tables if already exists
 */
DROP TABLE IF EXISTS BasicUsers;
DROP TABLE IF EXISTS Municipalities;
DROP TABLE IF EXISTS Musicians;
DROP TABLE IF EXISTS Owners;
DROP TABLE IF EXISTS Planners;
DROP TABLE IF EXISTS "Users";

DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS PrivateEvents;
DROP TABLE IF EXISTS PublicEvents;

DROP TABLE IF EXISTS Places;
DROP TABLE IF EXISTS PrivatePlaces;
DROP TABLE IF EXISTS PublicPlaces;

/*
 ---------------------USERS------------------------
 */
CREATE TABLE IF NOT EXISTS BasicUsers(
    id       SERIAL PRIMARY KEY,
    email    varchar(50),
    city     varchar(20),
    username varchar(50) not null
);

CREATE TABLE IF NOT EXISTS Municipalities (
    id  INT PRIMARY KEY, CONSTRAINT basicUser_fk
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Musicians(
    id              INT PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    genre           VARCHAR(50),
    componentNumb   INT NOT NULL,
    FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Owners(
    id      INT PRIMARY KEY,
    place   VARCHAR(50),
    name    VARCHAR(50),
    FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Planners(
    id  INT PRIMARY KEY, CONSTRAINT basicUser_fk
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Users"(
    id  INT PRIMARY KEY, CONSTRAINT basicUser_fk
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
 -------------------------EVENTS-------------------------------
 */

CREATE TABLE IF NOT EXISTS Events(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50) not null,
    open     BOOLEAN,
    date     DATE,
    city     VARCHAR(50),
    type     VARCHAR(50),
    duration VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS PrivateEvents(
    id              INT PRIMARY KEY,
    place           VARCHAR(50) not null,
    planner         VARCHAR(50),
    ownerPlanner    VARCHAR(50),
    FOREIGN KEY(id) REFERENCES Events(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PublicEvents(
    id      INT PRIMARY KEY,
    place   VARCHAR(50),
    planner VARCHAR(50),
    FOREIGN KEY(id) REFERENCES Events(id) ON UPDATE CASCADE ON DELETE CASCADE
);


/*
 ----------------------PLACES--------------------------
 */
CREATE TABLE IF NOT EXISTS Places(
    name        VARCHAR(50) PRIMARY KEY,
    city        VARCHAR(50),
    address     VARCHAR(50),
    capacity    INT,
    indoor      BOOLEAN
);
ALTER TABLE Owners ADD CONSTRAINT place_fk
    FOREIGN KEY(place) REFERENCES Places(name) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PrivateEvents ADD CONSTRAINT place_fk
    FOREIGN KEY(place) REFERENCES Places(name) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS PrivatePlaces (
    name    VARCHAR(50) PRIMARY KEY,
    owner   INT,
    type    VARCHAR(20),
    FOREIGN KEY(name) REFERENCES Places(name) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(owner) REFERENCES Owners(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PublicPlaces(
    name    VARCHAR(50) PRIMARY KEY,
    surface INT,
    FOREIGN KEY (name) REFERENCES Places(name) ON UPDATE CASCADE ON DELETE CASCADE
);
