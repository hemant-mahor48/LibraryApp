@Library('shared-library') _
pipeline {
    agent { label 'aws' }
    stages {
        stage('code') {
            steps {
                code(
                    "https://github.com/hemant-mahor48/LibraryApp.git",
                    "master"
                )
            }
        }
        stage('build') {
            steps {
                build(
                    "hemantmahor611",
                    "libraryapp",
                    "latest"
                )
            }
        }
        stage('push to dockerhub') {
            steps {
                docker_push(
                    "dockerHubCred",
                    "hemantmahor611",
                    "libraryapp",
                    "latest"
                )
            }
        }
        stage('deploy') {
            steps {
                echo "This is to deploy the code"
                sh "docker compose up -d"
            }
        }
    }
}
