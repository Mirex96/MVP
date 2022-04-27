package com.example.mvp


import android.os.Handler
import android.os.Looper

class StorePresenter private constructor(
    private val repository: StoreContract.Repository
) : StoreContract.Presenter {
    private var view: StoreContract.View? = null

    override fun attach(view: StoreContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onDelete(person: Person) {
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                repository.onDelete(person)
                replaceData()
            }, 1_000L

        )
    }




    override fun load() {
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                replaceData()
            }, 3_000L

        )
    }


    private fun replaceData() {
        val persons = repository.getPersons()
        view?.hideProgress()
        view?.showContext(persons)
    }

    companion object {
        fun create(
            repository: StoreContract.Repository
        ) = StorePresenter(repository)
    }
}