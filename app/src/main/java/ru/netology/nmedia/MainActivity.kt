package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.round

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
        post.likes = 999999

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
            binding.likeCount.text = likeView(post.likes)
        }

       binding.share.setOnClickListener{
            post.shares  = post.shares + 1
       binding.share.setImageResource(R.drawable.ic_share_24dp)
       binding.shareCount.text = post.shares.toString()
       }
   }
}

fun likeView (count:Int): String {

   return  when(count){
        in 0..999 ->"$count"
        in 1000..1099 ->"${round((count/1000).toDouble())}K"
        in 1100..10_000 ->"${round((count/100).toDouble())/10}K"
        in 10001..999_999 ->"${round((count/1_000).toDouble())}K"
        else -> "${round((count/10_000).toDouble())/10}M"
    }
}