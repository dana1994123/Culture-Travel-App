package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.BookingEvent
import kotlinx.android.synthetic.main.booking_history.view.*

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-12-02
*/
class BookingsAdapter (
    val context: Context,
    val bookingsList: MutableList<BookingEvent>,
    val itemClickListener: OnItemClickListener

) : RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingsAdapter.BookingViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.booking_history, null)
        return BookingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookingsList.size
    }

    override fun onBindViewHolder(holder:BookingsAdapter.BookingViewHolder, position: Int) {
        holder.bind(bookingsList[position], itemClickListener)
    }

    inner class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var edtDate: TextView = itemView.edtDateEvent
        var edtEventName: TextView = itemView.edtEventName
        var edtEventLocation: TextView = itemView.edtEventLocation
        var edtEventDuration: TextView = itemView.edtEventDuration

        fun bind(booking: BookingEvent, clickListener: OnItemClickListener){
            edtDate.setText(booking.event.date.toString())
            edtEventName.setText(booking.event.name.toString())
            edtEventLocation.setText(booking.event.location.toString())
            edtEventDuration.setText(booking.event.duration.toString())

            itemView.setOnClickListener{
                clickListener.onItemClicked(booking)
            }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(booking: BookingEvent)
}
