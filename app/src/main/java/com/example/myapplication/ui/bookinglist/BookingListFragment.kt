package com.example.myapplication.ui.bookinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.BookingsAdapter
import com.example.myapplication.adapters.OnItemClickListener
import com.example.myapplication.models.BookingEvent
import com.example.myapplication.models.Event
import com.example.myapplication.viewmodels.ViewModels


class BookingListFragment : Fragment(), OnItemClickListener  , View.OnClickListener{

    private lateinit var bookingViewModel: BookingListViewModel
    private lateinit var viewModel : ViewModels

    private lateinit var rvBooking : RecyclerView
    private lateinit var viewAdapter: BookingsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var bookingsList: MutableList<BookingEvent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_booking_list, container, false)


        this.rvBooking = root.findViewById(R.id.rvBooking)
        this.bookingsList = mutableListOf()

        this.viewAdapter = BookingsAdapter(this.requireContext(), this.bookingsList,this)
        this.rvBooking.adapter = this.viewAdapter

        this.viewManager = LinearLayoutManager(this.requireContext())
        this.rvBooking.layoutManager = this.viewManager

        this.rvBooking.setHasFixedSize(true)
        this.rvBooking.addItemDecoration(DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL))


        return root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModels()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getBookingEvent()
        this.getBookingList()
    }

    fun getBookingList(){
        this.viewModel.eventBookingsList.observe(viewLifecycleOwner, {eventBookingsList ->
            if (eventBookingsList != null){
                bookingsList.clear()
                bookingsList.addAll(eventBookingsList)
                viewAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onItemClicked(bookingEvent: BookingEvent) {
        Toast.makeText(context, bookingEvent.event.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        if (v!= null){
            when(v.id){
                    //viewModel.deleteBookingEvent()

            }

        }
    }
}

