package com.mgt.movieappmarvel.comics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mgt.domian.model.Comics
import com.mgt.movieappmarvel.R

class ComicsAdapter(
    private var comicsList: List<Comics>? = listOf()
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsAdapter.ComicsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_comics, parent, false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(comicsList?.get(position))
    }

    override fun getItemCount(): Int = comicsList?.size ?: 0

    inner class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageComic = itemView.findViewById<ImageView>(R.id.imageViewComics)
        private val nameComic: TextView = itemView.findViewById(R.id.textViewComicsName)

        fun bind(comic: Comics?) {
            comic ?: return

            Glide
                .with(itemView.context)
                .load(comic.thumbnail?.path + "/"
                        + PORTRAIT_ASPECT_RATIO + ".${comic.thumbnail?.extension}"
                )
                .into(imageComic)

            nameComic.text = comic.title
        }
    }

    companion object {
        const val PORTRAIT_ASPECT_RATIO = "portrait_uncanny"
    }
}