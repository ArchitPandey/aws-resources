## Connect to Mysql RDS from EC2 using IAM authentication
Resources: 
- [Using RDS with IAMAuth: amazon docs](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/UsingWithRDS.IAMDBAuth.Connecting.html)
- [rds-iam-authentication: easytutorials](https://www.easytutorials.live/howto/aws-rds-database-iam-authentication-in-spring-boot)

### 1. Enable IAM Authentication in EC2
To to RDS console and enable IAM Authentication in Configuration

### 2. Allow inbound TCP traffic to RDS
In RDS security group, add entry to allow inbound traffic from EC2. For this add a CIDR that includes EC2 private IP. For eg, if EC2 private IP is 10.0.0.82 then inbound rule CIDR can be 10.0.0.0/24

### 3. Allow EC2 instance to connect RDS
Add role to EC2 instance that has following policies attached:
- RDS Full Access
- Following custom policy:
`{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "rds-db:connect",
                "rds-db:*"
            ],
            "Resource": [
                "arn:aws:rds-db:us-west-2:025942732434:dbuser:db-NXDGGP3DM64KLBNOFOCCCXIW3E/iamUser",
            ]
        }
    ]
}`
Note that here iamUser is the db user that we will be using to connect to Mysql RDS db.
Also add same policy to the user (or group user is part of).

### 4. Create Mysql RDS user that can accept IAM tokens for authentication
 - Connect to Mysql RDS using master user and password using following command
  - mysql -h mysql–instance1.123456789012.us-east-1.rds.amazonaws.com -P 3306 -u mymasteruser -p
 - Create user that uses AWS provided 'AWSAuthenticationPlugin' to authenticate user using IAM token. Note that require SSL is mandatory. IAM authentication does not work without SSL
  - CREATE USER iamUser IDENTIFIED WITH AWSAuthenticationPlugin AS 'RDS' REQUIRE SSL;
 - Give privileges to user. Users in Mysql are identified with their user name and ip address, % matches all ip addresses. Following statement grants all privileges on tinyurl schema. Doing GRANT on *.* (all schemas or databases as they are called in Mysql) does not work.
  - GRANT ALL PRIVILEGES ON tinyurl.* TO 'iamUser'@'%'; 
            
### 5. Download SSL certificate. This is a pem file. This is placed in rds-connect folder. Certificates do expire and need to changes after a few years. Check web for latest certificate that your Mysql RDS supports.

### 6. Use following command for generating the IAM authentication token (AWS CLI must be installed and configured for this)
 - `aws rds generate-db-auth-token --hostname <<rds-end-point>> --port 3306 --region us-west-2 --username iamUser`
 - Copy the entire string generate from this command. This is the token.

### 7. Use token to connect to Mysql RDS:
  - `mysql --host=<<rds-end-point>> --port=3306 --ssl-ca=rds-combined-ca-bundle.pem --enable-cleartext-plugin --user=iamUser --password=authToken`
  - Note that `--enable-cleartext-plugin` option is not available and not required (it's enabled by default) if Maria DB client is used to connect to Mysql.

### 8. Since token can be big, steps 6 and 7 can be combined as
 - `RDSHOST="rdsmysql.123456789012.us-west-2.rds.amazonaws.com"`
 - `TOKEN="$(aws rds generate-db-auth-token --hostname $RDSHOST --port 3306 --region us-west-2 --username iamUser )"`
 - `mysql --host=$RDSHOST --port=3306 --ssl-ca=/sample_dir/rds-combined-ca-bundle.pem --enable-cleartext-plugin --user=iamUser --password=$TOKEN`

### 9. Resources for trouble shooting
 - [connect to rds using iam auth](https://aws.amazon.com/blogs/database/use-iam-authentication-to-connect-with-sql-workbenchj-to-amazon-aurora-mysql-or-amazon-rds-for-mysql/)
 - [rds-my-access-denied](https://aws.amazon.com/premiumsupport/knowledge-center/rds-mysql-access-denied/)
