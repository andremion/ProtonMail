package ch.protonmail.android.protonmailtest.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import ch.protonmail.android.protonmailtest.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = TabsAdapter(this, supportFragmentManager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(pager)
    }
}
