package com.mgt.movieappmarvel.comics

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgt.movieappmarvel.databinding.FragmentComicsBinding
import com.mgt.movieappmarvel.utils.Failure
import com.mgt.movieappmarvel.utils.NetworkException
import com.mgt.movieappmarvel.utils.NetworkStatusChecker
import com.mgt.movieappmarvel.utils.Success

@RequiresApi(Build.VERSION_CODES.M)
class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ComicsViewModel by viewModels()

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    private var offset: Int = 0
    private val limit = 20

    private lateinit var recyclerViewComics: RecyclerView
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getComicsList(limit, offset)
        setupRecyclerView()
        observerComicsList()
    }

    private fun setupRecyclerView() {
        recyclerViewComics = binding.recyclerViewComics
        layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerViewComics.layoutManager = layoutManager
    }

    private fun observerComicsList() {
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.comicsLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Success -> {
                        comicsAdapter = ComicsAdapter(it.data)
                        recyclerViewComics.adapter = comicsAdapter
                    }
                    is Failure -> {
                        when (it.error) {
                            is NetworkException -> {
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        return binding.root
    }
}