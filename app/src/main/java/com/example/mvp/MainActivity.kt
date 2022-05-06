package com.example.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp.BottomSheetDialogAction.*
import kotlinx.parcelize.Parcelize

const val EDIT_KEY = "EDIT_KEY"
const val EDIT_REQUEST_KEY = 100
const val KEY_SELECT = "KEY_SELECT"


@Parcelize
data class Person(
    val id: Int,
    val name: String,
    val age: String
) : Parcelable

class MainActivity : AppCompatActivity(), StoreContract.View {
    private val personRecyclerView by lazy {
        findViewById<RecyclerView>(R.id.personRecyclerView)
    }
    lateinit var progressBar: ProgressBar
    private val presenter: StoreContract.Presenter by lazy {
        StorePresenter.create(StoreRepository.create())
    }
    private val adapter = PersonAdapter(
        presenter::onDelete,
        presenter::onEdit,
        presenter::onClone,
        presenter::onMore,
        presenter::onSelect
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        personRecyclerView.adapter = adapter
        progressBar = findViewById(R.id.progress)
        findViewById<View>(R.id.reload).setOnClickListener { presenter.load() }

        presenter.attach(this)
        presenter.load()


        supportFragmentManager
            .setFragmentResultListener(REQUEST_KEY_BS, this) { _, bundle ->
                val action = bundle.getSerializable(BUNDLE_KEY_TYPE) as BottomSheetDialogAction
                when (action) {
                    DELETE -> {}
                    CLONE -> {}
                    EDIT -> {}
                    EXIT -> {
//                        exit()
                    }
                    SELECT -> {}

                }
            }

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

    override fun onEditView(person: Person) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra(EDIT_KEY, person)
        startActivityForResult(intent, EDIT_REQUEST_KEY)
    }

    override fun showAction(person: Person) {
        BottomSheetDialog.show(supportFragmentManager)
    }

    override fun showOnSelect(person: Person) {
        val intent = Intent(this, SelectActivity::class.java)
        intent.putExtra(KEY_SELECT, person)
        startActivity(intent)
    }


    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_REQUEST_KEY && resultCode == RESULT_OK && data != null) {
            val editPerson = data.getParcelableExtra<Person>(EDIT_KEY) ?: return

        }
    }


}


