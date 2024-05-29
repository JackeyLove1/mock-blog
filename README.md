# TODO
1. 文章可以放入es当中，便于后续中文分词搜索。springboot教程有和es的整合 
2. 评论数据，可以考虑放入mongodb当中 电商系统当中 评论数据放入mongo中
3. 为了加快访问速度，部署的时候，可以把图片，js，css等放入七牛云存储中，加快网站访问速度
4. 做一个后台 用spring security 做一个权限系统

# Mongo
```shell
docker run -d --name mongodb -p 27017:27017 -v D:\data\mongo:/data/db --restart always mongo:latest
```

