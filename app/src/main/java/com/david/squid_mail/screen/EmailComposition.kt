package com.david.squid_mail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EmailCompositionScreen(
    viewModel: EmailCompositionViewModel,
    navController: NavController
) {
    val recipient by viewModel::emailRecipient
    val subject by viewModel::emailSubject
    val body by viewModel::emailBody
    val recipientError by viewModel::emailRecipientError
    val subjectError by viewModel::emailSubjectError

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = recipient,
            onValueChange = { viewModel.updateRecipient(it)},
            label = { Text("Para (Destinatário)") },
            modifier = Modifier.fillMaxWidth(),
            isError = recipientError.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        if (recipientError.isNotEmpty()) {
            Text(
                text = recipientError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = subject,
            onValueChange = { viewModel.updateSubject(it)},
            label = { Text("Assunto") },
            modifier = Modifier.fillMaxWidth(),
            isError = subjectError.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )
        if (subjectError.isNotEmpty()) {
            Text(
                text = subjectError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = body,
            onValueChange = { viewModel.updateBody(it)},
            label = { Text("Corpo do E-mail") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            maxLines = 10
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.sendEmail()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.saveDraft() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar como Rascunho")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailComposeScreenPreview() {
    val viewModel = EmailCompositionViewModel(LocalContext.current)

    val navController = rememberNavController()

    EmailCompositionScreen(viewModel = viewModel, navController = navController)
}