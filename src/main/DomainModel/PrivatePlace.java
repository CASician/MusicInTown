package main.DomainModel;

public class PrivatePlace extends Place {
    PlaceType type;
    protected int ownerId;
    protected String ownerName;

    public PrivatePlace(String city, String name, String address, int capacity,
                        boolean indoor, PlaceType type, int ownerId, String ownerName) {
        super(name, city, address, capacity, indoor);
        this.type = type;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public String getOwnerName() { return ownerName; }
    public int getOwnerId() { return ownerId; }
    public PlaceType getType() {
        return type;
    }
}
