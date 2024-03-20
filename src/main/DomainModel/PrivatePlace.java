package main.DomainModel;

public class PrivatePlace extends Place {
    PlaceType type;
    protected Owner owner;

    public PrivatePlace(String city, String name, String address, int capacity,
                        boolean indoor, PlaceType type, Owner owner) {
        super(name, city, address, capacity, indoor);
        this.type = type;
        this.owner = owner;
    }
    public PlaceType getType() {
        return type;
    }
}
