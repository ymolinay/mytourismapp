package com.heyrex.mytourismapp.features.list.domain.usecase

import com.heyrex.mytourismapp.features.list.domain.repository.ListRepository
import javax.inject.Inject

class GetListPlacesUseCase
@Inject constructor(private val listRepository: ListRepository) {
    fun execute() = listRepository.getList()
}