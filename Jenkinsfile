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
      steps {
        echo 'Realizando Deploy no Nexus'
        powershell 'mvn clean compile package deploy'
      }
    }
    stage('Enviando Email') {
      steps {
        mail(subject: '${status}', body: 'Nome do Job: <b>${env.JOB_NAME}</b> <br>" +       "Build: <b>${env.BUILD_NUMBER}</b> <br>" +       "<a href=${env.BUILD_URL}>Check Console Output</a>', to: 'warleyvods@gmail.com')
      }
    }
  }
}