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

    DROP TABLE IF EXISTS EventsToBeAccepted CASCADE;
    DROP TABLE IF EXISTS Subscriptions CASCADE;

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
    ALTER TABLE Owners ADD CONSTRAINT owned_place_fk
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

------------------------------- WAITING LIST FOR EVENTS -------------------------

    CREATE TABLE IF NOT EXISTS EventsToBeAccepted (
        id_controller       INT,
        id_event            INT,
        FOREIGN KEY(id_controller) REFERENCES BasicUsers(id),
        FOREIGN KEY(id_event) REFERENCES Events(id)
    );

--------------------------------- SUBSCRIPTIONS ----------------------------------

    CREATE TABLE IF NOT EXISTS Subscriptions(
        id_subscriber   int,
        id_event        int,
        FOREIGN KEY(id_subscriber) REFERENCES Musicians(id),
        FOREIGN KEY(id_event) REFERENCES Events(id)
    );

---------------------------------- VIEWS ----------------------------------------

    DROP VIEW IF EXISTS planner_intero;
    DROP VIEW IF EXISTS privateplace_intero;
    DROP VIEW IF EXISTS publicplace_intero;
    DROP VIEW IF EXISTS publicEvents_intero;
    DROP VIEW IF EXISTS privateEvents_intero;
    DROP VIEW IF EXISTS owner_intero;
    DROP VIEW IF EXISTS privateevents_esteso_intero;
    DROP VIEW IF EXISTS publicevents_esteso_intero;
    DROP VIEW IF EXISTS privateplace_esteso_intero;
    DROP VIEW IF EXISTS municipality_intero;

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
           bu.username  as owner_username,
           o.place      as owner_place
    from owners o
             join basicusers bu on (o.id=bu.id);

    create view municipality_intero as
        select
            bu.id       as municipality_id,
            bu.username as municipality_username,
            m.city      as municipality_city
        from Municipalities m
        natural join BasicUsers bu;

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

--------------------------------- DATA ----------------------------------------

    INSERT INTO basicusers (id, username) VALUES (1, 'paba');
    INSERT INTO basicusers (id, username) VALUES (2, 'imaginedragons');
    INSERT INTO basicusers (id, username) VALUES (3, 'beatles');
    INSERT INTO basicusers (id, username) VALUES (4, 'annalisa');
    INSERT INTO basicusers (id, username) VALUES (5, 'maneskin');
    INSERT INTO basicusers (id, username) VALUES (6, 'florence');
    INSERT INTO basicusers (id, username) VALUES (8, 'dante');
    INSERT INTO basicusers (id, username) VALUES (9, 'oderisi');
    INSERT INTO basicusers (id, username) VALUES (10, 'provenzano');
    INSERT INTO basicusers (id, username) VALUES (11, 'jacopo');
    INSERT INTO basicusers (id, username) VALUES (12, 'virgilio');
    INSERT INTO basicusers (id, username) VALUES (13, 'cris');
    INSERT INTO basicusers (id, username) VALUES (14, 'pia');
    INSERT INTO basicusers (id, username) VALUES (15, 'guido');
    INSERT INTO basicusers (id, username) VALUES (16, 'giorgione');
    INSERT INTO basicusers (id, username) VALUES (17, 'franceschino');
    INSERT INTO basicusers (id, username) VALUES (30, 'tizio');
    INSERT INTO basicusers (id, username) VALUES (31, 'caio');
    INSERT INTO basicusers (id, username) VALUES (32, 'sempronio');

    ALTER SEQUENCE public.basicusers_id_seq RESTART WITH 33;

    INSERT INTO public.musicians (id, name, genre, componentnumb) VALUES (1, 'Paolo', 'Classic', 1);
    INSERT INTO public.musicians (id, name, genre, componentnumb) VALUES (2, 'Imagine Dragons', 'pop rock', 4);
    INSERT INTO public.musicians (id, name, genre, componentnumb) VALUES (3, 'Beatles', 'pop rock', 4);
    INSERT INTO public.musicians (id, name, genre, componentnumb) VALUES (4, 'Annalisa', 'pop', 1);
    INSERT INTO public.musicians (id, name, genre, componentnumb) VALUES (5, 'Maneskin', 'rock', 4);

    INSERT INTO public.municipalities (id, city) VALUES (6, 'Firenze');

    INSERT INTO public.planners (id, name) VALUES (13, 'Cristian');
    INSERT INTO public.planners (id, name) VALUES (14, 'Pia de Tolomei');
    INSERT INTO public.planners (id, name) VALUES (15, 'Guido Guinizzelli');
    INSERT INTO public.planners (id, name) VALUES (16, 'Giorgio Petrocchi');
    INSERT INTO public.planners (id, name) VALUES (17, 'Francesco Bausi');

    INSERT INTO public."Users" (id, name) VALUES (30, 'Tizio');
    INSERT INTO public."Users" (id, name) VALUES (31, 'Caio');
    INSERT INTO public."Users" (id, name) VALUES (32, 'Sempronio');

    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (1, 'Piazza Repubblica', 'Firenze', 'piazza Repubblica ', 70, false);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (2, 'Lungarno', 'Firenze', 'via Lungarno', 40, false);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (3, 'Circolo Arci', 'Fiesole', 'via Nazionale', 40, true);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (4, 'Teatro Fiesole', 'Fiesole', 'via Etrusca', 700, false);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (5, 'Piazzale Michelangiolo', 'Firenze', 'viale Michelangelo', 200, false);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (6, 'Inferno', 'Firenze', 'via del Girone', 100, true);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (7, 'Purgatorio', 'Fiesole', 'via della Balza', 40, true);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (8, 'Teatro Paradiso', 'Fiesole', 'via delle Cento Stelle', 700, true);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (9, 'Bottega di Pasticceria', 'Firenze', 'via Forese Donati', 20, true);
    INSERT INTO public.places (id, name, city, address, capacity, indoor) VALUES (10, 'Eden', 'Firenze', 'Piazzale delle Cascine', 60, true);
    ALTER SEQUENCE public.places_id_seq RESTART WITH 11;

    INSERT INTO public.owners (id, name, place) VALUES (8, 'Dante Alighieri', 'Eden');
    INSERT INTO public.owners (id, name, place) VALUES (9, 'Oderisi da Gubbio', 'Purgatorio');
    INSERT INTO public.owners (id, name, place) VALUES (10, 'Provenzano Salvani', 'Teatro Paradiso');
    INSERT INTO public.owners (id, name, place) VALUES (11, 'Jacopo del Cassero', 'Bottega di Pasticceria');
    INSERT INTO public.owners (id, name, place) VALUES (12, 'Virgilio', 'Inferno');

    INSERT INTO public.publicplaces (id) VALUES (1);
    INSERT INTO public.publicplaces (id) VALUES (2);
    INSERT INTO public.publicplaces (id) VALUES (3);
    INSERT INTO public.publicplaces (id) VALUES (4);
    INSERT INTO public.publicplaces (id) VALUES (5);

    INSERT INTO public.privateplaces (id, type, owner) VALUES (6, 'Restaurant', 'Virgilio');
    INSERT INTO public.privateplaces (id, type, owner) VALUES (7, 'Pub', 'Oderisi da Gubbio');
    INSERT INTO public.privateplaces (id, type, owner) VALUES (8, 'ConcertHall', 'Provenzano Salvani');
    INSERT INTO public.privateplaces (id, type, owner) VALUES (9, 'Cafe', 'Jacopo del Cassero');
    INSERT INTO public.privateplaces (id, type, owner) VALUES (10, 'LoungeBar', 'Dante Alighieri');

    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (1, 'Giornata della Musica', true, '2021-01-01', 'Firenze', 'Culturale', 'Un giorno', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (2, 'Festival - il Viaggio di Dante', true, '2022-02-02', 'Firenze', 'Culturale', 'Una settimana', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (3, 'Sagra di Settignano', true, '2023-03-03', 'Firenze', 'Culturale', 'Tre giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (4, 'Serata Rock', false, '2024-04-04', 'Firenze', 'Culturale', 'serata', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (5, 'Serata Indie', false, '2025-05-05', 'Firenze', 'Culturale', 'serata', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (6, 'Mostra Fotografica - Alluminar', true, '2021-06-06', 'Firenze', 'Culturale', 'serata', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (7, 'Matrimonio Infernale', false, '2022-07-07', 'Firenze', 'Culturale', 'serata', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (8, 'Aperitivo per Società Dantesca', false, '2023-08-08', 'Firenze', 'Culturale', 'serata', true);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (9, 'Torneo di Zara', true, '2024-09-09', 'Firenze', 'Culturale', 'serata', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (10, 'Concerto - Passione secondo Matteo', false, '2025-10-10', 'Firenze', 'Culturale', 'serata', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (11, 'Raperino', true, '2024-04-10', 'Firenze', 'L''ha proposto qualcuno', '4 giorni', null);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (12, 'Raperino 2', true, '2024-04-10', 'Firenze', 'Poco culturale', '4 giorni', null);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (13, 'Private Night', false, '2024-04-11', 'Firenze', 'Infernale', '1 giorni', null);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (14, 'All you can eat - Pizza', true, '2024-05-15', 'Firenze', 'Culinario', '1 giorni', true);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (15, 'Il boscaiuolo felice', true, '2024-04-17', 'Firenze', 'Not so cultural', '2 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (16, 'Il nido del magimbu', true, '2024-04-17', 'Firenze', 'Niente', '2 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (17, 'Boh', true, '2024-05-15', 'Firenze', 'Ih', '1 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (18, 'Finale?', true, '2025-05-25', 'Firenze', 'EHEhe', '2 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (19, 'Finalissimo?', true, '2024-06-12', 'Firenze', 'No.', '1 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (20, 'No.', true, '2026-12-31', 'Firenze', 'NO.', '1 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (21, 'Vabbe', true, '2025-12-31', 'Firenze', 'ool', '1 giorni', false);
    INSERT INTO public.events (id, name, open, date, city, type, duration, accepted) VALUES (22, 'Uno dei tanti', true, '2029-12-30', 'Firenze', 'Ma', '1 giorni', true);
    ALTER SEQUENCE public.events_id_seq RESTART WITH 23;

    INSERT INTO public.publicevents (id, place, planner) VALUES (1, 'Teatro Fiesole', 'pia');
    INSERT INTO public.publicevents (id, place, planner) VALUES (2, 'Piazzale Michelangiolo', 'cris');
    INSERT INTO public.publicevents (id, place, planner) VALUES (3, 'Lungarno', 'franceschino');
    INSERT INTO public.publicevents (id, place, planner) VALUES (4, 'Piazza Repubblica', 'giorgione');
    INSERT INTO public.publicevents (id, place, planner) VALUES (5, 'Circolo Arci', 'guido');

    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (6, 'Purgatorio', null, 'Oderisi da Gubbio');
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (7, 'Inferno', 'Pia de Tolomei', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (8, 'Eden', null, 'Dante Alighieri');
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (9, 'Purgatorio', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (10, 'Teatro Paradiso', 'Francesco Bausi', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (11, 'Eden', null, 'dante');
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (12, 'Eden', null, 'Dante Alighieri');
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (13, 'Inferno', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (14, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (15, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (16, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (17, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (18, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (19, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (20, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (21, 'Eden', 'Cristian', null);
    INSERT INTO public.privateevents (id, place, planner, ownerplanner) VALUES (22, 'Eden', 'Cristian', null);

    INSERT INTO public.eventstobeaccepted (id_controller, id_event) VALUES (9, 6);
    INSERT INTO public.eventstobeaccepted (id_controller, id_event) VALUES (12, 7);
    INSERT INTO public.eventstobeaccepted (id_controller, id_event) VALUES (9, 9);
    INSERT INTO public.eventstobeaccepted (id_controller, id_event) VALUES (10, 10);
    INSERT INTO public.eventstobeaccepted (id_controller, id_event) VALUES (9, 6);
