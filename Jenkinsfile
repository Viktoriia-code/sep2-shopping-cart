pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'vikikone/shopping-cart'
        DOCKER_IMAGE_TAG = 'latest_v1'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Viktoriia-code/sep2-shopping-cart.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test & Coverage') {
            steps {
                bat 'mvn test jacoco:report' // Runs tests & generates JaCoCo coverage report
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // Publish JUnit test results
                    jacoco execPattern: '**/target/jacoco.exec', // Reads JaCoCo execution file
                           classPattern: '**/target/classes',
                           sourcePattern: '**/src/main/java',
                           exclusionPattern: '**/test/**'
                }
            }
        }
        stage('Docker Login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID,
                                                     usernameVariable: 'DOCKERHUB_USER',
                                                     passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        bat """
                            echo %DOCKERHUB_PASSWORD% | docker login -u %DOCKERHUB_USER% --password-stdin
                        """
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    bat """
                        docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .
                    """
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    bat """
                        docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                    """
                }
            }
        }
    }
}
