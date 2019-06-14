import java.util.Date;
import java.util.Scanner;

public class ChargingTime {

    private String totalTimeCharged;
    private double totalHrCharged;
    private double newTotalHrCharged;


    private Date date;
    private Scanner user_input = new Scanner(System.in);

    private void getCurrentTime(int hr, int mn, String msg) {

        date = new Date();
        String dayTime = "";
        int hour = date.getHours() + hr;
        int min = date.getHours() + mn;

        if (min > 59) {

            hour += min / 60;
            min %= 60;

        }
        delay(5);
        if (hour > 12) {

            if (hour / 24 >= 1) dayTime = " am, ";
            else dayTime = " pm, ";
            hour %= 12;
            delay(5);

            System.out.println(msg + (hour < 10 ? "0" + hour : "" + hour) + ":" + (min < 10 ? "0" + min  :"" + min));
        } else if (hour % 12 == 0) {

            if (hour == 12) dayTime = " pm, ";
            if (hour == 0) dayTime = " am, ";
            hour = 12;
            System.out.println(msg + (hour < 10 ? "0" + hour : "" + hour) + ":" + (min < 10 ? "0" + min : "" + min));
        } else {
                delay(5);
            System.out.println(msg + (hour < 10 ? "0" + hour : "" + hour) + ":" + (min < 10 ? "0" + min : "" + min));

        }

    }

    private void getTotalHrTime(double batterymAh, double chargerAmp) {

        totalTimeCharged = "";
        double time = (batterymAh / 100) / chargerAmp;
        totalHrCharged = time;
        String timeString = time + "";
        for (int a = 0; a < 3; a++) {

            totalTimeCharged += timeString.substring(0, timeString.indexOf("."));
            time = Double.parseDouble(timeString.substring(timeString.indexOf("."))) * 60;
            timeString = time + "";

            if (a == 0) totalTimeCharged += "hr ";
            if (a == 1) totalTimeCharged += "min ";
            if (a == 2) totalTimeCharged += "sec ";
        }
        System.out.println("Total charged time: " + totalTimeCharged);
    }


    private void getChargeTimePercentage(int currPercent, int tarPercent) {

        double totalPercent = ((tarPercent - currPercent) + 16) / 100.0;
        newTotalHrCharged = totalPercent * totalHrCharged;
        String totalHrStr = newTotalHrCharged + "";
        int hour = Integer.parseInt(totalHrStr.substring(0, totalHrStr.indexOf(".")));
        double min = Double.parseDouble(totalHrStr.substring(totalHrStr.indexOf("."))) * 60;
        min = Math.round(min);
        System.out.println("Your phone will be charge in " + hour + " hr " + (int)min + "min.");
        getCurrentTime(hour, (int)min, "Your phone will be " + tarPercent + " % charged on ")  ;

    }

    private void start() {

        System.out.println();
        System.out.println("Welcome to the charging timer program: ");
        delay(800);
        System.out.println();
        System.out.println("This will tell how much time you will consumed in\nCharging your phone using the percentage you are about\nto give us ");
        delay(900);
        System.out.println();
        System.out.println("Enter your phone's battery capacity (mAh): ");
        double batcap = user_input.nextDouble();
        System.out.println("Enter your charger's output Ampere ");
        double chargerAmp = user_input.nextDouble();
        System.out.println("From 0 TO 100%, we have determined and calculated\nthat your battery will be charged approximately\nto the time below. Note that this is just a simple calculation that we work within this program, ");
        delay(900);
        System.out.println();
        getTotalHrTime(batcap, chargerAmp);
        System.out.println();
        System.out.println("Now let's get more detailed, are you worried about when your phone will be charged? ");
        delay(700);
        System.out.println();
        System.out.println("Don't worry! Not today my friend. Through this program\nit is like you are holding precious time\nthis will determine percentage charged per time.");
        delay(700);
        System.out.println();
        while (true) {

            System.out.println("Enter your current battery percentage: ");
            int curper = user_input.nextInt();
            System.out.println("Enter the percentage you want it to be charged: ");
            int tarper = user_input.nextInt();
            System.out.println();
            if (tarper < curper && tarper < 101 && tarper != 0 && curper > -1) {

            } else getChargeTimePercentage(curper, tarper);
            System.out.println("Have another try");
        }
    }

    private void delay(int ms) {

        try {
            Thread.sleep(50 + ms);
        } catch (Exception e) {
        }


    }

    private void print(Object obj) {

        System.out.println(obj);
    }

    private void ask(Object obj) {
        System.out.println(obj);
    }

    private void space() {
        System.out.println();
    }

    public static void main(String[] args) {
        ChargingTime ct = new ChargingTime();
        ct.start();
    }

}
