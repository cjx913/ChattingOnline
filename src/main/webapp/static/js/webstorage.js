//获取localSorage
function getLocalSorage() {
    var storage;
    if(window.localStorage){
        storage = window.localStorage;
    }else{
        alert("请更换浏览器或升级浏览器");
    }
    return storage;
}

//获取sessionStorage
function getSessionSorage() {
    var storage;
    if(window.sessionStorage){
        storage = window.sessionStorage;
    }else{
        alert("请更换浏览器或升级浏览器");
    }
    return storage;
}

//移除本地存储所有数据
function clearStorage(storage) {
    if(storage==localStorage||storage==sessionStorage){
        storage.clear();
    }
}