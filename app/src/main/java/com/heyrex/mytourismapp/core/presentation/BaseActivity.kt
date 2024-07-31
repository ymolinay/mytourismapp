package com.heyrex.mytourismapp.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    lateinit var binding: V
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = getViewBinding()
        setLayout(binding)
    }

    private fun setLayout(binding: V) {
        setContentView(binding.root)
    }

    protected abstract fun getViewBinding(): V
}