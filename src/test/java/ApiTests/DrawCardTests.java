package ApiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class DrawCardTests {
    private static final String BASE_URL = "https://deckofcardsapi.com";
    private static final String NEW_DECK = "/api/deck/new";
    private static final String DRAW_CARD = "/api/deck/{deck_id}/draw?count={count}";
    private static final String DECK_ID = "deck_id";
    private static final String SUCCESS = "success";
    private static final String REMAINING = "remaining";
    private static final int SUCCESS_STATUS_CODE = 200;
    private static String deckId;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        deckId = get(NEW_DECK).andReturn().jsonPath().get(DECK_ID);
    }

    @Test
    public void DrawTwoCards() {
        Response response = given().log().all().get(DRAW_CARD, deckId, 2).andReturn();
        checkDrawCardSuccess(response, 50, deckId);
        Response response1 = given().log().all().get(DRAW_CARD, deckId, 51).andReturn();
        checkDrawCardFail(response1, deckId);
    }

    private static void checkDrawCardSuccess(Response response, int numOfCards, String deckId) {
        Assert.assertEquals(SUCCESS_STATUS_CODE, response.getStatusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(true, jsonPathEvaluator.get(SUCCESS));
        Assert.assertEquals(numOfCards, jsonPathEvaluator.get(REMAINING));
        Assert.assertEquals(deckId, jsonPathEvaluator.get(DECK_ID));
    }

    private static void checkDrawCardFail(Response response, String deckId) {
        Assert.assertEquals(SUCCESS_STATUS_CODE, response.getStatusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(false, jsonPathEvaluator.get(SUCCESS));
        Assert.assertEquals(deckId, jsonPathEvaluator.get(DECK_ID));
    }
}
