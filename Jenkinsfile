pipeline {
    agent any
    tools {
        maven "MAVEN_LATEST"
    }
    stages {
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        echo 'Building...'
                        sh 'mvn clean package'
                    } else {
                        echo 'Building...'
                        bat 'mvn clean package'
                    }
                }
            }
        }
        stage ('Test') {
            steps {
                script {
                    if (isUnix()) {
                        echo 'Testing...'
                        sh 'mvn test'
                        jacoco(execPattern: '**/*.exec')
                    } else {
                        echo 'Testing...'
                        bat 'mvn test'
                        jacoco(execPattern: '**/*.exec')
                    }
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}