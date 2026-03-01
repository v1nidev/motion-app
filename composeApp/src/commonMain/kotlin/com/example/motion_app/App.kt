package com.example.motion_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import motion_app.composeapp.generated.resources.Res
import motion_app.composeapp.generated.resources.avatar_lola
import motion_app.composeapp.generated.resources.ic_calendar
import motion_app.composeapp.generated.resources.ic_home
import motion_app.composeapp.generated.resources.ic_run
import org.jetbrains.compose.resources.painterResource

private val NavBarBorderColor = Color(0x26FF6857)

private fun DrawScope.drawScreenBackground() {
    // Base color: #FBF7F7
    drawRect(Color(0xFFFBF7F7))

    // Radial gradient: 56.33% 51.35% at 99.07% 0.21%
    //   rgba(255,165,155,0.28) 0% → rgba(251,189,182,0.20) 53.79% → transparent 100%
    val cx = size.width * 0.9907f
    val cy = size.height * 0.0021f
    val rx = size.width * 0.5633f
    val ry = size.height * 0.5135f
    // Scale y-axis so a circle with radius rx appears as an ellipse (rx, ry) on screen
    val scaleY = ry / rx
    withTransform({ scale(1f, scaleY, pivot = Offset(cx, cy)) }) {
        // Compute rect bounds in scaled draw-space to cover the full screen
        val topY = cy - cy / scaleY
        val bottomY = cy + (size.height - cy) / scaleY
        drawRect(
            brush = Brush.radialGradient(
                colorStops = arrayOf(
                    0f to Color(0x47FFA59B),      // rgba(255,165,155,0.28)
                    0.5379f to Color(0x33FBBDB6), // rgba(251,189,182,0.20)
                    1f to Color(0x00FFFFFF),       // transparent
                ),
                center = Offset(cx, cy),
                radius = rx,
                tileMode = TileMode.Decal,
            ),
            topLeft = Offset(0f, topY),
            size = Size(size.width, bottomY - topY),
        )
    }

    // Linear gradient: 195deg
    //   transparent 52.62% → rgba(251,189,182,0.16) 72.94% → rgba(251,189,182,0.32) 84.15%
    //   → rgba(251,189,182,0.12) 99.29% → rgba(252,202,196,0.10) 99.64% → transparent 109.81%
    val theta = 195.0 * PI / 180.0
    val dx = sin(theta).toFloat()   // ≈ -0.2588 (leftward)
    val dy = -cos(theta).toFloat()  // ≈  0.9659 (downward)
    val centerX = size.width / 2f
    val centerY = size.height / 2f
    // Project all corners onto gradient direction to find CSS gradient line endpoints
    var minP = Float.MAX_VALUE
    var maxP = -Float.MAX_VALUE
    for (corner in arrayOf(
        Offset(0f, 0f), Offset(size.width, 0f),
        Offset(0f, size.height), Offset(size.width, size.height),
    )) {
        val p = (corner.x - centerX) * dx + (corner.y - centerY) * dy
        if (p < minP) minP = p
        if (p > maxP) maxP = p
    }
    drawRect(
        brush = Brush.linearGradient(
            colorStops = arrayOf(
                0.0f to Color(0x00FFFFFF),        // transparent
                0.5262f to Color(0x00FFFFFF),     // transparent at 52.62%
                0.7294f to Color(0x29FBBDB6),     // rgba(251,189,182,0.16)
                0.8415f to Color(0x52FBBDB6),     // rgba(251,189,182,0.32)
                0.9929f to Color(0x1FFBBDB6),     // rgba(251,189,182,0.12)
                0.9964f to Color(0x1AFCCAC4),     // rgba(252,202,196,0.10)
                1.0f to Color(0x00FFFFFF),         // transparent (clamped from 109.81%)
            ),
            start = Offset(centerX + dx * minP, centerY + dy * minP),
            end = Offset(centerX + dx * maxP, centerY + dy * maxP),
        ),
    )
}

@Composable
@Preview
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind { drawScreenBackground() }
    ) {
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 48.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color(0xFFFFDCD9), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.avatar_lola),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                    )
                }
                Text(
                    text = "Welcome, Lola!",
                    fontFamily = RanyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color(0xFF2D2929),
                )
            }

            // Today's goals
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Today's goals",
                    fontFamily = RanyFontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 20.sp,
                    color = Color(0xFF2D2929),
                )
                WorkoutCard(data = FirstPullWorkout, modifier = Modifier.fillMaxWidth())
                WorkoutCard(data = HiitWorkout, modifier = Modifier.fillMaxWidth())
            }
        }

        // Bottom navigation bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .border(
                    1.dp,
                    NavBarBorderColor,
                    RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                )
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NavBarItem(
                    iconRes = Res.drawable.ic_home,
                    contentDescription = "Home",
                    tint = Color(0xFF191615),
                )
                NavBarItem(
                    iconRes = Res.drawable.ic_run,
                    contentDescription = "Workouts",
                    tint = Color(0xFFAAAAAA),
                )
                NavBarItem(
                    iconRes = Res.drawable.ic_calendar,
                    contentDescription = "Schedule",
                    tint = Color(0xFFAAAAAA),
                )
            }
        }
    }
}

@Composable
private fun NavBarItem(
    iconRes: org.jetbrains.compose.resources.DrawableResource,
    contentDescription: String,
    tint: Color,
) {
    Box(
        modifier = Modifier.size(48.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(tint),
            modifier = Modifier.size(24.dp),
        )
    }
}