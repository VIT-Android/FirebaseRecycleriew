package com.example.firebaserecyclerview.firestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebaserecyclerview.databinding.FragmentFirestoreDetailsBinding

class FirestoreDetailsFragment : Fragment() {
    private var _binding: FragmentFirestoreDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirestoreDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        val arguments = arguments?.let { FirestoreDetailsFragmentArgs.fromBundle(it) }
        val st: FirestoreModel? = arguments?.student

        if (st != null) {
            binding.name.text = st.name
            binding.city.text = st.city
            binding.state.text = st.state
        }



        return view
    }
}