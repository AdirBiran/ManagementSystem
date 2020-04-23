package Presentation;

import Domain.Authorization.GuestAuthorization;

public class Guest {


    GuestAuthorization guestAuthorization;

    public Guest() {
        guestAuthorization = new GuestAuthorization();
    }

    public GuestAuthorization getGuestAuthorization() {
        return guestAuthorization;
    }
}
