<h1 align="center">
  <br>
  <br>
  PicqBotX-Mirai
  <h4 align="center">
  一个基于 Mirai 的 Java QQ 机器人类库
  </h4>
  <h5 align="center">
    <a href="#maven">Maven 导入</a>&nbsp;&nbsp;
    <a href="#license">开源条款</a>
    <br><br>
    <a href="https://jitpack.io/#Shimogawa/PicqBotX-Mirai">
      <img src="https://jitpack.io/v/Shimogawa/PicqBotX-Mirai.svg"></img>
    </a>
  </h5>
  <br>
  <br>
  <br>
</h1>

继承原来的 [PicqBotX][picqbotx-legacy]。

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

### API

现在所有 API 迁移到了 `cc.moecraft.icq.core.MiraiApi` 中，并且建议使用该类发送消息，而非使用 Mirai 提供的方法，因为这样会在框架内创建一个发送消息的事件。

使用 `event.getBot().getMiraiApi()` 或从别处获取 Bot 对象来获取 API 对象。

### 其它

有一些标注了 `@Deprecated` 的名称，替代方案写在了文档里，保留是为了保留与上一版本的兼容性。

重要的是，原有的表示用户、群组等的类全部删除，改为使用 Mirai 内置的类。但是，所有事件的字段全部都与原来一样，使用时只需要修改关于原有 `User` 或 `Group` 类的相关方法即可。`Event` 不需要作任何调整。

### 新功能

#### 消息模板

也就是以前的 CQ 码的简化版。`event.getMessage()` 返回的即是消息模板。

消息模板中非文本对象（图片、At 等）的格式是 `%<id>%`，其中 `id` 是自动生成的字符串。它存在一张哈希表中，并且值是那个非文本对象的 `WeakReference`。这个表会定时清理，时间的设置在 `PicqConfig.setScheduledClearWeakRefTimeInterval(long)`。消息模板中的 `%` 会变成 `\%`，`\` 会变成 `\\`。例如：

```
这是一个图片: %1ufD9Ca0%, 这是一个百分号: \%, 这是一个反斜杠和另一个图片: \\%mRs3a8qV%
```

其中，`%1ufD9Ca0%` 和 `%mRs3a8qV%` 都是图片。

想要将 `MessageChain` （Mirai 中的消息链）变成消息模板，使用 `PicqMessageTemplate.toMessageTemplate`。想要把消息模板变成 `MessageChain`，使用 `PicqMessageTemplate.messageTemplateToChain`。想要去除转义，使用 `PicqMessageTemplate.toSimpleString`。

在所有发送消息的方法中，默认**全部**将传入的字符串消息作为消息模板发送（也就是会转成一个消息链，而不是单纯的一个 `PlainText`）。可以在配置中设置 `defaultSendUseMessageTemplate` 字段修改这个配置。


<a name="maven"></a>
Maven 导入:
--------

没有添加 JitPack 的 Repo 的话首先添加 Repo, 在 pom 里面把这些粘贴进去:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

然后添加这个库:

```xml
<dependency>
    <groupId>com.github.Shimogawa</groupId>
    <artifactId>PicqBotX-Mirai</artifactId>
    <version>0.2.0</version> <!-- 这里换成最新版本 -->
</dependency>
```

然后 Reimport 之后就导入好了!

<br>

<a name="gradle"></a>
Gradle 导入:
--------

没有添加 JitPack 的 Repo 的话首先添加 Repo, 在 pom 里面把这些粘贴进去:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

然后添加这个库:

```gradle
dependencies {
    implementation 'com.github.Shimogawa:PicqBotX-Mirai:0.2.0'
}
```

<a name="license"></a>
[开源条款](https://choosealicense.com/licenses/mit/): MIT
--------


[picqbotx-legacy]: https://github.com/HyDevelop/PicqBotX
