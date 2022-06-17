package com.mgt.movieappmarvel.stories

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
import com.mgt.movieappmarvel.databinding.FragmentStoriesBinding
import com.mgt.movieappmarvel.utils.Failure
import com.mgt.movieappmarvel.utils.NetworkStatusChecker
import com.mgt.movieappmarvel.utils.Success

@RequiresApi(Build.VERSION_CODES.M)
class StoriesFragment : Fragment() {

    private var _binding: FragmentStoriesBinding? = null
    private val binding get() = _binding !!
    private val viewModel: StoriesViewModel by viewModels()

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    private var offset: Int = 2
    private var limit = 20

    private lateinit var recyclerViewStories: RecyclerView
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStoriesList(limit, offset)
        setupRecyclerView()
        observerStoriesList()
    }

    private fun setupRecyclerView() {
        recyclerViewStories = binding.recyclerViewStories
        layoutManager = GridLayoutManager(
            context,
            4,
            GridLayoutManager.HORIZONTAL,
            false
        )
        recyclerViewStories.layoutManager = layoutManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoriesBinding.inflate(inflater,container,false)
        return  binding.root
    }

    private fun observerStoriesList(){
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.storiesLiveData.observe(viewLifecycleOwner){
                when(it){
                    is Success ->{
                        storiesAdapter = StoriesAdapter(it.data)
                        recyclerViewStories.adapter = storiesAdapter
                    }
                    is Failure ->{
                        when(it.error){

                        }
                    }
                }
            }
        }
    }
}