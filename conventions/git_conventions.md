# GIT Conventions
Author: Ray Rafael S. Abenido
## GIT Commands Refresher
In case you forgotten the git commands:

1. `git help` provides a list of the most commonly used `git` commands you will need for this project.
2. `git clone <url>` clones a repository from the internet to your machine. You can find the URL for our project at the HTTPS URL field in the Code dropdown on GitHub.
3. `git pull origin master` means that you are pulling code from the `master` branch of the source repo (the `origin` part). The more general form of this code is `git pull <repo_source> <branch_name>`, where you basically pull the branch you specified from wherever you got the branch from.
4. `git push origin master` uploads your changes to the `master` branch at our main repo. Its more general form is `git push <repo_source> <branch_name>`.
5. `git add <file>` adds a file to the list of files that will be pushed later on to the main repository. If you are going to add all the files, use `git add .`
6. `git status` checks tracks which files are ready to be committed or not.
7. `git commit -m <header> -m <message>` commits the file to the repository with a header and message explaining the changes made. This does not mean your changes are now available for everyone to access. You will need to push them to the main repo too.
8. `git log` displays all the commits performed on a project so far. Useful for checking what changes were made and who made it.

## How to Write Commit Messages
### GIT Command
This will be the command we'll type whenever we are committing code to the central repo.
> `git commit -m "type  of  commit: brief  description" -m "detailed description"`

### Type of Commit
Whenever writing a commit message, make sure that you note first what kind of commit you are making. Here's a list of commits we will expect while working with the repo:
1. `feature`: Use this if committing a new feature you're adding to the project.
2. `fix`: Use this if committing any bug fixes you've made.
3. `test`:  Use this if you tested something in the code. Please share where in the code you performed the test, what kind of test, and the result in the detailed description part.
4. `docs`: Use this if you did anything related to the Java documentation of the code.
5. `clean`: Use this if you cleaned up the database, rearranged files and folders, or generally did some cleaning in the repository
6. `meta`: Use this any changes are made to README.md, git_conventions.md, or if the changes are not related to any changes made in the code but still important to the project.
6. `others`: Use this if what you did that does not fall under the above. If you do end up using this, inform Ray so he can update this file with a new type of commit later.

For example, if your commit is about adding a new feature then you start your message with:
> git commit -m "feature: "

### Message Body of Commit
When you have written down what kind of commit you're making, write a brief header summarizing what you did in less than 50 characters. For example:
> git commit -m "feature: added log-in functionality"

If you've done multiple changes such that a single type of commit listed above won't be enough to explain the changes, use "|" to separate them. For example if you made both `docs` and `feat` changes:
> git commit -m "feature: added log-in functionality | docs: documented log in"

After that, write a more detailed description right afterwards. A detailed description will help the others on the project know the details of what you did.
> git commit -m "feature: feat: added log-in functionality" -m "Created a login.java file in the src folder. It can accept credentials, check if credentials are valid, and can either accept or reject the credentials."