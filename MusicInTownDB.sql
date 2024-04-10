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
        name            VARCHAR(50) UNIQUE NOT NULL,
        genre           VARCHAR(50),
        componentNumb   INT NOT NULL,
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS Owners(
        id      INT PRIMARY KEY,
        name    VARCHAR(50) UNIQUE NOT NULL,
        place   VARCHAR(50),
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS Planners(
        id  INT PRIMARY KEY,
        name VARCHAR(50) UNIQUE NOT NULL,
        FOREIGN KEY(id) REFERENCES BasicUsers(id) ON UPDATE CASCADE ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS "Users"(
        id  INT PRIMARY KEY,
        name VARCHAR(50) UNIQUE NOT NULL,
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
        accepted BOOLEAN
    );

    CREATE TABLE IF NOT EXISTS PrivateEvents(
        id              INT PRIMARY KEY,
        place           VARCHAR(50) NOT NULL,
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
---------------------------------- VIEWS ----------------------------------------

    DROP VIEW IF EXISTS planner_intero;
    DROP VIEW IF EXISTS privateplace_intero;
    DROP VIEW IF EXISTS publicplace_intero;
    DROP VIEW IF EXISTS publicEvents_intero;
    DROP VIEW IF EXISTS privateEvents_intero;
    DROP VIEW IF EXISTS owner_intero;
    DROP VIEW IF EXISTS privateevents_esteso_intero;
    DROP VIEW IF EXISTS privateplace_esteso_intero;

    create view planner_intero as
    select p.id         as planner_id,
           p.name       as planner_name,
           bu.username  as planner_username
    from planners p
    join basicusers bu on (bu.id = p.id);


    create view privateplace_intero as
    select pp.id        as privatePlace_id,
           p.name       as privatePlace_name,
           pp.type      as privatePlace_type,
           p.city       as privatePlace_city,
           p.address    as privatePlace_address,
           p.capacity   as privatePlace_capacity,
           p.indoor     as privatePlace_indoor,
           pp.owner     as privatePlace_ownerName
    from privateplaces pp
    join places p on (p.id = pp.id);


    create view publicplace_intero as
    select pp.id        as publicplace_id,
           p.name       as publicplace_name,
           p.city       as publicplace_city,
           p.address    as publicplace_address,
           p.capacity   as publicplace_capacity,
           p.indoor     as publicplace_indoor
    from publicplaces pp
             join places p on (p.id = pp.id);

    create view publicEvents_intero as
    select e.id         as publicEvent_id,
           e.name       as publicEvent_name,
           pe.place     as publicevent_place,
           pe.planner   as publicevent_planner,
           e.open       as publicevent_open,
           e.date       as publicevent_date,
           e.city       as publicevent_city,
           e.type       as publicevent_type,
           e.duration   as publicevent_duration,
           e.accepted   as publicevent_accepted
    from publicEvents pe
             join events e on (pe.id = e.id);


    create view privateEvents_intero as
    select e.id            as privateEvent_id,
           e.name          as privateEvent_name,
           pe.place        as privateEvent_place,
           pe.planner      as privateEvent_plannername,
           pe.ownerplanner as privateEvent_ownerplannername,
           e.open          as privateEvent_open,
           e.date          as privateEvent_date,
           e.city          as privateEvent_city,
           e.type          as privateEvent_type,
           e.duration      as privateEvent_duration,
           e.accepted      as privateEvent_accepted
    from privateEvents pe
             join events e on (pe.id=e.id);


    create view owner_intero as
    select o.id         as owner_id,
           o.name       as owner_name,
           bu.username  as owner_username
    from owners o
             join basicusers bu on (o.id=bu.id);

    create view privateevents_esteso_intero as
    select *
    from privateevents_intero pei
             left join planner_intero pli on (pei.privateevent_plannername = pli.planner_name)
             left join owner_intero oi on (pei.privateevent_ownerplannername = oi.owner_name)
             left join privateplace_intero ppi on (pei.privateevent_place = ppi.privateplace_name);

    create view privateplace_esteso_intero as
    select *
    from privateplace_intero ppi
             join owner_intero oi on (ppi.privateplace_ownername = oi.owner_name);

create view publicevents_esteso_intero as
    select *
    from publicEvents_intero pEi
        left join planner_intero pi on (pi.planner_username = pEi.publicevent_planner)
        left join publicplace_intero ppi on (ppi.publicplace_name = pEi.publicevent_place);