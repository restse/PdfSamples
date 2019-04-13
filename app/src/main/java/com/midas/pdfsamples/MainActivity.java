package com.midas.pdfsamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
/**
 *
 *@author Dell
 *@time 2019/4/13 14:27
 *@description: 
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pdfJs = findViewById(R.id.pdf_js);
        Button pdfViewer = findViewById(R.id.pdf_viewer);
        Button pdfVp = findViewById(R.id.pdf_view_pager);
        Button pdfView = findViewById(R.id.pdf_view);
        Button x5WebView = findViewById(R.id.x5_web_view);
        Button x5Web = findViewById(R.id.x5_web);
        pdfJs.setOnClickListener(this);
        pdfViewer.setOnClickListener(this);
        pdfVp.setOnClickListener(this);
        pdfView.setOnClickListener(this);
        x5WebView.setOnClickListener(this);
        x5Web.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pdf_js:
                startActivity(new Intent(this, PdfJsActivity.class));
                break;
            case R.id.pdf_viewer:
                startActivity(new Intent(this, PdfViewerActivity.class));
                break;
            case R.id.pdf_view_pager:
                startActivity(new Intent(this, PdfViewpagerActivity.class));
                break;
            case R.id.pdf_view:
                startActivity(new Intent(this, PdfViewActivity.class));
                break;
            case R.id.x5_web_view:
              //  startActivity(new Intent(this, TbsX5WebviewActivity.class));
                break;
            case R.id.x5_web:
                startActivity(new Intent(this, TbsX5WebActivity.class));
                break;
            default:
                break;
        }
    }
}
