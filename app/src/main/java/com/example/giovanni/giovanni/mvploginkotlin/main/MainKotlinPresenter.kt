package com.example.giovanni.giovanni.mvploginkotlin.main

class MainKotlinPresenter (var mainView: MainView?, val findItemsInteractor: FindItemsInteractorKotlin) {

    fun onResume() {
        mainView?.showProgress()
        findItemsInteractor.findItems(::onItemsLoaded)
    }

    private fun onItemsLoaded(items: List<String>) {
        mainView?.apply {
            setItems(items)
            hideProgress()
        }
    }

    fun onItemClicked(item: String) {
        mainView?.showMessage(item)
    }

    fun onDestroy() {
        mainView = null
    }
}