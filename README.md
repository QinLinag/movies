# movies
电影评分系统



1.这个项目是一个分布式项目，一共有6个微服务  搭建在了三台阿里的服务器上（都是免费的可能性能不太好）
	movies-movies   电影服务
	movies-auth-server   注册登入认证服务
	movies-celebrities   演员服务
	movies-gateway     网关
	movies-member      用户服务
	movies-thrid-part   第三方中间服务，
	
2.我使用到的技术栈： 
		a.nacos作为注册中心、配置中心
		b.利用open-feign作为远程调用api
		c.gateway作为网关
		d.用到了docker容器化，
		e.数据持久化使用的是mysql数据库    使用mybatis-plus组件操作数据库
		f.缓存使用的redis
		g.rabbitmq消息队列，进行全局的分布式事务控制，所以没有用seata       rq进行流量控制流量控制，     
		h.用户注册用的是阿里的短信服务，但是申请没有给我通过，
		
