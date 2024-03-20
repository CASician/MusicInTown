package main.DomainModel;

public abstract class Place {
    private int id;
    private final String name;
    private final String city;
    private final String address;
    int capacity;

    boolean indoor;

    public Place(String name, String city, String address, int capacity, boolean indoor) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.indoor = indoor;
    }

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public boolean isIndoor(){
        return indoor;
    }
}
