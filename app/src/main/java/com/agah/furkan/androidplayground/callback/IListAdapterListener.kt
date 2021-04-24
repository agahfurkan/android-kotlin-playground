package com.agah.furkan.androidplayground.callback

interface IListAdapterListener<T> {

    fun listItemClicked(item: T) {}
}