package main.DomainModel;

public class PublicPlace extends Place {
    public int surface;

    public PublicPlace(int id, String city, String address, int placeCapacity,
                       boolean indoor) {
        super(id, city, address, placeCapacity, indoor);
    }

}