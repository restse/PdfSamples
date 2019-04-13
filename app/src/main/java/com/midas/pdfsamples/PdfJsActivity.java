package com.midas.pdfsamples;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.midas.pdfsamples.utils.Base64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @author Dell
 * @time 2019/4/13 14:51
 * @description: PdfJsActivity
 * <p>
 * 利用pdf.js预览文件demo
 * 支持在线加载 速度一般 界面一般 体积4M左右
 */
public class PdfJsActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_js);
        initView();

        //加载本地文件
        preView("file:///android_asset/demo.pdf");

        //String pdfUrl = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";
        //preView(pdfUrl);

        //加载允许跨域访问的文件
        // preView("http://p5grppofr.bkt.clouddn.com/pdf-js-demo.pdf");
        //跨域加载文件 先将pdf下载到本地在加载
        //download(pdfUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        mWebView = findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }


    /**
     * 预览pdf
     *
     * @param pdfUrl url或者本地文件路径
     */
    private void preView(String pdfUrl) {
        //1.只使用pdf.js渲染功能，自定义预览UI界面
        // mWebView.loadUrl("file:///android_asset/viewer.html?" + pdfUrl);
        //2.使用mozilla官方demo加载在线pdf
//        mWebView.loadUrl("http://mozilla.github.io/pdf.js/web/viewer.html?file=" + pdfUrl);
        //3.pdf.js放到本地
//        mWebView.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + pdfUrl);
        //4.使用谷歌文档服务
//        mWebView.loadUrl("http://docs.google.com/gviewembedded=true&url=" + pdfUrl);

        WebSettings settings = mWebView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);

        settings.setAllowUniversalAccessFromFileURLs(true);

        settings.setBuiltInZoomControls(true);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient());
        ////api >= 19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + pdfUrl);
        } else {
            if (!TextUtils.isEmpty(pdfUrl)) {
                byte[] bytes = null;
                try {// 获取以字符编码为utf-8的字符
                    bytes = pdfUrl.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (bytes != null) {
                    new Base64Encoder();
                    // BASE64转码
                    pdfUrl = Base64Encoder.encode(bytes);
                }
            }
            mWebView.loadUrl("file:///android_asset/pdfjs_compatibility/web/viewer.html?file=" + pdfUrl);

        }
    }
}
