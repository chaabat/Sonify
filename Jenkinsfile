pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'sonify-app'
        DOCKER_TAG = "${BUILD_NUMBER}"
        SONAR_PROJECT_KEY = 'sonify'
        PROJECT_DIR = '${WORKSPACE}'
        SOURCE_DIR = 'C:/Users/chaab/Desktop/Sonify'
    }

    tools {
        maven 'jenkins-maven'
        jdk 'jenkins-jdk-21'
    }

    stages {
        stage('Copy Project Files') {
            steps {
                script {
                    bat """
                        if exist "${PROJECT_DIR}" rmdir /s /q "${PROJECT_DIR}"
                        xcopy "${SOURCE_DIR}" "${PROJECT_DIR}" /E /I /Y
                    """
                }
            }
        }

        stage('Build') {
            steps {
                dir("${PROJECT_DIR}") {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                dir("${PROJECT_DIR}") {
                    bat 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    jacoco(
                        execPattern: '**/target/jacoco.exec',
                        classPattern: '**/target/classes',
                        sourcePattern: '**/src/main/java'
                    )
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                dir("${PROJECT_DIR}") {
                    withSonarQubeEnv('SonarQube') {
                        bat """
                            mvn sonar:sonar ^
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^
                            -Dsonar.host.url=http://localhost:9000 ^
                            -Dsonar.login=%SONAR_TOKEN%
                        """
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${PROJECT_DIR}") {
                    script {
                        bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                dir("${PROJECT_DIR}") {
                    script {
                        bat """
                            docker-compose down || true
                            docker-compose up -d
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}