exakt.repo
==========

Exakt's Software Development Team Code Repository
-------------------------------------------------

PLEASE READ!!!

Guidelines
----------
Two things you should never do:

- NEVER force a push
If you find yourself in a situation where your changes can't be pushed upstream, something is wrong. Contact another  developer for help tracking down the problem.
- NEVER rebase a branch that you pushed, or that you pulled from another person
Rebasing published branches can lead to duplicate commits in the shared repository.
In general, the preferred workflow is:

  -create a branch from master, check it out, do your work
  -test and commit your changes
  -optionally push your branch up to the remote repository (origin) OR
  -optionally rebase your branch to master (if your changes are unpublished)
  -checkout master, make sure it's up-to-date with upstream changes
  -merge your branch into master
  -test again (and again)
  -push your local copy of master up to the remote repository master (origin/master)
  -delete your branch (and remotely, too, if you published it)
  
  
For some best practices please read also http://www.lullabot.com/articles/git-best-practices-workflow-guidelines


