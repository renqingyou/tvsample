package jacky.com.myapplication;

import android.app.Application;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 数据接收的 URL
        final String SA_SERVER_URL = "http://sdk-test.datasink.sensorsdata.cn/sa?project=renqingyou&token=95c73ae661f85aa0";

        // 初始化 SDK
        SensorsDataAPI.sharedInstance(
                this,                               // 传入 Context
                SA_SERVER_URL, SensorsDataAPI.DebugMode.DEBUG_AND_TRACK                     // 数据接收的 URL
        );
        // 打开自动采集, 并指定追踪哪些 AutoTrack 事件
        List<SensorsDataAPI.AutoTrackEventType> eventTypeList = new ArrayList<>();
        // $AppStart
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START);
        // $AppEnd
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END);
        // $AppViewScreen
        //eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN);
        // $AppClick
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK);
        SensorsDataAPI.sharedInstance(this).enableAutoTrack(eventTypeList);
        SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen();
        SensorsDataAPI.sharedInstance().setFlushNetworkPolicy(SensorsDataAPI.NetworkType.TYPE_4G);
        SensorsDataAPI.sharedInstance().setMaxCacheSize(100*1024);
    }
}
