package main.DomainModel;

public abstract class Place {
    int id;
    String city;
    String address;
    int capacity;

    boolean indoor;

    public Place(int placeId, String city, String address, int capacity, boolean indoor) {
        this.id = placeId;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.indoor = indoor;
    }

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
