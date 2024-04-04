package main.DomainModel;

/*
* Class that represent the concrete object of the Public Places.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class PublicPlace extends Place {
    public PublicPlace(String name, String city, String address, int capacity,
                       boolean indoor) {
        super(name, city, address, capacity, indoor);
    }
}
