package main.DomainModel;

public abstract class Place {
    private final int id;
    private final String city;
    private final String address;
    int capacity;

    boolean indoor;

    public Place(int placeId, String city, String address, int capacity, boolean indoor) {
        this.id = placeId;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.indoor = indoor;
    }

    public int getId() { return id; }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }
}
