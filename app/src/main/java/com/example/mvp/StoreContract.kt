package com.example.mvp

interface StoreContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showContext(person: List<Person>)
        fun hideContext()

        fun showError(reason: String)
        fun hideError()


        fun onEditView(person: Person)

    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onDelete(person: Person)

        fun load()

        fun onEdit(person: Person)
        fun onClone(person: Person)
    }

    interface Repository {
        fun getPersons(): List<Person>

        fun onDelete(person: Person)
        fun onClone(person: Person)



    }
}