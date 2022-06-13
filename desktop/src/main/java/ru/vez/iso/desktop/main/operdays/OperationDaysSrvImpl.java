package ru.vez.iso.desktop.main.operdays;

import com.google.gson.Gson;
import javafx.collections.ObservableMap;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vez.iso.desktop.main.operdays.dto.OperationDaysResponse;
import ru.vez.iso.desktop.shared.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to load Operation Days
 * */
public class OperationDaysSrvImpl implements OperationDaysSrv {

    private static final Logger logger = LogManager.getLogger();
    private static final String API_OP_DAYS = "/abdd/operating-day/page";

    private final ObservableMap<AppStateType, AppStateData> appState;
    private final HttpClientWrap httpClient;
    private final OperationDayMapper mapper;

    public OperationDaysSrvImpl(
            ObservableMap<AppStateType, AppStateData> appState,
            HttpClientWrap httpClient,
            OperationDayMapper mapper
    ) {
        this.appState = appState;
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    @Override
    public List<OperatingDayFX> loadOperationDays(LocalDate from) {

        // Create multipart POST request
        String api = ((AppStateData<AppSettings>) appState.get(AppStateType.SETTINGS)).getValue().getBackendAPI() + API_OP_DAYS;
        String token = ((AppStateData<UserDetails>) appState.get(AppStateType.USER_DETAILS)).getValue().getToken();
        HttpPost httpPost = new HttpPost(api);
        // TODO jsonRequest should be from RequestBuilder
        String jsonRequest = "{\"page\":1,\"rowsPerPage\":100,\"criterias\":[{\"fields\":[\"operatingDayDate\"],\"operator\":\"GREATER_OR_EQUALS\",\"value\":\"2022-04-01\"}]}";

        try {
            StringEntity entity = new StringEntity(jsonRequest);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", token);
            String json = this.httpClient.postDataRequest(httpPost);
            OperationDaysResponse response = new Gson().fromJson(json, OperationDaysResponse.class);
            return response.getObjects().stream().map(mapper::map).collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error(ex);
            throw new RuntimeException(ex);
        }
    }

}
