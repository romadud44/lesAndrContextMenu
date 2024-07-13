package com.example.lesandrcontextmenu

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbarMain: Toolbar
    private lateinit var inputET: EditText
    private lateinit var randomBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Оценка за урок"
        toolbarMain.subtitle = "версия 1.0"


        inputET = findViewById(R.id.inputET)
        registerForContextMenu(inputET)

        randomBTN = findViewById(R.id.randomBTN)
        randomBTN.setOnClickListener(this)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.context_color_quality -> {

                if (inputET.text.isNotEmpty()) {
                    when (inputET.text.toString().toInt()) {
                        1 -> inputET.setBackgroundColor(
                            Color.rgb(
                                1.0f,
                                0.65f,
                                0.0f
                            )
                        )

                        2 -> inputET.setBackgroundColor(Color.YELLOW)
                        3 -> inputET.setBackgroundColor(Color.GREEN)
                        4 -> inputET.setBackgroundColor(Color.CYAN)
                        5 -> inputET.setBackgroundColor(Color.RED)
                        in 6..10 -> inputET.setBackgroundColor(Color.GRAY)
                        else -> {
                            inputET.setBackgroundColor(Color.TRANSPARENT)
                            Toast.makeText(this, "Введена неверная оценка", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                } else {
                    when (inputET.hint.toString().toInt()) {
                        in 1..10 -> inputET.setBackgroundColor(Color.RED)
                        in 11..20 -> inputET.setBackgroundColor(
                            Color.rgb(
                                1.0f,
                                0.65f,
                                0.0f
                            )
                        )

                        in 21..30 -> inputET.setBackgroundColor(Color.YELLOW)
                        in 31..40 -> inputET.setBackgroundColor(Color.GREEN)
                        in 41..50 -> inputET.setBackgroundColor(Color.CYAN)
                        else -> {
                            inputET.setBackgroundColor(Color.TRANSPARENT)
                            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            R.id.context_exit -> {
                finish()
            }

            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    override fun onClick(v: View?) {
        R.id.randomBTN
        inputET.text.clear()
        inputET.setBackgroundColor(Color.TRANSPARENT)
        val randomNumber = (1..50).random().toString()
        inputET.hint = randomNumber

        Toast.makeText(
            applicationContext,
            "Результат: $randomNumber",
            Toast.LENGTH_LONG
        ).show()


    }


}
/**
 * Хотел вывести в отдельные функции, но не смог заставить Toast работать вне меню :(
 */
//@RequiresApi(Build.VERSION_CODES.O)
//fun checkNumber(input: EditText){
//    when (input.text.toString().toInt()) {
//        in 1..10 -> input.setBackgroundColor(Color.RED)
//        in 11..20 -> input.setBackgroundColor(
//            Color.rgb(
//                1.0f,
//                0.65f,
//                0.0f
//            )
//        )
//
//        in 21..30 -> input.setBackgroundColor(Color.YELLOW)
//        in 31..40 -> input.setBackgroundColor(Color.GREEN)
//        in 41..50 -> input.setBackgroundColor(Color.CYAN)
//        else -> {
//            input.setBackgroundColor(Color.TRANSPARENT)
//            Toast.makeText(this,"Ошибка", Toast.LENGTH_LONG).show()
//        }
//    }
//}