def call(String url, String branch){
  echo "Cloning the repo"
  git url: "${url}", branch: "${branch}"
  echo "clone successfull" 
}
