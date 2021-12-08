package specifications;

import helpers.AuthHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static RequestSpecification generateToken(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        String token = AuthHelper.getUserToken();
        //Add header
        requestSpecBuilder.addHeader("Authorization", "Bearer " + token);

        return requestSpecBuilder.build();
    }

    public static RequestSpecification generateInvalidToken(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        String token = AuthHelper.getUserInvalidToken();
        //Add header
        requestSpecBuilder.addHeader("Authorization", "Bearer " + token);

        return requestSpecBuilder.build();
    }
}
