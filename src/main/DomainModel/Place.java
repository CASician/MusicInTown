package main.DomainModel;

/*
* Abstract class that has all the common attributes and methods for all the place objects
*/
public abstract class Place {

    protected final int id;
    protected final String city;
    protected final String address;
    protected final int capacity;

    protected final boolean indoor;

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

    public boolean isIndoor() { return indoor; }
}
