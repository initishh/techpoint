package com.example.techpoint

import android.content.res.ColorStateList
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
//import androidx.test.orchestrator.junit.BundleJUnitUtils.getDescription
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.annotation.TargetApi



class MainActivity : AppCompatActivity() {

	var progressBar: ProgressBar? = null
    var mySwipeRefreshLayout: SwipeRefreshLayout? = null
	internal var url = "http://thetechpoint.in"
    var web_view: WebView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		web_view = findViewById<WebView>(R.id.webView)
		progressBar = findViewById<ProgressBar>(R.id.progBar)
        mySwipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe)

		val webSettings = web_view!!.getSettings()
		webSettings.setJavaScriptEnabled(true)

		web_view!!.webViewClient = myWebClient()

		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
		webSettings.setAppCacheEnabled(true)
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
		webSettings.setUseWideViewPort(true)
		webSettings.setDomStorageEnabled(true)
		web_view!!.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)

		web_view!!.loadUrl(url)


        mySwipeRefreshLayout!!.setOnRefreshListener {
            web_view!!.reload()
            retriveDocuments()}


	}

    private fun retriveDocuments() {
        if(mySwipeRefreshLayout!!.isRefreshing )
            mySwipeRefreshLayout!!.isRefreshing = false

    }

    inner class myWebClient : WebViewClient() {

		override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

			progressBar!!.visibility = View.VISIBLE
            view.loadUrl(url)
			return true

		}

		override fun onPageFinished(view: WebView, url: String) {

			super.onPageFinished(view, url)
			progressBar!!.visibility = View.GONE
		}

//		override fun onReceivedError(
//			view: WebView,
//			errorCode: Int,
//			description: String,
//			failingUrl: String
//		) {
//			if (view.url == failingUrl) {
//				Toast.makeText(applicationContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
//			}
//			super.onReceivedError(view, errorCode, description, failingUrl)
//		}
//
//		@TargetApi(android.os.Build.VERSION_CODES.M)
//		override fun onReceivedError(
//			view: WebView,
//			request: WebResourceRequest,
//			error: WebResourceError
//		) {
//			onReceivedError(
//				view,
//				error.errorCode,
//				error.description.toString(),
//				request.url.toString()
//			)
//		}
		override fun onReceivedError(
			view: WebView,
			errorCode: Int,
			description: String,
			failingUrl: String
		) {

			webView.loadUrl("file:///android_asset/error.html")



		}

//		override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
//			Toast.makeText(applicationContext, "Got Error! $error", Toast.LENGTH_SHORT).show()
//		}

	}
    override fun onBackPressed() {
        if(web_view!= null && web_view!!.canGoBack())
            web_view!!.goBack()// if there is previous page open it
        else
            super.onBackPressed()
    }
//		override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//
//			progressBar!!.visibility = View.VISIBLE
//			view.loadUrl(url)
//			return true
//
//		}

//		override fun onPageFinished(view: WebView, url: String) {
//
//			super.onPageFinished(view, url)
//			progressBar!!.visibility = View.VISIBLE
//		}

}
