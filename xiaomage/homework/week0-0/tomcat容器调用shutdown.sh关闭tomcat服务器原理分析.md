### tomcat容器调用shutdown.sh关闭tomcat服务器原理分析



## 1. shundown.sh 脚本

通过找到catalina.sh的位置

PRGDIR=`dirname "$PRG"`
EXECUTABLE=catalina.sh

最终执行 catalina.sh stop

## 2. catalina.sh stop

尝试杀死catalina的进程 

kill -0 `cat "$CATALINA_PID"` >/dev/null 2>&1

并指定了关闭类和关闭方法 

org.apache.catalina.startup.Bootstrap "$@" stop

## 3. org.apache.catalina.startup.Bootstrap#stop（）

执行org.apache.catalina.startup.Bootstrap main()  参数匹配为stop

调用Bootstrap.stopServer

## 4. Bootstrap#stopServer（）

反射调用catalinaDaemon.stopServer()

catalinaDeamon实例是init()方法加载获得this.catalinaLoader.loadClass("org.apache.catalina.startup.Catalina")

调用org.apache.catalina.startup.Catalina.stopServer()

## 5. Catalina#（）stopServer

```
Server s = this.getServer();获取到org.apache.catalina.core.StandardServer
进入Lifecycle的stop生命周期
```

## 6. StandardServer#stopInternal()

```
monitorFuture.cancel()
取消监听org.apache.catalina.LifeCycleEvent
periodicLifecycleEventFuture.cancel()

(多个)关闭<Server>下的服务org.apache.catalin.service#stop();
service循环调用Connector#stop()关闭所有连接器，循环关闭线程池
StandardThreadExecutor#stop()
ThreadPoolExecutor#shutdown()

globalNamingResources.stop();
stopAwait() 监听到"SHUTDOWN"命令，关闭serversocket连接，关闭tomcat服务
```

##7. StandardServer#destoryInternal()

```
for(int var3 = 0; var3 < var2; ++var3) {
    Service service = var1[var3];
    service.destroy();
}

this.globalNamingResources.destroy();
this.unregister(this.onameMBeanFactory);
this.unregister(this.onameStringCache);
if (this.utilityExecutor != null) {
    this.utilityExecutor.shutdownNow();
    this.unregister("type=UtilityExecutor");
    this.utilityExecutor = null;
}

super.destroyInternal();
```

## 8.LifecycleMBeanBase#destroyInternal

```
this.unregister(this.oname); 注销
```