package com.cjx913.chattingonline.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cjx913.chattingonline.entity.MessageJsonObject;
import org.junit.Test;

public class FastJsonTest {
    @Test
    public void fastJson(){
        String json = "{\"fromId\":1,\"toId\":2,\"sendTime\":\"2018-05-02T15:05:02.023Z\",\"content\":\"acbdefgh\"}";
        MessageJsonObject messageJsonObject = JSON.parseObject(json,MessageJsonObject.class);
        messageJsonObject.setContent(null);
        String s = JSON.toJSONStringWithDateFormat(messageJsonObject, "yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
        assert messageJsonObject!=null;
        assert s!=null;
        System.out.println(s);
    }
}
