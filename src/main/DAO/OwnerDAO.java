package main.DAO;

import main.DomainModel.Owner;
import main.DomainModel.PrivatePlace;

public class OwnerDAO {
    Owner owner;

    public OwnerDAO(String owner, PrivatePlace privatePlace) {
        //Access the database to retrieve the info of the owner and generate a new instance
        this.owner = new Owner("owner", owner, "owner@gmail.com", 5,"Milano", privatePlace);
    }

    public Owner getOwner() {
        return owner;
    }
}
