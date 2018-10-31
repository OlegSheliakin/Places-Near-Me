package home.oleg.placesnearme.data.repositories

import home.oleg.placenearme.models.UserLocation
import home.oleg.placenearme.repositories.UserLocationRepository
import home.oleg.placesnearme.data.mapper.LocationMapper
import home.oleg.placesnearme.data.provider.CachedLocationsStore
import home.oleg.placesnearme.data.provider.ReactiveLocationStore
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 17.09.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
class UserLocationRepositoryImpl(
        private val reactiveLocationStore: ReactiveLocationStore,
        private val cachedLocationsStore: CachedLocationsStore) : UserLocationRepository {

    override val location: Single<UserLocation>
        get() = reactiveLocationStore
                .lastLocation
                .doOnSuccess { cachedLocationsStore.save(it) }
                .onErrorResumeNext(cachedLocationsStore.lastLocation)
                .map { LocationMapper.map(it) }

}