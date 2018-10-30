package home.oleg.placesnearme.core_presentation.base;

/**
 * Created by Oleg Sheliakin on 27.10.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
public class Event {

    private boolean isHandled;

    public boolean isHandled() {
        return isHandled;
    }

    public void setHandled(boolean handled) {
        isHandled = handled;
    }

    public void handle(Action action) {
        if (!this.isHandled()) {
            this.setHandled(true);
            action.perform();
        }
    }

    public interface Action {
        void perform();
    }

}