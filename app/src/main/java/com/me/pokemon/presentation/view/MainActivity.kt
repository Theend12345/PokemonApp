package com.me.pokemon.presentation.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.me.pokemon.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        lifecycleScope.launchWhenStarted {
            viewModel.data.collectLatest {
                Log.d("xyi", it.toString())
            }
        }

        return super.onCreateView(name, context, attrs)
    }
}