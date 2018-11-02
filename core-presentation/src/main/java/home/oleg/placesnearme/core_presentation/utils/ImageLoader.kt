package home.oleg.placesnearme.core_presentation.utils


import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.RemoteViews
import com.squareup.picasso.Picasso

import java.io.IOException

import home.oleg.placesnearme.core_presentation.R
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 * Created by Oleg Sheliakin on 21.09.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
object ImageLoader {

    fun clear(imageView: ImageView) {
        Picasso.get().cancelRequest(imageView)
    }

    fun loadIcon(imageView: ImageView, url: String?) {
        Picasso.get()
                .load(url)
                .transform(CropCircleTransformation())
                .placeholder(R.drawable.placeholder_icon)
                .into(imageView)
    }

    fun loadImage(imageView: ImageView, url: String?) {
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .placeholder(R.color.colorGrayLight)
                .error(R.color.colorGrayLight)
                .into(imageView)
    }

    @Throws(IOException::class)
    fun loadIcon(remoteViews: RemoteViews, id: Int, url: String?, appwidgetId: Int): Bitmap {
        return Picasso.get()
                .load(url)
                .transform(CropCircleTransformation())
                .get()
    }

}
