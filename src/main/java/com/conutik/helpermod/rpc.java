package com.conutik.helpermod;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class rpc implements IPCListener {

    private static final long APPLICATION_ID = 909837999062261772L;
    private static final long UPDATE_PERIOD = 4200L;
    private IPCClient client;
    private String detailsLine;
    private String stateLine;
    private OffsetDateTime startTimestamp;

    private Timer updateTimer;
    private boolean connected;

    public void start() {
        try {
            if (isActive()) {
                return;
            }

            startTimestamp = OffsetDateTime.now();
            client = new IPCClient(APPLICATION_ID);
            client.setListener(this);
            try {
                client.connect();
            } catch (Exception e) {
//                logger.warn("Failed to connect to Discord RPC: " + e.getMessage());
            }
        } catch (Throwable ex) {
//            logger.error("DiscordRP has thrown an unexpected error while trying to start...");
            ex.printStackTrace();
        }
    }

    public void stop() {
        if (isActive()) {
            client.close();
            connected = false;
        }
    }

    public boolean isActive() {
        return client != null && connected;
    }

    public void updatePresence() {

        // Early Winter 10th, 12:10am - Village
        String largeImageDescription = "Vibing on UwU Client";
        String smallImageDescription = "Vibing on UwU Client";
        RichPresence presence = new RichPresence.Builder()
                .setDetails(this.detailsLine)
                .setStartTimestamp(startTimestamp)
                .setLargeImage("aikoidk2", largeImageDescription)
                .setSmallImage("aikoidk", smallImageDescription)
                .build();
        client.sendRichPresence(presence);
    }

    public void setDetailsLine(String sa) {
        this.detailsLine = sa;
        if (isActive()) {
            updatePresence();
        }
    }

    @Override
    public void onReady(IPCClient client) {
//        logger.info("Discord RPC started");
        connected = true;
        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updatePresence();
            }
        }, 0, UPDATE_PERIOD);
    }

    @Override
    public void onClose(IPCClient client, JSONObject json) {
//        logger.info("Discord RPC closed");
        this.client = null;
        connected = false;
        cancelTimer();
    }

    @Override
    public void onDisconnect(IPCClient client, Throwable t) {
//        logger.warn("Discord RPC disconnected");
        this.client = null;
        connected = false;
        cancelTimer();
    }

    private void cancelTimer() {
        if(updateTimer != null) {
            updateTimer.cancel();
            updateTimer = null;
        }
    }
}
