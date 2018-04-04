pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat './gradlew.bat clean testClasses'
                bat './gradlew.bat war'
            }
        }
        stage('cleanDb') {
           steps {
               bat './gradlew.bat :evaluations-rest:flywayClean -P flyway.schemas=evaluations-dev -P flyway.url=jdbc:mysql://localhost:3306/evaluations-dev'
               bat './gradlew.bat :evaluations-rest:flywayMigrate -P flyway.schemas=evaluations-dev -P flyway.url=jdbc:mysql://localhost:3306/evaluations-dev'
           }
        }

        stage('Test') {
            steps {
                bat(script: './gradlew.bat test -Dspring.profiles.active=jenkins', returnStatus:true)
            }
        }

        stage('CodeCoverage') {
            steps {
                script {
                    try {
                        bat './gradlew.bat :evaluations-rest:jacocoTestCoverageVerification'
                    } catch(error) {
                        currentBuild.result='UNSTABLE'
                    }
                }
            }
        }

        stage('ArchiveArtifacts'){
            steps {
                archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: true
            }
        }
    }

    post {
        always {
            junit '**/build/test-results/test/*.xml'
            step( [ $class: 'JacocoPublisher' ] )
        }
    }
}