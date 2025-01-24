pipeline {
    
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub_zth_id')
        APP_NAME = "ziedth/spring-foyer"
    }


    agent any

    stages {
        // Stage 1: Checkout Code
        stage('Checkout Code') {
            steps {
                echo 'Pulling repository...'
                git branch: 'main', url: 'https://github.com/ziedtuihri/DevOpsWorkshop'
            }
        }


        // Stage 2: Clean (Moved to the beginning)
        stage('Clean') {
            steps {
                echo 'Running Maven clean...'
                dir('tp-foyer') {
                    sh 'mvn clean'
                }
            }
        }

        // Stage 3: Validate
        stage('Validate') {
            steps {
                echo 'Running Maven Validate...'
                dir('tp-foyer') {
                    sh 'mvn validate'
                }
            }
        }

        // Stage 4: Compile
        stage('Compile') {
            steps {
                echo 'Running Maven compile...'
                dir('tp-foyer') {
                    sh 'mvn compile'
                }
            }
        }

        // Stage 5: Test
        stage('Test') {
            steps {
                echo 'Running Maven Test...'
                dir('tp-foyer') {
                    sh 'mvn test'
                }
            }
        }

        // Stage 6: Package
        stage('Package') {
            steps {
                echo 'Running Maven package...'
                dir('tp-foyer') {
                    sh 'mvn package'
                }
            }
        }

        // Stage 7: Verify
        stage('Verify') {
            steps {
                echo 'Running Maven Verify...'
                dir('tp-foyer') {
                    sh 'mvn verify'
                }
            }
        }

        // Stage 8: Install
        stage('Install') {
            steps {
                echo 'Running Maven install...'
                dir('tp-foyer') {
                    sh 'mvn install'
                }
            }
        }

        // Stage 9: SonarQube Analysis
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                dir('tp-foyer') {
                    withSonarQubeEnv('SonarQube_server') {
                        sh '''
                            mvn sonar:sonar \
                                -Dsonar.sources=src/main/java \
                                -Dsonar.java.binaries=target/classes
                        '''
                    }
                }
            }
        }

        // Stage 10: Deploy
        stage('Deploy') {
            steps {
                echo 'Running Maven Deploy...'
                dir('tp-foyer') {
                    sh 'mvn deploy'
                }
            }
        }
                
          stage('Docker Build') {
            steps {
                echo 'Docker Build'
                dir('tp-foyer') {
                    sh 'docker build -t $APP_NAME:$BUILD_NUMBER .'
                }
            }
          }  

        stage('login to dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        
         stage('push image') {
            steps {
                sh 'docker push $APP_NAME:$BUILD_NUMBER'
            }
        }     


    }
}
