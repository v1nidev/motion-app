package com.example.motion_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import motion_app.composeapp.generated.resources.Rany
import motion_app.composeapp.generated.resources.RanyBold
import motion_app.composeapp.generated.resources.RanyBoldItalic
import motion_app.composeapp.generated.resources.RanyItalic
import motion_app.composeapp.generated.resources.RanyLight
import motion_app.composeapp.generated.resources.RanyLightItalic
import motion_app.composeapp.generated.resources.RanyMedium
import motion_app.composeapp.generated.resources.RanyMediumItalic
import motion_app.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

val RanyFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.Rany, FontWeight.Normal, FontStyle.Normal),
        Font(Res.font.RanyBold, FontWeight.Bold, FontStyle.Normal),
        Font(Res.font.RanyLight, FontWeight.Light, FontStyle.Normal),
        Font(Res.font.RanyMedium, FontWeight.W500, FontStyle.Normal),
        Font(Res.font.RanyItalic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.RanyBoldItalic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.RanyLightItalic, FontWeight.Light, FontStyle.Italic),
        Font(Res.font.RanyMediumItalic, FontWeight.W500, FontStyle.Italic),
    )
