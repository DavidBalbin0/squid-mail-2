package com.david.squid_mail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen() {
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF278AB0))
            .padding(16.dp)
    ) {
        // Month and Year Title with Navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Previous Month Button
            Button(onClick = {
                currentMonth.value = currentMonth.value.minusMonths(1)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF278AB0), // Cor de fundo do botão
                    contentColor = Color.White // Cor do ícone e do texto dentro do botão
                )
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_previous),
                    contentDescription = "Previous Month"
                )
            }

            // Month and Year Title
            Text(
                text = "${currentMonth.value.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.value.year}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Next Month Button
            Button(
                onClick = {
                    currentMonth.value = currentMonth.value.plusMonths(1)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF278AB0), // Cor de fundo do botão
                    contentColor = Color.White // Cor do ícone e do texto dentro do botão
                )
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_next),
                    contentDescription = "Next Month",
                    tint = Color.White // Cor do ícone
                )
            }
        }

        // Header with day names
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DayOfWeek.values().forEach { day ->
                Text(
                    text = day.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Days in the month
        val daysInMonth = currentMonth.value.lengthOfMonth()
        val firstDayOfMonth = LocalDate.of(currentMonth.value.year, currentMonth.value.month, 1).dayOfWeek

        val weeksInMonth = (daysInMonth + firstDayOfMonth.value - 1) / 7 + 1
        for (week in 0 until weeksInMonth) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (dayOfWeek in DayOfWeek.values()) {
                    val dayOfMonth = week * 7 + dayOfWeek.value - firstDayOfMonth.value + 1
                    if (dayOfMonth in 1..daysInMonth) {
                        Text(
                            text = dayOfMonth.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                        )
                    } else {
                        Text(
                            text = "",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
//@Preview(showSystemUi = true)
@Preview(showSystemUi = true)
fun CalendarScreenPreview(){
    CalendarScreen()
}
