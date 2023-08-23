package com.jetpack.submission.movietvshow.utils

import androidx.paging.PagedList
import org.mockito.Mockito

object PageListUtils {
    fun <T : Any> mockPagedList(list : List<T>) : PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList[Mockito.anyInt()]).then { inovaction ->
            val index = inovaction.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}