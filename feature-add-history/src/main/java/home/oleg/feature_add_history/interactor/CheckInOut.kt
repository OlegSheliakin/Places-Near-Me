package home.oleg.feature_add_history.interactor

import home.oleg.placenearme.models.DetailedVenue
import home.oleg.placenearme.repositories.VenueHistoryRepository
import io.reactivex.Single

class CheckInOut(private val venueHistoryRepository: VenueHistoryRepository) {

    fun execute(detailedVenue: DetailedVenue): Single<Boolean> {
        return if (detailedVenue.isHereNow) {
            venueHistoryRepository.checkOut(detailedVenue.id)
                    .andThen(Single.just(false))
        } else {
            venueHistoryRepository
                    .checkOutFromCurrent()
                    .andThen(venueHistoryRepository.checkIn(detailedVenue))
                    .andThen(Single.just(true))
        }
    }

}
