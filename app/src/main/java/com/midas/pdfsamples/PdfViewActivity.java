package com.midas.pdfsamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.joanzapata.pdfview.PDFView;
import com.midas.pdfsamples.utils.DownloadUtil;

import java.io.File;

/**
 * @author Dell
 * @time 2019/4/13 14:46
 * @description: PdfViewActivity
 * <p>
 * 不支持在线 体积8M 已停止维护 作者建议PdfViewer
 * 速度还算可以
 */
public class PdfViewActivity extends AppCompatActivity {

    private static final String TAG = "PdfViewActivity";

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        pdfView = findViewById(R.id.pdf_view);
        //download("http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf");
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
        pdfView.fromFile(new File(path))
                .defaultPage(0)
                .swipeVertical(true)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }

    private void preViewAsset(String path) {
        pdfView.fromAsset(path)
                .defaultPage(0)
                .swipeVertical(true)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }
}
