package com.example.qiosk;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void ScheduledJSON_isCorrect() throws JSONException {
        Model.Scheduled scheduled = new Model.Scheduled(90000002, "S1");
        JSONObject json = scheduled.toJSON();
        Model.Scheduled scheduled2 = new Model.Scheduled(json);
        assertEquals(scheduled.getMobile(), scheduled2.getMobile());
        System.out.printf(scheduled.getTime().toString() + "\n");
        System.out.printf(scheduled2.getTime().toString());

        assertEquals(scheduled.getTime(), scheduled2.getTime());
        assertEquals(scheduled.getBookingId(), scheduled2.getBookingId());
    }

    @Test
    public void AppointmentCompareTo_isCorrect() {
        List<Model.Appointment> apps = new ArrayList<>();
        apps.add(new Model.WalkIn(90000001));
        apps.add(new Model.Scheduled(90000002, "S1"));
        apps.add(new Model.Scheduled(90000003, "S2"));
        apps.add(new Model.WalkIn(90000000));

        Collections.sort(apps);
        assertEquals("S1", apps.get(0).getBookingId());
        assertEquals(90000003, apps.get(1).getMobile());
        assertEquals(90000001, apps.get(2).getMobile());
        assertEquals(90000000, apps.get(3).getMobile());
    }
}