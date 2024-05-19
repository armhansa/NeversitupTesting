package com.armhansa.neversituptesting.presentation

import javax.inject.Inject

class MainPresenter @Inject constructor() {

    private var view: MainView? = null

    fun setView(view: MainView) {
        this.view = view
    }

    fun testDi() {
        view?.updateData()
    }

}