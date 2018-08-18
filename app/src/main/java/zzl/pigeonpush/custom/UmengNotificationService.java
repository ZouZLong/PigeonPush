package zzl.pigeonpush.custom;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.umeng.message.UmengMessageService;

import org.android.agoo.common.AgooConstants;


/**
 * 自定义的推送  接收到有推送后的所有操作都自定义
 * 继承UmengMessageService 类后收到推送消息会自动执行onMessage方法
 */

//public class UmengNotificationService extends UmengMessageService {
//
//    @Override
//    public void onMessage(Context context, Intent intent) {
//        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
//        Intent intent1 = new Intent();
//        intent1.setClass(context, MyNotificationService.class);
//        intent1.putExtra("UmengMsg", message);
//        context.startService(intent1);
//    }
//}
