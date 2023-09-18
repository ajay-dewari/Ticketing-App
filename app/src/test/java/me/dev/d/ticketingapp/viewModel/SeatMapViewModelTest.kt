package me.dev.d.ticketingapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.dev.d.ticketingapp.data.fakeSeatA1
import me.dev.d.ticketingapp.data.fakeSeatA2
import me.dev.d.ticketingapp.data.fakeSeatA3
import me.dev.d.ticketingapp.data.fakeSeatMapResponse
import me.dev.d.ticketingapp.domain.usecase.FetchSeatMapUseCase
import me.dev.d.ticketingapp.domain.usecase.UpdateSelectedSeatsUseCase
import me.dev.d.ticketingapp.domain.util.Resource
import me.dev.d.ticketingapp.presentation.ui.SeatMapEvent
import me.dev.d.ticketingapp.presentation.ui.SeatMapViewModel
import me.dev.d.ticketingapp.repository.FakeCinemaRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SeatMapViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var seatMapViewModel: SeatMapViewModel
    private lateinit var fetchSeatMapUseCase: FetchSeatMapUseCase
    private lateinit var updateSelectedSeatsUseCase: UpdateSelectedSeatsUseCase
    private lateinit var fakeCinemaRepository: FakeCinemaRepository
    private lateinit var dispatcher: TestDispatcher


    @Before
    fun setup() {
        runTest {
            Dispatchers.setMain(Dispatchers.Unconfined)
            dispatcher = UnconfinedTestDispatcher(testScheduler)
            fakeCinemaRepository = FakeCinemaRepository()
            fetchSeatMapUseCase = FetchSeatMapUseCase(fakeCinemaRepository, dispatcher)
            updateSelectedSeatsUseCase = UpdateSelectedSeatsUseCase()
            seatMapViewModel = SeatMapViewModel(
                fetchSeatMapUseCase,
                updateSelectedSeatsUseCase
            )
        }
    }

    @Test
    fun `test loadSeatMap data for success`() = runTest {
        Truth.assertThat(seatMapViewModel.loadSeatMap())
            .isEqualTo(Resource.Success(fakeSeatMapResponse))
        Truth.assertThat(seatMapViewModel.loadSeatMap()).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat(seatMapViewModel.loadSeatMap()).isNotInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `test loadSeatMap data event for failure scenario`() = runTest {
        fakeCinemaRepository.prepareForError()
        Truth.assertThat(seatMapViewModel.uiState.value.isLoading).isFalse()
        Truth.assertThat(seatMapViewModel.loadSeatMap()).isInstanceOf(Resource.Error::class.java)
        Truth.assertThat(seatMapViewModel.loadSeatMap())
            .isNotInstanceOf(Resource.Success::class.java)
        fakeCinemaRepository.resetCase()
    }

    @Test
    fun `when seat is selected, seat should be added to selectedSeats list`() = runTest {
        seatMapViewModel.dispatcher(SeatMapEvent.SeatSelected(fakeSeatA1))
        val state = seatMapViewModel.uiState.value
        Truth.assertThat(state.selectedSeats.contains(fakeSeatA1)).isTrue()
    }

    @Test
    fun `when new seat is selected, and required seat are already filled oldest seat should be removed from selectedSeats list and new should be added`() =
        runTest {
            seatMapViewModel.dispatcher(SeatMapEvent.SeatSelected(fakeSeatA1))
            seatMapViewModel.dispatcher(SeatMapEvent.SeatSelected(fakeSeatA2))
            seatMapViewModel.dispatcher(SeatMapEvent.SeatSelected(fakeSeatA3))
            val state = seatMapViewModel.uiState.value
            Truth.assertThat(state.selectedSeats.contains(fakeSeatA1)).isFalse()
            Truth.assertThat(state.selectedSeats.contains(fakeSeatA3)).isTrue()
        }

    @Test
    fun `when required number of ticket changed, requiredTicketChanged state should be updated`() =
        runTest {
            seatMapViewModel.dispatcher(SeatMapEvent.RequiredTicketChanged("5"))
            val state = seatMapViewModel.uiState.value
            Truth.assertThat(state.requiredSeatText).isEqualTo("5")
        }

    @Test
    fun `when empty string is received required number of ticket changed, requiredTicketChanged state should be 0`() =
        runTest {
            seatMapViewModel.dispatcher(SeatMapEvent.RequiredTicketChanged(""))
            val state = seatMapViewModel.uiState.value
            Truth.assertThat(state.requiredSeatText).isEqualTo("")
            Truth.assertThat(state.requiredSeat == 0).isTrue()
        }

}