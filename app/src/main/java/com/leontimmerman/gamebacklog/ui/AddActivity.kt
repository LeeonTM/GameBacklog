package com.leontimmerman.gamebacklog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leontimmerman.gamebacklog.R
import com.leontimmerman.gamebacklog.model.Game
import com.leontimmerman.gamebacklog.ui.viewmodel.AddActivityViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var addActivityViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.title = "Add a game"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fabSave.setOnClickListener {
            var game = Game (
                txtTitle.editText?.text.toString(),
                txtPlatform.editText?.text.toString(),
                txtDay.editText?.text.toString().toInt(),
                txtMonth.editText?.text.toString(),
                txtYear.editText?.text.toString().toInt()
            )

            addActivityViewModel.insertGame(game)
        }
    }

    private fun initViewModel() {
        addActivityViewModel = ViewModelProviders.of(this).get(AddActivityViewModel::class.java)

        addActivityViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        addActivityViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }
}
