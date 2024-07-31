package com.heyrex.mytourismapp.features.detail.domain.usecase

import com.heyrex.mytourismapp.features.detail.domain.repository.DetailRepository
import javax.inject.Inject

class GetDetailPlaceUseCase
@Inject constructor(private val detailRepository: DetailRepository) {
    fun execute(placeId: String) = detailRepository.getDetail(id = placeId)
}