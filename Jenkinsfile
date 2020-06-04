#!groovy
pipeline {

    // 不指定执行Jenkinsfile脚本的节点
    agent any

    // 集成工具
    tools {
        maven 'maven'
        jdk 'jdk'
    }
    //环境变量
    environment{
        /** 拉取代码相关变量 **/
        // git仓库地址
        repositoryUrl = "https://github.com/hechaoqi123/Demo.git"
        // 凭证id（git登录账号及密码，已经配置在jenkins中，通过此id进行引用）
        credentialsId = "39b853579aa24971ad685bdfb3dd918f"


        /** maven打包相关变量 **/

        packageCommand = "mvn clean package -Dmaven.test.skip=true -Dmaven.repo.local=/project/release"

        /** 项目部署相关变量 **/
        // 远程服务器(host、user、password已配置在jenkins中，通过此名称进行引用)
        remoteServer = "腾讯云"
        // 远程服务器目录
        remoteDis = "/usr/project"

        // 本地包路径
        sourceFile = "/project/release/validparam.jar"
        // 本地包所在文件夹
        removePreFix = "/project/release"

        // 传输完成后的执行脚本
        runCommand = "java -jar validparam.jar"
    }


    // 构建阶段
    stages {

        stage('拉取代码'){
            steps {
                echo "拉取 release 分支的代码。"
                git branch: 'release', credentialsId: '${credentialsId}', url: '${repositoryUrl}'
            }
        }

        stage('进行打包'){
            steps{
                dir('validparam') {
                    sh '${packageCommand}'
                    echo "代码打包完成"
                }
            }
        }

        stage('开始部署'){
            steps{
                sshPublisher(publishers: [sshPublisherDesc(configName: '${remoteServer}', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '${runCommand}', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '${remoteDir}', remoteDirectorySDF: false, removePrefix: '${removePreFix}', sourceFiles: '${sourceFile}')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                echo "包部署完毕"
            }
        }
    }

    //异常处理
    post {
        unstable {
            echo "构建不稳定"
        }
        unsuccessful {
            echo "构建失败"
        }
        success {
            echo "构建成功"
        }
    }
}