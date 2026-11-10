pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Static Analysis') {
            steps {
                bat 'mvn checkstyle:check'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }
}