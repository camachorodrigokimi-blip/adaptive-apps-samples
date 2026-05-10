package com.example.gridsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gridsample.ui.theme.MyApplicationTheme

@OptIn(ExperimentalGridApi::class)
@Composable
fun GridSample() {
    val colors = listOf(
        Color(0xFFF44336), // Red
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0), // Purple
        Color(0xFF3F51B5), // Indigo
        Color(0xFF2196F3), // Blue
        Color(0xFF00BCD4), // Cyan
        Color(0xFF4CAF50), // Green
        Color(0xFFFFEB3B), // Yellow
        Color(0xFFFF9800), // Orange
        Color(0xFF795548)  // Brown
    )

    Column(modifier = Modifier.safeDrawingPadding().padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState())) {
        Text(
            text = "Grid example",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Three-column layout using Grid.",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Grid(
            config = {
                repeat(3) { column(0.333f) }
                gap(4.dp)
            },
        ) {
            for (index in 0 until 10) {
                val bgColor = colors[index % colors.size]
                val isLight = bgColor.luminance() > 0.5f
                val textColor = if (isLight) Color.Black else Color.White

                val itemModifier = when (index) {
                    0 -> Modifier.gridItem(rowSpan = 2)
                    6 -> Modifier.gridItem(columnSpan = 2)
                    else -> Modifier
                }

                val boxModifier = itemModifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .background(bgColor, shape = RoundedCornerShape(16.dp))
                    .let {
                        if (index == 0) it.fillMaxHeight() else it.height(100.dp)
                    }

                Box(
                    modifier = boxModifier
                ) {
                    Text(
                        text = "${index + 1}",
                        color = textColor,
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 40.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridSamplePreview() {
    MyApplicationTheme {
        GridSample()
    }
}
