### 构建 Spring 源码
#### 问题1：自己的测试模块 spring-mytest 的 build.gradle 文件中需要配置
```text
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation group: 'junit', name: 'junit', version: '4.13.2'
    //添加完要构建一下，否则代码中无法引用
    api(project(":spring-context"))
    api(project(":spring-beans"))
    api(project(":spring-core"))
    api(project(":spring-aop"))
}
```
```text
通过上述配置引入测试需要的 Spring 相关包
```

#### 问题2：构建报红
```text
'dependencies.dependency.version' for io.undertow:undertow-servlet-jakartaee9:test-jar is missing...
解决方式：
找到
E:\gradle_repository\caches\modules-2\files-2.1\io.undertow\undertow-websockets-jsr-jakartaee9\2.2.12.Final\f6bd0d6f0cd26fbbfa5acfc3ed41e000337b3a79
文件夹下的
undertow-websockets-jsr-jakartaee9-2.2.12.Final.pom
编辑上述 .pom 文件
```
```xml
<dependency>
    <groupId>io.undertow</groupId>
    <artifactId>undertow-servlet-jakartaee9</artifactId>
    <type>test-jar</type>
    <scope>test</scope>
    <!-- 添加下面这一行 —— 版本配置 -->
    <version>2.2.12.Final</version>
</dependency>
```

#### 问题三：找不到 kotlin-coroutines 下的 build 目录
File->Project Structure->Libraries->+->java->spring-core->kotlin-coroutines->build->libs->kotlin-coroutines-5.3.0-SNAPSHOT.jar
```text
需要将 kotlin-coroutines 模块 build 一下
```

#### 问题四：
```text
java: 找不到符号
符号:   类 InstrumentationSavingAgent
位置: 程序包 org.springframework.instrument
在 spring-mytest 的 build.gradle 文件中加入 
```
```text
compile(project(":spring-instrument"))
```
#### 问题五：
spring框架的 FlightRecorderStartupEvent 类中 java: 程序包jdk.jfr不存在 
与
spring框架的 ReflectUtils 类中
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.springframework.cglib.core.ReflectUtils$1 (file:/E:/workspace/spring-framework/spring-core/build/libs/spring-cglib-repack-3.3.0.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of org.springframework.cglib.core.ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
相互矛盾，是 spring 框架本身的 bug。
需要在主启动类的启动配置中 modify options -> VM Options
--add-opens java.base/java.lang=ALL-UNNAMED