package com.example.motion_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import motion_app.composeapp.generated.resources.Res
import motion_app.composeapp.generated.resources.ic_barbell
import motion_app.composeapp.generated.resources.ic_fullscreen
import motion_app.composeapp.generated.resources.ic_play
import motion_app.composeapp.generated.resources.ic_time
import motion_app.composeapp.generated.resources.workout_pull_up
import motion_app.composeapp.generated.resources.workout_running
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class WorkoutCardData(
    val title: String,
    val durationMinutes: Int,
    val exercisesCount: Int,
    val lastPerformed: String,
    val tags: List<String>,
    val illustration: DrawableResource,
)

val FirstPullWorkout = WorkoutCardData(
    title = "First Pull",
    durationMinutes = 45,
    exercisesCount = 7,
    lastPerformed = "2 weeks ago",
    tags = listOf("Back", "Rear delts"),
    illustration = Res.drawable.workout_pull_up,
)

val HiitWorkout = WorkoutCardData(
    title = "HIIT",
    durationMinutes = 15,
    exercisesCount = 1,
    lastPerformed = "2 weeks ago",
    tags = listOf("Back", "Rear delts"),
    illustration = Res.drawable.workout_running,
)

private val CardShape = RoundedCornerShape(22.dp)
private val CardBorderColor = Color(0x40FF6857) // rgba(255,104,87,0.25)
private val TextPrimary = Color(0xFF191615)
private val TextSecondary = Color(0xFF939393)
private val TagBackground = Color(0xFFEAF4F4)
private val TagText = Color(0xFF1B7875)
private val PlayButtonBg = Color(0xFF1B6B67)
private val FullscreenButtonBg = Color(0xFFFFEDEB)
private val EllipseBg = Color(0xFFFFEDEB)

@Composable
fun WorkoutCard(
    data: WorkoutCardData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .softShadow()
            .background(Color.White, CardShape)
            .border(1.dp, CardBorderColor, CardShape)
            .clip(CardShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            // Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(135.dp)
                        .background(EllipseBg, CircleShape)
                )
                Image(
                    painter = painterResource(data.illustration),
                    contentDescription = data.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxHeight(),
                )
            }

            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)

            // Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = data.title,
                                fontFamily = RanyFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 36.sp,
                                color = TextPrimary,
                            )

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(top = 4.dp),
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            painter = painterResource(Res.drawable.ic_time),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(TextPrimary),
                                            modifier = Modifier.size(24.dp),
                                        )
                                        Text(
                                            text = "${data.durationMinutes} minutes",
                                            fontFamily = RanyFontFamily,
                                            fontSize = 14.sp,
                                            color = TextPrimary,
                                        )
                                    }
                                    Text(
                                        text = "•",
                                        fontFamily = RanyFontFamily,
                                        fontSize = 14.sp,
                                        color = Color(0xFF322C29),
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            painter = painterResource(Res.drawable.ic_barbell),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(TextPrimary),
                                            modifier = Modifier.size(24.dp),
                                        )
                                        Text(
                                            text = "${data.exercisesCount} exercises",
                                            fontFamily = RanyFontFamily,
                                            fontSize = 14.sp,
                                            color = TextPrimary,
                                        )
                                    }
                                }
                                Text(
                                    text = data.lastPerformed,
                                    fontFamily = RanyFontFamily,
                                    fontSize = 14.sp,
                                    color = TextSecondary,
                                )
                            }

                        }
                        // Play button
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .background(PlayButtonBg, CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.ic_play),
                                contentDescription = "Play",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                    // Tags
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        data.tags.forEach { tag -> WorkoutTag(tag) }
                    }
                }
            }
        }

        // Fullscreen button
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(15.dp)
                .background(FullscreenButtonBg, CircleShape)
                .padding(12.dp),
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_fullscreen),
                contentDescription = "Expand",
                colorFilter = ColorFilter.tint(Color(0xFFFF6857)),
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

// Simulates CSS box-shadow: 0px 0px 17px 4px rgba(0,0,0,0.03)
// Uses layered concentric rounded rects to approximate a Gaussian glow,
// which works identically on all CMP targets (no platform-specific APIs).
private fun Modifier.softShadow(
    color: Color = Color.Black,
    alpha: Float = 0.03f,
    blur: Float = 8f,    // dp
    spread: Float = 0f,  // dp
    cornerRadius: Float = 22f, // dp
    layers: Int = 4,
): Modifier = this.drawBehind {
    val density = this.density  // dp → px factor is implied by drawBehind's DrawScope
    val blurPx = blur * density
    val spreadPx = spread * density
    val cornerPx = cornerRadius * density

    for (i in 1..layers) {
        val ratio = i.toFloat() / layers           // 0..1, 1 = outermost
        val expand = spreadPx + blurPx * ratio     // how far this layer extends
        val layerAlpha = alpha * (1f - ratio) / layers * layers.toFloat() * 0.5f

        drawRoundRect(
            color = color.copy(alpha = layerAlpha.coerceIn(0f, 1f)),
            topLeft = Offset(-expand, -expand),
            size = Size(size.width + expand * 2, size.height + expand * 2),
            cornerRadius = CornerRadius(cornerPx + expand * 0.3f),
        )
    }
}

@Composable
fun WorkoutTag(label: String) {
    Box(
        modifier = Modifier
            .background(TagBackground, CircleShape)
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        Text(
            text = label,
            fontFamily = RanyFontFamily,
            fontSize = 13.sp,
            color = TagText,
        )
    }
}
