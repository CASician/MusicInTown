# MUSIC IN TOWN
### By Paolo Sbarzagli e Cristian Sician

### Tabella del database (prima bozza)
1. table basicUsers(PK id, email, city, username)
   - table municipality(PK FK(basicUser))
   - table musicians(PK FK(basicUser), name, genre, componentNumb, foundation)
   - table owner(PK (FK(basicUser), place), name)
   - table planner(PK FK(basicUser))
   - table user(PK FK(basicUser))
1. table events(PK id, open, date)
   - table privateEvents(PK FK(events), place(privatePlace), planner, ownerPlanner)
   - table publicEvents(PK FK(events), place(publicPlace), planner)
1. table place(PK id, city, name, address, capacity, indoor)
   - table privatePlace(PK FK(place), FK(owner), type)
   - table publicPlace(PK FK(place), surface)
