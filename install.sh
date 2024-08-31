#!/bin/bash

INSTALL_DIR="/usr/local/network-monitoring-tool"
SERVICE_FILE="/etc/systemd/system/network-monitoring.service"
JAR_FILE="network-monitoring-tool-1.0-SNAPSHOT.jar"

sudo apt-get update
sudo apt-get install -y default-jdk tcpdump

sudo mkdir -p $INSTALL_DIR

sudo cp target/$JAR_FILE $INSTALL_DIR

sudo bash -c "cat > $SERVICE_FILE" <<EOL
[Unit]
Description=Network Monitoring Service By Raman Sharma in Java
After=network.target

[Service]
ExecStart=/usr/bin/java -jar $INSTALL_DIR/$JAR_FILE
User=$(whoami)
Restart=always

[Install]
WantedBy=multi-user.target
EOL

sudo systemctl daemon-reload
sudo systemctl enable network-monitoring.service
sudo systemctl start network-monitoring.service

echo "Installation completed. Network monitoring service is running."
