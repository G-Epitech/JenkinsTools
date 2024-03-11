pipeline {
    agent {
      docker {
        image 'epitechcontent/epitest-docker'
      }
    }

    stages {
        stage('Project compilation') {
            steps {
                sh 'make clean'
                sh 'make'
                sh 'make fclean'
            }
        }

        stage('Project tests') {
            steps {
                sh 'make tests_run'
            }
        }
    }
}
