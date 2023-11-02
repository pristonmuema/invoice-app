package com.invoice.app.ui.auth.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.invoice.app.ui.AppScreen
import com.invoice.app.ui.auth.home.customers.Customers
import com.invoice.app.ui.auth.home.customers.CustomersViewModel
import com.invoice.app.ui.auth.home.customers.ManageCustomer
import com.example.invoiceapp.ui.utils.getViewModelInstance

fun NavGraphBuilder.customersNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.Customers.Home.route,
        route = AppScreen.Customers.route,
    ) {
        composable(AppScreen.Customers.Home.route) {
            val vm = navController.getViewModelInstance<CustomersViewModel>(it, AppScreen.Customers.route)
            Customers(vm, navController)
        }

        composable(AppScreen.Customers.ManageCustomer.route) {
            val vm = navController.getViewModelInstance<CustomersViewModel>(it, AppScreen.Customers.route)
            ManageCustomer(vm, navController)
        }
    }
}