package main.DomainModel;

public class PrivatePlace extends Place {
    private final String ownerUsername;
    private final String name;
    PlaceType type;

    public PrivatePlace(int id, String city, String name, String address, int placeCapacity,
                        boolean indoor, String ownerUsername, PlaceType type) {
        super(id, city, address, placeCapacity, indoor);
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public PlaceType getType() {
        return type;
    }
}
