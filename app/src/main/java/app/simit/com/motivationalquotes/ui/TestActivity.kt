package app.simit.com.motivationalquotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.databinding.ActivityTestBinding
import app.simit.com.motivationalquotes.databinding.FragmentQuotesBinding
import com.bumptech.glide.Glide

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        Glide.with(this).load("http://test.ultralinx.co/wp-content/uploads/2020/03/1_50cc1ff2d9efc.jpg").into(binding.imageHolder)
    }

}