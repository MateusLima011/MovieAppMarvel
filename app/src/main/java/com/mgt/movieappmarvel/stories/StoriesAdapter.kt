package com.mgt.movieappmarvel.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mgt.domian.model.stories.Stories
import com.mgt.movieappmarvel.R

class StoriesAdapter(
    private var storiesList: List<Stories>? = listOf()
): RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoriesAdapter.StoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_stories,parent,false)
        return StoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoriesAdapter.StoriesViewHolder, position: Int) {
        holder.bind(storiesList?.get(position))
    }

    override fun getItemCount(): Int = storiesList?.size ?: 0

    inner class StoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageStories = itemView.findViewById<ImageView>(R.id.imageViewStories)
        private val titleStories = itemView.findViewById<TextView>(R.id.title_stories)
        private val descriptionStories = itemView.findViewById<TextView>(R.id.descriptionStories)

        fun bind(stories: Stories?){
            stories ?: return

            Glide
                .with(itemView.context)
                .load(stories.thumbnail?.path + "/" + PORTRAIT_ASPECT_RATIO
                + ".${stories.thumbnail?.extension}")
                .into(imageStories)

            titleStories.text = stories.title
            descriptionStories.text = stories.description
        }

    }

    companion object {
        const val PORTRAIT_ASPECT_RATIO = "portrait_uncanny"
    }
}