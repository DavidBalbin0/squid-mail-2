package com.david.squid_mail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailReadScreen(
    viewModel: EmailReadViewModel,
    onBack: () -> Unit,
    onReply: () -> Unit
) {
    // Load the email (this could be passed via nav arguments in a real app)
    LaunchedEffect(Unit) {
        viewModel.loadEmail("sample_email_id")
    }

    // Scaffold or a similar layout structure to organize the screen content
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Email Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.markAsFavorite() }) {
                        Icon(
                            imageVector = if (viewModel.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(onClick = { viewModel.replyToEmail(); onReply() }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Reply")
                    }
                    IconButton(onClick = { viewModel.deleteEmail() }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Delete")
                    }
                    IconButton(onClick = { viewModel.addToCalendar() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Add to Calendar")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(text = "From: ${viewModel.emailSender}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "To: ${viewModel.emailRecipients}", style = MaterialTheme.typography.bodyLarge)
                if (viewModel.emailCcRecipients.isNotBlank()) {
                    Text(text = "CC: ${viewModel.emailCcRecipients}", style = MaterialTheme.typography.bodyLarge)
                }
                Text(text = "Date: ${viewModel.emailReceivedDate}", style = MaterialTheme.typography.bodyMedium)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = viewModel.emailSubject, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = viewModel.emailBody, style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}
