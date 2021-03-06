package home.oleg.placesnearme.venueshistory.presentation

import com.smedialink.common.base.BaseViewModel
import home.oleg.placesnearme.corepresentation.recyclerview.VenueViewItem
import home.oleg.placesnearme.corepresentation.viewdata.PlaceViewData
import home.oleg.placesnearme.feature_add_history.presentation.viewmodel.CheckInViewModelDelegate
import home.oleg.placesnearme.feature_add_history.presentation.viewmodel.UpdateCheckIn
import home.oleg.placesnearme.venueshistory.domain.interactor.ObserveHistory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Oleg Sheliakin on 04.09.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
class VenuesHistoryViewModel(
        createFavoriteViewModelDelegate: CheckInViewModelDelegate,
        observeHistory: ObserveHistory) : BaseViewModel<List<VenueViewItem>>(),
        UpdateCheckIn by createFavoriteViewModelDelegate {

    init {
        observeHistory.invoke()
                .map { PlaceViewData.mapFrom(it) }
                .map { VenueViewItem.map(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { stateInternal.value = it },
                        onError = Throwable::printStackTrace).autoDispose()
    }

}
