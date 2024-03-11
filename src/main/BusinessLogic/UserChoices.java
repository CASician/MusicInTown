package main.BusinessLogic;

public enum UserChoices {
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

    public enum UserActions {
        Exit,
        FilterEvents,
        SeeAllEvents,
    };

    //TODO: implement all the possible actions
    public enum OwnerActions {

    }

}
