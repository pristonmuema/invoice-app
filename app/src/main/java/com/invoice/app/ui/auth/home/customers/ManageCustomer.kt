package com.invoice.app.ui.auth.home.customers

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.invoice.app.R
import com.invoice.app.data.Resource
import com.invoice.app.ui.commons.UserConfirmationDialog
import com.invoice.app.ui.faker.FakeViewModelProvider
import com.invoice.app.ui.theme.AppTheme
import com.invoice.app.ui.theme.spacing
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import java.io.InputStream


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ManageCustomer(viewModel: CustomersViewModel, navController: NavController) {

    val showDeleteConfirmation = remember { mutableStateOf(false) }

    val name = viewModel.name.collectAsState()
    val address = viewModel.address.collectAsState()
    val phone = viewModel.phone.collectAsState()
    val email = viewModel.email.collectAsState()
    val img = viewModel.img.collectAsState()

    val areInputsValid = viewModel.areInputsValid.collectAsState()
    val manageCustomerResult = viewModel.manageCustomerResult.collectAsState()

    val isUpdating = viewModel.isUpdating.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()
    val context = LocalContext.current

    val spacing = MaterialTheme.spacing

    val imgV: Bitmap =
        BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_report_image)
    val bitmap = remember { mutableStateOf(imgV) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {

        if (it != null) {
            bitmap.value = it
            viewModel.img.value = it.toString()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.xLarge,
                end = spacing.xLarge,
                top = spacing.medium,
                bottom = spacing.medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.add_customer),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = spacing.large, bottom = spacing.large)
        )



            Image(
                bitmap = bitmap.value.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = spacing.medium)
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(Color.DarkGray)
                    .clickable {
                        launcher.launch()
                    }
            )



        TextField(
            value = name.value,
            onValueChange = {
                viewModel.name.value = it
            },
            label = {
                if (name.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.name_required))
                } else {
                    Text(text = stringResource(id = R.string.name))
                }
            },
            modifier = Modifier
                .padding(bottom = spacing.medium)
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    viewModel.validateInputs()
                    focusManager.moveFocus(FocusDirection.Next)
                },
            ),
            maxLines = 1,
            isError = name.value.isEmpty()
        )

        TextField(
            value = address.value,
            onValueChange = {
                viewModel.address.value = it
            },
            label = {
                if (address.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.address_required))
                } else {
                    Text(text = stringResource(id = R.string.address))
                }
            },
            modifier = Modifier
                .padding(bottom = spacing.medium)
                .fillMaxWidth()
                .height(100.dp)
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    viewModel.validateInputs()
                    focusManager.moveFocus(FocusDirection.Next)
                },
            ),
            maxLines = 3,
            isError = address.value.isEmpty()
        )

        TextField(
            value = phone.value,
            onValueChange = {
                viewModel.phone.value = it
            },
            label = {
                if (phone.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.phone_required))
                } else {
                    Text(text = stringResource(id = R.string.phone))
                }
            },
            modifier = Modifier
                .padding(bottom = spacing.medium)
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    viewModel.validateInputs()
                    focusManager.moveFocus(FocusDirection.Next)
                },
            ),
            maxLines = 1,
            isError = phone.value.isEmpty()
        )

        TextField(
            value = email.value,
            onValueChange = {
                viewModel.email.value = it
            },
            label = {
                if (phone.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.email_required))
                } else {
                    Text(text = stringResource(id = R.string.email))
                }
            },
            modifier = Modifier
                .padding(bottom = spacing.medium)
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    viewModel.validateInputs()
                }
            ),
            maxLines = 1,
            isError = email.value.isEmpty()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(spacing.small)
        ) {
            manageCustomerResult.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                    }

                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.popBackStack()
                            viewModel.resetResource()
                        }
                    }

                    Resource.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        Button(
            onClick = {
                if (isUpdating.value == null) {
                    viewModel.addCustomer()
                } else {
                    viewModel.updateCustomer()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing.small, end = spacing.small)
                .bringIntoViewRequester(bringIntoViewRequester),
            enabled = areInputsValid.value
        ) {
            Text(
                text = if (isUpdating.value == null) stringResource(id = R.string.add) else stringResource(
                    id = R.string.update
                ),
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (isUpdating.value != null) {
            Button(
                onClick = {
                    showDeleteConfirmation.value = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = spacing.small, end = spacing.small),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(
                    text = stringResource(id = R.string.delete),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onError
                )
            }
        }
    }

    if (showDeleteConfirmation.value) {
        UserConfirmationDialog { confirmation ->
            if (confirmation) {
                viewModel.deleteCustomer()
            }
            showDeleteConfirmation.value = false
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ManageCustomerPreviewLight() {
    AppTheme {
        ManageCustomer(FakeViewModelProvider.provideCustomersViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ManageCustomerPreviewDark() {
    AppTheme {
        ManageCustomer(FakeViewModelProvider.provideCustomersViewModel(), rememberNavController())
    }
}