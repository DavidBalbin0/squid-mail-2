package com.david.squid_mail.screen

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
                        Icon(Icons.Default.Reply, contentDescription = "Reply")
                    }
                    IconButton(onClick = { viewModel.deleteEmail() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                    IconButton(onClick = { viewModel.addToCalendar() }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Add to Calendar")
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
                Text(text = "From: ${viewModel.emailSender}", style = MaterialTheme.typography.body1)
                Text(text = "To: ${viewModel.emailRecipients}", style = MaterialTheme.typography.body1)
                if (viewModel.emailCcRecipients.isNotBlank()) {
                    Text(text = "CC: ${viewModel.emailCcRecipients}", style = MaterialTheme.typography.body1)
                }
                Text(text = "Date: ${viewModel.emailReceivedDate}", style = MaterialTheme.typography.body2)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = viewModel.emailSubject, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = viewModel.emailBody, style = MaterialTheme.typography.body1)
            }
        }
    )
}
