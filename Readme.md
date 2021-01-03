<h1 align="center">
  <br>
  <br>
  PicqBotX-Mirai
  <h4 align="center">
  一个基于 Mirai 的 Java QQ 机器人类库
  </h4>
  <h5 align="center">
<a href="#license">开源条款</a>
</h5>
  <br>
  <br>
  <br>
</h1>

继承原来的 PicqBotX。

## 从原项目迁移

将会有一些不同之处：

### 配置与创建 `bot`

现在的 `config` 变成了单例，配置需要使用

```java
PicqConfig.modifyConfig((it) -> {
    it.setXXX();
    // ...
});
```

创建机器人对象：

```java
PicqBotX bot = new PicqBotX();
```

现在只支持单账户了。如果要多账户就需要使用不同的 bot 实例。设置账户：

```java
bot.setAccount(qq, password);
```

完整代码：

```java
public class TestBot {
    public static void main(String[] args) {
        // 配置
        PicqConfig.modifyConfig((it) -> {
            it
                .setDeviceInfoFile("device.json")
                .setDebug(true);
            // ...
        });
        // 创建机器人对象
        PicqBotX bot = new PicqBotX();
        // 添加一个机器人账户 ( qq号, 密码 )
        bot.setAccount(123L, "123");
        // 注册事件监听器, 可以注册多个监听器
        bot.getEventManager().registerListeners(
            new TestListener(),
            new ExceptionListener()
        );
        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        bot.enableCommandManager("!", "/");
        // 注册指令, 可以注册多个指令
        bot.getCommandManager().registerCommands(
            new CommandSay(),
            new CommandTest()
        );
        // 启动机器人, 不会占用主线程
        bot.startBot();
    }
}
```

### 其它

有一些标注了 `@Deprecated` 的名称，替代方案写在了文档里，保留是为了保留与上一版本的兼容性。

重要的是，原有的表示用户、群组等的类全部删除，改为使用 Mirai 内置的类。但是，所有事件的字段全部都与原来一样，使用时只需要修改关于原有 `User` 或 `Group` 类的相关方法即可。`Event` 不需要作任何调整。


<a name="license"></a>
[开源条款](https://choosealicense.com/licenses/mit/): MIT
--------
