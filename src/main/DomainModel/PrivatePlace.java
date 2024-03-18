package main.DomainModel;

public class PrivatePlace extends Place {
    private final String name;
    PlaceType type;
    protected int ownerId;
    protected String ownerName;

    public PrivatePlace(int id, String city, String name, String address, int placeCapacity,
                        boolean indoor, PlaceType type, int ownerId, String ownerName) {
        super(id, city, address, placeCapacity, indoor);
        this.name = name;
        this.type = type;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public String getOwnerName() { return ownerName; }
    public int getOwnerId() { return ownerId; }
    public String getName() {
        return name;
    }
    public PlaceType getType() {
        return type;
    }
}
