package com.devYoussef.cleanarchtest.common.extentions

import com.devYoussef.cleanarchtest.common.model.state.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <Dto, Domain> Flow<Status<Dto>>.mapStatus(
    crossinline mapper: (Dto) -> Domain
): Flow<Status<Domain>> {
    return this.map { status ->
        when (status) {
            is Status.Success -> Status.Success(mapper(status.data))
            is Status.Failure -> Status.Failure(status.exception)
            is Status.Loading -> Status.Loading()
        }
    }
}