pipeline {
    agent any
    tools {
             maven 'Maven'
             'org.jenkinsci.plugins.docker.commons.tools.DockerTool' 'Docker'
        }

    environment {
            GITHUB_REPO_URL = 'https://github.com/samayochita/VeryGratefulPlate.git'
            frontendImage = "verygratefulplate-frontend"
            backendImage = "verygratefulplate-backend"
        }

        stages {
            stage('Fetch code from GitHub') {
                steps {
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
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
                    dir('demo') {
                        sh 'docker build -t backend-image .'
                    }
                }
            }

            stage('Create frontend Docker image') {
                steps {
                    dir('frontendUpdated') {
                        sh 'docker build -t frontend-image .'
                    }
                }
            }

            stage('Push images to Docker Hub') {
                steps {
                    script {
                        withCredentials([usernamePassword(credentialsId: 'DockerHubCred', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                            sh "docker login --username $DOCKER_USERNAME --password $DOCKER_PASSWORD"

                            sh 'docker tag backend-image samayochita/verygratefulplate-backend:latest'
                            sh 'docker push samayochita/verygratefulplate-backend:latest'

                            sh 'docker tag frontend-image samayochita/verygratefulplate-frontend:latest'
                            sh 'docker push samayochita/verygratefulplate-frontend:latest'
                        }
                    }
                }
            }
        }
    }