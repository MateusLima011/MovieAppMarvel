package com.mgt.movieappmarvel.movies.marvel

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgt.movieappmarvel.databinding.FragmentMarvelMoviesBinding
import com.mgt.movieappmarvel.movies.MoviesAdapter
import com.mgt.movieappmarvel.utils.*

@RequiresApi(Build.VERSION_CODES.M)
class MarvelMoviesFragment : Fragment() {
    private var _binding: FragmentMarvelMoviesBinding? = null
    private val binding get() = _binding!!
    private var currentPage: Int = 1
    private var listId: Int = 1

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(
            activity?.getSystemService(ConnectivityManager::class.java)
        )
    }

    private val viewModel: MarvelViewModel by viewModels()
    private lateinit var recyclerViewMarvel: RecyclerView
    private lateinit var marvelAdapter: MarvelAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentMarvelMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observerMoviesList()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerViewMarvel = binding.recyclerViewMarvel
        layoutManager = LinearLayoutManager(
            context,LinearLayoutManager.VERTICAL, false
        )
        recyclerViewMarvel.layoutManager = layoutManager
    }

    private fun setupViews(){
        binding.refreshMovies.setOnRefreshListener {
            currentPage = 2
            viewModel.getListMarvel(currentPage)
        }
        viewModel.getListMarvel(currentPage)
    }

    private fun observerMoviesList() {
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.moviesMarvel.observe(viewLifecycleOwner) {
                when (it) {
                    is Success -> {
                        if (listId > 1) {
                            marvelAdapter.updateList(it.data)
                        } else {
                            marvelAdapter = MarvelAdapter(it.data?.toCollection(arrayListOf()))
                            recyclerViewMarvel.adapter = marvelAdapter
                        }

                    }
                    is Failure -> {
                        when (it.error) {
                            is NetworkException -> {

                            }
                            is GeneralException -> {

                            }
                        }
                    }
                }
            }
        }
    }
}