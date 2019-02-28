package com.app4fun.carros.activity

import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app4fun.carros.R
import com.app4fun.carros.dialogs.AboutDialog
import com.app4fun.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_site_livro.*
import org.jetbrains.anko.toast

class SiteLivroActivity : AppCompatActivity() {

    private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)
        //Toolbar
        setupToolbar(R.id.toolbar, "Sobre", true)
        //Carrega página
        setWebViewClient(webView)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadData("<html>\n" +
                "    <head>\n" +
                "        <title>Carros</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "        <script type=\"text/javascript\">\n" +
                "\t}\n" +
                "</script>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <table width=\"100%\" cellpadding=\"10\">\n" +
                "            <tr>\n" +
                "                <td width=\"30%\"><h3>Projeto<br>Carros</h3></td>\n" +
                "                <td width=\"70%\" align=\"right\"><img src=\"https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png\" width=\"160\" height=\"189\" onClick=\"sobre();\" /></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td colspan=\"2\">\n" +
                "                    Aplicativo feito para aplicar conhecimentos apresentado no livro \"Android Essencial com Kotlin - Ricardo Lecheta\"\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <h3> Recursos Utilizados </h3>\n" +
                "        <ul>\n" +
                "            <li>Kotlin</li>\n" +
                "            <li>Anko</li>\n" +
                "            <li>Fragments</li>\n" +
                "            <li>Navigation Drawer</li>\n" +
                "            <li>WebView</li>\n" +
                "            <li>RecyclerView + CardView</li>\n" +
                "            <li>Tabs</li>\n" +
                "            <li>Elementos Material Design</li>\n" +
                "            <li>OkHttp</li>\n" +
                "            <li>Retrofit</li>\n" +
                "            <li>RxAndroid</li>\n" +
                "            <li>SQLite + Room</li>\n" +
                "            <li>VideoView</li>\n" +
                "            <li>Maps</li>\n" +
                "            <li>Marshmallow Permisisons</li>\n" +
                "        </ul>\n" +
                "    </body>\n" +
                "</html>",
            "text/html", "UTF-8")
        //Swipe to Refresh
        swipeRefreshLayout.setOnRefreshListener { webView.reload() }
        //Cores da animação
        swipeRefreshLayout.setColorSchemeColors(
            resources.getColor(R.color.refresh_progress_1),
            resources.getColor(R.color.refresh_progress_2),
            resources.getColor(R.color.refresh_progress_3)
        )

    }

    //Controla eventos webView
    private fun setWebViewClient(webView: WebView){
        webView.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //Liga o progress
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //Desliga o progress
                progress.visibility = View.INVISIBLE
                swipeRefreshLayout.isRefreshing = false
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                if(url.contains("sobre.html")){
                    //Mostra o dialog
                    AboutDialog.showAbout(supportFragmentManager)
                    //Returna tru para informar que interceptamos o evento
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}
