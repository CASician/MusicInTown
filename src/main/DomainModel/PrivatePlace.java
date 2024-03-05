package main.DomainModel;

public class PrivatePlace extends Place {
    private final int ownerId;
    String name;
    PlaceType type;

    public PrivatePlace(int id, String city, String name, String address, int placeCapacity,
                        boolean indoor, int ownerId, PlaceType type) {
        super(id, city, address, placeCapacity, indoor);
        this.name = name;
        this.ownerId = ownerId;
        this.type = type;

    }

    public int getOwnerId() {
        return ownerId;
    }

    public PlaceType getType() {
        return type;
    }
}
