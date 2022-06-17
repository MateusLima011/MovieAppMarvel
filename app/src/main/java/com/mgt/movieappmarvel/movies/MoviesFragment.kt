package com.mgt.movieappmarvel.movies

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
import com.mgt.movieappmarvel.databinding.FragmentMoviesBinding
import com.mgt.movieappmarvel.utils.*

@RequiresApi(Build.VERSION_CODES.M)
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private var currentPage: Int = 1

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(
            activity?.getSystemService(ConnectivityManager::class.java)
        )
    }
    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observerMoviesList()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerViewMovies = binding.recyclerViewMovies
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        recyclerViewMovies.layoutManager = layoutManager
    }

    private fun observerMoviesList() {
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.moviesList.observe(viewLifecycleOwner) {
                when (it) {
                    is Success -> {
                        if (currentPage > 1) {
                            moviesAdapter.updateList(it.data)
                        } else {
                            moviesAdapter = MoviesAdapter(it.data?.toCollection(arrayListOf()))
                            recyclerViewMovies.adapter = moviesAdapter
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

    private fun setupViews() {
        binding.refreshMovies.setOnRefreshListener {
            currentPage = 1
            moviesAdapter.clearData()
            viewModel.getPopularMoviesList(currentPage)
        }
        viewModel.getPopularMoviesList(currentPage)
    }
}