pipeline {
    agent any

    stages {
        stage('Project compilation') {
            steps {
                sh 'docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make clean'
                sh 'docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make'
                sh 'docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make fclean'
            }
        }

        stage('Project tests') {
            steps {
                sh 'docker run --rm -v "$PWD:/usr/app" epitechcontent/epitest-docker make tests_run'
            }
        }
    }
}
