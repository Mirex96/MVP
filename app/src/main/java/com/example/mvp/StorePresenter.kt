package com.example.mvp


import android.os.Handler
import android.os.Looper
import com.example.mvp.StoreContract.*
import com.example.mvp.StoreContract.Presenter.State.*


class StorePresenter private constructor(
    private val repository: Repository
) : Presenter {
    private var view: View? = null

    override var state: Presenter.State = LOADING


    override fun attach(view: View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onDelete(person: Person) {
        if (state == LOADING) {
            return
        }
        state = LOADING
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


    override fun onEdit(person: Person) {
        if (state == LOADING) {
            return
        }
        state = LOADING

        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.onEditView(person)
                replaceData()
            }, 1_000L
        )


    }

    override fun onClone(person: Person) {
        if (state == LOADING) {
            return
        }
        state = LOADING
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                repository.onClone(person)
                replaceData()
            }, 1_000L

        )
    }

    override fun onMore(person: Person) {
        if (state == LOADING) {
            return
        }
        state = LOADING
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.hideProgress()
                view?.showAction(person)
            }, 1_000L

        )

    }

    override fun onPersonEdited(person: Person) {
        view?.onEditView(person)
    }

    override fun onSelect(person: Person) {
        if (state == LOADING) {
            return
        }
        state = LOADING
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.showOnSelect(person)
                replaceData()
            }, 1_000L

        )
    }


    private fun replaceData() {
        val persons = repository.getPersons()
        view?.hideProgress()
        view?.showContext(persons)
        state = CONTENT
    }

    companion object {
        fun create(
            repository: Repository
        ) = StorePresenter(repository)
    }
}