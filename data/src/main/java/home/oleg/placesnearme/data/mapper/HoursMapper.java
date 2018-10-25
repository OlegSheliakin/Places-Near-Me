package home.oleg.placesnearme.data.mapper;

import home.oleg.placenearme.models.Hours;

/**
 * Created by Oleg Sheliakin on 25.09.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
public final class HoursMapper {

    private HoursMapper() {
    }

    public static Hours map(home.oleg.placesnearme.network.models.Hours hours) {
        Hours result = new Hours();

        if(hours != null) {
            result.setIsOpen(hours.getIsOpen());
            result.setStatus(hours.getStatus());
        }

        return result;
    }


}