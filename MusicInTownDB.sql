    /*
     MODELLO DEL DATABASE

     1. table basicUsers(PK id, email, city, username)
       - table municipalities(PK FK(basicUser.id))
       - table musicians(PK FK(basicUser.id), name, genre, componentNumb)
       - table owner(PK (FK(basicUser.id), place.id), name)
       - table planner(PK FK(basicUser.id))
       - table user(PK FK(basicUser.id))
    1. table events(PK id, name, open, date, city, type, duration)
       - table privateEvents(PK FK(events.id), place FK(privatePlace.id), planner, ownerPlanner)
       - table publicEvents(PK FK(events.id), place FK(publicPlace.id), planner)
    1. table place(PK id, name, city, address, capacity, indoor)
       - table privatePlace(PK FK(place.id), FK(owner.id), type)
       - table publicPlace(PK FK(place.id), surface)
    1. table events_created
    1. table events_subscribed


     */


    /*
     Delete tables if already exists
     */
    DROP TABLE IF EXISTS BasicUsers CASCADE;
    DROP TABLE IF EXISTS Municipalities CASCADE;
    DROP TABLE IF EXISTS Musicians CASCADE;
    DROP TABLE IF EXISTS Owners CASCADE;
    DROP TABLE IF EXISTS Planners CASCADE;
    DROP TABLE IF EXISTS "Users" CASCADE;

    DROP TABLE IF EXISTS Events CASCADE;
    DROP TABLE IF EXISTS PrivateEvents CASCADE;
    DROP TABLE IF EXISTS PublicEvents CASCADE;

    DROP TABLE IF EXISTS Places CASCADE;
    DROP TABLE IF EXISTS PrivatePlaces CASCADE;
    DROP TABLE IF EXISTS PublicPlaces CASCADE;

    /*
     ---------------------USERS------------------------
     */
    CREATE TABLE IF NOT EXISTS BasicUsers(
        id       SERIAL PRIMARY KEY,
        username VARCHAR(50) UNIQUE NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Municipalities (
        id  INT PRIMARY KEY,
        city VARCHAR(20),
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
        name    VARCHAR(50) UNIQUE,
        place   VARCHAR(50),
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS Planners(
        id  INT PRIMARY KEY,
        name VARCHAR(50),
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS "Users"(
        id  INT PRIMARY KEY,
        name VARCHAR(50),
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
    );

    /*
     -------------------------EVENTS-------------------------------
     */

    CREATE TABLE IF NOT EXISTS Events(
        id       SERIAL PRIMARY KEY,
        name     VARCHAR(50) UNIQUE NOT NULL,
        open     BOOLEAN,
        date     DATE,
        city     VARCHAR(50),
        type     VARCHAR(50),
        duration VARCHAR(50),
        accepted BOOLEAN    -- TODO: add this in the DAO
    );

    CREATE TABLE IF NOT EXISTS PrivateEvents(
        id              INT PRIMARY KEY,
        place           VARCHAR(50) NOT NULL,
        -- TODO: make sure that planner and OwnerPlanner actually refer to a valid ID. They are FK from planners and owners.
        -- or maybe it's not necessary: if you do it, you need to pass the ID and not the username.
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
        id          SERIAL PRIMARY KEY,
        name        VARCHAR(50) UNIQUE,
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
        id      INT PRIMARY KEY,
        type    VARCHAR(20),
        owner   VARCHAR(50),
        FOREIGN KEY(id) REFERENCES Places(id) ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY(owner) REFERENCES Owners(name) ON UPDATE CASCADE ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS PublicPlaces(
        id      INT PRIMARY KEY,
        FOREIGN KEY (id) REFERENCES Places(id) ON UPDATE CASCADE ON DELETE CASCADE
    );
