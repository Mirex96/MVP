package com.example.mvp

interface StoreContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showContext(person: List<Person>)
        fun hideContext()

        fun showError(reason: String)
        fun hideError()

    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onDelete(person: Person)
        fun load()
    }

    interface Repository {
        fun getPersons(): List<Person>
        fun onDelete(person: Person)

    }
}