
action="$1"
actionService="$2"

projectPath="/app/clever"
mavenRepositoryPath="/var/.m2/repository"
projectGit=git@github.com:iyanghong/clever.git

actionServicePath="${projectPath}/clever-$actionService"

serviceList=("system" "blog")


build(){
  checkDockerContainerIsRunning install-clever
  if [ $? -eq 0 ]; then
      echo -e "\033[32m 启动编译容器 \033[0m"
      docker-compose -f "${projectPath}/docker-composer.yml" up -d
  fi
  echo -e "\033[32m [${actionService}]开始编译 \033[0m"
  # "${actionServicePath}" || exit
  #mvn clean package -T 1C -Dmaven.test.skip=true -Dmaven.compile.fork=true
  docker run --rm -v "${projectPath}":"${projectPath}" -v "${mavenRepositoryPath}":/root/.m2/repository -w "${actionServicePath}" maven:3.6.3-jdk-8 mvn clean package -T 1C -Dmaven.test.skip=true -Dmaven.compile.fork=true
  echo -e "\033[32m [${actionService}]编译完成 \033[0m"
}

pull(){
    if [ -d ${projectPath} ]; then
        cd ${projectPath} || exit
        git pull "${projectGit}"
        echo -e "\033[32m 后端服务代码更新成功 \033[0m"
    else
        echo -e "\033[33m 后端服务还不存在, 即将为您拉取 \033[0m"
        cd /app || exit
        git clone "${projectGit}"
        echo -e "\033[32m 后端服务代码拉取成功 \033[0m"
    fi
    echo -e "\033[32m 后端服务代码更新成功 \033[0m"
}

deploy(){
  # 拉取最新代码
  pull
  # 编译
  build
  echo -e "\033[32m 删除未命名的镜像 \033[0m"
  # 删除未命名的镜像
  docker image prune -f
  # 停止正在运行的容器
  echo -e "\033[32m 停止正在运行的容器 \033[0m"
  docker-compose -f "${actionServicePath}/docker-composer.yml" down
  docker rm -f clever-"$actionService"
  docker rmi clever-"$actionService"
  docker build -t clever-"$actionService" "$projectPath"
  # 强制重启正在运行的容器
  echo -e "\033[32m 启动容器 \033[0m"
  docker-compose -f "${actionServicePath}/docker-composer.yml" up -d --force-recreate
}


restart(){
  docker-compose -f "${actionServicePath}/docker-composer.yml" down
  docker-compose -f "${actionServicePath}/docker-composer.yml" up -d
}

stop(){
  docker-compose -f "${actionServicePath}/docker-composer.yml" down
}

checkDockerContainerIsRunning(){
  # 检查容器状态
  container_status=$(docker inspect -f '{{.State.Status}}' "${1}" 2>/dev/null)
  if [ "$container_status" == "running" ]; then
      return 1
  else
      return 0
  fi
}

checkInServiceList(){
    [ "pull" = "$action" ] && return 1
    [ -z "$actionService" ] && return 0
    for item in "${serviceList[@]}"
    do
        [ "${item}" = "${actionService}" ] && return 1
    done
    return 0
}

run(){
  cd "${actionServicePath}" || exit
  case "$action" in
      deploy)
          deploy
          ;;
      restart)
          restart
          ;;
      stop)
          stop
          ;;
      pull)
          pull
          ;;
      *)
          echo "Usage: $0 {deploy}"
          exit 1
  esac
}


checkInServiceList
if [  $? -eq 0 ];then
    echo "service is not found!"
    exit 1
fi


run