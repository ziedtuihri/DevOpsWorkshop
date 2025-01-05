pipeline {
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
                dir('timesheet-devops') {
                    sh 'mvn clean'
                }
            }
        }

        // Stage 3: Validate
        stage('Validate') {
            steps {
                echo 'Running Maven Validate...'
                dir('timesheet-devops') {
                    sh 'mvn validate'
                }
            }
        }

        // Stage 4: Compile
        stage('Compile') {
            steps {
                echo 'Running Maven compile...'
                dir('timesheet-devops') {
                    sh 'mvn compile'
                }
            }
        }

        // Stage 5: Test
        stage('Test') {
            steps {
                echo 'Running Maven Test...'
                dir('timesheet-devops') {
                    sh 'mvn test'
                }
            }
        }

        // Stage 6: Package
        stage('Package') {
            steps {
                echo 'Running Maven package...'
                dir('timesheet-devops') {
                    sh 'mvn package'
                }
            }
        }

        // Stage 7: Verify
        stage('Verify') {
            steps {
                echo 'Running Maven Verify...'
                dir('timesheet-devops') {
                    sh 'mvn verify'
                }
            }
        }

        // Stage 8: Install
        stage('Install') {
            steps {
                echo 'Running Maven install...'
                dir('timesheet-devops') {
                    sh 'mvn install'
                }
            }
        }

        // Stage 9: SonarQube Analysis
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                dir('timesheet-devops') {
                    withSonarQubeEnv('SonarQue_server') {
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
                dir('timesheet-devops') {
                    sh 'mvn deploy'
                }
            }
        }
    }
}