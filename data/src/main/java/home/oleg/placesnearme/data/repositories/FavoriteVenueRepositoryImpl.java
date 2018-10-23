package home.oleg.placesnearme.data.repositories;

import java.util.List;

import home.oleg.placenearme.models.DetailedVenue;
import home.oleg.placenearme.repositories.FavoriteVenuesRepository;
import home.oleg.placesnearme.data.dao.DetailedVenueDao;
import home.oleg.placesnearme.data.mapper.DetailedVenueMapper;
import home.oleg.placesnearme.data.model.DetailedVenueDbEntity;
import home.oleg.placesnearme.data.model.DetailedVenueWithPhotos;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class FavoriteVenueRepositoryImpl implements FavoriteVenuesRepository {

    private final DetailedVenueDao dao;

    public FavoriteVenueRepositoryImpl(DetailedVenueDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<DetailedVenue>> observeFavorites() {
        return dao.getAllFavorites().map(DetailedVenueMapper::map);
    }

    @Override
    public Completable addToFavorite(DetailedVenue venue) {
        return Completable.fromAction(() -> {
            DetailedVenueWithPhotos detailedVenueWithPhotos = DetailedVenueMapper.map(venue);
            detailedVenueWithPhotos.getVenue().setFavorite(true);

            DetailedVenueDbEntity oldDetailedVenueDbEntity = dao.getDetailedVenueById(venue.getId());
            if (oldDetailedVenueDbEntity != null) {
                dao.update(detailedVenueWithPhotos.getVenue(), detailedVenueWithPhotos.getPhotos());
            } else {
                dao.insert(detailedVenueWithPhotos);
            }
        });
    }

    @Override
    public Completable deleteFromFavorite(DetailedVenue venue) {
        return Completable.fromAction(() -> {
            DetailedVenueWithPhotos detailedVenueWithPhotos = DetailedVenueMapper.map(venue);
            DetailedVenueDbEntity detailedVenueDbEntity = detailedVenueWithPhotos.getVenue();
            detailedVenueDbEntity.setFavorite(false);
            dao.update(detailedVenueDbEntity);
        });
    }

}
