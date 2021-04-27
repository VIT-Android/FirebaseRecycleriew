package com.example.firebaserecyclerview.realtime

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserecyclerview.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

/**
 * Created by Avinash Vijayvargiya on 27-04-2021.
 */
class RealtimeAdapter(
    private val onEventClickListener: OnEventClickListener,
    private val recyclerOptions: FirestoreRecyclerOptions<RealtimeModel>
) : FirestoreRecyclerAdapter<RealtimeModel, RealtimeAdapter.RealtimeViewHolder>(recyclerOptions) {

    companion object {
        private const val TAG = "Realtime Adapter"
    }

    interface OnEventClickListener {
        fun onEventClick(position: Int)
    }

    inner class RealtimeViewHolder(
        @NonNull itemView: View,
        onEventClickListener: OnEventClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val name: TextView
        private val city: TextView
        private val state: TextView
        private val onEventClickListener: OnEventClickListener

        init {
            name = itemView.findViewById(R.id.tv_name_of_orphanage_realtime)
            city = itemView.findViewById(R.id.tv_city_of_orphanage_realtime)
            state = itemView.findViewById(R.id.tv_state_of_orphanage_realtime)

            this.onEventClickListener = onEventClickListener

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onEventClickListener.onEventClick(adapterPosition)
        }

        fun bindRealtimeData(realtimeModel: RealtimeModel) {
            name.text = realtimeModel.name
            city.text = realtimeModel.city
            state.text = realtimeModel.state
        }
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RealtimeViewHolder {
        Log.d("Adapter", "onCreateViewHolder()")

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView =
            layoutInflater.inflate(R.layout.recycler_event_realtime_list_items, parent, false)
        return RealtimeViewHolder(itemView, this.onEventClickListener)
    }

    override fun onBindViewHolder(holder: RealtimeViewHolder, position: Int, model: RealtimeModel) {
        Log.d("Adapter", "onBindViewHolder() for position $position")

        holder.bindRealtimeData(model)
    }

    override fun getItemCount(): Int {
        return recyclerOptions.snapshots.size
    }
}