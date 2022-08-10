package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepository: PostRepository{


    override val data = MutableLiveData(
    Post(
    id = 1,
    author = "Нетология. Университет интернет-профессий будущего",
    content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
    published = "21 мая в 18:50",
    likes = 999_999,
    shares = 0,
    likedByMe = false,
    sharedByMe = false
    )
    )

    override fun like() {
       val currentPost = checkNotNull(data.value)
       var lik = currentPost.likes
       if(!currentPost.likedByMe) lik++ else lik--
       currentPost.likes = lik
       currentPost.likedByMe = !currentPost.likedByMe
       data.value = currentPost

    }

    override fun shar() {
     val sharePost = checkNotNull(data.value)
     sharePost.shares++
     data.value = sharePost
     }
}
