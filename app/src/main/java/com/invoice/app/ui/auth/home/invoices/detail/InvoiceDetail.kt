package com.invoice.app.ui.auth.home.invoices.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.invoice.app.data.utils.toDateTime
import com.invoice.app.ui.auth.home.invoices.InvoicesViewModel
import com.invoice.app.ui.faker.FakeViewModelProvider
import com.invoice.app.ui.theme.AppTheme
import com.invoice.app.ui.theme.spacing
import com.invoice.app.ui.utils.toStringOrEmpty

@Composable
fun InvoiceDetail(viewModel: InvoicesViewModel, navController: NavController) {
    val spacing = MaterialTheme.spacing
    val invoice = viewModel.invoice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(spacing.medium)
    ) {

        Text(
            text = invoice.value.business?.name.toStringOrEmpty(),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = invoice.value.business?.address.toStringOrEmpty(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Divider(modifier = Modifier.padding(top = spacing.medium))

        Text(
            text = stringResource(id = com.invoice.app.R.string.invoice),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Divider(modifier = Modifier.padding(bottom = spacing.medium))

        Row(
            modifier = Modifier.padding(bottom = spacing.medium)
        ) {
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                Text(
                    text = stringResource(id = com.invoice.app.R.string.to),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = invoice.value.customer?.name.toStringOrEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = invoice.value.customer?.address.toStringOrEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                Text(
                    text = stringResource(id = com.invoice.app.R.string.date),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = invoice.value.createdAt.toDateTime(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


        Row {
            TableCell(text = stringResource(id = com.invoice.app.R.string.particulars), heading = true, weight = 0.45f)
            TableCell(text = stringResource(id = com.invoice.app.R.string.qty), alignment = TextAlign.End, heading = true, weight = 0.15f)
            TableCell(text = stringResource(id = com.invoice.app.R.string.price), alignment = TextAlign.End, heading = true, weight = 0.2f)
            TableCell(text = stringResource(id = com.invoice.app.R.string.amount), alignment = TextAlign.End, heading = true, weight = 0.2f)
        }

        invoice.value.items.forEach { item ->
            Row {
                TableCell(text = item.desc, weight = 0.45f)
                TableCell(text = item.qty.toString(), alignment = TextAlign.End, weight = 0.15f)
                TableCell(text = item.price.toString(), alignment = TextAlign.End, weight = 0.2f)
                TableCell(text = item.amount.toString(), alignment = TextAlign.End, weight = 0.2f)
            }
        }

        Row {
            TableCell(text = stringResource(id = com.invoice.app.R.string.total_amount), heading = true, alignment = TextAlign.End, weight = 0.8f)
            TableCell(text = invoice.value.totalAmount.toString(), alignment = TextAlign.End, weight = 0.2f)
        }

        Row {
            TableCell(text = invoice.value.tax?.taxLabel.toStringOrEmpty(), heading = true, alignment = TextAlign.End, weight = 0.8f)
            TableCell(text = invoice.value.taxAmount.toString(), alignment = TextAlign.End, weight = 0.2f)
        }

        Row {
            TableCell(text = stringResource(id = com.invoice.app.R.string.invoice_amount), heading = true, alignment = TextAlign.End, weight = 0.8f)
            TableCell(text = invoice.value.invoiceAmount.toString(), alignment = TextAlign.End, weight = 0.2f)
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InvoiceDetailPreviewLight() {
    AppTheme {
        InvoiceDetail(FakeViewModelProvider.provideInvoicesViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvoiceDetailPreviewDark() {
    AppTheme {
        InvoiceDetail(FakeViewModelProvider.provideInvoicesViewModel(), rememberNavController())
    }
}