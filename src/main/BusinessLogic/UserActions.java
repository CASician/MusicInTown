package main.BusinessLogic;

public enum UserActions {
    LOGIN,
    EXIT;

    public enum BasicUser {
        Exit,
        SeeEventsMenu,
        SeeInfo
    }

    public enum MusicianActions {
        Exit,
        FilterEvents,
        SeeAllEvents,
        SeeEventsSubscriptions,
        SubscribeEvent,

    };

    //TODO: implement all the possible actions
    public enum OwnerActions {

    }

}
