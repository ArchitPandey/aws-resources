# Commands tested on EC2 Amazon Linux 2 AMI

## For running yum install commands, change to root user using:
sudo su

## To install git
sudo yum install git -y
git --version

## install Java 8
sudo yum install java-1.8.0-devel
sudo /usr/sbin/alternatives --config java
sudo /usr/sbin/alternatives --config javac

## install Apache Maven
sudo wget https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn --version

## install Cloud watch agent
sudo yum install amazon-cloudwatch-agent

## install Mysql command line client
yum install mysql
mysql --version
### connecting to Mysql RDS usins MySql client
mysql -h mysql–instance1.123456789012.us-east-1.rds.amazonaws.com -P 3306 -u mymasteruser -p
### connecting to Mysql RDS using Mysql client and using SSL certificate
mysql -h <aws-account>.rdsinstance.<region>.rds.amazonaws.com --ssl-ca=rds-combined-ca-bundle.pem -u <user> -P 33306 -p
