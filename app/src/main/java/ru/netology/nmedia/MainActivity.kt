package ru.netology.nmedia

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel
import kotlin.math.round

class MainActivity : AppCompatActivity(){

    private val viewModel = PostViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this){post ->
            binding.render(post)
        }

        binding.like.setOnClickListener {
            viewModel.onLikeClicked()
        }

        binding.share.setOnClickListener {
            viewModel.onSharClicked()
        }
   }
}
private fun ActivityMainBinding.render(post:Post){

    val countLik = likeView(post.likes)
    val countShar = likeView(post.shares)
    authorName.text = post.author
    datepost.text = post.published
    textpost.text = post.content
    likeCount.text = countLik
    shareCount.text = countShar
    like.setImageResource(getLikeIconResId(post.likedByMe))
}


@DrawableRes
private fun getLikeIconResId(liked:Boolean) =
    if(liked)R.drawable.ic_favorite_24dp else R.drawable.ic_favorite_border_24dp


fun likeView (count:Int): String {

    return when (count) {
        in 0..999 -> "$count"
        in 1000..1099 -> "${round((count / 1000).toDouble())}K"
        in 1100..10_000 -> "${round((count / 100).toDouble()) / 10}K"
        in 10001..999_999 -> "${round((count / 1_000).toDouble())}K"
        else -> "${round((count / 10_000).toDouble()) / 10}M"
    }

}
