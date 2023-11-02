package com.invoice.app.ui.auth.home.invoices

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.invoice.app.data.Resource
import com.invoice.app.data.models.Invoice
import com.invoice.app.ui.AppScreen
import com.invoice.app.ui.commons.EmptyScreen
import com.invoice.app.ui.commons.FullScreenProgressbar
import com.invoice.app.ui.commons.UserConfirmationDialog
import com.invoice.app.ui.faker.FakeViewModelProvider
import com.invoice.app.ui.theme.AppTheme
import com.invoice.app.ui.utils.toast
import com.invoice.app.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoicesScreen(viewModel: InvoicesViewModel, navController: NavController) {
    val context = LocalContext.current
    val invoices = viewModel.invoices.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.initNewInvoice()
                    navController.navigate(AppScreen.Invoices.ManageInvoice.route)
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = com.invoice.app.R.string.empty))
            }
        },
        content = {
            invoices.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        context.toast(it.exception.message!!)
                    }

                    Resource.Loading -> {
                        FullScreenProgressbar()
                    }

                    is Resource.Success -> {
                        if (it.result.isEmpty()) {
                            EmptyScreen(title = stringResource(id = R.string.empty_invoice)) { }
                        } else {
                            Invoices(
                                invoices = it.result,
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun Invoices(invoices: List<Invoice>, viewModel: InvoicesViewModel, navController: NavController) {

    val invoiceDeleteConfirmation = remember { mutableStateOf<String?>(null) }

    LazyColumn {
        items(invoices) { item ->
            InvoiceCard(
                invoice = item,
                onClick = {
                    viewModel.setCurrentInvoice(item)
                    navController.navigate(AppScreen.Invoices.InvoiceDetail.route)
                },
                onMenuClick = {
                    when (it) {
                        InvoiceMenu.Delete -> {
                            invoiceDeleteConfirmation.value = item.id
                        }
                        InvoiceMenu.Edit -> {
                            viewModel.initInvoiceUpdate(item)
                            navController.navigate(AppScreen.Invoices.ManageInvoice.route)
                        }
                        InvoiceMenu.MarkAsPaid -> {
                            viewModel.updatePaidState(item.id, true)
                        }
                        InvoiceMenu.MarkAsUnPaid -> {
                            viewModel.updatePaidState(item.id, false)
                        }
                    }
                }
            )
        }
    }

    if (invoiceDeleteConfirmation.value != null) {
        UserConfirmationDialog { confirmation ->
            if (confirmation) {
                viewModel.deleteInvoice(invoiceDeleteConfirmation.value!!)
            }
            invoiceDeleteConfirmation.value = null
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InvoicesPreviewLight() {
    AppTheme {
        InvoicesScreen(FakeViewModelProvider.provideInvoicesViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvoicesPreviewDark() {
    AppTheme {
        InvoicesScreen(FakeViewModelProvider.provideInvoicesViewModel(), rememberNavController())
    }
}