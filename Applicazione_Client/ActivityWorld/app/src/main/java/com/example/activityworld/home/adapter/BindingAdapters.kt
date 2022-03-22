package com.example.activityworld.home.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.activityworld.R
import com.example.activityworld.home.constant.AvailabilityStatus
import com.example.activityworld.home.model.FieldAvailability
import com.example.activityworld.home.model.PlayingField
import com.example.activityworld.home.utilities.convertLongToDateString2
import com.example.activityworld.home.utilities.convertTimeToFormatted
import com.example.activityworld.network.ApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@BindingAdapter("availabilityTimeFormatted")
fun Button.setAvailabilityTimeFormatted(item: FieldAvailability?) {
    //TODO: Fix the availability time format displayed inside the button
    item?.let {
        Log.v("BINDING_setTimeString", "Availability Found: ${item.id}")
        /* CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
             // Converts starting and ending time to hh:mm string to display
             val time = convertTimeToFormatted(it.startingTime, it.endingTime)
             text = time
         }*/
        // Converts starting and ending time to hh:mm string to display
        val time = convertTimeToFormatted(it.startingTime, it.endingTime)
        text = time
    }
}

/**
 * Updates the availabilities Button inside [RecyclerView].
 */
@BindingAdapter("availabilityStatus")
fun Button.setAvailabilityStatus(status: AvailabilityStatus) {
    Log.v("BINDING_ADAPTER","Availability Status: $status")
    when (status) {
        AvailabilityStatus.AVAILABLE-> {
            isEnabled = true
        }
        AvailabilityStatus.EXPIRED -> {
            isEnabled = false
        }
        AvailabilityStatus.RESERVED -> {
            isEnabled = false
            Log.v("BINDING_ADAPTER", "Availability Reserved -> Button RED")
            setBackgroundColor(Color.RED)
        }
    }
}

@BindingAdapter("availabilityDateFormatted")
fun TextView.setAvailabilityDateFormatted(item: FieldAvailability?) {
    Log.v("BINDING_setDateString", "Availability Found: $item")
    item?.let {
        val dateString = convertLongToDateString2(it.date)
        val timeString = convertTimeToFormatted(item.startingTime, item.endingTime)
        text = context.resources.getString(R.string.date, dateString, timeString)
    }
}

@BindingAdapter("fieldPriceString")
fun TextView.setFieldPriceString(item: PlayingField?) {
    if(item != null) {
        val priceString = item.getFormattedPrice()
        text = context.resources.getString(R.string.price, priceString)
    } else  {
        text = context.resources.getString(R.string.price, "")
    }
}

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<FieldAvailability>?) {
    val adapter = recyclerView.adapter as AvailabilityAdapter
    adapter.submitList(data)
}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("status", "imageRes")
fun loadImage(imgView: ImageView, status: ApiStatus?, imageResourceID: Int?) {
    when (status) {
        ApiStatus.LOADING -> {
            Log.v("BINDING_ADAPTER", "status_LOADING")
            imgView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            Log.v("BINDING_ADAPTER", "status_ERROR")
            imgView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            Log.v("BINDING_ADAPTER", "status_DONE")
            if(imageResourceID != null) {
                imgView.setImageResource(imageResourceID)
            } else {
                imgView.setImageResource(R.drawable.ic_connection_error)
            }
        }
        else -> {
            Log.v("BINDING_ADAPTER", "status_ELSE")
            imgView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

/**
 * This binding adapter displays the [ApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            Log.v("BINDING_ADAPTER", "ApiStatus_LOADING")
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            Log.v("BINDING_ADAPTER", "ApiStatus_ERROR")
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            Log.v("BINDING_ADAPTER", "ApiStatus_DONE")
            statusImageView.visibility = View.GONE
        }
        else -> {
            Log.v("BINDING_ADAPTER", "ApiStatus_ELSE")
            statusImageView.visibility = View.GONE
        }
    }
}