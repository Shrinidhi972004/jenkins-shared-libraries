
def call(String imageName = "cicd-for-retail-app_frontend-app:latest") {

    echo "🔍 Checking if Docker image '${imageName}' exists locally..."
    def imageExists = sh(script: "docker images -q ${imageName}", returnStdout: true).trim()

    if (!imageExists) {
        echo "⚠️ Image not found. Building it using docker-compose..."
        sh "docker-compose build"
    } else {
        echo "✅ Image found locally. Proceeding to push..."
    }

    withCredentials([usernamePassword(credentialsId: "dockerHubCred", passwordVariable: "dockerHubPass", usernameVariable: "dockerHubUser")]) {
        echo "🔑 Logging into Docker Hub..."
        sh "docker login -u ${dockerHubUser} -p ${dockerHubPass}"

        echo "🏷️ Tagging image..."
        sh "docker tag ${imageName} ${dockerHubUser}/${imageName}"

        echo "📤 Pushing image to Docker Hub..."
        sh "docker push ${dockerHubUser}/${imageName}"
    }
    
    echo "✅ Docker image push process completed successfully!"
}
