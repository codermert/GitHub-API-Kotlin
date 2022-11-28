package com.codermert.retrofit_get

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.codermert.retrofit_get.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnOk.setOnClickListener {
            userId = binding.etId.text.toString()

            if(userId != "") {
                callGithubAPI(userId)
                subscribeViewModel()
            } else {
                Toast.makeText(this, "Lütfen Github kimliğinizi girin.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun callGithubAPI(userId: String) = mainViewModel.requestUserInfo(userId)

    private fun subscribeViewModel() {
        mainViewModel.okCode.observe(this){
            if(it) {
                val userData = mainViewModel.userData

                if(userData.userId != "") {
                    showInfoViews()
                    Glide.with(this).load(userData.image).override(150, 150).into(binding.ivImage)
                    binding.tvName.text = userData.name
                    binding.tvId.text = userData.userId
                    binding.tvRepos.text = userData.repos.toString()
                } else {
                    notShowInfoViews()
                    Toast.makeText(this, "Girdiğiniz kimlik mevcut değil. lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show()
                }

            } else {
                notShowInfoViews()
                Toast.makeText(this, "Girdiğiniz kimliğin ayrıntılı bilgileri alınamadı. lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showInfoViews() {
        binding.ivImage.visibility = VISIBLE
        binding.name.visibility = VISIBLE
        binding.tvName.visibility = VISIBLE
        binding.id.visibility = VISIBLE
        binding.tvId.visibility = VISIBLE
        binding.repos.visibility = VISIBLE
        binding.tvRepos.visibility = VISIBLE
    }

    private fun notShowInfoViews() {
        binding.ivImage.visibility = GONE
        binding.name.visibility = GONE
        binding.tvName.visibility = GONE
        binding.id.visibility = GONE
        binding.tvId.visibility = GONE
        binding.repos.visibility = GONE
        binding.tvRepos.visibility = GONE
    }
}