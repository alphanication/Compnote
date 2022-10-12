package com.example.compnote.data.data_source.util

import com.example.compnote.domain.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseDataSource {

    protected suspend fun <T> getRequest(data: Flow<T>): Flow<Resource<T>> = flow {
        try {
            data.collect { emit(Resource.Success(it)) }
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }

    protected suspend fun <T> otherRequest(data: Flow<T>): Flow<Resource<T>> = flow {
        try {
            data.collect { emit(Resource.Success(it)) }
        } catch (e: Exception) {
            emit(Resource.Fail(e))
        }
    }
}