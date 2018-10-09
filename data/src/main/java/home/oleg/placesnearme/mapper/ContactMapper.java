package home.oleg.placesnearme.mapper;

import home.oleg.placenearme.models.Contact;

/**
 * Created by Oleg Sheliakin on 09.10.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
public final class ContactMapper {

    private ContactMapper() {
    }

    public static Contact map(home.oleg.placesnearme.network.models.Contact contact) {
        Contact result = new Contact();
        result.setFacebook(contact.getFacebook());
        result.setFacebookName(contact.getFacebookName());
        result.setFacebookUsername(contact.getFacebookUsername());
        result.setFormattedPhone(contact.getFormattedPhone());
        result.setInstagram(contact.getInstagram());
        result.setPhone(contact.getPhone());
        result.setTwitter(contact.getTwitter());

        return result;
    }

}
