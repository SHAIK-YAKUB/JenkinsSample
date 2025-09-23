pipeline {
    agent any

    environment {
        NODE_HOME = 'C:/Program Files/nodejs'   // adjust if different
        MAVEN_HOME = 'C:/apache-maven-3.9.9'    // adjust if different
        TOMCAT_HOME = 'C:/Program Files/Apache Software Foundation/Tomcat 10.1'

        // merge PATH updates here
        PATH = "${NODE_HOME};${MAVEN_HOME}/bin;${env.PATH}"
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
                bat """if exist "%TOMCAT_HOME%\\webapps\\reactpetapi" rmdir /s /q "%TOMCAT_HOME%\\webapps\\reactpetapi" """
                bat """xcopy /E /I /Y "frontend-jenkins\\dist" "%TOMCAT_HOME%\\webapps\\reactpetapi" """
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
                bat """if exist "%TOMCAT_HOME%\\webapps\\sdpbackend" rmdir /s /q "%TOMCAT_HOME%\\webapps\\sdpbackend" """
                bat """if exist "%TOMCAT_HOME%\\webapps\\sdpbackend.war" del "%TOMCAT_HOME%\\webapps\\sdpbackend.war" """
                bat """copy "backend-jenkins\\target\\sdpbackend.war" "%TOMCAT_HOME%\\webapps\\" """
            }
        }

        stage('Restart Tomcat') {
            steps {
                echo "🔄 Restarting Tomcat..."
                bat """%TOMCAT_HOME%\\bin\\shutdown.bat || exit 0"""
                bat """%TOMCAT_HOME%\\bin\\startup.bat"""
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful!"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
