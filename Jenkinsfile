pipeline {

    // 不指定执行Jenkinsfile脚本的节点
    agent any

    // 构建步骤
    stages {

        stage('拉取代码'){
            steps {
                echo "拉取 release 分支的代码。"
                git branch: 'release', credentialsId: '39b853579aa24971ad685bdfb3dd918f', url: 'https://github.com/hechaoqi123/Demo.git'
                // echo "拉取 ${BRANCH_NAME} 分支的代码。"
                // checkout([$class: 'GitSCM', branches: [[name: "*/${BRANCH_NAME}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${credentialsId}", url: "${repositoryUrl}"]]])
            }
        }

    }

}