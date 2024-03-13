package main.DomainModel;

public abstract class Place {
    String name;
    String city;
    String address;
    int capacity;

    boolean indoor;

    public Place(String name, String city, String address, int capacity, boolean indoor) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.indoor = indoor;
    }

    public String getName(){return name;}
    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }
    public Boolean isIndoor(){return indoor;}
}
