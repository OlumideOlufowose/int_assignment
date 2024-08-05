# Reproduction Steps

- Create a new Github repository
- Open Terminal/Command Prompt
- Clone this repository to your local system using git command
  * git clone "https://github.com/OlumideOlufowose/int_task.git"
- Navigate to cloned folder on the Terminal/Command prompt
  * cd int_task
- Initialized the cloned folder as your local repository
  * git init
- Add your new Github repository to your local repository
  * git remote add origin "yourNewGithubRepositoryLink"
- Add all files to index
  * git add -A
- Commit to your local repository
  * git commit -m "Commit to your remote repository"
- Push to your new Github repository
  * git push origin main
- Test is triggered automatically after Push
- View test actions and results in the Actions tab of your new Github repository
