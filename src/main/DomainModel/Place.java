package main.DomainModel;

public abstract class Place {
    int placeId;
    String city;
    String name;
    String address;
    int placeCapacity;

    boolean indoor;

    public Place(int placeId, String city, String name, String address, int placeCapacity, boolean indoor) {
        this.placeId = placeId;
        this.city = city;
        this.name = name;
        this.address = address;
        this.placeCapacity = placeCapacity;
        this.indoor = indoor;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPlaceCapacity() {
        return placeCapacity;
    }
}
