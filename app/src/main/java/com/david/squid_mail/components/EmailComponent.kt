package com.david.squid_mail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.EmailPreview
import java.util.Date

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailComponent(
    emailPreview: EmailPreview,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val backgroundColor = if (emailPreview.isSelected.value) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.surface
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = emailPreview.email.sender + emailPreview.isSelected,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))
//                Text(
//                    text = emailPreview.email.time,
//                    fontSize = 14.sp
//                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = emailPreview.email.subject,
                fontSize = 14.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.btn_star_big_off),
                contentDescription = "Star"
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun EmailComponentPreview() {
    EmailComponent(
        onClick = {},
        onLongClick = {},
        emailPreview = EmailPreview(

            email = Email(
                1,
                "Alice",
                "Meeting Tomorrow",
                "Don't forget our meeting...",
                "Hi Bob, don't forget our meeting tomorrow at 10am. See you there!",
                Date(),
                false,
                false,
                1
            ),
            isSelected = mutableStateOf(true)
        ))
}
