
action="$1"
actionService="$2"
actionServiceScriptPath="clever-$actionService/script"

serviceGit=git@github.com:iyanghong/clever.git

serviceList=("system" "blog")


updateCode(){
    if [ -d ${serviceGit} ]; then
        git pull "${serviceGit}"
        echo -e "\033[32m 后端服务代码更新成功 \033[0m"
    else
        echo -e "\033[33m 后端服务还不存在, 即将为您拉取 \033[0m"
        cd /app || exit
        git clone "${serviceGit}"
        echo -e "\033[32m 后端服务代码拉取成功 \033[0m"
    fi
    echo -e "\033[32m 即将编译后端代码 \033[0m"
    mvn clean
    mvn install
    echo -e "\033[32m 后端服务代码更新成功 \033[0m"
}

deploy(){
  # 删除未命名的镜像
  docker image prune -f
  # 停止正在运行的容器
  docker-compose down
  docker build -t clever-$actionService .
  # 强制重启正在运行的容器
   docker-compose up -d --force-recreate
}


restart(){
  docker-compose down
  docker-compose up -d
}

stop(){
  docker-compose down
}

checkInServiceList(){
    [ -z "$actionService" ] && return 0
    for item in "${serviceList[@]}"
    do
        [ "${item}" = "${actionService}" ] && return 1
    done
    return 0
}

run(){
  cd "${actionServiceScriptPath}}" || exit
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