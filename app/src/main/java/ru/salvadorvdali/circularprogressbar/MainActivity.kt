package ru.salvadorvdali.circularprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.salvadorvdali.circularprogressbar.ui.theme.CircularProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
    MySpinner(color = Color.DarkGray)
}
                }
            }
        }
    }
}

@Composable
fun MySpinner(color: Color, animationDelay: Int = 1500, spinnerSize: Dp = 100.dp) {

    val opacity = listOf(
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
        remember{Animatable(initialValue = 0.9f)},
    )

    opacity.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit){
            delay(timeMillis = (animationDelay/ opacity.size+1).toLong() * index)
            animatable.animateTo(
                targetValue = 0.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    Canvas(modifier = Modifier.size(spinnerSize), onDraw = {

        val cornerRadius = CornerRadius(size.height / 12, size.height / 12)
        val itemVertical = Size(size.width / 6, size.height / 3)
        val itemHorizontal = Size(size.width / 3, size.height / 6)

        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius,
            size = itemHorizontal,
            topLeft = Offset(0f, (size.height/2 - size.height/12)),
            alpha = opacity[0].value
        )

        rotate(degrees = 45f){
            drawRoundRect(
                color = color,
                cornerRadius = cornerRadius,
                size = itemHorizontal,
                topLeft = Offset(0f, (size.height/2 - size.height/12)),
                alpha = opacity[1].value
            )
        }

        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius,
            size = itemVertical,
            topLeft = Offset((size.height/2 - size.height/12),0f ),
            alpha = opacity[2].value
        )

        rotate(degrees = -45f){
            drawRoundRect(
                color = color,
                cornerRadius = cornerRadius,
                size = itemHorizontal,
                topLeft = Offset(size.width*2/3, (size.height/2 - size.height/12)),
                alpha = opacity[3].value
            )
        }

        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius,
            size = itemHorizontal,
            topLeft = Offset(size.width*2/3, (size.height/2 - size.height/12)),
            alpha = opacity[4].value
        )

        rotate(degrees = 45f){
            drawRoundRect(
                color = color,
                cornerRadius = cornerRadius,
                size = itemHorizontal,
                topLeft = Offset(size.width*2/3, (size.height/2 - size.height/12)),
                alpha = opacity[5].value
            )
        }

        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius,
            size = itemVertical,
            topLeft = Offset((size.height/2 - size.height/12), size.width*2/3 ),
            alpha = opacity[6].value
        )

        rotate(degrees = -45f){
            drawRoundRect(
                color = color,
                cornerRadius = cornerRadius,
                size = itemHorizontal,
                topLeft = Offset(0f, (size.height/2 - size.height/12)),
                alpha = opacity[7].value
            )
        }
    })
}