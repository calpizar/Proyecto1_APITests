import helpers.AuthHelper;
import helpers.DataHelper;
import model.User;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class Users extends BaseTest{

    @Test
    public void Test_User_Registration(){
        User newUser = new User (DataHelper.generateRandomName(),
                "pass",
                DataHelper.generateRandomEmail());
        given()
                .body(newUser)
                .post("/v1/user/register")
                .then()
                .body("message", equalTo("Successfully registered"))
                .statusCode(200);
    }

    @Test
    public void Test_User_Registration_Existing_User(){
        User existingUser = new User ("Luis","pass", "luisramirez@gmail.com");

        given()
                .body(existingUser)
                .post("/v1/user/register")
                .then()
                .body("message", equalTo("User already exists"))
                .statusCode(406);
    }

    @Test
    public void Test_Login(){
        User existingUser = new User ("Luis","pablo", "pablo@test.com");

            given()
                .body(existingUser)
                .post("/v1/user/login")
                .then()
                .body("message", equalTo("User signed in"))
                .statusCode(200);
    }

    @Test
    public void Test_Logout(){

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .when()
                .get("/v1/user/logout")
                .then()
                .body("message", equalTo("Successfully logged out"))
                .statusCode(200);
    }
}
