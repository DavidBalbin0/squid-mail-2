package com.david.squid_mail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = null,
                        tint = Color(0xFF7C7C7C),
                        modifier = Modifier
                            .clickable { onCloseClick() }
                            .size(24.dp)
                    )
                    Row(){

                        IconButton(onClick = { /* Handle edit action */ }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color(0xFF7C7C7C),)
                        }
                        IconButton(onClick = { /* Handle more actions */ }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "More", tint = Color(0xFF7C7C7C))
                        }
                    }

                }

                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFF1C4670), shape = CircleShape)
                        ) {
                            Text(
                                text = "JM",
                                fontSize = (16.sp),
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Para: Jamal Malik", fontSize = 14.sp)
                                Text(text = "De: meuemail@squidmail.com", fontSize = 14.sp)

                            }

                        }
                    },
                )
            }
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = recipient,
                    onValueChange = { viewModel.updateRecipient(it) },
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
                    onValueChange = { viewModel.updateSubject(it) },
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
                    onValueChange = { viewModel.updateBody(it) },
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
                    onClick = { viewModel.sendEmail() },
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
    )
}

fun onCloseClick() {
    TODO("Not yet implemented")
}

//@Preview(showBackground = true)
//@Composable
//fun EmailCompositionScreenPreview() {
//    val viewModel = EmailCompositionViewModel()
//    val navController = rememberNavController()
//    EmailCompositionScreen(viewModel = viewModel, navController = navController)
//}
