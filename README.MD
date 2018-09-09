# 欢迎使用 wosFrameWork 

------

wosFrameWork是基于springboot开发的超实用脚手架，所有的功能都是经过本人亲身验证的最简单方便的实现,目前包含的技术栈如下：

> * springboot 1.5.15.RELEASE
> * tk.mybatis+druid 最简单的mybatis（含代码生成器，分页功能和自定义mapper）
> * 配置文件分布式动态化，修改配置无需重启应用（Environment+curator+zookeeper）
> * redis的实现使用jedis，支持单机连接和哨兵模式
> * springboot自带的定时task和异步task实现
> * 全局的异常捕获 @ControllerAdvice 实现
> * 拦截器 WebMvcConfigurerAdapter 实现