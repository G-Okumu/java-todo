pipeline{
    agent any
    tools {
        gradle "Gradle-7"
    }
    stages{
        stage('clone repo'){
            steps{
                git 'https://github.com/G-Okumu/java-todo.git'
            }
        }
        
        stage('build dependencies'){
            steps{
                sh 'gradle build'
            }
        }
        
        stage('project testing'){
            steps{
                sh 'gradle test'
            }
        }
    }
    
}
