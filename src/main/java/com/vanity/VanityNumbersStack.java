package com.vanity;

import software.amazon.awscdk.*;
import software.amazon.awscdk.aws_apigatewayv2_integrations.HttpLambdaIntegration;
import software.amazon.awscdk.services.amplify.CfnApp;
import software.amazon.awscdk.services.amplify.CfnBranch;
import software.amazon.awscdk.services.amplify.CfnBranchProps;
import software.amazon.awscdk.services.apigatewayv2.*;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.util.Arrays;
import java.util.List;

public class VanityNumbersStack extends Stack {
    public VanityNumbersStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public VanityNumbersStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Function vanityLambdaFunction = Function.Builder.create(this, "vanity-numbers-lambda")
                .functionName("vanity-numbers-lambda")
                .code(Code.fromAsset(System.getProperty("user.dir") + "/vanity-number-generator/build/distributions/vanity-number-generator-1.0-SNAPSHOT.zip"))
                .handler("com.vanity.VanityNumbersHandler")
                .runtime(Runtime.JAVA_17)
                .memorySize(512)
                .timeout(Duration.seconds(10))
                .build();

        Table vanityTable = Table.Builder.create(this, "VanityNumbers")
                .tableName("VanityNumbers")
                .partitionKey(Attribute.builder()
                        .name("VanityId")
                        .type(AttributeType.STRING).build())
                .build();
        vanityTable.grantFullAccess(vanityLambdaFunction);
        HttpLambdaIntegration vanityLambdaIntegration = new HttpLambdaIntegration("VanityLambdaIntegration", vanityLambdaFunction);
        HttpApi httpApi = HttpApi.Builder.create(this, "VanityNumbersApi")
                .corsPreflight(CorsPreflightOptions.builder()
                        .allowOrigins(Arrays.asList("*"))
                        .allowMethods(Arrays.asList(CorsHttpMethod.ANY))
                        .allowHeaders(Arrays.asList("*"))
                        .build())
                .build();
        httpApi.addRoutes(AddRoutesOptions.builder()
                .path("/vanity-numbers")
                .methods(List.of(HttpMethod.POST))
                .integration(vanityLambdaIntegration)
                .build());
        CfnOutput.Builder.create(this, "URL").value(httpApi.getUrl() + "vanity-numbers").build();

        // key to be replaced
        CfnApp amplifyApp = CfnApp.Builder.create(this, "VanityApp")
                .repository("https://github.com/sr9n9vas/vanity-numbers.git")
                .accessToken("key to be replaced")
                .name("VanityApp")
                .build();
        new CfnBranch(this, "main", CfnBranchProps.builder()
                .appId(amplifyApp.getAttrAppId())
                .branchName("main")
                .build());
        CfnOutput.Builder.create(this, "AmplifyAppUrl")
                .value(amplifyApp.getAttrDefaultDomain())
                .description("The URL of the Amplify App")
                .build();
    }
}
