pipeline {
    agent any
    tools {
             maven 'Maven'
        }

    environment {

        GITHUB_REPO_URL = 'https://github.com/samayochita/VeryGratefulPlate.git'
        frontendRepositoryName = "samayochita/verygratefulplate-frontend"
        backendRepositoryName = "samayochita/verygratefulplate-backend"
        tag = "latest"
        frontendImage = ""
        backendImage = ""
    }
    stages {
        stage('Fetch code from GitHub') {
            steps {
                git branch: 'main',
                url: "${GITHUB_REPO_URL}"
            }
        }
        stage('Build backend code using Maven') {
            steps {
                script {
                    sh 'mvn -f demo clean install'
                }
            }
        }
        stage ('Unit Tests using Maven') {
                steps {
                    script {
                        sh 'mvn test'
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

        stage ("Run Ansible Playbook") {
                   steps {
                        script {

                         sh '/Users/samayochita/.local/bin/ansible-playbook -i inventory playbook.yml'

                         }
                   }
        }
    }

}
