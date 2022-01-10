### EC2 Instance Types

- On Demand Instances: 
    - Pay for what you use. 
    - Instances can be spun up or terminated any time. 
    - For Linux and windows instances per second billing after first minute. 
    - For other operating systems per hour billing. 
    - This has the highest cost but no upfront payment or commitment. 
    - Ideal for short and uninterrupted workloads where we can't predict how application will behave.
- Reserved Instances: We can reserve the instance. Reservation period of one year or three year (more discount on three year reservation period). For making payment, we can pay no upfront fee (no extra discount), or partial upfront fee (little bit more discount), or all upfront fee (maximum amount of discount). We also need to select the instance type to reserve (t2.micro, m4.large etc). This is suitable for steady-state usage applications (like databases). Reserved Instances can give upto 75% discount compared to on-demand instances.
    - Convertible Reserved Instances: This gives the option of changing EC2 instance type. Gives upto 54% discount.
    - Scheduled Reserved Instances: Instance is launched within the time window. Needed when instance is required for a fraction of a day / week / month. But still needs commitment of over one to three years.
- Spot Instances: These instances can give upto 90% discount compared to on-demand instances. But we can lose this instance at any time if the max price is less than current spot price (hourly price of spot instance is called spot price, varies based on the availability zone spot instance runs in and type of instance). Spot instances are good for Batch jobs, Data analysis and image processing (not good for critical jobs and databases). Spot instances are also available in hourly increments upto six hours. Understanding more on spot instances here - [aws spot instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-spot-instances.html).
- Dedicated Hosts: This is a physical server with ec2 instance fully dedicated for your use. This type of ec2 instances can be used to meet compliance requirements (which requires the ec2 instance or application does not share host with any other customer's application) and use existing server bound licenses (where licenses are applicable for a machine, not OS). They are allocated for a three year period and are more expensive. Users are billed based on per host (instead of per instance).
- Dedicated Instances: This is for instances running on the hardware that's dedicated to you. The hardware may run other instances in the same AWS account.
