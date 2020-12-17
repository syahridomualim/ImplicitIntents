package com.mualim.implicitintents

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_open_website.setOnClickListener(this)
        btn_open_location.setOnClickListener(this)
        btn_share.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_open_website -> openWeb(website_edt.text.toString())
            R.id.btn_open_location -> openLocation(location_edt.text.toString())
            R.id.btn_share -> shareText(share_edt.text.toString())
        }
    }

    private fun shareText(text: String) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(R.string.share_text)
            .setText(text)
            .startChooser()
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openLocation(addressUri: String) {
        val addressUrl = Uri.parse("geo:0,0?q=$addressUri")
        val intent = Intent(Intent.ACTION_VIEW, addressUrl)

        if (intent.resolveActivity(packageManager) != null) {
            Toast.makeText(this, "Open $addressUri", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        } else {
            Log.d("Implicit Intent", "Can't handle this intent!")
            Toast.makeText(this, "Failed to open this page", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openWeb(url: String) {
        val webPage = Uri.parse("https://$url")
        val intent = Intent(Intent.ACTION_VIEW, webPage)

        if (intent.resolveActivity(packageManager) != null) {
            Toast.makeText(this, "Open $webPage", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        } else {
            Log.d("Implicit Intent", "Can't handle this intent!")
            Toast.makeText(this, "Failed to open this page", Toast.LENGTH_SHORT).show()
        }
    }
}