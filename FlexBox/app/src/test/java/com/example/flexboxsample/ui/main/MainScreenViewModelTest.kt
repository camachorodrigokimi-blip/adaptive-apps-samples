package com.example.flexboxsample.ui.main

import com.example.flexboxsample.data.DataRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainScreenViewModelTest {
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun uiState_initiallyLoading() = runTest(testDispatcher) {
    val viewModel = MainScreenViewModel(FakeLoadingModelRepository())
    assertEquals(MainScreenUiState.Loading, viewModel.uiState.value)
  }

  @Test
  fun uiState_onItemSaved_isDisplayed() = runTest(testDispatcher) {
    val viewModel = MainScreenViewModel(FakeMyModelRepository())
    val uiStates = mutableListOf<MainScreenUiState>()
    val job = launch {
      viewModel.uiState.collect { uiStates.add(it) }
    }
    assertEquals(
      listOf(MainScreenUiState.Loading, MainScreenUiState.Success(listOf("Sample"))),
      uiStates
    )
    job.cancel()
  }
}

private class FakeMyModelRepository : DataRepository {
  override val data: Flow<List<String>> = flow { emit(listOf("Sample")) }
}

private class FakeLoadingModelRepository : DataRepository {
  override val data: Flow<List<String>> = MutableSharedFlow()
}
