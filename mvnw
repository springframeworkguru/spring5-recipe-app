#!/bin/bash

# Function to validate the Java version
validate_java_version() {
  required_version="11"

  if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
    version=$("$JAVA_HOME/bin/java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$version" < "$required_version" ]]; then
      echo "Error: Java version $required_version or later is required."
      exit 1
    fi
  else
    echo "Error: JAVA_HOME is not set or invalid."
    exit 1
  fi
}

# Function to check if Maven Wrapper is configured
check_maven_wrapper() {
  if [ ! -f "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar" ]; then
    echo "Error: Maven Wrapper is not configured for this project."
    echo "Please run './mvnw wrapper:wrapper' to configure Maven Wrapper."
    exit 1
  fi
}

# Function to execute Maven
execute_maven() {
  WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

  exec "$JAVACMD" \
    $MAVEN_OPTS \
    -classpath "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar" \
    "-Dmaven.home=${M2_HOME}" "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" \
    ${WRAPPER_LAUNCHER} $MAVEN_CONFIG "$@"
}

# Main execution

# Validate Java version
validate_java_version

# Load Maven configuration files
if [ -z "$MAVEN_SKIP_RC" ]; then
  if [ -f /etc/mavenrc ]; then
    . /etc/mavenrc
  fi

  if [ -f "$HOME/.mavenrc" ]; then
    . "$HOME/.mavenrc"
  fi
fi

# Set OS-specific variables
cygwin=false
darwin=false
mingw=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  MINGW*) mingw=true ;;
  Darwin*) darwin=true
    # Use /usr/libexec/java_home if available, otherwise fall back to /Library/Java/Home
    # See https://developer.apple.com/library/mac/qa/qa1170/_index.html
    if [ -z "$JAVA_HOME" ]; then
      if [ -x "/usr/libexec/java_home" ]; then
        export JAVA_HOME="$(/usr/libexec/java_home)"
      else
        export JAVA_HOME="/Library/Java/Home"
      fi
    fi
    ;;
esac

# Set JAVA_HOME if not already set
if [ -z "$JAVA_HOME" ]; then
  if [ -r /etc/gentoo-release ]; then
    JAVA_HOME=$(java-config --jre-home)
  fi
fi

# Set M2_HOME if not already set
if [ -z "$M2_HOME" ]; then
  # Resolve links - $0 may be a link to Maven's home
  PRG="$0"

  # Need this for relative symlinks
  while [ -h "$PRG" ] ; do
    ls=$(ls -ld "$PRG")
    link=$(expr "$ls" : '.*-> \(.*\)$')
    if expr "$link" : '/.*' > /dev/null; then
      PRG="$link"
    else
      PRG="$(dirname "$PRG")/$link"
    fi
  done

  saveddir=$(pwd)

  M2_HOME=$(dirname "$PRG")/..

  # Make it fully qualified
  M2_HOME=$(cd "$M2_HOME" && pwd)

  cd "$saveddir"
fi

# Convert paths to UNIX format for Cygwin
if $cygwin; then
  [ -n "$M2_HOME" ] && M2_HOME=$(cygpath --unix "$M2_HOME")
  [ -n "$JAVA_HOME" ] && JAVA_HOME=$(cygpath --unix "$JAVA_HOME")
  [ -n "$CLASSPATH" ] && CLASSPATH=$(cygpath --path --unix "$CLASSPATH")
fi

# Convert paths to UNIX format for Mingw
if $mingw; then
  [ -n "$M2_HOME" ] && M2_HOME="$(cd "$M2_HOME"; pwd)"
  [ -n "$JAVA_HOME" ] && JAVA_HOME="$(cd "$JAVA_HOME"; pwd)"
  # TODO: Handle CLASSPATH for Mingw
fi

# Validate JAVA_HOME
if [ -z "$JAVA_HOME" ]; then
  javaExecutable="$(which javac)"
  if [ -n "$javaExecutable" ] && ! [ "$(expr "$javaExecutable" : '\([^ ]*\)')" = "no" ]; then
    # readlink(1) is not available as standard on Solaris 10
    readLink=$(which readlink)
    if [ ! "$(expr "$readLink" : '\([^ ]*\)')" = "no" ]; then
      if $darwin; then
        javaHome="$(dirname "$javaExecutable")"
        javaExecutable="$(cd "$javaHome" && pwd -P)/javac"
      else
        javaExecutable="$(readlink -f "$javaExecutable")"
      fi
      javaHome="$(dirname "$javaExecutable")"
      javaHome=$(expr "$javaHome" : '\(.*\)/bin')
      JAVA_HOME="$javaHome"
      export JAVA_HOME
    fi
  fi
fi

# Set JAVACMD if not already set
if [ -z "$JAVACMD" ]; then
  if [ -n "$JAVA_HOME" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
    else
      JAVACMD="$JAVA_HOME/bin/java"
    fi
  else
    JAVACMD="$(which java)"
  fi
fi

# Validate JAVACMD
if [ ! -x "$JAVACMD" ]; then
  echo "Error: JAVA_HOME is not defined correctly." >&2
  echo "We cannot execute $JAVACMD" >&2
  exit 1
fi

# Display script information
print_header() {
  echo "---------------------------------------------------------"
  echo " Maven Wrapper Script"
  echo "---------------------------------------------------------"
  echo " Script name: $0"
  echo " User: $(whoami)"
  echo " Date: $(date)"
  echo " Java Home: $JAVA_HOME"
  echo " Maven Home: $M2_HOME"
  echo "---------------------------------------------------------"
  echo ""
}

print_header

# Check if Maven Wrapper is configured
check_maven_wrapper

# Find Maven base directory
find_maven_basedir() {
  if [ -z "$1" ]; then
    echo "Path not specified to find_maven_basedir"
    return 1
  fi

  basedir="$1"
  basedir=$(cd "$basedir" && pwd)

  while [ "$basedir" != "/" ]; do
    if [ -f "$basedir/mvnw" ] || [ -f "$basedir/mvnw.cmd" ] || [ -f "$basedir/.mvn/wrapper/maven-wrapper.jar" ]; then
      echo "$basedir"
      return 0
    fi

    basedir=$(dirname "$basedir")
  done

  echo "Maven base directory not found in '$1' or its ancestors"
  return 1
}

# Find Maven base directory for current working directory
MAVEN_PROJECTBASEDIR=$(find_maven_basedir "$PWD")
if [ $? -ne 0 ]; then
  echo "$MAVEN_PROJECTBASEDIR"
  exit 1
fi

# Execute Maven
execute_maven "$@"
