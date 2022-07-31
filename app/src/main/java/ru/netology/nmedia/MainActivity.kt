package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
         id = 0L,
         author = "Неотология",
         content = "78",
         published = "555"
        )
        post.likes = 1000

        binding.like.setOnClickListener{

           post.likedByMe =!post.likedByMe
           val imageResult = if(post.likedByMe)
           {
               post.likes  ++


               R.drawable.ic_favorite_24dp
           }
           else {
               post.likes --



               R.drawable.ic_favorite_border_24dp
           }
            binding.like.setImageResource(imageResult)
            binding.likeCount.text = post.likes.toString()
       }

       binding.share.setOnClickListener{
            post.shares  = post.shares + 1
       binding.share.setImageResource(R.drawable.ic_share_24dp)
       binding.shareCount.text = post.shares.toString()
       }
   }
}
