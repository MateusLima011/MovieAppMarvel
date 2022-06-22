package com.mgt.movieappmarvel.movies.marvel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mgt.domian.model.movies.Movie
import com.mgt.movieappmarvel.R

class MarvelAdapter(
    private var marvelList: ArrayList<Movie>? = arrayListOf()
) : RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapter.MarvelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_movies, parent, false)
        return MarvelViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarvelAdapter.MarvelViewHolder, position: Int) {
        holder.bind(marvelList?.get(position))
    }

    fun updateList(data: List<Movie>?){
        data?.let {
            marvelList?.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = marvelList?.size ?: 0

    inner class MarvelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewMoviePoster: ImageView =
            itemView.findViewById(R.id.imageViewMoviePoster)
        private val textViewMovieTitle: TextView = itemView.findViewById(R.id.textViewMovieTitle)
        private val textViewOriginTitle: TextView = itemView.findViewById(R.id.textViewOriginTitle)
        private val textViewVotesValue: TextView = itemView.findViewById(R.id.textViewVotesValue)
        private val textViewMediaValue: TextView = itemView.findViewById(R.id.textViewMediaValue)
        private val ratingBarPopularity: RatingBar = itemView.findViewById(R.id.ratingBarPopularity)

        fun bind(marvel: Movie?) {
            marvel ?: return
            textViewMovieTitle.text = marvel.title

            textViewVotesValue.text = marvel.vote_count.toString()
            textViewMediaValue.text = marvel.vote_average.toString()
            ratingBarPopularity.rating = (marvel.vote_average.div(2)).toFloat()

            Glide
                .with(itemView.context)
                .load("$URL_MOVIES_IMAGE${marvel.poster_path}")
                .into(imageViewMoviePoster)
        }
    }

    companion object {
        private const val URL_MOVIES_IMAGE = "https://image.tmdb.org/t/p/w342"
    }
}