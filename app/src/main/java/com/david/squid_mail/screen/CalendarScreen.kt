package com.david.squid_mail.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen() {
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    val events = remember { mutableStateOf(mutableMapOf<LocalDate, MutableList<String>>()) }
    val showDialog = remember { mutableStateOf(false) }
    val newEventText = remember { mutableStateOf(TextFieldValue()) }
    val editingEvent = remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF278AB0))
            .padding(16.dp)
    ) {
        // Navegação entre os meses
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    currentMonth.value = currentMonth.value.minusMonths(1)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF278AB0),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_previous),
                    contentDescription = "Previous Month"
                )
            }

            Text(
                text = "${currentMonth.value.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.value.year}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Button(
                onClick = {
                    currentMonth.value = currentMonth.value.plusMonths(1)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF278AB0),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_next),
                    contentDescription = "Next Month",
                    tint = Color.White
                )
            }
        }

        // Cabeçalho com os dias da semana
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
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Dias do mês
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
                        val date = LocalDate.of(currentMonth.value.year, currentMonth.value.month, dayOfMonth)
                        val hasEvent = events.value[date]?.isNotEmpty() == true

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(if (hasEvent) Color.White else Color.Transparent)
                                .clickable {
                                    selectedDate.value = date
                                    showDialog.value = true
                                },
                            contentAlignment = Alignment.Center,
//                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = dayOfMonth.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (hasEvent) Color.Black else Color.Black,
                                textAlign = TextAlign.Center
                            )
                            if (hasEvent) {
                                Text(
                                    text = "",
                                    fontSize = 24.sp,
                                    color = Color.Red,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
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

        // Dialog para adicionar, editar ou excluir eventos
        if (showDialog.value) {
            val date = selectedDate.value
            if (date != null) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text("Gerenciar Eventos") },
                    text = {
                        Column {
                            Text("Eventos para ${date.dayOfMonth}/${date.monthValue}/${date.year}")
                            BasicTextField(
                                value = newEventText.value,
                                onValueChange = { newEventText.value = it },
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(8.dp)
                            )
                            events.value[date]?.forEachIndexed { index, event ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = event)
                                    Row {
                                        Text(
                                            text = "Editar",
                                            modifier = Modifier
                                                .clickable {
                                                    newEventText.value = TextFieldValue(event)
                                                    editingEvent.value = event
                                                }
                                                .padding(end = 8.dp)
                                        )
                                        Text(
                                            text = "Excluir",
                                            modifier = Modifier
                                                .clickable {
                                                    events.value[date]?.removeAt(index)
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                val text = newEventText.value.text
                                if (text.isNotBlank()) {
                                    if (editingEvent.value != null) {
                                        events.value[date]?.remove(editingEvent.value)
                                    }
                                    events.value.getOrPut(date) { mutableListOf() }.add(text)
                                    newEventText.value = TextFieldValue()
                                    editingEvent.value = null
                                }
                                showDialog.value = false
                            }
                        ) {
                            Text(if (editingEvent.value != null) "Salvar" else "Adicionar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog.value = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun CalendarScreenPreview(){
    CalendarScreen()
}
