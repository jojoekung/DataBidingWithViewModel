package my.learing.com.recyclerviewbinding.di

import dagger.Subcomponent
import my.learing.com.recyclerviewbinding.home.HomeComponent

@Subcomponent(modules = [HomeComponent::class])
interface AppSubComponent