package com.example.deer.navigationdrawersample

import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* init Views */
        setupToolbar()
        setupNavigation()
        setupCustomNavigation()
        setupWebView()
    }

    override fun onBackPressed() {
        if (!checkNavigationBar()) {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(checkWebViewBack())
                return true
            else
                System.exit(0)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)
    }

    private fun setupNavigation() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun checkNavigationBar() : Boolean {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            drawer_layout.isDrawerOpen(GravityCompat.END) -> drawer_layout.closeDrawer(GravityCompat.END)
            else -> return false
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var selection: SelectionData? = SelectionData.getSelectionItem(item.itemId)
        return if(selection != null) {
            toolbar.title = getString(selection.titleResId)
            setupLoadUrl(selection.url)
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        } else
            false
    }

    private fun setupCustomNavigation() {
        val listView = findViewById<ListView>(R.id.lv_custom_nav_view)
        listView.adapter = CustomNavigationAdapter(
                CustomNavigationAdapter.OnClickListener { selectionData ->
                    toolbar.title = getString(selectionData.titleResId)
                    setupLoadUrl(selectionData.url)
                    drawer_layout.closeDrawer(GravityCompat.END)
                })
    }

    private var webView: WebView? = null
    private fun setupWebView() {
        findViewById<ImageView>(R.id.main_iv_logo).setOnClickListener({})
        val anim = NormalAnimation.blinkLoading(findViewById<ImageView>(R.id.main_iv_logo))
        webView = findViewById(R.id.main_web_view)
        webView!!.settings.javaScriptEnabled = true
        webView!!.isHorizontalScrollBarEnabled = false
        webView!!.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        webView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                anim.start()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                anim.pause()
                anim.cancel()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        setupLoadUrl("http://idolish7.com/")
    }

    private fun setupLoadUrl(url: String) {
        webView!!.clearHistory()
        webView!!.visibility = View.VISIBLE
        webView!!.loadUrl(url)
    }

    private fun checkWebViewBack(): Boolean {
        return if (webView!!.canGoBack()) {
            webView!!.goBack()
            true
        } else
            false
    }
}
