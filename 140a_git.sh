#!/bin/bash

printf "Installing e2cprog in `pwd`.. (Press enter)"
read
git clone git@github.com:huangsam/e2cprog.git

printf "Instructions for use:\n"
printf "step 1: git add * after copying your files\n"
printf "step 2: git commit -m {comment}\n"
printf "step 3: git push origin master\n"
printf "\nHave fun with git, and may the rigged man be with you! :)"
