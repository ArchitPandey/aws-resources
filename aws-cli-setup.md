## AWS CLI Setup

### 1. Install AWS CLI
 - [install aws cli: aws docs](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)

### 2. Configure AWS CLI
 - [configure aws cli: aws docs](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html)
 - use this command to do the configuration
 - `aws configure`
 - enter aws_access_key_id and aws_secret_access_key
 - enter default region for example us-west-2
 - enter default output format : json

### 3. Create default credentials and config files
 - create `~/.aws/credentials` with 
`[default]
aws_access_key_id=AKIAIOSFODNN7EXAMPLE
aws_secret_access_key=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY`

- create `~/.aws/config` with
`[default]
region=us-west-2
output=json`
