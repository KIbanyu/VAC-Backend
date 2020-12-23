
#!/bin/sh

# Local paths and variables
SERVICE_NAME=global_farm_test

# Application name
REPO=https://github.com/KIbanyu/VAC-Backend.git

# Source directory
DIR_SRC=src

LOCAL_APP=global-farm-backend-0.0.1-SNAPSHOT.jar

# Compiled Application Name (in target folder in source directory)
COMPILED_APP_JAR=global-farm-backend-0.0.1-SNAPSHOT.jar

# Branch
BRANCH=dev_branch

# Logs
LOGS=logs/mshop.log

SLEEP=10s

# End of local paths and variables

COMMAND_INDEX=0

RED='\033[0;31m'
GREEN='\033[0;92m'
GREEN_UNDERLINED='\033[4;92m'
BLUE='\033[4;34m'
PURPLE='\033[4;35m'
NC='\033[0m' # No Color

# ANSI Colors
echoRed() { echo $'\e[091m'"$1"$'\e[0m'; }
echoGreen() { echo ${GREEN} $1 ${NC}; }
echoGreenUnderline() { echo -e ${GREEN_UNDERLINED} $1 ${NC}; }
echoYellow() { echo $'\e[093m'"$1"$'\e[0m'; }
