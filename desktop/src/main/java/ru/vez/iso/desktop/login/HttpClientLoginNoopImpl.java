package ru.vez.iso.desktop.login;

import com.google.gson.JsonObject;
import org.apache.http.client.methods.HttpPost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vez.iso.desktop.shared.HttpClientWrap;
import ru.vez.iso.desktop.shared.UtilsHelper;

/**
 * HttpClient wrapper NOOP implementation
 * Successful server response expected:
 * {
 *     "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLbnJmWnRVeGZYZDRYaklkZ0plTXlyVFJjT2w4cnViS3duNnQ0bkNFVzhZIn0.eyJleHAiOjE2NjcxMjcxMzYsImlhdCI6MTY2NzEyNTMzNiwianRpIjoiZmJjYWJlZmMtY2VkNy00ZDAyLTg3ZDctZjg4ODM0NWU2NmJjIiwiaXNzIjoiaHR0cDovLzE3Mi4yOS43My41OTo4MDgwL3JlYWxtcy9BQkREIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6ImNiYzk3MWY4LTkyMTctNGE3NS04ZmFkLWJiNDFjMWRmNTMxOCIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFiZGQtY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImZjMTA2NDQ2LWM5ZWMtNGIwYi1hYWIxLTZjY2E5MjBiMDA1ZiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtYWJkZCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImZjMTA2NDQ2LWM5ZWMtNGIwYi1hYWIxLTZjY2E5MjBiMDA1ZiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiQXJjaCBHTyAwMSBHTyAwMSIsInByZWZlcnJlZF91c2VybmFtZSI6ImFyY2hfZ28wMUB2b2xnb2dyYWQubG9jYWwiLCJnaXZlbl9uYW1lIjoiQXJjaCBHTyAwMSIsImZhbWlseV9uYW1lIjoiR08gMDEifQ.v3oDehK99p1PwBtuZFzbsWDI8ynQBuycVNGJKfubU26f9H1j7jzJOU7ZcUlUMsSI-sUeHqmolfcdMjx9xJeXj-MfKHRVH6DbUmuZkfnDOw5R0-R2XnGhoOUW7kiRT72mtIS9QTmiKCNadgncYqQqswNTSDr8hAgfXFt79mi1HpgINnZSLJLps-25tPkyFC7MNd-lRArK8GlNmw6u5SyUosPJ5vOqzG-nJKU2WiyRe8wHddjAEVf4SwEjYzVEjSMj4TYDnrzhClAPWSi-Zc7OxvUxA-RQYkGl6RQbSLpxorltKsJquNXVCngxGoEgw10Fc2PWwIPDFuu2RwiKtEyGlg",
 *     "expires_in": 1800,
 *     "refresh_expires_in": 1800,
 *     "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIwYjFmMTVjNy1iMTNlLTQxOTYtOGExMi0xMjRiZjRlMzM0NTgifQ.eyJleHAiOjE2NjcxMjcxMzYsImlhdCI6MTY2NzEyNTMzNiwianRpIjoiOThkYjY1MDYtNWRkOC00MzkzLThkNTktMTgwNGQ1MjgxNGE2IiwiaXNzIjoiaHR0cDovLzE3Mi4yOS43My41OTo4MDgwL3JlYWxtcy9BQkREIiwiYXVkIjoiaHR0cDovLzE3Mi4yOS43My41OTo4MDgwL3JlYWxtcy9BQkREIiwic3ViIjoiY2JjOTcxZjgtOTIxNy00YTc1LThmYWQtYmI0MWMxZGY1MzE4IiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImFiZGQtY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImZjMTA2NDQ2LWM5ZWMtNGIwYi1hYWIxLTZjY2E5MjBiMDA1ZiIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImZjMTA2NDQ2LWM5ZWMtNGIwYi1hYWIxLTZjY2E5MjBiMDA1ZiJ9.U7xq35jU6ol9qeNgwbYNOksrofxOMg-g-JEBNfDWhKg",
 *     "token_type": "Bearer",
 *     "not-before-policy": 0,
 *     "session_state": "fc106446-c9ec-4b0b-aab1-6cca920b005f",
 *     "scope": "profile email"
 * }
 * */
public class HttpClientLoginNoopImpl implements HttpClientWrap {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public JsonObject postDataRequest(HttpPost httpPost) {

        logger.debug(httpPost);

        UtilsHelper.makeDelaySec(1);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("access_token", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLbnJmWnRVeGZYZDRYaklkZ0plTXlyVFJjT2w4cnViS3duNnQ0bkNFVzhZIn0.eyJleHAiOjE2NjcxMjcxMzYsImlhdCI6MTY2NzEyNTMzNiwianRpIjoiZmJjYWJlZmMtY2VkNy00ZDAyLTg3ZDctZjg4ODM0NWU2NmJjIiwiaXNzIjoiaHR0cDovLzE3Mi4yOS43My41OTo4MDgwL3JlYWxtcy9BQkREIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6ImNiYzk3MWY4LTkyMTctNGE3NS04ZmFkLWJiNDFjMWRmNTMxOCIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFiZGQtY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImZjMTA2NDQ2LWM5ZWMtNGIwYi1hYWIxLTZjY2E5MjBiMDA1ZiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtYWJkZCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImZjMTA2NDQ2LWM5ZWMtNGIwYi1hYWIxLTZjY2E5MjBiMDA1ZiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiQXJjaCBHTyAwMSBHTyAwMSIsInByZWZlcnJlZF91c2VybmFtZSI6ImFyY2hfZ28wMUB2b2xnb2dyYWQubG9jYWwiLCJnaXZlbl9uYW1lIjoiQXJjaCBHTyAwMSIsImZhbWlseV9uYW1lIjoiR08gMDEifQ.v3oDehK99p1PwBtuZFzbsWDI8ynQBuycVNGJKfubU26f9H1j7jzJOU7ZcUlUMsSI-sUeHqmolfcdMjx9xJeXj-MfKHRVH6DbUmuZkfnDOw5R0-R2XnGhoOUW7kiRT72mtIS9QTmiKCNadgncYqQqswNTSDr8hAgfXFt79mi1HpgINnZSLJLps-25tPkyFC7MNd-lRArK8GlNmw6u5SyUosPJ5vOqzG-nJKU2WiyRe8wHddjAEVf4SwEjYzVEjSMj4TYDnrzhClAPWSi-Zc7OxvUxA-RQYkGl6RQbSLpxorltKsJquNXVCngxGoEgw10Fc2PWwIPDFuu2RwiKtEyGlg");
        return jsonObject;
    }
}
