package com.example.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    private val onDelete: (Person) -> Unit,
    private val onEdit: (Person) -> Unit,
    private val onClone: (Person) -> Unit,
    private val onMore: (Person) -> Unit,
    private val onSelect: (Person) -> Unit

) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {


    private var personList = listOf<Person>()
    fun setData(personList: List<Person>) {
        this.personList = personList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    inner class PersonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(person: Person) {
            itemView.findViewById<TextView>(R.id.name).text = person.name
            itemView.findViewById<TextView>(R.id.age).text = person.age

            itemView.findViewById<View>(R.id.delete).setOnClickListener { onDelete(person) }
            itemView.findViewById<View>(R.id.moreBs).setOnClickListener { onMore(person) }

            itemView.findViewById<View>(R.id.edit).setOnClickListener { onEdit(person) }
            itemView.findViewById<View>(R.id.clone).setOnClickListener { onClone(person) }
            itemView.setOnClickListener { onSelect(person) }
        }
    }
}