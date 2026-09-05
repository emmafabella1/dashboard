#!/bin/bash
# build-deploy.sh
# Automates Maven build and Tomcat deployment

PROJECT_DIR=/root/projects/SMLCTP/dashboard
TOMCAT_DIR=/opt/tomcat9
WAR_NAME=ops-dashboard-1.0-SNAPSHOT.war

echo ">>> Cleaning and building project..."
cd $PROJECT_DIR
mvn clean install

if [ -f "$PROJECT_DIR/target/$WAR_NAME" ]; then
    echo ">>> Copying WAR to Tomcat webapps..."
    cp $PROJECT_DIR/target/$WAR_NAME $TOMCAT_DIR/webapps/

    echo ">>> Restarting Tomcat..."
    $TOMCAT_DIR/bin/shutdown.sh
    sleep 10
    $TOMCAT_DIR/bin/startup.sh

    echo ">>> Deployment complete!"
    echo ">>> Tail logs for verification:"
    tail -n 1 $TOMCAT_DIR/logs/catalina.out
else
    echo "!!! Build failed: WAR not found in target/"
fi
