pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Buildando'
        sh 'mvn clean install -U'
      }
    }
    stage('Test') {
      steps {
        echo 'Testando'
        sh 'mvn test'
      }
    }
    stage('Sonar') {
      steps {
        echo 'Executando Sonar'
        sh 'sonar:sonar'
      }
    }
    stage('Deploy') {
      steps {
        echo 'clean compile package deploy'
      }
    }
  }
}