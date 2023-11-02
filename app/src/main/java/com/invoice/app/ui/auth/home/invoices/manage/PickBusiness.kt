package com.invoice.app.ui.auth.home.invoices.manage

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.invoice.app.data.Resource
import com.invoice.app.data.models.Business
import com.invoice.app.ui.AppScreen
import com.invoice.app.ui.auth.home.invoices.InvoicesViewModel
import com.invoice.app.ui.auth.home.mybusinesses.MyBusiness
import com.invoice.app.ui.commons.EmptyScreen
import com.invoice.app.ui.commons.FullScreenProgressbar
import com.invoice.app.ui.faker.FakeViewModelProvider
import com.invoice.app.ui.theme.AppTheme
import com.invoice.app.ui.theme.spacing
import com.invoice.app.ui.utils.toast
import com.invoice.app.R

@Composable
fun PickBusinessScreen(viewModel: InvoicesViewModel, navController: NavController) {
    val context = LocalContext.current
    val businesses = viewModel.businesses.collectAsState()

    businesses.value?.let {
        when (it) {
            is Resource.Failure -> {
                context.toast(it.exception.message!!)
            }
            Resource.Loading -> {
                FullScreenProgressbar()
            }
            is Resource.Success -> {
                PickBusiness(viewModel, it.result, navController)
            }
        }
    }
}

@Composable
fun PickBusiness(viewModel: InvoicesViewModel, businesses: List<Business>, navController: NavController) {
    val spacing = MaterialTheme.spacing
    if (businesses.isEmpty()) {
        EmptyScreen(title = stringResource(id = R.string.empty_business)) { }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.pick_a_business),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(spacing.medium)
            )

            LazyColumn {
                items(businesses) { item ->
                    MyBusiness(
                        business = item,
                        onClick = {
                            viewModel.setBusiness(item)
                            navController.navigate(AppScreen.Invoices.ManageInvoice.PickCustomer.route)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PickBusinessPreviewLight() {
    AppTheme {
        PickBusiness(
            FakeViewModelProvider.provideInvoicesViewModel(),
            listOf(
                Business(
                    name = "Biz 1",
                    address = "Stone town, Zanzibar",
                    email = "bizone@gmail.com",
                    phone = "68340154535"
                ),
                Business(
                    name = "Biz 2",
                    address = "Stone town, Zanzibar",
                    email = "biztwo@gmail.com",
                    phone = "68340154534"
                )
            ),
            rememberNavController()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PickBusinessPreviewDark() {
    AppTheme {
        PickBusiness(
            FakeViewModelProvider.provideInvoicesViewModel(),
            listOf(
                Business(
                    name = "Biz 1",
                    address = "Stone town, Zanzibar",
                    email = "bizone@gmail.com",
                    phone = "68340154535"
                ),
                Business(
                    name = "Biz 2",
                    address = "Stone town, Zanzibar",
                    email = "biztwo@gmail.com",
                    phone = "68340154534"
                )
            ),
            rememberNavController()
        )
    }
}