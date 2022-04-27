package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView


data class Person(
    val id: Int,
    val name: String,
    val age: String
)

class MainActivity : AppCompatActivity(), StoreContract.View {
    private val personRecyclerView by lazy {
        findViewById<RecyclerView>(R.id.personRecyclerView)
    }
    lateinit var progressBar: ProgressBar
    private val presenter: StoreContract.Presenter by lazy {
        StorePresenter.create(StoreRepository.create())
    }
    private val adapter = PersonAdapter(presenter::onDelete)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        personRecyclerView.adapter = adapter
        progressBar = findViewById(R.id.progress)
        findViewById<View>(R.id.reload).setOnClickListener { presenter.load() }

        presenter.attach(this)
        presenter.load()


    }

    override fun showProgress() {
        progressBar.isVisible = true
    }

    override fun hideProgress() {
        progressBar.isVisible = false
    }

    override fun showContext(person: List<Person>) {
        personRecyclerView.isVisible = true
        adapter.setData(person)
    }

    override fun hideContext() {
        personRecyclerView.isVisible = false
    }

    override fun showError(reason: String) {
        findViewById<View>(R.id.errorGroup).isVisible = true
        findViewById<TextView>(R.id.reason).text = reason
    }

    override fun hideError() {
        findViewById<View>(R.id.errorGroup).isVisible = false
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

}


