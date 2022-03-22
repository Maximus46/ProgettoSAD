package com.example.activityworld.home.model

import android.util.Log
import androidx.lifecycle.*
import com.example.activityworld.home.datasource.HomeRepository
import com.example.activityworld.home.datasource.TitleRefreshError
import com.example.activityworld.home.utilities.convertDateStringToLong
import com.example.activityworld.home.utilities.convertLongToDateString2
import com.example.activityworld.home.utilities.getCurrentDateTime
import com.example.activityworld.home.utilities.toString
import com.example.activityworld.network.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val TAG_FIELD_MODEL = "FieldViewModel"


class FieldViewModel(
    private val fieldTypeName: String,
    val repository: HomeRepository
) : ViewModel() {

    val fields = repository.playingFields
    private val avails = repository.availabilities

    // Field selected
    private val _field = MutableLiveData<PlayingField?>()
    val field: LiveData<PlayingField?> = _field

    /**
     * Show a loading spinner if true
     */
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    // ID of the selected availability
    private val _availabilityID = MutableLiveData<Long?>()
    private val availabilityID: LiveData<Long?> = _availabilityID

    // Selected availability
    var availability: FieldAvailability? = null

    // Selected Date
    private val _currentSelectedDate = MutableLiveData<Long?>()
    val currentSelectedDate: LiveData<Long?> = _currentSelectedDate

    val dateInString = Transformations.map(_currentSelectedDate) { date ->
        date?.let {
            Log.v(TAG_FIELD_MODEL, "dateInString updated")
            convertLongToDateString2(it)
        }
    }

    val dropMenuItems: Map<String, PlayingField>?
        get() = fields.value?.associateBy { it.name }


    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose MutableLiveData
     *
     * MutableLiveData allows anyone to set a value, and MainViewModel is the only
     * class that should be setting values.
     */
    private val _snackBar = MutableLiveData<String?>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String?>
        get() = _snackBar


    init {
        retrieveFields()
        setCurrentDate()
    }


    /**
     * Set the field selected.
     */
    fun setField(field: PlayingField) {
        Log.v(TAG_FIELD_MODEL, "setField: $field")
        _field.value = field

        // Update availabilities based on the new selected Field
        retrieveAvailabilities()
        Log.d(TAG_FIELD_MODEL, "Field: $field")
    }


    /**
     * Set selected date.
     */
    fun setDate(date: Long) {
        Log.v(TAG_FIELD_MODEL, "Called setDate")
        _currentSelectedDate.value = date
        Log.v(TAG_FIELD_MODEL, "New Date: ${_currentSelectedDate.value}")

        // Retrieve the field availabilities filtered by Date
        // retrieveAvailabilitiesByDate()
        retrieveAvailabilities()
    }

    /**
     * Set current date
     */
    private fun setCurrentDate() {
        val dateInString = getCurrentDateTime().toString("MMM DD YYYY")
        val dateInMillis = convertDateStringToLong(dateInString)
        Log.v(TAG_FIELD_MODEL, "Called setCurrentDate with date: $dateInString")
        setDate(dateInMillis)
    }

    /**
     * Reset all values pertaining to the FieldType Selected.
     */
    fun resetOrder() {
        _field.value = null
        _availabilityID.value = null
        _currentSelectedDate.value = null
    }

    /**
     * Set the clicked availability
     */
    fun onAvailabilityClicked(id: Long) {
        _availabilityID.value = id
        availability = avails.value?.find { it.id == id }
        Log.v(TAG_FIELD_MODEL, "AvailabilityID sent: ${availabilityID.value}")
        Log.v(TAG_FIELD_MODEL, "Field: ${field.value?.id}")
    }


    /**
     * Retrieve the fields, showing a loading spinner while it refreshes and errors via snackbar.
     */
    private fun retrieveFields() {
        launchDataLoad {
            Log.v(TAG_FIELD_MODEL, "Requesting fields to the server...")
            repository.retrieveFieldByType(fieldTypeName)
            Log.v(TAG_FIELD_MODEL, "Requesting done FIELDS")
            Log.v(TAG_FIELD_MODEL, "Received Fields: ${fields.value}")

            /*
            fields.value?.let { fieldsList ->
                Log.v(TAG_FIELD_MODEL, "Field received, setting the first field: ${fieldsList.first()}")
                setField(fieldsList.first())
            }
            */
        }
    }

    private fun retrieveAvailabilities() {
        launchDataLoad {
            field.value?.let { field ->
                Log.v(TAG_FIELD_MODEL, "Requesting availabilities to the server...")
                currentSelectedDate.value?.let { date ->
                    repository.retrieveAvailabilities(field, date)
                }
            }
            Log.v(TAG_FIELD_MODEL, "Requesting done AVAILABILITIES")
            Log.v(TAG_FIELD_MODEL, "Received Availabilities: ${avails.value}")
        }
    }

    /**
     * Helper function to call a data load function with a loading spinner, errors will trigger a
     * snackbar.
     *
     * By marking `block` as `suspend` this creates a suspend lambda which can call suspend
     * functions.
     *
     * @param block lambda to actually load data. It is called in the viewModelScope. Before calling the
     *              lambda the loading spinner will display, after completion or error the loading
     *              spinner will stop
     */
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(ApiStatus.LOADING)
            try {
                block()
                Log.v(TAG_FIELD_MODEL, "launchDataLoad after Block()")
                _status.postValue(ApiStatus.DONE)
            } catch (error: TitleRefreshError) {
                Log.v(TAG_FIELD_MODEL, "launchDataLoad CATCH")
                _snackBar.postValue(error.message)
                _status.postValue(ApiStatus.ERROR)
                Log.v(TAG_FIELD_MODEL, error.message!!)
            } /*finally {
                _status.value = ApiStatus.DONE
            }*/
        }
    }
}