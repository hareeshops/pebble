
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
    stage("Declarative:Copy artifacts(war)")
    {
      steps
      {
       sh /home/anil/.jenkins/workspace/pebble_declarative_v_1.0/target/pebble-2.6.7-SNAPSHOT.war /tmp/executables 
      }
    }
    stage("Declarative:Static code analysis")
    {
      steps
      {
       echo 'code executed'
      }
    }
  }
}
