package com.example.scrolling

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.scrolling.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*" findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            if (findViewById<BottomAppBar>(R.id.bottn_app_bar).fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                findViewById<BottomAppBar>(R.id.bottn_app_bar).fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            } else {
                findViewById<BottomAppBar>(R.id.bottn_app_bar).fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }*/

        binding.fab.setOnClickListener {
            if (binding.bottnAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                binding.bottnAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            } else {
                binding.bottnAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }
        binding.bottnAppBar.setNavigationOnClickListener({
            Snackbar.make(binding.root, R.string.message_action_succes, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .show()
        })

        binding.content.btnSkyp!!.setOnClickListener { binding.content.cvAd.visibility = View.GONE }

        binding.content.btnBuy.setOnClickListener {
            Snackbar.make(it, R.string.card_buying, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .setAction(R.string.card_to_go, {
                    Toast.makeText(this, R.string.card_historial, Toast.LENGTH_SHORT).show()
                })
                .show()
        }

        loadImage()

        binding.content.cbEnablePass.setOnClickListener {
            binding.content.tilPassword.isEnabled = !binding.content.tilPassword.isEnabled
        }

        binding.content.etUrl.onFocusChangeListener = View.OnFocusChangeListener { view, focused ->
            var errorStr: String? = null

            val url = binding.content.etUrl.text.toString()
            if (!focused) {

                if (url.isEmpty()) {
                    errorStr = getString(R.string.card_required)
                } else if (URLUtil.isValidUrl(url)) {

                    loadImage(url)
                } else {
                    errorStr = getString(R.string.card_invalid_url)
                }
            }
            binding.content.tilUrl.error = errorStr
        }
    }


        private fun loadImage(url: String = "https://www.soyvisual.org/sites/default/files/images/materials/lince-ropa-02.jpg"){
            Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.content.imgCover)
        }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}