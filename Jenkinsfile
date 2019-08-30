pipeline {
  agent any
  stages {
    stage('Construindo') {
      steps {
        powershell 'mvn clean install -U'
      }
    }
    stage('Testando') {
      steps {
        echo 'Testando com Maven'
        powershell 'mvn test'
      }
    }
    stage('Analisando Codigo') {
      steps {
        echo 'Analisando Qualidade do Codigo no Sonarqube'
        powershell 'mvn sonar:sonar'
      }
    }
    stage('Publicando') {
      post {
        success {
          emailNotification 'SUCESSO ao Realizar o Deploy'

        }

        failure {
          emailNotification 'FALHA ao Realizar o Deploy'

        }

      }
      steps {
        echo 'Realizando Deploy no Nexus'
        powershell 'mvn clean compile package deploy'
        mail(to: 'warletvods@gmail.com', body: 'teste', subject: 'teste')
      }
    }
  }
}