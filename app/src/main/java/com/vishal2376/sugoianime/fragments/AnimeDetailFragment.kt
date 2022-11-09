package com.vishal2376.sugoianime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vishal2376.sugoianime.R

class AnimeDetailFragment : Fragment() {

    //get arguments from HomeFragment through AnimeAdapter
    private val args: AnimeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //--------------------------testing-------------------------------------
        // TODO : remove this after data binding
        val animeTitle = requireActivity().findViewById<TextView>(R.id.tvAnimeTitleAD)
        val animeSynopsis = requireActivity().findViewById<TextView>(R.id.tvAnimeSynopsisAD)
        val animeShare = requireActivity().findViewById<FloatingActionButton>(R.id.fabShareAD)

        //----------------------------------------------------------------------
    }
}