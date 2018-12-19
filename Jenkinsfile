
pipeline
{
  agent any
  stages
  {
    stage("Declarative:Maven packaging")
    {
      steps
      {
       sh 'mvn clean package'
      }
    }
    stage("Declarative:Static code analysis") {
    environment {
        scannerHome = tool name: 'Sonar7.4', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
    }

    steps {
        withSonarQubeEnv('sonarqube') {
            sh "${scannerHome}/bin/sonar-scanner"
        }

        timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
}
  }
}
