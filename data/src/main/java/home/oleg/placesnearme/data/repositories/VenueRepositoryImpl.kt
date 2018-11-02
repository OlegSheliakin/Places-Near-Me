package home.oleg.placesnearme.data.repositories

import home.oleg.placenearme.models.Section
import home.oleg.placenearme.models.Venue
import home.oleg.placenearme.repositories.VenueRepository
import home.oleg.placenearme.repositories.VenueRequestParams
import home.oleg.placesnearme.data.mapper.VenueMapper
import home.oleg.placesnearme.network.service.IFourSquareAPI
import io.reactivex.Single

class VenueRepositoryImpl(private val api: IFourSquareAPI) : VenueRepository {
    private val queryParamCreator = QueryParamCreator()

    override fun getRecommendedBySection(section: Section, requestParams: VenueRequestParams): Single<List<Venue>> {
        val queryMap = queryParamCreator.create(section, requestParams)

        return api.explore(queryMap)
                .map { it.response }
                .map { it.venues }
                .map { VenueMapper.map(it) }
    }

    override fun search(query: String, requestParams: VenueRequestParams): Single<List<Venue>> {
        val queryMap = queryParamCreator.create(query, requestParams)
        return api.search(queryMap)
                .map { it.response }
                .map { it.venues }
                .map { VenueMapper.map(it) }
    }

}
