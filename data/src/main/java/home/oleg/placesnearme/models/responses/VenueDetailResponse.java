package home.oleg.placesnearme.models.responses;

import home.oleg.placenearme.models.DetailedVenue;

/**
 * Created by Oleg Sheliakin on 14.08.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
public class VenueDetailResponse {

    private DetailedVenue venue;

    public DetailedVenue getVenue() {
        return venue;
    }

    public void setVenue(DetailedVenue venue) {
        this.venue = venue;
    }
}
