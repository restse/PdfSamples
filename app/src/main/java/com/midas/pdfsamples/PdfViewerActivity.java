package com.midas.pdfsamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.midas.pdfsamples.utils.DownloadUtil;

import java.io.File;

/**
 * @author Dell
 * @time 2019/4/13 14:42
 * @description: PdfViewerActivity
 * 不支持在线 体积大于16M
 * 核心：https://github.com/barteksc/PdfiumAndroid
 * 速度比较快
 */
public class PdfViewerActivity extends AppCompatActivity {


    private static final String TAG = "PdfViewerActivity";

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        pdfView = findViewById(R.id.pdfView);
        // download("http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf");
        preViewAsset("demo.pdf");
    }

    /**
     * 下载pdf文件到本地
     *
     * @param url 文件url
     */
    private void download(String url) {
        DownloadUtil.download(url, getCacheDir() + "/temp.pdf",
                new DownloadUtil.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess(final String path) {
                        Log.d(TAG, "onDownloadSuccess: " + path);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                preView(path);
                            }
                        });
                    }

                    @Override
                    public void onDownloading(int progress) {
                    }

                    @Override
                    public void onDownloadFailed(String msg) {
                    }
                });
    }

    private void preView(String path) {
        //pdfView.fromAsset("demo.pdf")
        pdfView.fromFile(new File(path))
                .defaultPage(0)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();
    }

    private void preViewAsset(String path) {
        pdfView.fromAsset(path)
                .defaultPage(0)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();
    }
}
