/*
 ---------------------USERS------------------------
 */
CREATE TABLE BasicUsers(
    id SERIAL PRIMARY KEY,
    email varchar(50),
    city varchar(20),
    username varchar(50) not null
);

CREATE TABLE Municipalities (
    id INT PRIMARY KEY
);

ALTER TABLE Municipalities ADD CONSTRAINT basicUser_fk
    FOREIGN KEY(id) REFERENCES BasicUsers(id);

CREATE TABLE Musicians(
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    genre VARCHAR(50),
    componentNumb INT NOT NULL
);
ALTER TABLE Musicians ADD CONSTRAINT basicUser_fk
    FOREIGN KEY(id) REFERENCES BasicUsers(id);

CREATE TABLE Owners(
    id INT PRIMARY KEY,
    place VARCHAR(50),
    name VARCHAR(50)
);

ALTER TABLE Owners ADD CONSTRAINT basicUser_fk
    FOREIGN KEY(id) REFERENCES BasicUsers(id);

CREATE TABLE Planners(
    id INT PRIMARY KEY, CONSTRAINT basicUser_fk
        FOREIGN KEY(id) REFERENCES BasicUsers(id)
);

CREATE TABLE "Users"(
    id INT PRIMARY KEY, CONSTRAINT basicUser_fk
        FOREIGN KEY(id) REFERENCES BasicUsers(id)
);

/*
 -------------------------EVENTS-------------------------------
 */

CREATE TABLE Events(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) not null,
    open BOOLEAN,
    date DATE,
    city VARCHAR(50),
    type varchar(50),
    duration VARCHAR(50)
);
CREATE TABLE PrivateEvents(
    id INT PRIMARY KEY,
    place VARCHAR(50) not null,
    planner VARCHAR(50),
    ownerPlanner VARCHAR(50)
);

ALTER TABLE PrivateEvents ADD CONSTRAINT event_fk
    FOREIGN KEY(id) REFERENCES Events(id);

CREATE TABLE PublicEvents(
    id INT PRIMARY KEY,
    place VARCHAR(50),
    planner VARCHAR(50)
);
ALTER TABLE PublicEvents ADD CONSTRAINT event_fk
    FOREIGN KEY(id) REFERENCES Events(id);


/*
 ----------------------PLACES--------------------------
 */
CREATE TABLE Places(
    name VARCHAR(50) PRIMARY KEY,
    city VARCHAR(50),
    address VARCHAR(50),
    capacity INT,
    indoor BOOLEAN
);
ALTER TABLE Owners ADD CONSTRAINT place_fk
    FOREIGN KEY(place) REFERENCES Places(name);
ALTER TABLE PrivateEvents ADD CONSTRAINT place_fk
    FOREIGN KEY(place) REFERENCES Places(name);


CREATE TABLE PrivatePlaces (
    name VARCHAR(50) PRIMARY KEY,
    owner INT,
    type VARCHAR(20)
);

ALTER TABLE PrivatePlaces ADD CONSTRAINT place_fk
    FOREIGN KEY(name) REFERENCES Places(name);
ALTER TABLE PrivatePlaces ADD CONSTRAINT owner_fk
    FOREIGN KEY(owner) REFERENCES Owners(id);

CREATE TABLE PublicPlaces(
    name VARCHAR(50) PRIMARY KEY,
    surface INT
);
ALTER TABLE PublicPlaces ADD CONSTRAINT place_fk
    FOREIGN KEY (name) REFERENCES Places(name);