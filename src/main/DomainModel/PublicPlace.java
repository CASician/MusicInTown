package main.DomainModel;

public class PublicPlace extends Place {
    public int surface;

    public PublicPlace(int id, String city, String name, String address, int placeCapacity,
                       boolean indoor, int surface) {
        super(id, city, name, address, placeCapacity, indoor);
        this.surface = surface;
    }

    public int getSurface() {
        return surface;
    }
}
