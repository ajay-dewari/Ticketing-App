package me.dev.d.ticketingapp.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.dev.d.ticketingapp.data.fakeSeatMapResponse
import me.dev.d.ticketingapp.domain.usecase.FetchSeatMapUseCase
import me.dev.d.ticketingapp.domain.util.Resource
import me.dev.d.ticketingapp.repository.FakeCinemaRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class FetchSeatMapUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fetchSeatMapUseCase: FetchSeatMapUseCase
    private lateinit var fakeCinemaRepository: FakeCinemaRepository
    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setup() {
        runTest {
            Dispatchers.setMain(Dispatchers.Unconfined)
            dispatcher = UnconfinedTestDispatcher(testScheduler)
            fakeCinemaRepository = FakeCinemaRepository()
            fetchSeatMapUseCase =
                FetchSeatMapUseCase(fakeCinemaRepository, dispatcher)
        }
    }

    @Test
    fun `test SeatMapResponse for success`() = runTest {
        Truth.assertThat(fetchSeatMapUseCase.invoke())
            .isEqualTo(Resource.Success(fakeSeatMapResponse))
        Truth.assertThat(fetchSeatMapUseCase.invoke()).isNotInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `test SeatMapResponse for error`() = runTest {
        fakeCinemaRepository.prepareForError()
        Truth.assertThat(fetchSeatMapUseCase.invoke()).isNotInstanceOf(Resource.Success::class.java)
        Truth.assertThat(fetchSeatMapUseCase.invoke())
            .isNotEqualTo(Resource.Success(fakeSeatMapResponse))
        Truth.assertThat(fetchSeatMapUseCase.invoke()).isInstanceOf(Resource.Error::class.java)
        fakeCinemaRepository.resetCase()
    }
}
