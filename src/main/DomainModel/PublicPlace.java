package main.DomainModel;

public class PublicPlace extends Place {
    private final String name;

    public PublicPlace(String name, int id, String city, String address, int placeCapacity,
                       boolean indoor) {
        super(id, city, address, placeCapacity, indoor);
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
