package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.models.StayOverBooking
import kotlinx.android.synthetic.main.fragment_event.view.*
import kotlinx.android.synthetic.main.stay_over_history.view.*

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-12-11
*/class StayBookingAdapter(
    val context: Context,
    val stayoverBookingList: MutableList<StayOverBooking>,

    ) : RecyclerView.Adapter<StayBookingAdapter.BookingStayViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):StayBookingAdapter.BookingStayViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.stay_over_history, null)
        return BookingStayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stayoverBookingList.size
    }

    override fun onBindViewHolder(holder:StayBookingAdapter.BookingStayViewHolder, position: Int) {
        holder.bind(stayoverBookingList[position])
    }

    inner class BookingStayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var edtExploreCulture: TextView = itemView.edtExploreCulture
        var imgstayover: ImageView = itemView.imgstayover
        var edtHostName: TextView = itemView.edtHostName
        var edtDate :TextView = itemView.edtStayDate
        var edtstayGuestNumber :TextView = itemView.edtstayGuestNumber

        fun bind(overBooking: StayOverBooking){
            edtExploreCulture.setText(overBooking.culture)
            imgstayover.load(overBooking.img1Stay)
            edtstayGuestNumber.setText(overBooking.stayGuestNumber)
            edtHostName.setText(overBooking.hostName)
            edtDate.setText(overBooking.bookingDate)
        }
    }
}

