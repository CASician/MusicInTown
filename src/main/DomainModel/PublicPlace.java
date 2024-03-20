package main.DomainModel;

public class PublicPlace extends Place {
    private final String name;

    public PublicPlace(String name, String city, String address, int placeCapacity,
                       boolean indoor) {
        super(city, address, placeCapacity, indoor);
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
