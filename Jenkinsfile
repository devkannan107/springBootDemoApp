pipeline{
    agent any
    tools {
        maven 'mvn-3.10.1'
    }
    environment{
        DOCKER_IMAGE='springboot_pipeline_demoapp_jenkins_local'
        CONTAINER_NAME='springboot_pipeline_demoapp_jenkins'
    }
    parameters{
        choice(name: 'ENVIRONMENT', choices:['testing', 'prod'], description: 'Environment name')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip tests?')
        }
    stages {
        stage('git clone'){
            steps{
                git branch: 'main', credentialsId: 'github-pat-pwd', url: 'https://github.com/devkannan107/springBootDemoApp.git'
            }
        }
        stage('list comments'){
            steps{
                sh 'ls'
            }
        }
        stage('mevan test stage'){
            when{
                expression{
                    params.ENVIRONMENT == 'testing' && !params.SKIP_TESTS
                }
            }
            steps{
                sh 'mvn test'
            }
        }
        stage('mevan build stage'){
            steps{
                sh 'mvn clean install -DskipTests=$params.SKIP_TESTS'
            }
        }
        stage('docker image build stage'){
            steps{
                sh 'docker build -t $DOCKER_IMAGE:$BUILD_NUMBER .'
            }
        }
        stage('docker old container remove'){
            steps{
                sh 'docker rm -f $CONTAINER_NAME || true'
            }
        }
        stage('docker new container stage'){
            steps{
                sh 'docker run -d -p 9093:9093 -e SERVER_PORT=9093 --name=$CONTAINER_NAME $DOCKER_IMAGE:$BUILD_NUMBER'
            }
        }
    }
}
