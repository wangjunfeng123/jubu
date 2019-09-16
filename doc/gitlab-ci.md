# gitlab-ci

## 概念：

```
Pipeline:一个pipeline就是构建一个任务，包含多个stages
stages：每个构建阶段由多个jobs组成；stage是顺序执行，例如：test，build，deploy
jobs:标示要构建工作;相同stage中的jobs会并行执行
gitlab-runner:jobs的执行者
```

## 安装gitlab-runner

```txt
gitlab-runner 是jobs的执行者，gitlab-runner属于gitlab，可以单独安装在不同的服务器上
gitlab-runner 把git仓库注册到gitlab-runner中（gitlab设置中）
```

## gitlab-ci.yml

```yml
stages:
- build
- package
# 构建 Job
build:
  stage: build
  only:
  - master
  script:
  - echo "=============== 开始编译构建任务 ==============="
  - /usr/local/apache-maven-3.6.1/bin/mvn compile
# 打包
package:
  stage: package
  only:
  - master
  script:
  - echo "=============== 开始打包任务  ==============="
  - /usr/local/apache-maven-3.6.1/bin/mvn clean package
```

