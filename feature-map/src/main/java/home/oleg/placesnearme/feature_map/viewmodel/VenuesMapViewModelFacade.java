package home.oleg.placesnearme.feature_map.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.Nullable;

import java.util.Map;

import javax.inject.Inject;

import home.oleg.placenearme.models.Section;
import home.oleg.placesnearme.core_presentation.view_actions.ViewActionObserver;
import home.oleg.placesnearme.core_presentation.viewdata.PreviewVenueViewData;
import home.oleg.placesnearme.feature_map.view.VenuesMapView;

/**
 * Created by Oleg Sheliakin on 20.09.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
public class VenuesMapViewModelFacade {

    private final LifecycleOwner lifecycleOwner;
    private final VenuesViewModel venuesViewModel;
    private final UserLocationViewModel userLocationViewModel;

    @Nullable
    private VenueClickListener venueClickListener;

    public interface VenueClickListener {
        void onVenueSelected(PreviewVenueViewData venueMapViewData);
    }

    @Inject
    public VenuesMapViewModelFacade(LifecycleOwner lifecycleOwner,
                                    VenuesViewModel venuesViewModel,
                                    UserLocationViewModel userLocationViewModel) {
        this.lifecycleOwner = lifecycleOwner;
        this.venuesViewModel = venuesViewModel;
        this.userLocationViewModel = userLocationViewModel;
    }

    public void addOnVenueCLickListener(VenueClickListener venueClickListener) {
        this.venueClickListener = venueClickListener;
    }

    public void attachView(VenuesMapView venuesMapView) {
        venuesViewModel.getObserver().observe(lifecycleOwner, ViewActionObserver.create(venuesMapView));
        userLocationViewModel.getObserver().observe(lifecycleOwner, ViewActionObserver.create(venuesMapView));
    }

    public void refreshRecommendedVenues(Section section) {
        venuesViewModel.getRecommendedVenues(section);
    }

    public void refreshUserLocation() {
        userLocationViewModel.getUserLocation();
    }

    public void setVenues(Map<String, PreviewVenueViewData> venues) {
        venuesViewModel.setVenues(venues);
    }

    public void select(String id) {
        PreviewVenueViewData venueMapViewData = venuesViewModel.getVenue(id);
        com.smedialink.common.Optional.of(venueClickListener)
                .ifPresent(venueClickListener -> venueClickListener.onVenueSelected(venueMapViewData));
    }
}
