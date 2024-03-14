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

    }

    public enum UserActions {
        Exit,
        FilterEvents,
        SeeAllEvents,
    }

    public enum OwnerActions {
        Exit,
        FilterEvents,
        SeeAllEvents,
        CreateEvent,
    }

}
