package home.oleg.placesnearme.feature_map.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Oleg Sheliakin on 20.11.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
class LifeCycleAwareMap private constructor(
        lifecycleOwner: LifecycleOwner,
        private val onMapReadyCallback: OnMapReadyCallback
) : LifecycleObserver {

    private var mapView: MapView? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    fun onViewCreated(mapView: MapView, savedInstanceState: Bundle?) {
        this.mapView = mapView
        this.mapView?.getMapAsync(onMapReadyCallback)
        this.mapView?.onCreate(savedInstanceState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        mapView?.onResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        mapView?.onPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mapView?.onDestroy()
    }

    fun onLowMemory() {
        mapView?.onLowMemory()
    }

    companion object {
        fun create(lifecycleOwner: LifecycleOwner, onMapReadyCallback: OnMapReadyCallback) : LifeCycleAwareMap {
            return LifeCycleAwareMap(lifecycleOwner, onMapReadyCallback)
        }
    }
}
