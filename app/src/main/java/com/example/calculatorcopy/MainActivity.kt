package com.example.calculatorcopy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculatorcopy.databinding.ActivityMainBinding

import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var buttons_original: List<View>
    private lateinit var buttons: List<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttons_original = listOf(
            binding.no0, binding.no1, binding.no2, binding.no3,
            binding.no4, binding.no5, binding.no6, binding.no7,
            binding.no8, binding.no9, binding.dot, binding.plus,
            binding.minus, binding.multiply, binding.division,
            binding.openBracket, binding.closeBracket, binding.clearText
        )

        binding.no0.setOnClickListener {
            value_add("0", false)
            shuffleButtons()
        }
        binding.no1.setOnClickListener {
            value_add("1", false)
            shuffleButtons()
        }
        binding.no2.setOnClickListener {
            value_add("2", false)
            shuffleButtons()
        }
        binding.no3.setOnClickListener {
            value_add("3", false)
            shuffleButtons()
        }
        binding.no4.setOnClickListener {
            value_add("4", false)
            shuffleButtons()
        }
        binding.no5.setOnClickListener {
            value_add("5", false)
            shuffleButtons()
        }
        binding.no6.setOnClickListener {
            value_add("6", false)
            shuffleButtons()
        }
        binding.no7.setOnClickListener {
            value_add("7", false)
            shuffleButtons()
        }
        binding.no8.setOnClickListener {
            value_add("8", false)
            shuffleButtons()
        }
        binding.no9.setOnClickListener {
            value_add("9", false)
            shuffleButtons()
        }
        binding.dot.setOnClickListener {
            value_add(".", false)
            shuffleButtons()
        }
        binding.plus.setOnClickListener {
            value_add("+", false)
            shuffleButtons()
        }
        binding.minus.setOnClickListener {
            value_add("-", false)
            shuffleButtons()
        }
        binding.multiply.setOnClickListener {
            value_add("*", false)
            shuffleButtons()
        }
        binding.division.setOnClickListener {
            value_add("/", false)
            shuffleButtons()
        }
        binding.openBracket.setOnClickListener {
            value_add("(", false)
            shuffleButtons()
        }
        binding.closeBracket.setOnClickListener {
            value_add(")", false)
            shuffleButtons()
        }
        binding.clearText.setOnClickListener {
            value_add("", true)
            shuffleButtons()
        }

        binding.back.setOnClickListener { back_function() }
        binding.equal.setOnClickListener { equal_function() }

        binding.resetButton.setOnClickListener { resetFunction() }


    }

    fun value_add(string: String, is_clear: Boolean) {
        if (is_clear == true) {
            binding.calculations.text = ""
            binding.answerText.text = ""

        } else {
            binding.calculations.append(string)
        }
    }

    fun back_function() {
        val expression = binding.calculations.text.toString()
        if (expression != "") {
            binding.calculations.text = expression.substring(0, expression.length - 1)
        }
    }

    fun equal_function() {
        try {
            var expression = ExpressionBuilder(binding.calculations.text.toString()).build()
            var result = expression.evaluate()
            binding.answerText.text = result.toString()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            Log.d("EXCEPTION", "Message: ${e.message}")
        }

    }

    fun shuffleButtons() {
        val parentLayout = binding.buttonContainer
        val childLayouts = mutableListOf<LinearLayout>()
        val originalChildCount = mutableListOf<Int>()

        for (i in 0 until parentLayout.childCount) {
            val child = parentLayout.getChildAt(i)
            if (child is LinearLayout) {
                childLayouts.add(child)
                originalChildCount.add(child.childCount)
            }
        }

        val buttons = mutableListOf<View>()
        for (layout in childLayouts) {
            for (j in 0 until layout.childCount) {
                buttons.add(layout.getChildAt(j))
            }
        }

        buttons.shuffle()

        for (layout in childLayouts) {
            layout.removeAllViews()
        }

        var buttonIndex = 0
        for (i in childLayouts.indices) {
            val layout = childLayouts[i]
            val count = originalChildCount[i]
            for (j in 0 until count) {
                layout.addView(buttons[buttonIndex])
                buttonIndex++
            }
        }
    }

    fun resetFunction() {
        val parentLayout = binding.buttonContainer
        val childLayouts = mutableListOf<LinearLayout>()
        val originalChildCount = mutableListOf<Int>()

        for (i in 0 until parentLayout.childCount) {
            val child = parentLayout.getChildAt(i)
            if (child is LinearLayout) {
                childLayouts.add(child)
                originalChildCount.add(child.childCount)
            }
        }

        val buttons = mutableListOf<View>()
        for (layout in childLayouts) {
            for (j in 0 until layout.childCount) {
                buttons.add(layout.getChildAt(j))
            }
        }

        for (layout in childLayouts) {
            layout.removeAllViews()
        }

        var buttonIndex = 0
        for (i in childLayouts.indices) {
            val layout = childLayouts[i]
            val count = originalChildCount[i]
            for (j in 0 until count) {
                layout.addView(buttons[buttonIndex])
                buttonIndex++
            }
        }
    }
}