package com.AS.eyeshield

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.AS.eyeshield.databinding.SplashBinding

class splash : AppCompatActivity() {

    lateinit var binding: SplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=SplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.iv.alpha=0f
        binding.iv.animate().setDuration(500).alpha(1f).withEndAction{
            Intent(this,Login::class.java)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }


    }
}