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
        fun showAction(person: Person)
        fun showOnSelect(person: Person)
    }

    interface Presenter {
        var state: State

        fun attach(view: View)
        fun detach()
        fun onDelete(person: Person)
        fun load()
        fun onEdit(person: Person)
        fun onClone(person: Person)
        fun onMore(person: Person)
        fun onPersonEdited(person: Person)

        fun onSelect(person: Person)

        enum class State {
            LOADING,
            CONTENT,
            ERROR
        }
    }

    interface Repository {
        fun getPersons(): List<Person>

        fun onDelete(person: Person)
        fun onClone(person: Person)
        fun onEdit(person: Person)
    }
}