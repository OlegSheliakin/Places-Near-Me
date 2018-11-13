package home.oleg.placesnearme.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class DetailedVenueWithPhotos(
        @Embedded
        var venue: DetailedVenueDbEntity) {

    @Relation(parentColumn = "id", entityColumn = "venueId")
    var photos: List<PhotoDbEntity> = emptyList()
}
