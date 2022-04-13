package ru.vez.iso.desktop.main;

import javafx.collections.ObservableMap;
import lombok.extern.java.Log;
import ru.vez.iso.desktop.model.*;
import ru.vez.iso.desktop.state.AppStateData;
import ru.vez.iso.desktop.state.AppStateType;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log
public class MainSrvImpl implements MainSrv {

    private final ObservableMap<AppStateType, AppStateData> appState;
    private final Executor exec;
    private Future<Void> future = CompletableFuture.allOf();

    public MainSrvImpl(ObservableMap<AppStateType, AppStateData> appState, Executor exec) {
        this.appState = appState;
        this.exec = exec;
    }

    @Override
    public void loadOperatingDaysAsync(int period) {

        // Avoid multiply pressing
        if (!future.isDone()) {
            log.info("Async operation in progress, skipping");
            return;
        }

        log.info("getOperatingDaysAsync. period: " + period);
        CompletableFuture<List<OperatingDayFX>> opsDaysFut = CompletableFuture.supplyAsync(() -> getOpsDaysWithDelay(period), exec);
        CompletableFuture<List<StorageUnitFX>> storeUnitsFut = CompletableFuture.supplyAsync(() -> getStorageUnitsWithDelay(period), exec);

        future = opsDaysFut.thenCombine(
                storeUnitsFut,
                (opsDaysList, storeUnitList) -> {
                    opsDaysList.forEach(day -> {
                        List<StorageUnitFX> units = storeUnitList.stream().filter(u -> u.getOperatingDayId().equals(day.getObjectId())).collect(Collectors.toList());
                        day.setStorageUnits(units);
                    });
                    return opsDaysList;
        }).thenAccept(opsDay -> appState.put(
                AppStateType.OPERATION_DAYS, AppStateData.builder().value(opsDay).build()
        ));
    }

    //region PRIVATE

    private List<OperatingDayFX> getOpsDaysWithDelay(int period) {

        log.info("getOpsDaysWithDelay: " + Thread.currentThread().getName());
        try {
            Thread.sleep(period * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return IntStream.rangeClosed(0, period)
                .mapToObj(i -> {
                    LocalDate date = LocalDate.of(1900+i, i+1, i+1);
                    return new OperatingDayFX(String.valueOf(i), date, TypeSu.CD, OpsDayStatus.READY_TO_RECORDING, date, i%2==0);
                })
                .collect(Collectors.toList());
    }

    private List<StorageUnitFX> getStorageUnitsWithDelay(int period) {

        log.info("getStorageUnitsWithDelay: " + Thread.currentThread().getName());
        try {
            Thread.sleep(period * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return IntStream.rangeClosed(0, period * 2)
                .mapToObj(i -> {
                    LocalDate date = LocalDate.of(1900+i, i+1, i+1);
                    String opsDayId = String.valueOf((int)(i/2));
                    return new StorageUnitFX(
                            String.valueOf(i), opsDayId, "numberSu-" + i,
                            date, i, date, StorageUnitStatus.DRAFT, date
                    );
                })
                .collect(Collectors.toList());
    }

    //endregion
}
