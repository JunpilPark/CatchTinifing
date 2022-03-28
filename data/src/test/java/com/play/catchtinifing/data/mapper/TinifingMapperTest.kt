package com.play.catchtinifing.data.mapper

import com.google.common.truth.Truth.assertThat
import com.play.catchtinifing.data.entity.Tinifing
import com.play.catchtinifing.data.entity.TinifingCage
import com.play.catchtinifing.domain.tinifing.model.CatchingTinifing
import com.play.catchtinifing.mapper.toData
import com.play.catchtinifing.mapper.toDomain
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import com.play.catchtinifing.domain.tinifing.model.Tinifing as DomainTinifing

class TinifingMapperTest {

    @DisplayName("Tinifing 객체가 Domain Layer 의 Tinifing 으로 변환된다.")
    @Test
    fun tinifingToDomainTest() {
        val tinifing = Tinifing(1, "조아핑", "블라 블라", "https://www.a.net/1.png")

        val actual = tinifing.toDomain()

        assertThat(actual).isEqualTo(
            DomainTinifing(1, "조아핑", "블라 블라", "https://www.a.net/1.png")
        )
    }

    @DisplayName("Domain Layer 의 Tinifing 이 data Layer 의 Tinifing 으로 변환된다")
    @Test
    fun domainTinifingToDataTest() {
        val domainTinifing = DomainTinifing(1, "조아핑", "블라 블라", "https://www.a.net/1.png")

        val actual = domainTinifing.toData()

        assertThat(actual).isEqualTo(
            Tinifing(1, "조아핑", "블라 블라", "https://www.a.net/1.png")
        )
    }

    @DisplayName("TinifingCage 가 도메인 레이어의 CatchingTinifing 으로 변환된다.")
    @Test
    fun tnifingCageToDomainTest() {
        val tinifingCage = TinifingCage(
            tinifing = Tinifing(1, "조아핑", "블라 블라", "https://www.a.net/1.png"),
            catchDateTime = "2022-03-27T15:28:28.906"
        )

        val actual = tinifingCage.toDomain()

        assertThat(actual).isEqualTo(
            CatchingTinifing(
                DomainTinifing(1, "조아핑", "블라 블라", "https://www.a.net/1.png"),
                LocalDateTime(2022, 3,27,15,28,28,906000000)
            )
        )
    }
}