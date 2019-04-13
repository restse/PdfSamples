package com.midas.pdfsamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * @author Dell
 * @time 2019/4/13 14:48
 * @description: PdfViewpagerActivity
 * <p>
 * 注意：only on API 21 or higher
 * viewPager横向展示 体积大概200K
 */
public class PdfViewpagerActivity extends AppCompatActivity implements DownloadFile.Listener {

    private RelativeLayout mPdfRootRl;
    private RemotePDFViewPager mRemotePDFViewPager;
    private PDFPagerAdapter pdfPagerAdapter;

    private String mPdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewpager);
        mPdfUrl = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";
        //pdfViewPager = findViewById(R.id.pdf_view_pager);
        initView();
        setDownloadListener();
    }

    /**
     * 初始化
     */
    private void initView() {
        mPdfRootRl = findViewById(R.id.pdf_root_rl);
    }

    /**
     * 设置监听
     */
    protected void setDownloadListener() {
        mRemotePDFViewPager = new RemotePDFViewPager(PdfViewpagerActivity.this, mPdfUrl, this);
        mRemotePDFViewPager.setId(R.id.pdf_view_pager);
    }


    @Override
    public void onSuccess(String url, String destinationPath) {
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        mRemotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();
        //setContentView(mRemotePDFViewPager);
    }

    /**
     * 更新视图
     */
    private void updateLayout() {
        mPdfRootRl.removeAllViewsInLayout();
        mPdfRootRl.addView(mRemotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pdfPagerAdapter.close();
    }
}
