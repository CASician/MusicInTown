package main.DomainModel;

public class PrivatePlace extends Place {
    private final int ownerId;

    PlaceType type;

    public PrivatePlace(int id, String city, String name, String address, int placeCapacity,
                        boolean indoor, int ownerId, PlaceType type) {
        super(id, city, name, address, placeCapacity, indoor);
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
