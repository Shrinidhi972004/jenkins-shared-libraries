def call() {
    echo "🚀 Deploying application using Docker Compose..."
    
    try {
        sh 'docker-compose down || true'
        sh 'docker-compose up -d'
        echo "✅ Deployment completed successfully!"
    } catch (Exception e) {
        echo "❌ Deployment failed: ${e.getMessage()}"
        error("Deployment stage failed.")
    }
}
