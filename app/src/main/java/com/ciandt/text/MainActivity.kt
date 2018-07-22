package com.ciandt.text

import android.os.Build
import android.os.Bundle
import android.support.annotation.FontRes
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFont(R.id.radioRoboto)

        radioGroup.setOnCheckedChangeListener { _, checkedId -> changeFont(checkedId) }
    }

    private fun changeFont(checkedId: Int) {
        fun setFont(@FontRes font: Int) {
            val typeface = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                resources.getFont(font)
            } else {
                ResourcesCompat.getFont(this, font)
            }

            txtText.typeface = typeface
        }

        when (checkedId) {
            R.id.radioRoboto -> {
                if (radioRoboto.isChecked) {
                    setFont(R.font.roboto_regular)
                }
            }
            R.id.radioQuicksand -> {
                if (radioQuicksand.isChecked) {
                    setFont(R.font.quicksand_regular)
                }
            }
        }

        autosize.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Enable autosize and resize text
                TextViewCompat.setAutoSizeTextTypeWithDefaults(
                    txtText,
                    TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                )
            } else {
                // Disable autosize, but don't resize text
                TextViewCompat.setAutoSizeTextTypeWithDefaults(
                    txtText,
                    TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE
                )
            }
        }

        btnDefaultSize.setOnClickListener {
            // Force reset text size
            txtText.textSize = 16f
        }
    }
}