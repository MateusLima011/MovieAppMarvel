package com.mgt.domian.repository

import com.mgt.domian.model.comics.Comics

interface ComicsRepository {
    suspend fun getComicsList(limit: Int, offset: Int): List<Comics>?
}