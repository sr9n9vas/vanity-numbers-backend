# Vanity Numbers Generate
This repo contains Lambda for generating vanity numbers and CDK code for creating stack in AWS

**Architecture**
Refer  docs/Architecture.png

**Implemention**

Refer docs/Implementation.MD

**Prerequisites**

* Java 17
* Gradle > 8.9 

**Build Project**

./gradlew clean build

**AWS CDK**
* `cdk synth`       emits the synthesized CloudFormation template
* `cdk deploy`      deploy this stack to your default AWS account/region
 
**Endpoint**

/vanity-numbers

Enjoy!
