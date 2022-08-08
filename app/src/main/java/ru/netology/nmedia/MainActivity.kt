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
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )
        post.likes = 999999

        with(binding){
         authorName.text = post.author
         datepost.text = post.published
         textpost.text = post.content


        binding.like.setOnClickListener {

            post.likedByMe = !post.likedByMe
            val imageResult =
                if (post.likedByMe) R.drawable.ic_favorite_24dp else R.drawable.ic_favorite_border_24dp
            if (post.likedByMe) post.likes++ else post.likes--
            binding.like.setImageResource(imageResult)
            binding.likeCount.text = likeView(post.likes)
        }

        binding.share.setOnClickListener {

            post.shares = post.shares + 1
            binding.shareCount.text = shareView(post.shares)
        }
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

fun shareView (count:Int): String {

    return  when(count){
        in 0..999 ->"$count"
        in 1000..1099 ->"${round((count/1000).toDouble())}K"
        in 1100..10_000 ->"${round((count/100).toDouble())/10}K"
        in 10001..999_999 ->"${round((count/1_000).toDouble())}K"
        else -> "${round((count/10_000).toDouble())/10}M"
    }
}