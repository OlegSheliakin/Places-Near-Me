package home.oleg.placesnearme.repositories;

import java.util.List;
import java.util.Map;

import home.oleg.placenearme.models.Venue;
import home.oleg.placenearme.repositories.Category;
import home.oleg.placenearme.repositories.VenueRepository;
import home.oleg.placesnearme.service.IFourSquareAPI;
import io.reactivex.Single;

public class VenueRepositoryImpl implements VenueRepository {

    private final IFourSquareAPI api;
    private final QueryParamCreator queryParamCreator;

    public VenueRepositoryImpl(IFourSquareAPI api, QueryParamCreator queryParamCreator) {
        this.api = api;
        this.queryParamCreator = queryParamCreator;
    }

    @Override
    public Single<List<Venue>> getRecommendedByCategory(Category category, Filter filter) {
        Map<String, String> queryMap = queryParamCreator.create(category, filter);

        return api.explore(queryMap).map(fullResponse -> fullResponse.getResponse().getVenues());
    }

    @Override
    public Single<List<Venue>> search(String query, Filter filter) {
        Map<String, String> queryMap = queryParamCreator.create(query, filter);

        return api.search(queryMap).map(fullResponse -> fullResponse.getResponse().getVenues());
    }

}