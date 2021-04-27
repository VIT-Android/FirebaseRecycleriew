package com.example.firebaserecyclerview.firestore

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
class FirestoreAdapter(
    private val onEventClickListener: OnEventClickListener,
    private val recyclerOptions: FirestoreRecyclerOptions<FirestoreModel>
) : FirestoreRecyclerAdapter<FirestoreModel, FirestoreAdapter.FireStoreViewHolder>(recyclerOptions) {

    companion object {
        private const val TAG = "Firestore Adapter"
    }

    interface OnEventClickListener {
        fun onEventClick(student: FirestoreModel)
    }

    inner class FireStoreViewHolder(
        @NonNull itemView: View,
        onEventClickListener: OnEventClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val name: TextView
        private val city: TextView
        private val state: TextView
        private val onEventClickListener: OnEventClickListener

        init {
            name = itemView.findViewById(R.id.tv_name_of_orphanage_firestore)
            city = itemView.findViewById(R.id.tv_city_of_orphanage_firestore)
            state = itemView.findViewById(R.id.tv_state_of_orphanage_firestore)

            this.onEventClickListener = onEventClickListener

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onEventClickListener.onEventClick(getItem(absoluteAdapterPosition))
        }

        fun bindFirestoreData(firestoreModel: FirestoreModel) {
            name.text = firestoreModel.name
            city.text = firestoreModel.city
            state.text = firestoreModel.state
        }
    }

    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): FireStoreViewHolder {
        Log.d("Adapter", "onCreateViewHolder()")

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView =
            layoutInflater.inflate(R.layout.recycler_event_firestore_list_items, parent, false)
        return FireStoreViewHolder(itemView, this.onEventClickListener)
    }

    override fun onBindViewHolder(
        holder: FireStoreViewHolder,
        position: Int,
        model: FirestoreModel
    ) {
        Log.d("Adapter", "onBindViewHolder() for position $position")

        holder.bindFirestoreData(model)
    }

    override fun getItemCount(): Int {
        return recyclerOptions.snapshots.size
    }
}