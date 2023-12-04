## GIT REVIEW
Will clone the directory into a folder with the \<new-name>:
```bash
git clone <link> <new-name>
```
`.git` folder is what makes it a `Git` repository.
### PULL CONFLICT
Can stash...
Can `git commit` and then `git pull`.
This creates a merge conflict on `pull`.
Provides 'accept current change', 'accept incoming change', 'accept both changes', 'compare changes'. You do not have to use this functionality. You could just delete the lines that delimit the `git merge`.
Can also abort the commit with `git merge abort`.
### BRANCHES
Make a copy of current branch with the name \<name>
```bash
git branch -c <name>
```
You periodically want to take a merge from `main`. Do this at least once a day, maybe twice a day. To do this you can use the `merge` command.
In the second branch do `git merge main`. This will pull all the commits from `main` to your current branch.
Now say your work is done. Do one last pull from `main`. Resolve any conflicts. Then merge back to `main`. So switch to `main` from your current branch. Then `git merge <branch to merge>`. There should not be any conflicts. Then delete your extra branch when you're done with it. `git branch -d <branch name>`.
You want `main` to always be deployable. If you have partial changes on `main` you can't deploy it. So probably better to work in feature branches than working in `main`.