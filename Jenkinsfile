pipeline {
    agent any

    stages {
        stage('Buildando..!') {
	    steps {
      	bat 'start cmd.exe /c C:\\Users\\Administrator\\.jenkins\\workspace\\arquivo.bat'
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
