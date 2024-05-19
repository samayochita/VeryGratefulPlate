pipeline {
    agent any
    environment {
        frontendRepositoryName = "your-dockerhub-username/verygratefulplate-frontend"
        backendRepositoryName = "your-dockerhub-username/verygratefulplate-backend"
        tag = "latest"
        frontendImage = ""
        backendImage = ""
    }
    stages {
        stage('Fetch code from GitHub') {
            steps {
                git branch: 'main',
                url: 'https://github.com/yourusername/VeryGratefulPlate'
            }
        }
        stage('Build backend code using Maven') {
            steps {
                script {
                    sh 'mvn -f demo clean install'
                }
            }
        }
        stage('Create backend Docker image') {
            steps {
                script {
                    backendImage = docker.build(backendRepositoryName + ":" + tag, "./demo")
                }
            }
        }
        stage('Push backend image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        backendImage.push()
                    }
                }
            }
        }
        stage('Create frontend Docker image') {
            steps {
                script {
                    frontendImage = docker.build(frontendRepositoryName + ":" + tag, "./frontendUpdated")
                }
            }
        }
        stage('Push frontend image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        frontendImage.push()
                    }
                }
            }
        }
    }

}
