package main.BusinessLogic;

public enum UserChoices {
    LOGIN,
    EXIT;

    public enum BasicUser {
        Exit,
        SeeEventsMenu,
        SeeInfo,
        SeeAllPlaces,
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

    public enum OwnerPlannerActions {
        Exit,
        FilterEvents,
        SeeAllEvents,
        CreateEvent,
        SelectMusician,
        AcceptEvents,
    }

    public enum MunicipalityActions {
        Exit,
        FilterEvents,
        SeeAllEvents,
        CreateEvent,
        AcceptEvents,
    }

}
