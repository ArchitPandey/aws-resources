### EC2 Roles

Use case: Let's say we want to run this command from ec2 instace: `aws iam list-users`

Ways to do this:
- do aws configure, enter your access key id and secret access key id. run the cli command to list users. It'a a very bad idea to enter your personal access key and secret access key on ec2 instance as any other user with access to account can access them.
- create a role for ec2 instance using iam. in this role attach policy *IAM Read Only Access*. Go to EC2 instance -> Security -> IAM Role. Attach the IAM role with *IAM Read Only Access* policy. Now go to ec2 instance and run the command to list users. This way EC2 instance has access to list users command and user credentials are not exposed as well.
