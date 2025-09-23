pipeline {
    agent any

    environment {
        NODE_HOME = '/usr/local/node'     // adjust to your Node.js path
        PATH = "$NODE_HOME/bin:$PATH"
        MAVEN_HOME = '/usr/share/maven'   // adjust to your Maven path
        PATH = "$MAVEN_HOME/bin:$PATH"
        TOMCAT_HOME = 'C:/Program Files/Apache Software Foundation/Tomcat 10.1' // adjust for your server
    }

    stages {

        stage('Build Frontend') {
            steps {
                dir('frontend-jenkins') {
                    echo "📦 Installing frontend dependencies..."
                    sh 'npm install'

                    echo "🚀 Building frontend..."
                    sh 'npm run build'
                }
            }
        }

        stage('Deploy Frontend to Tomcat') {
            steps {
                echo "📂 Copying frontend build to Tomcat..."
                // Clear old frontend folder
                bat """if exist "%TOMCAT_HOME%\\webapps\\reactpetapi" rmdir /s /q "%TOMCAT_HOME%\\webapps\\reactpetapi" """
                // Copy new build
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
                // Remove old WAR + exploded folder
                bat """if exist "%TOMCAT_HOME%\\webapps\\sdpbackend" rmdir /s /q "%TOMCAT_HOME%\\webapps\\sdpbackend" """
                bat """if exist "%TOMCAT_HOME%\\webapps\\sdpbackend.war" del "%TOMCAT_HOME%\\webapps\\sdpbackend.war" """

                // Copy new WAR
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
