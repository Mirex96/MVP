package com.example.mvp




class StoreRepository : StoreContract.Repository {
    private val persons = mutableListOf(
        Person(IdGenerator.generatorId(), "Mary", "24"),
        Person(IdGenerator.generatorId(), "Olga", "27"),
        Person(IdGenerator.generatorId(), "Klara", "29"),
    )

    override fun getPersons(): List<Person> = persons


    override fun onDelete(person: Person) {
        persons.remove(person)
    }

    override fun onClone(person: Person) {
        persons.add(person)
    }

    override fun onEdit(person: Person) {

    }


    companion object {
        fun create() = StoreRepository()

    }
}