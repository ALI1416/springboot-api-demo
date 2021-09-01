# springboot-api-demo

## Gradle编译错误
### 报错内容:
Error running 'ServiceStarter': Command line is too long.  
Shorten command line for ServiceStarter or also for Application default configuration.
### 解决方法:
修改项目下.idea\workspace.xml文件  
找到标签&lt;component name="PropertiesComponent">  
在标签里加一行&lt;property name="dynamic.classpath" value="true" />
