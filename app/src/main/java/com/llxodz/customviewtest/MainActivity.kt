package com.llxodz.customviewtest

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import by.kirich1409.viewbindingdelegate.viewBinding
import com.llxodz.customviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val token = Any()
    private val handler = Handler(Looper.getMainLooper())

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomButtons.setListener {
            if (it == BottomButtonAction.POSITIVE) {
                binding.bottomButtons.isProgressMode = true
                handler.postDelayed({
                    binding.bottomButtons.isProgressMode = false
                    binding.bottomButtons.setPositiveButtonText("Updated OK!")
                }, token, 2000)
            } else if (it == BottomButtonAction.NEGATIVE) {
                binding.bottomButtons.setNegativeButtonText("Updated cancel")
                Toast.makeText(this, "Negative button press", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(token)
    }
}
