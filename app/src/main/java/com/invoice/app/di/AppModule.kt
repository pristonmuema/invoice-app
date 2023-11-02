package com.invoice.app.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.invoice.app.data.auth.AuthRepositoryImpl
import com.invoice.app.data.*
import com.invoice.app.data.home.CustomersRepository
import com.invoice.app.data.home.CustomersRepositoryImpl
import com.invoice.app.data.home.DashboardRepository
import com.invoice.app.data.home.DashboardRepositoryImpl
import com.invoice.app.data.home.InvoiceRepository
import com.invoice.app.data.home.InvoiceRepositoryImpl
import com.invoice.app.data.home.MyBusinessRepository
import com.invoice.app.data.home.MyBusinessRepositoryImpl
import com.invoice.app.data.home.TaxRepository
import com.invoice.app.data.home.TaxRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.invoice.app.data.auth.AuthRepository

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseDb(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideMyBusinessRepository(impl: MyBusinessRepositoryImpl): MyBusinessRepository = impl

    @Provides
    fun provideCustomersRepository(impl: CustomersRepositoryImpl): CustomersRepository = impl

    @Provides
    fun provideTaxRepository(impl: TaxRepositoryImpl): TaxRepository = impl

    @Provides
    fun provideInvoiceRepository(impl: InvoiceRepositoryImpl): InvoiceRepository = impl

    @Provides
    fun provideDashboardRepository(impl: DashboardRepositoryImpl): DashboardRepository = impl
}