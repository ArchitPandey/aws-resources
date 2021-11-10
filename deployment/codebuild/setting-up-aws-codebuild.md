### Setting up AWS CodeBuild
Setting up Codebuild has three major parts :

- Creating a codebuild project: 
  - Repository used to build the artifact is specified in code build project. This can be a git, bitbucket or AWS Code Commit repository.
  - Environment information, that will be used for running the build.
  - Code build service role. We can create this from build project wizard itself. By default it would give code build access to write to cloud watch logs (CreateLogGrouop, CreateLogStream, PutLogEvents).
  - Adding some environment variables that will be accessible during the build.
 
 - Creating a S3 bucket:
  - Create a S3 bucket. Give it permissions ( for testing code build, gave it public access, but need to try with more restrictive permissions)
  - This S3 bucket will also be specified when creating a code build project. Code build project will upload the generated artifacts to this bucket.

 - Adding extra permissions to Code build service role:
  - Attach policy to code build service role, to allow code build to access S3 bucket. Code build would need to upload objects to S3 bucket (PutObject, PutObjectAcl)

 - Creating a buildspec.yml:
  - Create a buildspec.yml file and put it in root of project. Codebuild would look for this file, for running the build.
  - buildspec.yml file contains different phases and commands to run in each phase. 
  - Install phase would setup the required tools to run the build (like install angular/cli, install project dependencies to create node_modules)
  - build phase would run commands to build the artifact
  - post_build phase would run commands to package the artifact and other necessary files to zip and upload to s3 bucket.


Link used to setup code build project:
[codebuild-angular-app](https://blog.shikisoft.com/bitbucket-aws-codebuild-angular/)

Working Code build project:
todo: will upload to this repo. currently present in aws code commit.

Other helpful aws links:
[https://docs.aws.amazon.com/codebuild/latest/userguide/welcome.html](https://docs.aws.amazon.com/codebuild/latest/userguide/welcome.html)
[https://docs.aws.amazon.com/codebuild/latest/userguide/how-to-run.html#how-to-run-prerequisites](https://docs.aws.amazon.com/codebuild/latest/userguide/how-to-run.html#how-to-run-prerequisites)
[https://docs.aws.amazon.com/codebuild/latest/userguide/planning.html](https://docs.aws.amazon.com/codebuild/latest/userguide/planning.html)
[https://docs.aws.amazon.com/codebuild/latest/userguide/create-project-console.html](https://docs.aws.amazon.com/codebuild/latest/userguide/create-project-console.html)
[Available-runtimes-codebuild](https://docs.aws.amazon.com/codebuild/latest/userguide/available-runtimes.html)
[build-spec-reference](https://docs.aws.amazon.com/codebuild/latest/userguide/build-spec-ref.html)
[code-build-access-related-setup](https://docs.aws.amazon.com/codebuild/latest/userguide/setting-up.html#setting-up-service-role)
