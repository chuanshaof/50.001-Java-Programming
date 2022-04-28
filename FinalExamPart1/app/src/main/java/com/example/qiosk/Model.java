package com.example.qiosk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Model {
    public static abstract class Appointment implements Comparable<Appointment> {
        private int mobile;
        private Date time;

        public Appointment(int mobile) {
            this.mobile = mobile;
            this.time = new Date();
        }

        public Appointment(JSONObject json) throws JSONException {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSZ");
            try {
                this.time = dateFormat.parse(json.getString("time"));
            } catch (JSONException e) {
                this.time = new Date();
            } catch (ParseException e) {
                this.time = new Date();
            }
            try {
                this.mobile = json.getInt("mobile");
            } catch (JSONException e) {
                throw e;
            }
        }

        public int getMobile() {
            return this.mobile;
        }

        public Date getTime() {
            return this.time;
        }

        public JSONObject toJSON() throws JSONException {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSZ");
            JSONObject json = new JSONObject();
            json.put("time", dateFormat.format(this.time));
            json.put("mobile", this.mobile);
            return json;
        }

        abstract public String getBookingId();

        // TODO: Question 1A.c
        public int compareTo(Appointment that) {
            // Case where this is a schedule, and that is walk in
            if (this.getBookingId() != null & that.getBookingId() == null) {
                return -1;
            }

            // Case where this is a walk in, and that is a scheduled
            if (this.getBookingId() == null & that.getBookingId() != null) {
                return 1;
            }

            return this.time.compareTo(that.time);
        }
    }

    public static class WalkIn extends Appointment {
        public WalkIn(int mobile) {
            super(mobile);
        }

        public WalkIn(JSONObject json) throws JSONException {
            super(json);
        }

        @Override
        public JSONObject toJSON() throws JSONException {
            return super.toJSON();
        }

        @Override
        public String getBookingId() { return null; }
    }


    public static class Scheduled extends Appointment {
        private String bookingId;

        // TODO: Question 1A.a
        public Scheduled(int mobile, String bookingId) {
            super(mobile);
            this.bookingId = bookingId;
        }

        @Override
        public String getBookingId() {
            return this.bookingId;
        }

        // TODO: Question 1A.b
        public Scheduled(JSONObject json) throws JSONException {
            super(json);
            this.bookingId = json.getString("bookingId");
        }

        @Override
        // TODO: Question 1A.b
        public JSONObject toJSON() throws JSONException {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSZ");
            JSONObject json = new JSONObject();
            json.put("time", dateFormat.format(this.getTime()));
            json.put("mobile", this.getMobile());
            json.put("bookingId", bookingId);
            return json;
        }
    }

    // The following is meant for Question 1 Part B. There is no modification required here.
    public static class PQueue {
        private List<Appointment> data;
        static String JSON_KEY = "ds";
        public PQueue() {
            this.data = new ArrayList<>();
        }

        public PQueue(JSONObject json) throws JSONException {
            this.data = new ArrayList<>();
            if (json.has(JSON_KEY)) {
                Object obj = json.get(JSON_KEY);
                if (obj instanceof JSONArray) {
                    JSONArray jarr = json.getJSONArray(JSON_KEY);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject o = (JSONObject) jarr.get(i);
                        if (o.has("bookingId")) {
                            this.data.add(new Scheduled(o));
                        } else {
                            this.data.add(new WalkIn(o));
                        }
                    }
                } else { // singleton obj
                    JSONObject o = (JSONObject) obj;
                    if (o.has("bookingId")) {
                        this.data.add(new Scheduled(o));
                    } else {
                        this.data.add(new WalkIn(o));
                    }
                }
            }
        }

        public JSONObject toJSON() throws JSONException {
            JSONObject json = new JSONObject();
            for (Appointment a: data) {
                json.accumulate(JSON_KEY, a.toJSON());
            }
            return json;
        }

        public void add(Appointment a) {
            this.data.add(a);
            Collections.sort(this.data);
        }

        public int size() {
            return this.data.size();
        }

        public Appointment get(int i) {
            return this.data.get(i);
        }

        public Appointment dequeue() {
            if (this.size() == 0) { return null; }
            else {
                Appointment a = data.get(0);
                data.remove(0);
                return a;
            }
        }

        public Appointment peek() {
            if (this.size() == 0) { return null; }
            else {
                Appointment a = data.get(0);
                return a;
            }
        }
    }





}

