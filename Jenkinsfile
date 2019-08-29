pipeline {
  agent any
  stages {
    stage('Buildando..!') {
      steps {
        powershell 'mvn clean install'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
        sh 'mvn test'
      }
    }
    stage('Sonar') {
      steps {
        echo 'Testing..'
        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:RELEASE:sonar'
      }
    }
    stage('Deploy') {
      steps {
        echo 'clean compile package deploy'
      }
    }
  }
}