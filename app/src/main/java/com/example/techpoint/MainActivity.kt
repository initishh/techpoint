package com.example.techpoint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val webSettings = web_view.getSettings()
		webSettings.setJavaScriptEnabled(true)

		web_view.setWebViewClient(WebViewClient())

		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
		webSettings.setAppCacheEnabled(true)
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
		webSettings.setUseWideViewPort(true)
		webSettings.setDomStorageEnabled(true)
		web_view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)

		web_view.loadUrl("http://thetechpoint.in");
	}
}
