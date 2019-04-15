package com.midas.pdfsamples;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.midas.pdfsamples.utils.MPermissionUtils;

/**
 * @author Dell
 * @time 2019/4/15 14:16
 * @description: 启动页
 */
public class SplashActivity extends AppCompatActivity {

    private final int PERMISSION_REQUEST = 10000;
    private String[] mPermissionArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        mPermissionArr = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPer();
    }

    /**
     * 请求权限
     */
    private void requestPer() {
        MPermissionUtils.requestPermissionsResult(this, PERMISSION_REQUEST, mPermissionArr,
                new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onPermissionDenied() {
                        showDialog();
                    }
                });
    }

    /**
     * 弹窗选择
     * 根据包名打开对应的设置界面
     */
    public void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(SplashActivity.this)
                .setMessage("请您打开相应权限在使用\n设置路径：设置->应用->Pdf Samples->权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SplashActivity.this, R.string.no_permissions, Toast.LENGTH_SHORT).show();

                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                }).create();
        dialog.show();
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestPer();
    }
}
