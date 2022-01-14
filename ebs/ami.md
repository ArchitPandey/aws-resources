### AMI

- AMI = Amazon Machine Image
- AMI is a customization of EC2 instance software. AMI has information about operation system, software on top of operating system and other configuration data. 
- Using this AMI we can spin up the EC2 instances and boot is faster as all the software is pre-packaged.
- AMIs are region specific (and can be copied to other regions)
- We can make our own AMIs (or use a public AMI or an AMI from AWS marketplace that may have cost associated)

##### AMI process:
- Create an EC2 instance and customize it.
- Stop it and create an AMI from it.
- Launch another instance from same AMI in another region.
