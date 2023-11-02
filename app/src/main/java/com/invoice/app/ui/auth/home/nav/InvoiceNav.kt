package com.invoice.app.ui.auth.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.invoice.app.ui.AppScreen
import com.invoice.app.ui.auth.home.invoices.detail.InvoiceDetail
import com.example.invoiceapp.ui.utils.getViewModelInstance
import com.invoice.app.ui.auth.home.invoices.InvoicesScreen
import com.invoice.app.ui.auth.home.invoices.InvoicesViewModel
import com.invoice.app.ui.auth.home.invoices.manage.AddInvoiceItem
import com.invoice.app.ui.auth.home.invoices.manage.PickBusinessScreen
import com.invoice.app.ui.auth.home.invoices.manage.PickCustomerScreen
import com.invoice.app.ui.auth.home.invoices.manage.PickTaxScreen


fun NavGraphBuilder.invoiceNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.Invoices.Home.route,
        route = AppScreen.Invoices.route
    ) {
        composable(AppScreen.Invoices.Home.route) {
            val vm = navController.getViewModelInstance<InvoicesViewModel>(it, AppScreen.Invoices.route)
            InvoicesScreen(vm, navController)
        }

        composable(AppScreen.Invoices.InvoiceDetail.route) {
            val vm = navController.getViewModelInstance<InvoicesViewModel>(it, AppScreen.Invoices.route)
            InvoiceDetail(vm, navController)
        }

        navigation(
            startDestination = AppScreen.Invoices.ManageInvoice.PickBusiness.route,
            route = AppScreen.Invoices.ManageInvoice.route
        ) {

            composable(AppScreen.Invoices.ManageInvoice.PickBusiness.route) {
                val vm = navController.getViewModelInstance<InvoicesViewModel>(it, AppScreen.Invoices.route)
                PickBusinessScreen(vm, navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.PickCustomer.route) {
                val vm = navController.getViewModelInstance<InvoicesViewModel>(it, AppScreen.Invoices.route)
                PickCustomerScreen(vm, navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.PickTax.route) {
                val vm = navController.getViewModelInstance<InvoicesViewModel>(it, AppScreen.Invoices.route)
                PickTaxScreen(vm, navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.AddItems.route) {
                val vm = navController.getViewModelInstance<InvoicesViewModel>(it, AppScreen.Invoices.route)
                AddInvoiceItem(vm, navController)
            }
        }
    }
}