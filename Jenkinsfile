pipeline {
  agent any
  stages {
    stage('Buildando..!') {
      steps {
        powershell 'mvn clean install -U'
      }
    }
    stage('Test') {
      steps {
        echo 'Testando'
        powershell 'mvn test'
      }
    }
    stage('Sonar') {
      steps {
        echo 'Analisando Qualidade do Codigo'
        powershell 'mvn sonar:sonar'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Realizando Deploy'
        powershell 'mvn clean compile package deploy'
      }
    }
  }
}