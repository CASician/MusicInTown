package main.DomainModel;

/*
* Class that represent the concrete object of the Private Places.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
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

    public String getOwnerName(){
        return owner.getName();
    }
    public Owner getOwner(){return owner;}
}
