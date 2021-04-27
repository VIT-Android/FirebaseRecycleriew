package com.example.firebaserecyclerview.firestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaserecyclerview.databinding.FragmentFirestoreBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class FirestoreFragment : Fragment(), FirestoreAdapter.OnEventClickListener {

    private lateinit var adapter: FirestoreAdapter
    private var _binding: FragmentFirestoreBinding? = null
    private lateinit var options: FirestoreRecyclerOptions<FirestoreModel>

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirestoreBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        // Realtime purpose
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        // Realtime purpose
        adapter.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val query: Query = FirebaseFirestore.getInstance()
            .collection("users")

        options = FirestoreRecyclerOptions.Builder<FirestoreModel>()
            .setQuery(query, FirestoreModel::class.java)
            .build()

        // Setting up Adapter and RecyclerView
        setupParentList()
    }

    private fun setupParentList() {
        adapter = FirestoreAdapter(this, options)
        binding.rvFirestoreItems.layoutManager = LinearLayoutManager(activity)
        binding.rvFirestoreItems.adapter = adapter
    }

    override fun onEventClick(student: FirestoreModel) {
        val action = FirestoreFragmentDirections
            .actionFirestoreFragmentToFirestoreDetailsFragment(student)
        NavHostFragment.findNavController(this).navigate(action)
    }
}