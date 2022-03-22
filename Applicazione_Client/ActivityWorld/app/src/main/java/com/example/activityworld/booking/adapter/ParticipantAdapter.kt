package com.example.activityworld.booking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.activityworld.R
import com.example.activityworld.booking.model.Participant
import com.example.activityworld.booking.ui.ParticipantFormState
import com.example.activityworld.databinding.ListItemParticipantBinding
import com.example.activityworld.login.ui.afterTextChanged
import java.util.*


/**
 * Adapter for the [RecyclerView] in [BookingFragment]. Displays [Participant] data object.
 *
 * @param clickListener
 * Used to set a listener when an item inside the RecyclerView gets clicked
 */
class ParticipantAdapter(
    private val context: Context,
    private val clickListener: ParticipantListener
    ): ListAdapter<Participant, ParticipantAdapter.ParticipantViewHolder>(ParticipantDiffCallback()) {

    /**
     * Initialize view elements
     * Provide a reference to the views for each data item
     * Each data item is just an Participant object.
     */
    class ParticipantViewHolder private constructor(val binding: ListItemParticipantBinding): RecyclerView.ViewHolder(binding.root) {

        // Binding the objects
        fun bind(context: Context, item: Participant, clickListener: ParticipantListener) {
            // Set the availability for dataBinding
            binding.participant = item

            // Set the clickListener
            binding.clickListener = clickListener

            binding.nameEditText.afterTextChanged {
                participantDataChanged(
                    context,
                    binding.nameEditText.text.toString(),
                    binding.surnameEditText.text.toString()
                )
            }

            binding.surnameEditText.afterTextChanged {
                participantDataChanged(
                    context,
                    binding.nameEditText.text.toString(),
                    binding.surnameEditText.text.toString()
                )
            }

            // Slightly improves the performances
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ParticipantViewHolder {
                // Create a new view
                val layoutInflater = LayoutInflater.from(parent.context)
                // Get a reference to the binding object.
                val binding = ListItemParticipantBinding.inflate(layoutInflater, parent, false)

                return ParticipantViewHolder(binding)
            }
        }

        private fun participantDataChanged(context: Context, name: String, surname: String) {
            val participantForm = if (!isNameValid(name)) {
                ParticipantFormState(nameError = R.string.invalid_name)
            } else if (!isSurnameValid(surname)) {
                ParticipantFormState(surnameError = R.string.invalid_surname)
            } else {
                ParticipantFormState(isDataValid = true)
            }

            if (participantForm.nameError != null) {
                binding.nameEditText.error = context.getString(participantForm.nameError)
            }
            if (participantForm.surnameError != null) {
                binding.surnameEditText.error = context.getString(participantForm.surnameError)
            }
        }

        // Name validation check
        private fun isNameValid(name: String): Boolean {
            return name.all { it.isLetter() }
        }

        // Surname validation check
        private fun isSurnameValid(surname: String): Boolean {
            return surname.all { it.isLetter() }
        }
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        return ParticipantViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        // Retrieve the Availability in the given position
        val participant =  getItem(position)

        // Update the view with the participant data
        holder.bind(context, participant, clickListener)
    }
}

/**
 * DiffUtil helps the List Adapter to find out which item has actually changed and should be updated
 */
class ParticipantDiffCallback : DiffUtil.ItemCallback<Participant>() {
    override fun areItemsTheSame(oldItem: Participant, newItem: Participant): Boolean {
        // Test whether the two passed-in Participant items, oldItem and newItem, are the same
        // through their ID
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Participant,
        newItem: Participant
    ): Boolean {
        // Check whether oldItem and newItem contain the same data; that is, whether they are equal.
        return oldItem == newItem
    }
}

class ParticipantListener(val clickListener: (participantID: Long) -> Unit) {
    fun onClick(participant: Participant) = clickListener(participant.id)
}