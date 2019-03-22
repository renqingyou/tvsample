package jacky.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

public class WebViewActivity extends Activity {
    final static String TAG = "SADemo.WebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initWebView();
    }

    /**
     * 打通 App 和 H5 文档：https://www.sensorsdata.cn/manual/app_h5.html
     */
    private WebView webView=null;
    private void initWebView() {
        webView = new WebView(this);
        ViewGroup viewGroup=(ViewGroup)findViewById(R.id.webview);
        viewGroup.addView(webView);
        // TODO 打通 App 和 H5
        // 打通 App 和 H5，enableVerify 指定为 true 开启数据接收地址 URL 安全校验
        //SensorsDataAPI.sharedInstance(this).showUpX5WebView(webView);
        SensorsDataAPI.sharedInstance().showUpWebView(webView, false,  false);
        webView.loadUrl("file:///android_asset/index.html");// 这里使用一个本地 html 来模拟验证 App 和 H5打通
        SensorsDataAPI.sharedInstance().login("renqingyou12345");
        //webView.loadUrl("http://192.168.50.235/");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return false;
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
    }
}
