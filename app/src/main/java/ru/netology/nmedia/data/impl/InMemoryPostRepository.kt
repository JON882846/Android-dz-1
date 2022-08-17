package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepository: PostRepository{
    private val posts
        get() = checkNotNull(data.value){}

    override val data = MutableLiveData(
    List (1000) { index ->
        Post(
            id = index + 1L,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:50",
            likes = 999_999,
            shares = 0,
            likedByMe = false,
            sharedByMe = false
           )
        }
    )

    override fun like(postId: Long) {
           posts.map {
           if (it.id != postId) it
           else {
               var lik = it.likes
               if (!it.likedByMe) lik++ else lik--
               it.likes = lik
               it.likedByMe = !it.likedByMe
               data.value = posts
           }
       }
    }

    override fun shar(postId: Long) {
        posts.map{
            if (it.id == postId) {
               it.shares++
               data.value = posts
            } else it
        }
    }
}
