package ru.netology.nmedia.data.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.round
import kotlin.properties.Delegates

internal class PostsAdapter(
    private val onLikeClicked:(Post)-> Unit,
    private val onSharClicked:(Post)-> Unit
): ListAdapter<Post,PostsAdapter.ViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: PostBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var  post: Post

        init {
            binding.share.setOnClickListener { onSharClicked(post) }
            binding.like.setOnClickListener { onLikeClicked(post) }

        }


      fun bind(post: Post) {
          this.post = post
          with (binding) {

              val countLik = likeView(post.likes)
              val countShar = likeView(post.shares)
              authorName.text = post.author
              datepost.text = post.published
              textpost.text = post.content
              likeCount.text = countLik
              shareCount.text = countShar
              like.setImageResource(getLikeIconResId(post.likedByMe))

          }
      }
        @DrawableRes
        private fun getLikeIconResId(liked: Boolean) =
            if (liked) R.drawable.ic_favorite_24dp else R.drawable.ic_favorite_border_24dp

        private fun likeView(count: Int): String {
            return when (count) {
                in 0..999 -> "$count"
                in 1000..1099 -> "${round((count / 1000).toDouble())}K"
                in 1100..10_000 -> "${round((count / 100).toDouble()) / 10}K"
                in 10001..999_999 -> "${round((count / 1_000).toDouble())}K"
                else -> "${round((count / 10_000).toDouble()) / 10}M"
            }
        }
        }

    private object DiffCallback :DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
           oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem
   }
}