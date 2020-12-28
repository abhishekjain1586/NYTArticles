package com.nytarticles.view.fragments

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nytarticles.R
import com.nytarticles.viewmodel.ArticlesActivityViewModel
import kotlinx.android.synthetic.main.fragment_article_detail.*

class ArticleDetailFragment : Fragment() {

    private lateinit var rootView: View
    //private lateinit var webView: WebView
    //lateinit var loader: ProgressBar

    private lateinit var viewModel : ArticlesActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_article_detail, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        viewModel.getSelectedArticle()?.let {
            it -> it.url?.let {
            it1 -> loadArticleDetail(it1)
        }
        }
    }

    private fun initViews() {
        viewModel = ViewModelProviders.of(requireActivity()).get(ArticlesActivityViewModel::class.java)
        //loader = rootView.findViewById(R.id.progress)
        //webView = rootView.findViewById(R.id.web_view)
        web_view.webViewClient = MyWebViewClient()
        web_view.settings.javaScriptEnabled = true
        web_view.settings.useWideViewPort = true
    }

    private fun loadArticleDetail(strUrl: String) {
        web_view.loadUrl(strUrl)
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progress.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progress.visibility = View.GONE
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError
        ) {
            handler?.proceed()
        }
    }
}