/**
 * 保存消息到本地sessionSorage
 * @param key sessionSorage存储消息的key，由fromId+toId组成
 * @param message 消息的json字符串
 */
function saveMessage(key, messageJsonString) {
    //获取sessionSorage对象
    var storage = getSessionSorage();

    //如果不存在，则创建
    if (!storage.getItem(key)) {
        //首先创建json数组
        var messagesJsonArray = [];
        //json数组添加json对象，根据messageJsonString转化为json对象
        messagesJsonArray.push(JSON.parse(messageJsonString));
        //再将json数组转化为json数组字符串
        var messageJsonArrayString = JSON.stringify(messagesJsonArray);
        //保存storage
        storage.setItem(key, messageJsonArrayString);
    } else {
        //如果已经存在
        //获取json数组字符串
        var messageJsonArrayString = storage.getItem(key);
        //转化为json数组对象
        var messageJsonArray = JSON.parse(messageJsonArrayString);
        //添加json对象
        messageJsonArray.push(JSON.parse(messageJsonString));
        //转化json数组字符串
        messageJsonArrayString = JSON.stringify(messageJsonArray);
        //保存
        storage.setItem(key, messageJsonArrayString);
    }
}

/**
 * 获取消息的json数组
 * @param key
 * @returns
 */
function getMessageJsonArray(key) {
    var messageJsonArrayString = getSessionSorage().getItem(key);
    var messageJsonArray = JSON.parse(messageJsonArrayString);
    return messageJsonArray;
}

//获取MessageArrayItemKey
//根据两个id提供固定的key
function getMessageArrayItemKey(id1, id2) {
    if (parseInt(id1) < parseInt(id2)) {
        return String(id1) + String(id2);
    } else {
        return String(id2) + String(id1);
    }
}
