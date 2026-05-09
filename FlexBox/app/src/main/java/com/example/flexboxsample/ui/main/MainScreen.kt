package com.example.flexboxsample.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.FlexDirection
import androidx.compose.foundation.layout.FlexAlignItems
import androidx.compose.foundation.layout.FlexJustifyContent
import androidx.compose.foundation.layout.FlexWrap
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flexboxsample.data.DefaultDataRepository
import com.example.flexboxsample.theme.MyApplicationTheme

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  viewModel: MainScreenViewModel = viewModel { MainScreenViewModel(DefaultDataRepository()) },
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  when (uiState) {
    is MainScreenUiState.Loading -> {
      // Statically render or show loading, but for the sample we render the FlexBox examples
      MainScreenContent(modifier = modifier)
    }
    is MainScreenUiState.Success -> {
      MainScreenContent(modifier = modifier)
    }
    is MainScreenUiState.Error -> {
      // Render error state or default examples
      MainScreenContent(modifier = modifier)
    }
  }
}

@OptIn(ExperimentalFlexBoxApi::class)
@Composable
internal fun MainScreenContent(modifier: Modifier = Modifier) {
  Column(modifier = Modifier.safeDrawingPadding().then(modifier).verticalScroll(rememberScrollState())) {
    Text("FlexBox examples", fontSize = 32.sp, modifier = Modifier.padding(bottom = 16.dp))
    Text("Example 0: Column direction (center)", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
    FlexBox(
      config = {
        direction(FlexDirection.Column)
        alignItems(FlexAlignItems.Center)
        justifyContent(FlexJustifyContent.Center)
      },
      modifier = Modifier.fillMaxWidth().height(133.dp).background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp)
    ) {
      Text(text = "Hello,", fontSize = 32.sp)
      Text(text = "World!", fontSize = 32.sp)
    }
    Spacer(modifier = Modifier.height(24.dp))

    Text("Example 1: Row direction (SpaceBetween)", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
    FlexBox(
      config = {
        direction(FlexDirection.Row)
        justifyContent(FlexJustifyContent.SpaceBetween)
      },
      modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp)
    ) {
      Box(Modifier.height(50.dp).background(Color(0xFFFF0000), RoundedCornerShape(8.dp)).flex { basis(90.dp); grow(0f); shrink(1f) }) {
          Text("Basis: 90dp\nGrow: 0f\nShrink: 1f", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
      }
      Box(Modifier.height(50.dp).background(Color(0xFF00FF00), RoundedCornerShape(8.dp)).flex { basis(90.dp); grow(0f); shrink(1f) }) {
          Text("Basis: 90dp\nGrow: 0f\nShrink: 1f", color = Color.Black, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
      }
      Box(Modifier.height(50.dp).background(Color(0xFF0000FF), RoundedCornerShape(8.dp)).flex { basis(90.dp); grow(0f); shrink(1f) }) {
          Text("Basis: 90dp\nGrow: 0f\nShrink: 1f", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
      }
    }
    Spacer(modifier = Modifier.height(24.dp))

    Text("Example 2: Wrapping and growing", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
    FlexBox(
      config = {
        wrap(FlexWrap.Wrap)
        gap(8.dp)
      },
      modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp)
    ) {
      // All boxes have a basis of 100.dp
      Box(Modifier.height(50.dp).background(Color(0xFFFF0000), RoundedCornerShape(8.dp)).flex { basis(100.dp) }) {
          Text("Basis: 100dp\nGrow: 0f", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(4.dp))
      }
      Box(Modifier.height(50.dp).background(Color(0xFF0000FF), RoundedCornerShape(8.dp)).flex { basis(100.dp) }) {
          Text("Basis: 100dp\nGrow: 0f", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(4.dp))
      }
      Box(Modifier.height(50.dp).background(Color(0xFF00FF00), RoundedCornerShape(8.dp)).flex { basis(100.dp); grow(1.0f) }) {
          Text("Basis: 100dp\nGrow: 1f", color = Color.Black, fontSize = 14.sp, modifier = Modifier.padding(4.dp))
      }
      Box(Modifier.height(50.dp).background(Color(0xFFFFFF00), RoundedCornerShape(8.dp)).flex { basis(100.dp); grow(1.0f) }) {
          Text("Basis: 100dp\nGrow: 1f", color = Color.Black, fontSize = 14.sp, modifier = Modifier.padding(4.dp))
      }
      Box(Modifier.height(50.dp).background(Color.Black, RoundedCornerShape(8.dp)).flex { basis(100.dp); grow(1.0f) }) {
          Text("Basis: 100dp\nGrow: 1f", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(4.dp))
      }
    }
    Spacer(modifier = Modifier.height(24.dp))
    
    Text("Example 3: Basis and shrink", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
    FlexBox(
        config = {
            direction(FlexDirection.Row)
        },
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp)
    ) {
        Box(Modifier.height(50.dp).background(Color(0xFFFF0000)).flex {
            basis(160.dp)
            shrink(1f)
        }) {
            Text("Basis: 160dp\nShrink: 1f", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
        }
        Box(Modifier.height(50.dp).background(Color(0xFF0000FF)).flex {
            basis(160.dp)
            shrink(2f)
        }) {
            Text("Basis: 160dp\nShrink: 2f", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
        }
        Box(Modifier.height(50.dp).background(Color(0xFF00FF00)).flex {
            basis(160.dp)
            shrink(0f)
        }) {
             Text("Basis: 160dp\nShrink: 0f", color = Color.Black, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
        }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
  MyApplicationTheme { MainScreenContent() }
}
