pipeline {
    agent any

    environment {
        // Adjust Maven, Node, and Tomcat paths for your system
        MAVEN_HOME = "C:/apache-maven-3.9.9"
        NODE_HOME  = "C:/Program Files/nodejs"
        PATH = "${MAVEN_HOME}/bin;${NODE_HOME};${env.PATH}"
        TOMCAT_HOME = "C:/Program Files/Apache Software Foundation/Tomcat 10.1"
    }

    stages {
        stage('Build Frontend') {
            steps {
                dir('frontend-jenkins') {
                    echo "📦 Installing frontend dependencies..."
                    bat 'npm install'

                    echo "🚀 Building frontend..."
                    bat 'npm run build'
                }
            }
        }

        stage('Deploy Frontend to Tomcat') {
            steps {
                echo "📂 Copying frontend build to Tomcat..."
                bat """
                if exist "${TOMCAT_HOME}\\webapps\\reactpetapi" rmdir /s /q "${TOMCAT_HOME}\\webapps\\reactpetapi"
                xcopy /E /I /Y "frontend-jenkins\\dist" "${TOMCAT_HOME}\\webapps\\reactpetapi"
                """
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend-jenkins') {
                    echo "📦 Building backend WAR..."
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Deploy Backend to Tomcat') {
            steps {
                echo "📂 Deploying backend WAR to Tomcat..."
                bat """
                if exist "${TOMCAT_HOME}\\webapps\\sdpbackend" rmdir /s /q "${TOMCAT_HOME}\\webapps\\sdpbackend"
                if exist "${TOMCAT_HOME}\\webapps\\sdpbackend.war" del "${TOMCAT_HOME}\\webapps\\sdpbackend.war"
                copy "backend-jenkins\\target\\sdpbackend.war" "${TOMCAT_HOME}\\webapps\\"
                """
            }
        }

        stage('Restart Tomcat') {
            steps {
                echo "🔄 Restarting Tomcat..."
                // Shutdown (ignore errors if already stopped)
                bat "\"${TOMCAT_HOME}\\bin\\shutdown.bat\" || exit 0"
                // Startup
                bat "\"${TOMCAT_HOME}\\bin\\startup.bat\""
            }
        }
    }

    post {
        success {
            echo "✅ Deployment completed successfully!"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
