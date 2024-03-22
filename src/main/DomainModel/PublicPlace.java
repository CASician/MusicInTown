package main.DomainModel;

/*
* Class that represent the concrete object of the Public Places.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class PublicPlace extends Place {

    public PublicPlace(int id, String city, String address, int placeCapacity,
                       boolean indoor) {
        super(id, city, address, placeCapacity, indoor);
    }

}
