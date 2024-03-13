package main.DomainModel;

public class PrivatePlace extends Place {
    private final String name;
    PlaceType type;

    public PrivatePlace(String name, String city, String address, int placeCapacity,
                        boolean indoor, PlaceType type) {
        super(name, city, address, placeCapacity, indoor);
        this.name = name;
        this.type = type;

    }

    public String getName() {
        return name;
    }
    public PlaceType getType() {
        return type;
    }
}
