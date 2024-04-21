# MUSIC IN TOWN
### By Paolo Sbarzagli e Cristian Sician


## Presentazione
Questo è il progetto elaborato da due studenti del corso di Ingegneria del Software 2023, Ingegneria Informatica all'UNIFI.
Presenta un applicativo per la gestione degli spazi adibiti a esibizioni musicali nel territorio di un comune. 
Possono usare questo applicativo gli artisti, i gestori di locali privati e il comune stesso.

## Architettura
L'architettura del progetto è divisa in quattro sezioni interne e una esterna: il database. 

### 1. Domain Model
Nel Domain Model viene rappresentata la realtà degli oggetti e degli attori del progetto.
Nel nostro caso, vengono rappresentati tramite classi gli utenti: musicisti, possessori di locali, organizzatori e il municipio. 
Essi possono contenere Eventi e Posti ma non interagirgi direttamente. 

### 2. Business Logic
Il compito di fare interagire le varie classi tra di loro è responsabilità della Business Logic, che si declina in diversi Controllori. 
Ogni classe ha il suo controllore. Se ad esempio vuoi interagire con un evento, avrai bisogno di un EventController. 

### 3. Interface
L'utente viene immaginato privo della possibilità di accedere al codice. Pertanto si realizza un'interfaccia (molto elementare nel nostro caso)
che lo aiuti a compiere le azioni desiderate, stampando a schermo istruzioni concise sull'utilizzo dell'applicativo. 

### 4. DAO - Data Access Object
Per poter accedere al Data Base è necessario avere un intermediario. Queste classi manipolano direttamente istruzioni in linguaggio SQL, 
accedendo al data base e modificandolo. Queste funzioni vengono poi inglobate (generalmente) dai Controller. 

### 5. DataBase

Di seguito una rappresenzatione della struttura base del nostro DB. 

1. table basicUsers(PK id, username)
   - table municipalities(PK id FK(basicUsers.id), city)
   - table musicians(PK id FK(basicUsers.id), name, genre, componentNumb)
   - table owners(PK id FK(basicUsers.id), place FK(places.name), name)
   - table planners(PK id FK(basicUsers.id), name)
   - table users(PK id FK(basicUsers.id), name)
1. table events(PK id, name, open, date, city, type, duration, accepted)
   - table privateEvents(PK id FK(events.id), place, planner, ownerPlanner)
   - table publicEvents(PK id FK(events.id), place, planner)
1. table place(PK id, name, city, address, capacity, indoor)
   - table privatePlace(PK id FK(places.id), owner FK(owners.name), type)
   - table publicPlace(PK id FK(places.id))
1. table eventsToBeAccepted(id_controller FK(basicUsers.id), id_event FK(events.id))
1. table subscriptions(id_subscriber FK(musicians.id), id_event FK(events.id))

Il tutto è supportato anche da diverse viste che permettono di visualizzare le informazioni nella loro interezza.
Come ad esempio, vedere un evento privato con tutti i dettagli dell'evento, dell'organizzatore e dell'owner del posto in un'unica tabella.

Per replicare il database, è possibile copiare la query presente in MusicInTownDB.sql.
In essa sono presente anche le query per popolare il database. Grazie ai dati aggiunti in tal modo è possibile vedere il completo funzionamento del programma. 

Di seguito le istruzioni che abbiamo seguito noi per collegare il DB al programma:
- scaricare JDBC e connetterlo all'IDE utilizzato (Jetbrains Idea)
- installare PostgresSQL e pgAdmin4
- collegare il DB al progetto tramite l'interfaccia fornita dall'IDE.
- assicurarsi di usare gli stessi account e password. (cfr. /DAO/DBconnection)

